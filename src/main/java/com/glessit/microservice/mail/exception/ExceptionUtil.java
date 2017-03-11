package com.glessit.microservice.mail.exception;

import javax.servlet.ServletException;
import static java.lang.String.format;

public class ExceptionUtil {

    /* generate exception with message in JSON */
    public static void raise(String message, Object... args) throws ServletException {
        throw new ServletException(format(message, args));
    }
}
