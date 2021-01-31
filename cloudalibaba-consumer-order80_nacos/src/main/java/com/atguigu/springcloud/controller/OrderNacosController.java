package com.atguigu.springcloud.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderNacosController {
    @Value("${service-url.nacos-user-service}")
    private String nacos_user_service;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("consumer/payment/nacos/{id}")
    public String orderId(@PathVariable("id")Integer id){
        return restTemplate.getForObject(nacos_user_service + "/payment/nacos/" + id, String.class);
    }
}
