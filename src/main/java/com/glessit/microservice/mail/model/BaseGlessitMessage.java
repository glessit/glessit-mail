package com.glessit.microservice.mail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseGlessitMessage implements IGlessitMessage {
    private String email;
    private String subject;
    private String body;
}
