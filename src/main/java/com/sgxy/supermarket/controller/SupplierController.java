package com.sgxy.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.sgxy.supermarket.entity.Supplier;
import com.sgxy.supermarket.service.SupplierService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @ResponseBody
    @RequestMapping("supplierList")
    public JSONObject supplierList(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("limit"));
        String supplierName = request.getParameter("supplierName");
        List<Supplier> suppliers = supplierService.query_SupplierByName(page, size, supplierName);
        PageInfo pageInfo = new PageInfo(suppliers);
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", (int) pageInfo.getTotal());
        data.put("data", suppliers);
        return data;
    }

    @RequestMapping("to_addSupplier")
    public String to_addSupplier() {
        return "addSupplier";
    }

    @ResponseBody
    @RequestMapping("addSupplier")
    public JSONObject addSupplier(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        try {
            Supplier supplier = (Supplier) JSONObject.toBean(JSONObject.fromObject(request.getParameter("supplier")), Supplier.class);
            count = supplierService.addSupplier(supplier);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "");
            } else {
                data.put("code", 500);
                data.put("msg", "添加失败");
            }
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("updateSupplier")
    public JSONObject updateSupplier(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
//        There is no getter for property named 'supplier' in 'class com.sgxy.supermarket.entity.Supplier'  --》mapper.xml文件写错，属性名字
        Supplier supplier = (Supplier) JSONObject.toBean(JSONObject.fromObject(request.getParameter("supplier")), Supplier.class);
        System.out.println("=====");
        try {
            count = supplierService.updateSupplier(supplier);
        } catch (DataAccessException e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "");
            } else {
                data.put("code", 500);
                data.put("msg", "更新失败");
            }
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("deleteSupplier")
    public JSONObject deleteSupplier(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        Integer supplierId = Integer.parseInt(request.getParameter("supplierId"));
        try {
            count = supplierService.deleteSupplier(supplierId);
        } catch (DataAccessException e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "");
            } else {
                data.put("code", 500);
                data.put("msg", "删除错误");
            }
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("deleteSupplierBatch")
    public JSONObject deleteSupplierBatch(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        boolean flag = false;
        String supplierIds = request.getParameter("supplierIds");
        try {
            flag = supplierService.deleteSupplierBatch(supplierIds);
        } catch (DataAccessException e) {
            System.out.println(e);
        } finally {
            if (flag) {
                data.put("code", 200);
                data.put("msg", "");
            } else {
                data.put("code", 500);
                data.put("msg", "批量删除错误");
            }
        }
        return data;
    }

}
