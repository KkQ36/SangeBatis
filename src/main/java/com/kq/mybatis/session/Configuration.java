package com.kq.mybatis.session;

import com.kq.mybatis.binding.MapperRegistry;
import com.kq.mybatis.datasource.druid.DruidDataSourceFactory;
import com.kq.mybatis.datasource.pooled.PooledDataSourceFactory;
import com.kq.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.kq.mybatis.executor.Executor;
import com.kq.mybatis.executor.SimpleExecutor;
import com.kq.mybatis.executor.resultset.DefaultResultSetHandler;
import com.kq.mybatis.executor.resultset.ResultSetHandler;
import com.kq.mybatis.executor.statement.PreparedStatementHandler;
import com.kq.mybatis.executor.statement.StatementHandler;
import com.kq.mybatis.mapping.BoundSql;
import com.kq.mybatis.mapping.Environment;
import com.kq.mybatis.mapping.MappedStatement;
import com.kq.mybatis.transaction.Transaction;
import com.kq.mybatis.transaction.jdbc.JdbcTransactionFactory;
import com.kq.mybatis.type.TypeAliasRegistry;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kq
 * 2024-06-04 21:58
 **/
public class Configuration {

    //环境
    @Setter
    @Getter
    protected Environment environment;

    // 映射注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句，存在Map里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    // 类型别名注册机
    @Getter
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);

        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    /**
     * 创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    /**
     * 生产执行器
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }

}