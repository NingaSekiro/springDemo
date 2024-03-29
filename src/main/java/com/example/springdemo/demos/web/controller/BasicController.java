/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springdemo.demos.web.controller;

import com.alibaba.fastjson.JSON;
import com.example.springdemo.demos.web.model.R;
import com.example.springdemo.demos.web.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;


/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
@Slf4j
public class BasicController {
    @Autowired
    private ApplicationContext applicationContext;

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    //     http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    public synchronized R saveUser(SysUser u) throws InterruptedException {
        Thread.sleep(30000);
        return R.success("成功");
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, SysUser user) {
        user.setName("zhangsan");
        user.setAge(18);
    }

    @GetMapping("/beanList")
    public String beanList() {
        return JSON.toJSONString(applicationContext.getBeanDefinitionNames());
    }


}
