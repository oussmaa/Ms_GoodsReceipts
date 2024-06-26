package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Article;
import com.example.ms_goodsreceipts.Entity.Globalestock;
import com.example.ms_goodsreceipts.Repository.ArticleRepository;
import com.example.ms_goodsreceipts.Repository.GlobalestockRepository;
import com.example.ms_goodsreceipts.Request.ArticleRequest;
import com.example.ms_goodsreceipts.Request.GlobaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    public    ArticleRepository articleRepository;

    @Autowired
    public   GlobalesStockService globalesStockService;

    @Autowired
    public GlobalestockRepository globalestockRepository;

  @Autowired
    MouvementService mouvementService;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public ArticleRequest getArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            ArticleRequest articleRequest = new ArticleRequest();
            articleRequest.setId(article.get().getId());
            articleRequest.setCreationDate(article.get().getCreationDate());
            articleRequest.setDescription(article.get().getDescription());
            articleRequest.setPrice(article.get().getPrice());
            articleRequest.setTypeArticle(article.get().getTypeArticle());

            List<Globalestock> globalestock = globalestockRepository.findlistById(articleRequest.getId());
            List<GlobaleRequest> globaleRequests = globalestock.stream().map(st -> {
                GlobaleRequest g = new GlobaleRequest();
                g.setQuantityUsed(st.getQuantityUsed());
                g.setOpeningQuantity(st.getOpeningQuantity()); // Corrected to get OpeningQuantity
                return g;
            }).collect(Collectors.toList());

            articleRequest.setStocks(globaleRequests); // Set the stocks

            return articleRequest; // Return the articleRequest here
        }
        return null; // Return null if article is not present
    }


    public Article createArticle(Article article) {
        try {
       return  articleRepository.save(article);

        }catch (Exception e){
            e.getCause();
        }
      return null;
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        Optional<Article> existingArticleOptional = articleRepository.findById(id);
        if (existingArticleOptional.isPresent()) {
            Article existingArticle = existingArticleOptional.get();
            existingArticle.setArticel(updatedArticle.getArticel());
            existingArticle.setTypeArticle(updatedArticle.getTypeArticle());
            existingArticle.setDescription(updatedArticle.getDescription());
            existingArticle.setPrice(updatedArticle.getPrice());
            return articleRepository.save(existingArticle);
        }
        return null;
    }

    public boolean deleteArticle(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            articleRepository.delete(articleOptional.get());
            return true;
        }
        return false;
    }
}

