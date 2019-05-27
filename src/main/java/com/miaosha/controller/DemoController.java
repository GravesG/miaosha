package com.miaosha.controller;

import com.miaosha.domain.User;
import com.miaosha.redis.RedisService;
import com.miaosha.redis.UserKey;
import com.miaosha.result.CodeMsg;
import com.miaosha.result.Result;
import com.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/hello")
    @ResponseBody
    public Result hello() {
        return Result.sucess("hello,imooc");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "zeTao");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.sucess(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> tx() {
        userService.tx();
        return Result.sucess(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User u =  redisService.get(UserKey.getById,""+1,User.class);
        return Result.sucess(u);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById,""+1,user);
        return Result.sucess(true);
    }
}
