package com.sgxy.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.sgxy.supermarket.entity.Sold;
import com.sgxy.supermarket.mapper.SoldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldService {
    @Autowired
    private SoldMapper soldMapper;

    public List<Sold> querySoldById(Integer page, Integer size, String soldId) {
        PageHelper.startPage(page,size);
        return soldMapper.querySoldById(soldId);
    }

    public Integer addSold(Sold sold) {
        return soldMapper.addSold(sold);
    }

    public Integer updateSold(Sold sold) {
        return soldMapper.updateSold(sold);
    }

    public boolean deleteSoldBatch(String soldIds) {
        String[] dids = soldIds.split(",");
        boolean flag = false;
        int count = 0;
        for (String did : dids) {
            count =soldMapper.deleteSold(Integer.parseInt(did));
        }
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    public Integer deleteSold(Integer soldId) {
        return soldMapper.deleteSold(soldId);
    }

}
