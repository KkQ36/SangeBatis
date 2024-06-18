package com.kq.mybatis.builder;

import com.kq.mybatis.session.Configuration;
import com.kq.mybatis.type.TypeAliasRegistry;
import lombok.Getter;

/**
 * 构建器的基类
 */
public abstract class BaseBuilder {

    @Getter
    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = configuration.getTypeAliasRegistry();
    }

}
