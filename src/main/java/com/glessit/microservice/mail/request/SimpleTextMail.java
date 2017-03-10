package com.glessit.microservice.mail.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleTextMail {
    private String subject;
    private String body;
    private String to;
}
