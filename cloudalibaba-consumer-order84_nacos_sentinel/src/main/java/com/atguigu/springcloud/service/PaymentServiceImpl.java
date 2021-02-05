package com.atguigu.springcloud.service;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {

    @Override
    public CommonResult paymentSql(Long id) {
        return new CommonResult(444, "open-feign整合sentinel实现的全局服务降级策略",null);
    }

}
