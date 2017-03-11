package com.glessit.microservice.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glessit.microservice.mail.config.Account;
import com.glessit.microservice.mail.config.Glessit;
import com.glessit.microservice.mail.config.util.GlessitConfiguration;
import com.glessit.microservice.mail.exception.ExceptionUtil;
import com.glessit.microservice.mail.model.BaseGlessitMessage;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.glessit.microservice.mail.utils.LogUtil.log;
import static java.lang.String.format;
import static com.glessit.microservice.mail.utils.LogUtil.LogItem;

/**
 * Main servlet
 * Handle request and send email
 */
public class GlessitMailServlet extends HttpServlet {

    private final static Logger LOG = LoggerFactory.getLogger(GlessitMailServlet.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    /* simple wrapper for getting props. from XML configuration */
    private GlessitConfiguration configuration;

    private enum KEY {
        MAIL_TYPE,
        ACCOUNT_TYPE
    }

    private final static Map<KEY,String> KEYS = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        /* init. available keys param. */
        KEYS.put(KEY.MAIL_TYPE, "type");
        KEYS.put(KEY.ACCOUNT_TYPE, "account");

        // set GlessitConfiguration based on XML
        configuration = new GlessitConfiguration((Glessit) config.getServletContext().getAttribute("glessit"));

        // set glessit configuration for servlet
//        glessitConfiguration = (Glessit) config.getServletContext().getAttribute("glesssit");



//        if (glessitConfiguration == null) {
//            throw new ServletException("www");
//        }

        log.accept(new LogUtil.LogItem("Servlet is starting ..", Level.DEBUG), LOG);
        log.accept(
                new LogUtil.LogItem(format("Access token is %s", config.getInitParameter("access-token")), Level.DEBUG)
                , LOG);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.accept(new LogItem("Start reading request ..",  Level.DEBUG), LOG);

        String requestAccount = request.getParameter(KEYS.get(KEY.ACCOUNT_TYPE));
        String requestMailType = request.getParameter(KEYS.get(KEY.MAIL_TYPE));

        /* resolve type of email */
        MailType mailType = MailType.valueOf(requestMailType);
        if (mailType == null) {
            ExceptionUtil.raise("Cant resolve MailType param. Was provided [ %s ]", requestMailType);
        }

        /* check account */
        Optional<Account> account = configuration.getAccounts()
                .stream()
                .filter(acc -> acc.getAccountType().toString().equals(requestAccount.toUpperCase()))
                .findFirst();

        if (!account.isPresent()) {
            ExceptionUtil.raise("Cant resolve Account param. Was provided [ %s ]", requestMailType);
        }

        /* get sender component */
        ISender sender = MailSenderFactory.getSender(mailType, account.get());
//        Map<String, String> message = sender.extractParams(request);
        Object senderResult = sender.send(
                new BaseGlessitMessage(
                        "to@yandex.ru\n",
                        "subj.",
                        "some message is here \n newline"
                )
        );

        IOUtils.write(
                objectMapper.writeValueAsString(senderResult),
                response.getWriter()
        );
    }
}
