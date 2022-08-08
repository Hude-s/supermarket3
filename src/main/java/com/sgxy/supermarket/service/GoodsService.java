package com.sgxy.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.sgxy.supermarket.entity.Goods;
import com.sgxy.supermarket.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> query_GoodsByName(Integer page, Integer size, String goodsName) {
        PageHelper.startPage(page, size);
        return goodsMapper.queryGoodsByName(goodsName);
    }

    public Integer addGoods(Goods goods) {
        return goodsMapper.addGoods(goods);
    }

    public Integer updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    public Integer deleteGoods(Integer goodsId) {
        return goodsMapper.deleteGoods(goodsId);
    }

    public boolean deleteGoodsBatch(String goodsIds) {
        String[] ids = goodsIds.split(",");
        boolean flag = false;
        int count = 0;
        for (String id : ids) {
            count = goodsMapper.deleteGoods(Integer.parseInt(id));
        }
        if (count > 0) {
            flag = true;
        }
        return flag;
    }
}
