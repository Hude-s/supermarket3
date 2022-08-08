package com.sgxy.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

  private long empId;
  private String empName;
  private long empGender;
  private long empAge;
  private String empPost;
  private String empPhone;
  private double empSalary;

}
