package com.sgxy.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.sgxy.supermarket.entity.Employee;
import com.sgxy.supermarket.service.EmployeeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //返回查询列表
    @ResponseBody
    @RequestMapping("empList")
    public JSONObject employeeList(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        List<Employee> employees = null;
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("limit"));
        String empName = request.getParameter("empName");
        try {
            employees = employeeService.queryEmployeeByName(page, size, empName);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            PageInfo pageInfo = new PageInfo(employees);
            if (employees != null) {
                data.put("code", 0);
                data.put("msg", "");
                data.put("count", pageInfo.getTotal());
                data.put("data", employees);
            } else {
                data.put("code", 500);
                data.put("msg", "错误");
            }
        }
        return data;
    }

    //跳转到添加页面
    @RequestMapping("to_addEmployee")
    public String to_addEmployee() {
        return "addEmployee";
    }

    //添加员工
    @ResponseBody
    @RequestMapping("addEmployee")
    public JSONObject addEmployee(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        try {
            Employee employee = (Employee) JSONObject.toBean(JSONObject.fromObject(request.getParameter("employee")), Employee.class);//数据转换
            count = employeeService.addEmployee(employee);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "添加成功");
            } else {
                data.put("code", 500);
                data.put("msg", "添加失败");
            }
        }
        return data;
        //对前端传来的异常数据无法响应，执行数据库sql语句后卡死不动的问题
    }

    //删除
    @ResponseBody
    @RequestMapping("deleteEmployee")
    public JSONObject delEmployee(HttpServletRequest request) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        Integer empId = Integer.parseInt(request.getParameter("empId"));
        try {
            count = employeeService.deleteEmployee(empId);
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
    @RequestMapping("updateEmployee")
    public JSONObject updateEmployee(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        try {
            Employee employee = (Employee) JSONObject.toBean(JSONObject.fromObject(request.getParameter("employee")), Employee.class);
            count = employeeService.updateEmployee(employee);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "成功更改数据");
            } else {
                data.put("code", 500);
                data.put("msg", "更新失败");
            }
        }
        return data;
    }

    //批量删除
    @ResponseBody
    @RequestMapping("deleteEmployeeBatch")
    public JSONObject deleteEmployeeBatch(String empIds) {
        JSONObject data = new JSONObject();
        boolean flag = false;
        try {
            flag = employeeService.deleteEmployeeBatch(empIds);
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
