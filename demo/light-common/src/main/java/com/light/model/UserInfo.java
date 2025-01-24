package com.light.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ：light
 * @date ：2025/1/24 10:51:56
 * @description : 用户
 */
@Data
public class UserInfo {
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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
