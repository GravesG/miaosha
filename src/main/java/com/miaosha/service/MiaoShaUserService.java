package com.miaosha.service;

import com.miaosha.dao.MiaoShaUserDao;
import com.miaosha.domain.MiaoShaUser;
import com.miaosha.result.CodeMsg;
import com.miaosha.util.MD5Util;
import com.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoShaUserService {
    @Autowired
    MiaoShaUserDao miaoShaUserDao;

    public MiaoShaUser getById(Long id){
        return miaoShaUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo){
        if(loginVo == null){
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String fromPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoShaUser user = getById(Long.valueOf(mobile));
        if(user == null){
            return CodeMsg.MOBILE_NOT_EXIT;
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String realPass = MD5Util.formPassToDBPass(fromPass,saltDB);
        if(!realPass.equals(dbPass)){
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
