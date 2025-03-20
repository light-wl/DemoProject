package com.light.model;

import lombok.Data;
import java.util.Date;;
import java.io.Serializable;

@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 正文
     */
    private Object content;

    /**
     * 点赞数
     */
    private Integer upvoteNum;

    /**
     * 收藏数
     */
    private Integer collectNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 浏览量
     */
    private Integer viewNum;

    /**
     * 标签ID，逗号分割
     */
    private String label;

    /**
     * 封面
     */
    private String imageName;

    /**
     * 权重 首页文章排序使用
     */
    private Integer priority;

    /**
     * 该文章是否被删除 0否 1是
     */
    private Integer isDelete;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新日期
     */
    private Date updateTime;

}