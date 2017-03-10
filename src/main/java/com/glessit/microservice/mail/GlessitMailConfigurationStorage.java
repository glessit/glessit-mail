package com.glessit.microservice.mail;

import com.glessit.microservice.mail.config.Glessit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GlessitMailConfigurationStorage {
    private Glessit glessit;
}
