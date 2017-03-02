package com.glessit.microservice.mail;

public enum MailType {
    SIMPLE_MAIL("SIMPLE_MAIL");

    private String value;

    MailType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
