package com.light.mapper;

import com.light.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> getAll();

    Article getById(Long id);

    void save(Article article);

    void update(Long id, Article article);

    void delete(Long id);
}