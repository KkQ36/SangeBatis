package com.kq.mybatis.executor;
import com.kq.mybatis.mapping.BoundSql;
import com.kq.mybatis.mapping.MappedStatement;
import com.kq.mybatis.session.ResultHandler;
import com.kq.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 *  执行器接口
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}