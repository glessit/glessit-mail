package com.glessit.microservice.mail.service;


import com.glessit.microservice.mail.config.Account;
import com.glessit.microservice.mail.model.IGlessitMessage;
import com.glessit.microservice.mail.response.SenderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import javax.mail.*;
import java.util.Properties;

import static com.glessit.microservice.mail.utils.LogUtil.log;
import static java.lang.String.format;
import static com.glessit.microservice.mail.utils.LogUtil.LogItem;


/**
 * Send simple text
 * No images, No HTML support.
 */
public class SimpleMailSender extends BaseMailSender implements ISender {

    private final static Logger LOG = LoggerFactory.getLogger(SimpleMailSender.class);

    // used account
    private Account account;

    public SimpleMailSender(Account account) {
        this.account = account;
    }

    @Override
    public SenderResult send(IGlessitMessage message) {

        String to = message.getEmail();
        String subject = message.getSubject();
        String body = message.getBody();

        String from = account.getFrom();

        /* init. mail settings */

        Properties properties = new Properties();
        account.getProperties().stream().forEach(property -> {
            properties.put(property.getKey(), property.getValue());
        });

        // create session obj.
        Session session = Session.getInstance(
                properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(account.getUsername(), account.getPassword());
                    }
                }
            );

        // prepare mime message
        Message mimeMessage = null;
        try {
            mimeMessage = prepareMimeMessage(
                    session,
                    from,
                    to,
                    subject,
                    body
            );
        } catch (MessagingException e) {
            LOG.error("Can't prepare mime message", e);
            throw new RuntimeException(e.getMessage());
        }

        log.accept(
                new LogItem(
                        String.format("Sending message ..  \n Params: \n to: [%s], from: [%s] ", to, from),
                        Level.INFO
                ),
                LOG
        );

        SenderResult result = new SenderResult();

        try {
            // send message
            Transport.send(mimeMessage);

            log.accept(
                    new LogItem(
                            "Message was send",
                            Level.INFO
                    ),
                    LOG
            );
            result.setResult("OK");

        } catch (MessagingException e) {
            result.setResult(e.getMessage());
            LOG.error("Can't send message", e);
        }

        return result;
    }
}
