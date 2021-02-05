package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    //按照资源名限流,限流 value = "byResource"
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200, "按照资源名限流测试0K", new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException e){
        return new CommonResult(444, e.getClass().getCanonicalName() + "\t 服务不可用");
    }
    //按照资源url限流,限流 /rateLimit/byUrl
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult customerRequest(){
        return new CommonResult(200, "按照资源url限流测试0K", new Payment(2020L,"serial002"));
    }

    //使用全局兜底方法
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2" )  //兜底处理类里面的方法
    public CommonResult customerBlockHandler(){
        return new CommonResult(200, "自定义----success", new Payment(2020L,"serial003"));
    }
}
