package com.example.demo02;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo02Application {

    public static void main(String[] args) {
        JSONObject jsonObject = JSON.parseObject("{'123':'456'}");
        System.out.println(jsonObject.getString("123"));
        SpringApplication.run(Demo02Application.class, args);
    }

}
