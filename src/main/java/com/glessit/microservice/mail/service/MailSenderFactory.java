package com.glessit.microservice.mail.service;

import com.glessit.microservice.mail.MailType;
import com.glessit.microservice.mail.config.Account;
import com.glessit.microservice.mail.exception.ExceptionUtil;
import com.glessit.microservice.mail.exception.GlessitMailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

public class MailSenderFactory {

    private final static Logger LOG = LoggerFactory.getLogger(MailSenderFactory.class);

    /* Get sender by type */
    public final static ISender getSender(MailType type, Account account) throws ServletException {
        switch (type) {
            case SIMPLE_MAIL:
                LOG.info("SimpleMailSender is starting");
                return new SimpleMailSender(account);
            default:
                ExceptionUtil.raise("Sender is not found!");
                return null;
        }
    }

}
