package com.kq.mybatis.session.defaults;

import com.kq.mybatis.executor.Executor;
import com.kq.mybatis.mapping.BoundSql;
import com.kq.mybatis.mapping.Environment;
import com.kq.mybatis.mapping.MappedStatement;
import com.kq.mybatis.session.Configuration;
import com.kq.mybatis.session.SqlSession;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kq
 * 2024-06-04 19:32
 * 默认的 SQLSession
 **/
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return this.selectOne(statement, null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement(statement);
        List<T> list = executor.query(ms, parameter, Executor.NO_RESULT_HANDLER, ms.getBoundSql());
        return list.get(0);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

}
