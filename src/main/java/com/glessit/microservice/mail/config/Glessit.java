package com.glessit.microservice.mail.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name="Glessit")
@XmlAccessorType(XmlAccessType.FIELD)
public class Glessit {

    @XmlElement(name = "Mail")
    private Mail mail;

    @XmlElement(name = "Test")
    private String test;
}
