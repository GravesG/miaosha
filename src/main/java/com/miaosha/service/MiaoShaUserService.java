package com.miaosha.service;

import com.miaosha.dao.MiaoShaUserDao;
import com.miaosha.domain.MiaoShaUser;
import com.miaosha.exception.GlobalException;
import com.miaosha.redis.MiaoshaUserKey;
import com.miaosha.redis.RedisService;
import com.miaosha.result.CodeMsg;
import com.miaosha.util.MD5Util;
import com.miaosha.util.UUIDUtil;
import com.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoShaUserService {
    public static final String COOKIENAME_TOKEN = "token";

    @Autowired
    MiaoShaUserDao miaoShaUserDao;
    @Autowired
    RedisService redisService;

    public MiaoShaUser getById(Long id){
        return miaoShaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String fromPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoShaUser user = getById(Long.valueOf(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIT);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String realPass = MD5Util.formPassToDBPass(fromPass,saltDB);
        if(!realPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        //生成Cookie
        addCookie(response,user);
        return true;
    }
    public void addCookie(HttpServletResponse response,MiaoShaUser user){
        String token = UUIDUtil.uuid();
        redisService.set(MiaoshaUserKey.token,token,user);
        Cookie cookie = new Cookie(COOKIENAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    public MiaoShaUser getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        //延长cookie有效期
        MiaoShaUser user = redisService.get(MiaoshaUserKey.token,token,MiaoShaUser.class);
        if (user != null){
            addCookie(response,user);
        }
        return user;
    }
}
