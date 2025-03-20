package com.light.service;

import com.light.model.Article;
import com.light.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public List<Article> getAll() {
        return articleMapper.getAll();
    }

    public Article getById(Long id) {
        return articleMapper.getById(id);
    }

    public Article save(Article article) {
        articleMapper.save(article);
        return article;
    }

    public Article update(Long id, Article article) {
        articleMapper.update(id, article);
        return article;
    }

    public void delete(Long id) {
        articleMapper.delete(id);
    }
}