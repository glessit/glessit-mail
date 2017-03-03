package com.glessit.microservice.mail.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LogUtil {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class LogItem {
        private String log;
        private Level level;
    }

    public final static BiConsumer<LogItem, Logger> loggerConsumer = (logItem, logger) -> {
        if (logItem.getLevel().equals(Level.DEBUG)) {
            if (logger.isDebugEnabled()) {
                logger.debug(logItem.getLog());
            }
        }
    };
}
