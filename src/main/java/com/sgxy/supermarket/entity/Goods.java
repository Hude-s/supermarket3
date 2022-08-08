package com.sgxy.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

    private Integer goodsId;
    private String goodsName;
    private String goodsUnit;
    private double goodsPrice;
    private int number;
    private String productionDate;

}
