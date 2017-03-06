package com.glessit.microservice.mail.exception;

import javax.servlet.ServletException;

public class ExceptionUtil {

    /* generate exception with message in JSON */
    public static void raise(String s) throws ServletException {
        throw new ServletException("test");
    }
}
