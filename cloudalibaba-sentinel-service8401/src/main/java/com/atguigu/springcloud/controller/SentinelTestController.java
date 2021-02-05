package com.atguigu.springcloud.controller;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelTestController {
    @GetMapping("/testA")
    public String testA(){
        return "-------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "-------testB";
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey", blockHandler = "deal_testHotkey")
    //这个value是随意的值，并不和请求路径必须一致
    //在填写热点限流的 资源名 这一项时，可以填 /testhotkey 或者是 @SentinelResource的value的值
    public String testHotKey(
            @RequestParam(value="p1", required = false) String p1,
            @RequestParam(value="p2", required = false) String p2){
        return "testHotKey__success";
    }
    public String deal_testHotkey(String p1, String p2, BlockException e){//兜底方法
        return "testHotkey__fail";
    }
}
