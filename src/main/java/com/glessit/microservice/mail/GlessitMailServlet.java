package com.glessit.microservice.mail;

import com.glessit.microservice.mail.utils.LogUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.glessit.microservice.mail.utils.LogUtil.loggerConsumer;

public class GlessitMailServlet extends HttpServlet {

    private final static Logger LOG = LoggerFactory.getLogger(GlessitMailServlet.class);

    private final static String MAIL_TYPE_PARAM = "type";


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        loggerConsumer.accept(new LogUtil.LogItem("Start reading request ..",  Level.DEBUG), LOG);

        switch (MailType.valueOf(request.getParameter(MAIL_TYPE_PARAM))) {
            case SIMPLE_MAIL:
//                debugLogConsumer.accept("Start sending simple mail ..");
                break;
            default:
//                debugLogConsumer.accept("");

        }

        IOUtils.toString(
                request.getReader()
        );
    }
}
