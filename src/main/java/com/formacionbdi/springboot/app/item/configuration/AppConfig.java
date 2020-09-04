package com.formacionbdi.springboot.app.item.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Value("${urlExternal}")
    private String URL;

    @Bean("clienteRest")
    @LoadBalanced
    public RestTemplate registrarRestTemplate(){
        return new RestTemplateBuilder().rootUri(URL).build();
    }

/*    @Bean("clienteRest")
    @LoadBalanced
    public RestTemplate registrarRestTemplate(){
        return new RestTemplate();
    }*/
}
