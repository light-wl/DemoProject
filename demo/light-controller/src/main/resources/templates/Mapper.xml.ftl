<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.light.mapper.${entityName}Mapper">

    <select id="getAll" resultType="com.light.model.${entityName}">
        SELECT
        <#list fields as field>
            ${field.name}<#if field_has_next>,</#if>
        </#list>
        FROM ${tableName}
    </select>

    <select id="getById" resultType="com.example.demo.entity.${entityName}">
        SELECT
        <#list fields as field>
            ${field.name}<#if field_has_next>,</#if>
        </#list>
        FROM ${tableName} WHERE id = #{id}
    </select>

    <insert id="save" parameterType="com.example.demo.entity.${entityName}">
        INSERT INTO ${tableName} (
        <#list fields as field>
            ${field.name}<#if field_has_next>,</#if>
        </#list>
        ) VALUES (
        <#list fields as field>
            #{${field.name}}<#if field_has_next>,</#if>
        </#list>
        )
    </insert>

    <update id="update" parameterType="com.example.demo.entity.${entityName}">
        UPDATE ${tableName}
        <set>
            <#list fields as field>
                <#if field.name != "id">
                    ${field.name} = #{${field.name}}<#if field_has_next>,</#if>
                </#if>
            </#list>
        </set>
        WHERE id = #{id}
    </update>

    <delete id="delete" parameterType="java.lang.Long">
        DELETE FROM ${tableName} WHERE id = #{id}
    </delete>

</mapper>