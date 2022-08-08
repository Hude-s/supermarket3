package com.sgxy.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.sgxy.supermarket.entity.Employee;
import com.sgxy.supermarket.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> queryEmployeeByName(Integer page, Integer size, String empName) {
        PageHelper.startPage(page,size);
        return employeeMapper.queryEmployeeByName(empName);
    }

    public Integer addEmployee(Employee e) {
        return employeeMapper.addEmployee(e);
    }

    public Integer updateEmployee(Employee e) {
        return employeeMapper.updateEmployee(e);
    }

    public boolean deleteEmployeeBatch(String empIds) {
        String[] dids = empIds.split(",");
        boolean flag = false;
        int count = 0;
        for (String did : dids) {
            count = employeeMapper.deleteEmployee(Integer.parseInt(did));
        }
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    public Integer deleteEmployee(Integer empId) {
        return employeeMapper.deleteEmployee(empId);
    }

}
