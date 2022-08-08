package com.sgxy.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sold {
    private String soldId;
    private long goodsId;
    private int soldNumber;
    private String soldDate;
    private long empId;
}
