package com.glessit.microservice.mail.config.util;

import com.glessit.microservice.mail.config.Glessit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class GlessitConfiguration {

    @Getter
    @Setter
    private Glessit configuration;

    public GlessitConfiguration(Glessit glessit) {
        configuration = glessit;
    }

    public List<com.glessit.microservice.mail.config.Account> getAccounts() {
        return configuration.getMail().getAccounts();
    }
}
