package com.atguigu.springcloud.service;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentServiceImpl.class)//兜底类
public interface PaymentService {

    @GetMapping("/paymentSQL/{id}")
    public CommonResult paymentSql(@PathVariable("id")Long id);

}