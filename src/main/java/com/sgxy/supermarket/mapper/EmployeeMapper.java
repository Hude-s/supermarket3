package com.sgxy.supermarket.mapper;

import com.sgxy.supermarket.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {

    Integer addEmployee(Employee e);

    Integer updateEmployee(Employee e);

    Integer deleteEmployee(Integer empId);

    List<Employee> queryEmployeeByName(String empName);
}
