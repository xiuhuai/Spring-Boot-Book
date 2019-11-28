package com.example.demo.repository;
import com.example.demo.entity.Article;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ArticleRepository extends JpaRepository<Article,Long> , JpaSpecificationExecutor<Article> {
    Article findById(long id);
    @Modifying
    @Transactional
    @Query("update Article set view = :view where id =:id")
    int updateArticleViewById(@Param("view")long view, @Param("id")long id);
}
