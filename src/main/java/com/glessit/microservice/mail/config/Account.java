package com.glessit.microservice.mail.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

    @XmlAttribute(name = "type", required = true )
    private AvailableAccountType accountType;

    @XmlElement(name = "Username", required = true)
    private String username;

    @XmlElement(name = "Password", required = true)
    private String password;

    @XmlElement(name = "From", required = true)
    private String from;

    @XmlElementWrapper(name="Properties")
    @XmlElement(name="Property")
    private List<Property> properties;
}
