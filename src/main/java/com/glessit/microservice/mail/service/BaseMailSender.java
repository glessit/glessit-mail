package com.glessit.microservice.mail.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BaseMailSender {

    protected Message prepareMimeMessage(Session session, String from, String to, String subject, String body) throws MessagingException {
        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

        // Set Subject: header field
        message.setSubject(subject);

        // Now set the actual message
        message.setText(body);

        return message;

    }
}
