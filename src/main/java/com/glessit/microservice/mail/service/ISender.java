package com.glessit.microservice.mail.service;

import com.glessit.microservice.mail.request.SenderResult;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@FunctionalInterface
public interface ISender {

    default Map<String, String> extractParams(HttpServletRequest request) {
        return new HashMap<>();
    }

    SenderResult send();
}
