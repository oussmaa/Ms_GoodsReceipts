package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT at from Article at where at.Articel =:articel")
    Optional<Article> findByArticel(String articel);
}
