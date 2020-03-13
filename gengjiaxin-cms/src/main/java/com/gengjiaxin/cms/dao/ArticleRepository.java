package com.gengjiaxin.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.gengjiaxin.cms.domain.Article;

public interface ArticleRepository extends ElasticsearchRepository<Article, Integer>{

	List<Article> findByTitle(String key);
}
