package com.light.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ：light
 * @date ：2025/1/24 10:58:24
 * @description : 用户信息返回
 */
@Data
public class UserInfoDTO {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 扩展字段：JSON格式，key为字段名称；value为值
     */
    private String extendField;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
