package com.kq.mybatis.session;

import java.io.FileNotFoundException;

/**
 * @author kq
 * 2024-06-04 19:33
 **/
public interface SqlSessionFactory{

    /**
     * 打开一个 session
     */
    SqlSession openSession() throws FileNotFoundException;
}
