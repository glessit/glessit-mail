package com.glessit.microservice.mail;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Consumer;

public class GlessitMailServlet extends HttpServlet {

    private final static Logger LOG = LoggerFactory.getLogger(GlessitMailServlet.class);

    private final static String MAIL_TYPE_PARAM = "type";

    private final static Consumer<String> debugLogConsumer = line -> {
        if (LOG.isDebugEnabled()) {
            LOG.debug(line);
        }
    };

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        debugLogConsumer.accept("Read request..");

        switch (MailType.valueOf(request.getParameter(MAIL_TYPE_PARAM))) {
            case SIMPLE_MAIL:
                debugLogConsumer.accept("Start sending simple mail");
                break;
            default:
                debugLogConsumer.accept("");

        }

        IOUtils.toString(
                request.getReader()
        );
    }
}
