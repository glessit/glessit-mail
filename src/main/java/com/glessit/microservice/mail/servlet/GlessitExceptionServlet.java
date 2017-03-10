package com.glessit.microservice.mail.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glessit.microservice.mail.GlessitMailServlet;
import com.glessit.microservice.mail.exception.GlessitMailException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlessitExceptionServlet extends HttpServlet {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public final static class GlessitError {
        private int statusCode;
        private String exception;
    }

    private final static Logger LOG = LoggerFactory.getLogger(GlessitMailServlet.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.error("Error!");
        IOUtils.write(
                objectMapper.writeValueAsString(new GlessitError(
                        (Integer) req.getAttribute("javax.servlet.error.status_code"),
                                (String) req.getAttribute("javax.servlet.error.message")
                )),
                resp.getWriter()
        );
    }
}
