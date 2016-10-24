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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;

@Configuration
public class AppConfig {
    @Bean
    @Profile("swagger")
    public Docket connectorApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfo(
                "FSMP TMS Connector",
                "FSMP TMS Connector to provide data from Smart Outage to the aggregators."
                , "1.0", null, "FSMP", null, null))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ge.fsmp.connector.controllers"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class,
                        String.class)
                .genericModelSubstitutes(ResponseEntity.class)

                .useDefaultResponseMessages(false);
    }
}
