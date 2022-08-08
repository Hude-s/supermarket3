package com.sgxy.supermarket.mapper;

import java.util.List;
import com.sgxy.supermarket.entity.Goods;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsMapper {
    List<Goods> queryGoodsByName(String goodsName);

    Integer addGoods(Goods goods);

    Integer updateGoods(Goods goods);

    Integer deleteGoods(Integer goodsId);
}
