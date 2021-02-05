package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
//全局
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(414, "用户自定义的全局降级处理类----1");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(414, "用户自定义的全局降级处理类====2");
    }
}
