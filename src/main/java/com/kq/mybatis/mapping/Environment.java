package com.kq.mybatis.mapping;

import com.kq.mybatis.transaction.TransactionFactory;
import lombok.Builder;
import lombok.Getter;

import javax.sql.DataSource;

/**
 * 环境
 */
@Getter
@Builder
public final class Environment {
    // 环境id
    private final String id;

    // 事务工厂
    private final TransactionFactory transactionFactory;

    // 数据源
    private final DataSource dataSource;

}
