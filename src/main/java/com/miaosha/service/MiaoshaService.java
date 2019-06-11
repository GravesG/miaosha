package com.miaosha.service;

import com.miaosha.domain.Goods;
import com.miaosha.domain.MiaoShaUser;
import com.miaosha.domain.OrderInfo;
import com.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaService {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoShaUser user, GoodsVo goods) {
        //减少库存，下订单，写入秒杀订单
        goodsService.reduceStock(goods);

        //order_info , miaosha_order
        return orderService.createOrder(user,goods);

    }
}
