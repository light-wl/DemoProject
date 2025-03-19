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
    private Integer upvote_num;

    /**
     * 收藏数
     */
    private Integer collect_num;

    /**
     * 评论数
     */
    private Integer comment_num;

    /**
     * 浏览量
     */
    private Integer view_num;

    /**
     * 标签ID，逗号分割
     */
    private String label;

    /**
     * 封面
     */
    private String image_name;

    /**
     * 权重 首页文章排序使用
     */
    private Integer priority;

    /**
     * 该文章是否被删除 0否 1是
     */
    private Integer is_delete;

    /**
     * 创建日期
     */
    private Date create_time;

    /**
     * 更新日期
     */
    private Date update_time;

}