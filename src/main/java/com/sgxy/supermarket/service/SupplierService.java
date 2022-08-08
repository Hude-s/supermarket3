package com.sgxy.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.sgxy.supermarket.entity.Supplier;
import com.sgxy.supermarket.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierMapper supplierMapper;

    public List<Supplier> query_SupplierByName(int page, int size, String name) {
        PageHelper.startPage(page, size);
        return supplierMapper.querySupplierByName(name);
    }

    public Integer addSupplier(Supplier supplier) {
        return supplierMapper.addSupplier(supplier);
    }

    public Integer updateSupplier(Supplier supplier) {
        return supplierMapper.updateSupplier(supplier);
    }

    public Integer deleteSupplier(Integer supplierId) {
        return supplierMapper.deleteSupplier(supplierId);
    }

    public boolean deleteSupplierBatch(String supplierIds) {
        String[] ids = supplierIds.split(",");
        boolean flag = false;
        int count = 0;
        for (String id : ids) {
            count = supplierMapper.deleteSupplier(Integer.parseInt(id));
        }
        if (count > 0) {
            flag = true;
        }
        return flag;
    }
}
