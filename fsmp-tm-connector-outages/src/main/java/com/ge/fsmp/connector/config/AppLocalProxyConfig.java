
/*
 * Copyright (c) 2015 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.fsmp.connector.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Created by Gagandeep Singh on 9/20/16.
 */

@Configuration
@Profile("local")
public class AppLocalProxyConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppLocalProxyConfig.class);

    @Value("${ge.http.proxy.set}")
    private Boolean httpProxySet;

    @Value("${ge.http.proxy}")
    private String httpProxy;

    @Value("${ge.http.proxy.port}")
    private String httpProxyPort;

    @Value("${ge.https.proxy.set}")
    private Boolean httpsProxySet;

    @Value("${ge.http.proxy}")
    private String httpsProxy;

    @Value("${ge.http.proxy.port}")
    private String httpsProxyPort;

    @PostConstruct
    public void init() {
        LOGGER.info("AppLocalProxyConfig -Started");

        Properties props = System.getProperties();
        if (Boolean.TRUE.equals(httpsProxySet)) {
            props.setProperty("https.proxySet", String.valueOf(httpsProxySet));
            props.setProperty("https.proxyHost", httpsProxy);
            props.setProperty("https.proxyPort", httpsProxyPort);
        }

        if (Boolean.TRUE.equals(httpProxySet)) {
            props.setProperty("http.proxySet", String.valueOf(httpProxySet));
            props.setProperty("http.proxyHost", httpProxy);
            props.setProperty("http.proxyPort", httpProxyPort);
        }
       
        LOGGER.info(" AppLocalProxyConfig -Completed");

    }
}
