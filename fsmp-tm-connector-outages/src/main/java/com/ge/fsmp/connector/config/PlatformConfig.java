package com.ge.fsmp.connector.config;

import com.ge.fsmp.platform.connector.configs.AbstractPlatformConfig;
import com.ge.fsmp.platform.connector.configs.IPlatformConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jacobschoen on 5/10/16.
 */
public class PlatformConfig {

    @Profile("!platform")
    @Configuration
    private static class LocalConfig implements IPlatformConfig {

        public LocalConfig() {
            // throws an error if you do not define a local constructor for some
            // reason
        }

        @Bean
        @Override
        @Primary
        public RestTemplate platformRestTemplate() {
            return new RestTemplate();
        }
    }

    @Profile("platform")
    @Configuration
    private static class CloudConfig extends AbstractPlatformConfig {

        @Value("${vcap.services.tms-env-service.credentials.FSMP_ISSUER_ID}")
        private String accessTokenURI;

        @Value("${vcap.services.tms-env-service.credentials.FSMP_CLIENT_ID}")
        private String clientId;

        @Value("${vcap.services.tms-env-service.credentials.FSMP_CLIENT_SECRET}")
        private String clientSecret;

        @Value("${vcap.services.tms-env-service.credentials.FSMP_USERNAME}")
        private String username;

        @Value("${vcap.services.tms-env-service.credentials.FSMP_PASSWORD}")
        private String password;

        public CloudConfig() {
            // throws an error if you do not define a local constructor for some reason
        }

        @Override
        public String getAccessTokenURI() {
            return accessTokenURI;
        }

        @Override
        public String getClientId() {
            return clientId;
        }

        @Override
        public String getClientSecret() {
            return clientSecret;
        }

        @Override
        public String getUserName() {
            return username;
        }

        @Override
        public String getPassword() {
            return password;
        }

    }
}
