package com.sgxy.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

  private long supplierId;
  private String supplierName;
  private String address;
  private String phone;
  private String linkName;

}
