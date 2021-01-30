package com.atguigu.springcloud.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //bus动态更新(curl -X POST "http://localhost:3344/actuator/bus-refresh" 来更新)
public class ConfigClientController {
    @Value("${server.port}")
    private String port;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return "server.port:"+port+"\t configInfo:"+configInfo;
    }
}
