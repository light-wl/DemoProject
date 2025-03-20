<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.light.mapper.${entityName}Mapper">

<!-- 返回信息 -->
    <resultMap id="BaseResultMap" type="com.light.model.${entityName}">
        <id property="id" column="id" jdbcType="BIGINT"/>
        <#list fields as field>
        <result property="${field.modelName}" column="${field.columnName}" jdbcType="${field.columnType}" />
        </#list>
    </resultMap>

<!-- 基础查询 -->
    <sql id="BaseSelect">
        SELECT
        <#list fields as field>
            ${field.modelName}<#if field_has_next>,</#if>
        </#list>
        FROM ${tableName}
    </sql>

    <!-- 查询所有记录 -->
    <select id="getAll" resultType="com.light.model.${entityName}">
        <include refid="BaseSelect"/>
    </select>

    <!-- 根据 ID 查询单条记录 -->
    <select id="getById" resultType="com.light.model.${entityName}">
        <include refid="BaseSelect"/>
    </select>

    <!-- 插入记录 -->
    <insert id="save" parameterType="com.light.model.${entityName}">
        INSERT INTO ${tableName} (
        <#list fields as field>
            <#if field.modelName != "id">
                ${field.modelName}<#if field_has_next>,</#if>
            </#if>
        </#list>
        ) VALUES (
        <#list fields as field>
            <#if field.modelName != "id">
             ${field.modelName}<#if field_has_next>,</#if>
             </#if>
        </#list>
        )
    </insert>

    <!-- 更新记录 -->
    <update id="update" parameterType="com.light.model.${entityName}">
        UPDATE ${tableName}
        <set>
            <#list fields as field>
                <#if field.modelName != "id">
                    <if test="${field.modelName} != null">${field.columnName} = ${field.modelName}<#if field_has_next>,</#if></if>
                </#if>
            </#list>
        </set>
        <#noparse>
            WHERE id = #{id}
        </#noparse>
    </update>

    <!-- 删除记录 -->
    <delete id="delete" parameterType="java.lang.Long">
        DELETE FROM ${tableName}
        <#noparse>
            WHERE id = #{id}
        </#noparse>
    </delete>

</mapper>