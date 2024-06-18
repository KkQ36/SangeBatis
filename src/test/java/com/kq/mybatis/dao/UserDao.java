package com.kq.mybatis.dao;

import com.kq.mybatis.entity.User;

/**
 * @author kq
 * 2024-06-04 17:23
 **/
public interface UserDao {

    /**
     * 获取姓名
     */
    User queryUserInfoById(int id);

}
