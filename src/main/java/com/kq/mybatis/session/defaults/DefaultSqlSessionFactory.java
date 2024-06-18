package com.kq.mybatis.session.defaults;

import com.kq.mybatis.executor.Executor;
import com.kq.mybatis.mapping.Environment;
import com.kq.mybatis.mapping.TransactionIsolationLevel;
import com.kq.mybatis.session.Configuration;
import com.kq.mybatis.session.SqlSession;
import com.kq.mybatis.session.SqlSessionFactory;
import com.kq.mybatis.transaction.Transaction;
import com.kq.mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * @author kq
 * 2024-06-04 19:32
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }

}