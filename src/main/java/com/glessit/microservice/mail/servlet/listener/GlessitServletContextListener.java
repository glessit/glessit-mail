package com.glessit.microservice.mail.servlet.listener;

import com.glessit.microservice.mail.GlessitMailServlet;
import com.glessit.microservice.mail.config.Glessit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GlessitServletContextListener implements ServletContextListener {

    private final static Logger LOG = LoggerFactory.getLogger(GlessitServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        if (true) {
            // for stop application initialization
            throw new RuntimeException();
        }

        /* try to build available accounts based on glessit.xml config. */

        // resolve glessit.xml from classpath
        URI glessitConfigurationURL = null;
        try {
            glessitConfigurationURL = this.getClass().getResource("/glessit.xml").toURI();
        } catch (URISyntaxException e) {
            LOG.error("Can't create URI for glessit.xml", e);
        }

        if (glessitConfigurationURL != null) {

            // create JAXB context
            JAXBContext jaxbContext = null;
            try {
                jaxbContext = JAXBContext.newInstance(Glessit.class);
            } catch (JAXBException e) {
                LOG.error("Can't create JAXB context", e);
            }

            if (jaxbContext != null) {

                // create unmarshaller
                Unmarshaller jaxbUnmarshaller = null;
                try {
                    jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                } catch (JAXBException e) {
                    LOG.error("Can't create JAXB unmarshaller", e);
                }

                // create Glessit configuration class from XML
                Glessit glessitConfiguration = null;
                try {
                    glessitConfiguration = (Glessit) jaxbUnmarshaller.unmarshal(new File(glessitConfigurationURL));
                    servletContextEvent.getServletContext().setAttribute("glessit", glessitConfiguration);
                    System.out.println();
                } catch (JAXBException e) {
                    LOG.error("Can't create Glessit configuration class", e);
                }
            }
        }



        System.out.println();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
