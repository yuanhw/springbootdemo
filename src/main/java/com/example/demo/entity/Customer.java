package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Customer
 *
 * @author Wang Yuanhang
 * @date 2020/3/21 1:05 下午
 */
@Data
@Accessors(chain = true)
public class Customer {

    private Long id;

    private String name;

    private Integer age;

    private String birthDay;

    private String type;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
