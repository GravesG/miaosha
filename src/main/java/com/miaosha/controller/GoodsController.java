package com.miaosha.controller;

import com.miaosha.domain.MiaoShaUser;
import com.miaosha.service.MiaoShaUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    MiaoShaUserService miaoShaUserService;

    @RequestMapping("/to_list")
    public String toLogin(Model model,MiaoShaUser user){
        model.addAttribute("user",user);
        return "goods_list";
    }
}
