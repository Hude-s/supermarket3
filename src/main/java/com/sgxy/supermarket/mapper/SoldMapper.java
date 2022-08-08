package com.sgxy.supermarket.mapper;

import com.sgxy.supermarket.entity.Sold;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldMapper {
    Integer addSold(Sold sold);

    Integer updateSold(Sold sold);

     Integer deleteSold(Integer soldId);

    List<Sold> querySoldById(String soldId);
}
