package com.ge.fsmp.connector.config;

import com.ge.fsmp.core.models.platform.BUConnectorDetails;
import com.ge.fsmp.core.models.platform.GrantType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jacobschoen on 5/10/16.
 */
@Configuration
public class RegistrationConfig {
    //wonder if we should put this in out environment variables instead, as then it is configured in one place
    //for all out connectors
    private static final String BU_KEY = "tm";

    //Really want the keys to come from the interfaces somehow, but not sure the best
    //way to do that
    private static final String OUTAGES_MODULE = "outages";

    @Value("${vcap.services.tms-env-service.credentials.zuul}/${spring.application.name}")
    private String url;

    @Value("${vcap.services.tms-env-service.credentials.PLATFORM_URL}")
    private String baseAdminUrl;

    @Value("${vcap.services.tms-env-service.credentials.GLOBAL_UAA}")
    private String issuerId;

    @Value("${vcap.services.tms-env-service.credentials.SHARED_FSMP_CLIENT_ID}")
    private String clientId;

    @Value("${vcap.services.tms-env-service.credentials.SHARED_FSMP_CLIENT_SECRET}")
    private String clientSecret;

    @Bean
    public String baseUrl() {
        return this.baseAdminUrl;
    }

    @Bean
    public BUConnectorDetails fsoutages() {
        return new BUConnectorDetails(BU_KEY, OUTAGES_MODULE, issuerId, clientId, clientSecret, url, GrantType.CLIENT_CREDENTIALS);
    }
}
