package com.sgxy.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.sgxy.supermarket.entity.Sold;
import com.sgxy.supermarket.service.SoldService;
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
public class SoldController {
    @Autowired
    private SoldService soldService;

    //返回查询列表
    @ResponseBody
    @RequestMapping("soldList")
    public JSONObject soldList(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        List<Sold> solds = null;
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("limit"));
        String soldId = request.getParameter("soldId");
        try {
            solds = soldService.querySoldById(page, size, soldId);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            PageInfo pageInfo = new PageInfo(solds);
            if (solds != null) {
                data.put("code", 0);
                data.put("msg", "");
                data.put("count", pageInfo.getTotal());
                data.put("data", solds);
            } else {
                data.put("code", 500);
                data.put("msg", "错误");
            }
        }
        return data;
    }

    //跳转到添加页面
    @RequestMapping("to_addSold")
    public String to_addSold() {
        return "addSold";
    }

    //添加
    @ResponseBody
    @RequestMapping("addSold")
    public JSONObject addSold(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        try {
            Sold sold = (Sold) JSONObject.toBean(JSONObject.fromObject(request.getParameter("sold")),Sold.class);//数据转换
            count = soldService.addSold(sold);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "添加成功");
            } else {
                data.put("code", 500);
                data.put("msg", "用户添加失败");
            }
        }
        return data;
        //对前端传来的异常数据无法响应，执行数据库sql语句后卡死不动的问题
    }

    //删除
    @ResponseBody
    @RequestMapping("deleteSold")
    public JSONObject delEmployee(HttpServletRequest request) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        Integer soldId = Integer.parseInt(request.getParameter("soldId"));
        try {
            count = soldService.deleteSold(soldId);
        } catch (DataAccessException e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
            } else {
                data.put("code", 500);
                data.put("msg", "删除失败");
            }
        }
        return data;
    }


    @ResponseBody
    @RequestMapping("updateSold")
    public JSONObject updateSold(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        try {
            Sold sold = (Sold) JSONObject.toBean(JSONObject.fromObject(request.getParameter("sold")), Sold.class);
            count = soldService.updateSold(sold);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "成功更改数据");
            } else {
                data.put("code", 500);
                data.put("msg", "数据更改失败");
            }
        }
        return data;
    }

    //批量删除
    @ResponseBody
    @RequestMapping("deleteSoldBatch")
    public JSONObject deleteSoldBatch(String soldIds) {
        JSONObject data = new JSONObject();
        boolean flag = false;
        try {
            flag = soldService.deleteSoldBatch(soldIds);
        } catch (DataAccessException e) {
            System.out.println(e);
        } finally {
            if (flag) {
                data.put("code", 200);
                data.put("msg", "删除成功");
            } else {
                data.put("code", 500);
                data.put("msg", "删除失败");
            }
        }
        return data;
    }
}