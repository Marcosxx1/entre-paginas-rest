package com.entrepaginas.demo.security;


 import com.entrepaginas.demo.security.config.HttpSecurityConfigurer;
 import jakarta.annotation.Priority;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static com.entrepaginas.demo.security.Path.LOGIN_PATH;

@Configuration // Indica que esta classe é uma classe de configuração do Spring
@EnableWebSecurity // Habilita a segurança web no projeto
@EnableMethodSecurity // Habilita a segurança baseada em anotações para métodos (como @PreAuthorize)
@RequiredArgsConstructor // Injeta automaticamente os argumentos obrigatórios (final) pelo construtor
public class SecurityConfig {

    private static final String[] ALLOWED_POST_PATHS = {LOGIN_PATH}; // Caminho de login para requisições POST permitidas sem autenticação
    private static final String[] ALLOWED_REQUEST_MATCHERS = {"/actuator/"}; // Caminhos para monitoramento e saúde permitidos sem autenticação

    private final HandlerExceptionResolver handlerExceptionResolver; // Para manipular exceções de autenticação

    @Bean
    @Priority(Ordered.HIGHEST_PRECEDENCE) // Define alta prioridade para garantir que esta configuração seja aplicada primeiro
    public HttpSecurityConfigurer httpSecurityConfigurer() {
        return httpSecurity -> httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        // Permite que qualquer um acesse os caminhos especificados (como o login)
                        .requestMatchers(ALLOWED_POST_PATHS).permitAll()
                        .requestMatchers(ALLOWED_REQUEST_MATCHERS).permitAll()
                        .anyRequest().authenticated()) // Exige autenticação para qualquer outra requisição
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults())
                                .authenticationEntryPoint(authenticationEntryPoint())) // Configura a aplicação para usar JWT como OAuth2
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Define política de sessão sem estado (para APIs REST)
                .csrf(AbstractHttpConfigurer::disable) // Desabilita CSRF para permitir requisições de APIs REST
                .cors(Customizer.withDefaults()); // Habilita CORS com configurações padrão
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        // Define um ponto de entrada personalizado para tratar erros de autenticação,
        // resolvendo exceções usando o handlerExceptionResolver
        return (request, response, authException) ->
                handlerExceptionResolver.resolveException(request, response, null, authException);
    }

}