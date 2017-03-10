package com.glessit.microservice.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glessit.microservice.mail.config.Glessit;
import com.glessit.microservice.mail.exception.ExceptionUtil;
import com.glessit.microservice.mail.exception.GlessitMailException;
import com.glessit.microservice.mail.service.IGlessitMailBuilder;
import com.glessit.microservice.mail.service.ISender;
import com.glessit.microservice.mail.service.MailSenderFactory;
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
import java.util.Optional;
import java.util.function.Supplier;

import static com.glessit.microservice.mail.MailType.SIMPLE_MAIL;
import static com.glessit.microservice.mail.utils.LogUtil.loggerConsumer;
import static java.lang.String.format;
import static com.glessit.microservice.mail.utils.LogUtil.LogItem;

/**
 * Main servlet
 * Handle request and send email
 */
public class GlessitMailServlet extends HttpServlet {

    private final static Logger LOG = LoggerFactory.getLogger(GlessitMailServlet.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    private static Glessit glessitConfiguration;

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        // set glessit configuration for servlet
        glessitConfiguration = (Glessit) config.getServletContext().getAttribute("glesssit");

        if (glessitConfiguration == null) {
            throw new ServletException("www");
        }

        loggerConsumer.accept(new LogUtil.LogItem("Servlet is starting ..", Level.DEBUG), LOG);
        loggerConsumer.accept(
                new LogUtil.LogItem(format("Access token is %s", config.getInitParameter("access-token")), Level.DEBUG)
                , LOG);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        loggerConsumer.accept(new LogItem("Start reading request ..",  Level.DEBUG), LOG);

        /* resolve type of email */
        MailType mailType = MailType.valueOf(request.getParameter("mail_type"));
        if (mailType == null) {
            ExceptionUtil.raise("Parametr mail_type is null!");
        }

        /* get sender component */
        ISender sender = MailSenderFactory.getSender(mailType);
        Object senderResult = sender.send();

        IOUtils.write(
                objectMapper.writeValueAsString(senderResult),
                response.getWriter()
        );
    }
}
