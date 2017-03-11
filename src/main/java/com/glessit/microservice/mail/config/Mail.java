package com.glessit.microservice.mail.config;

import com.glessit.microservice.mail.config.Account;
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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Mail {

    @XmlElementWrapper(name="Accounts")
    @XmlElement(name="Account")
    private List<Account> accounts;
}
