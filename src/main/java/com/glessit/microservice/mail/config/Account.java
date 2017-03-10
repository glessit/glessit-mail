package com.glessit.microservice.mail.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

    @XmlAttribute(name = "type", required = true )
    private AvailableAccountType accountType;

    @XmlElement(name = "username", required = true)
    private String username;

    @XmlElement(name = "password", required = true)
    private String password;
}
