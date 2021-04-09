package com.devtanush.microservices.devtanushpropertyaccessservice.controller;

import com.devtanush.microservices.devtanushpropertyaccessservice.beans.PropertyAccessBean;
import com.devtanush.microservices.devtanushpropertyaccessservice.beans.PropertyAccessValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/access")
public class PropertyFileAccessController {

    @Autowired
    PropertyAccessBean propertyAccessBean;

    @GetMapping("accessPropertyFile")
    public PropertyAccessValue accessPropertyFile() {
        refreshActuator();
        return new PropertyAccessValue(propertyAccessBean.getName(), propertyAccessBean.getDescription());
    }

    public void refreshActuator() {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8100/actuator/refresh";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity entity = new HttpEntity(httpHeaders);

        ResponseEntity<String> results = restTemplate.postForEntity(baseUrl, entity, String.class);
    }
}
