package com.entrepaginas.rest.utils;

import static com.entrepaginas.rest.constant.ErrorMessages.ERROR_USER_NOT_FOUND_WITH_CPF_DETAIL;
import static com.entrepaginas.rest.constant.ErrorMessages.ERROR_USER_NOT_FOUND_WITH_CPF_TITLE;

import com.entrepaginas.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class BuildExceptionMessages {

    private static MessageSourceAccessor staticMessageSourceAccessor;

    @Autowired
    private BuildExceptionMessages(MessageSourceAccessor messageSourceAccessor) {}

    public static NotFoundException notFoundUserException(String document) {
        String title = staticMessageSourceAccessor.getMessage(ERROR_USER_NOT_FOUND_WITH_CPF_TITLE);
        String detail =
                staticMessageSourceAccessor.getMessage(ERROR_USER_NOT_FOUND_WITH_CPF_DETAIL, new Object[] {document});
        return new NotFoundException(title, detail);
    }
}
