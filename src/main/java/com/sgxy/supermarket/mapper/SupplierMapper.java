package com.sgxy.supermarket.mapper;

import com.sgxy.supermarket.entity.Supplier;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierMapper {
    List<Supplier> querySupplierByName(String name);

    Integer addSupplier(Supplier supplier);

    Integer updateSupplier(Supplier supplier);

    Integer deleteSupplier(Integer supplierId);
}
