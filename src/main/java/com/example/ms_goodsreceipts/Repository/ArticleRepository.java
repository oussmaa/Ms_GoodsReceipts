package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
