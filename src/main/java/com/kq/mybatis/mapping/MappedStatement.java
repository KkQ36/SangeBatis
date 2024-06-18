package com.kq.mybatis.mapping;

import com.kq.mybatis.session.Configuration;
import lombok.Builder;
import lombok.Data;

/**
 * 映射 XML 文件中的具体 SQL 语句
 *      <insert id="addUser" parameterType="User">
 *         INSERT INTO `user`
 *         (`name`,`email`,`age`,`sex`,`schoolName`)
 *         VALUES
 *         (#{name},#{email},#{age},#{sex},#{schoolName})
 *     </insert>
 */
@Data
@Builder
public class MappedStatement {

    /**
     * 配置类
     */
    private Configuration configuration;

    /**
     * 语句 id
     */
    private String id;

    /**
     * 语句类型
     */
    private SqlCommandType sqlCommandType;

    /**
     * 关联的 SQL
     */
    private BoundSql boundSql;
}
