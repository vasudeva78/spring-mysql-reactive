package com.aj.spring.mysql.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("customers")
public class Customer {
  @Id
  @Column("cust_id")
  private Integer custId;

  @Column("name")
  private String name;

  @Column("address")
  private String address;

  @Column("country")
  private String country;
}
