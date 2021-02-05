package com.atguigu.springcloud.controller;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
//用ribbon服务调用
@RestController
public class RibbonController {
    @Value("${service-url.nacos-user-service}")
    private String service_url;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("consumer/fallback/{id}")
//    @SentinelResource(value = "fallback", fallback = "handleFallback") //fallback只处理业务异常
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") //blockHandler负责sentinel控制台配置违规
    @SentinelResource(value = "fallback", fallback = "handleFallback",blockHandler = "blockHandler")
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult<Payment> result = restTemplate.getForObject(service_url + "/paymentSQL/" + id, CommonResult.class, id);
        if(id == 4){
            throw new IllegalArgumentException("非法参数异常...");
        }else if(result.getData()==null){
            throw new NullPointerException("空指针异常");
        }
        return result;
    }
    //====fallback
    public CommonResult handleFallback(@PathVariable Long id, Throwable e){
        Payment payment=new Payment(id,null);
        return new CommonResult<Payment>(444, "兜底方法，异常为"+ e.getMessage(),payment);
    }
    //====blockHandler
    public CommonResult blockHandler(@PathVariable Long id, BlockException e){
        Payment payment=new Payment(id,null);
        return new CommonResult<Payment>(445, "blockHandler-sentinel限流"+ e.getMessage(),payment);
    }
}
