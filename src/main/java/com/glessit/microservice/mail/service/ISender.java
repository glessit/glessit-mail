package com.glessit.microservice.mail.service;

import com.glessit.microservice.mail.model.IGlessitMessage;
import com.glessit.microservice.mail.response.SenderResult;

@FunctionalInterface
public interface ISender {

    SenderResult send(IGlessitMessage message);
}
