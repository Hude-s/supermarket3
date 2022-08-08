package com.sgxy.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.sgxy.supermarket.entity.Goods;
import com.sgxy.supermarket.service.GoodsService;
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
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @ResponseBody
    @RequestMapping("goodsList")
    public JSONObject goodsList(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("limit"));
        String goodsName = request.getParameter("goodsName");
        List<Goods> goods = goodsService.query_GoodsByName(page, size, goodsName);
        System.out.println(goods);
        PageInfo pageInfo = new PageInfo(goods);
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", (int) pageInfo.getTotal());
        data.put("data", goods);
        return data;
    }

    @RequestMapping("to_addGoods")
    public String to_addGoods() {
        return "addGoods";
    }

    @ResponseBody
    @RequestMapping("addGoods")
    public JSONObject addGoods(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        try {
            Goods goods = (Goods) JSONObject.toBean(JSONObject.fromObject(request.getParameter("goods")), Goods.class);
            count = goodsService.addGoods(goods);
        } catch (DataAccessException e) {
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
    @RequestMapping("updateGoods")
    public JSONObject updateGoods(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        try {
            Goods goods = (Goods) JSONObject.toBean(JSONObject.fromObject(request.getParameter("goods")), Goods.class);
            count = goodsService.updateGoods(goods);
        } catch (Exception e) {
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
    @RequestMapping("deleteGoods")
    public JSONObject deleteGoods(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));
        Integer count = 0;
        try {
            count = goodsService.deleteGoods(goodsId);
        } catch (DataAccessException e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "");
            } else {
                data.put("code", 500);
                data.put("msg", "删除失败");
            }
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("deleteGoodsBatch")
    public JSONObject deleteGoodsBatch(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        String goodsIds = request.getParameter("goodsIds");
        boolean flag = false;
        try {
            flag = goodsService.deleteGoodsBatch(goodsIds);
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
