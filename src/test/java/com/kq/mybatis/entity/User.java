package com.kq.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kq
 * 2024-06-04 22:02
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;
}
