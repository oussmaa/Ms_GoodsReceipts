package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Article;
import com.example.ms_goodsreceipts.Entity.Globalestock;
import com.example.ms_goodsreceipts.Repository.ArticleRepository;
import com.example.ms_goodsreceipts.Repository.GlobalestockRepository;
import com.example.ms_goodsreceipts.Request.GlobaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GlobalesStockService {

@Autowired
        private ArticleRepository articleRepository;

    @Autowired
    private GlobalestockRepository globalestockRepository;


    public List<Globalestock> getAllGlobalestocks() {
        return globalestockRepository.findAll();
    }

    public Optional<Globalestock> getGlobalestockById(Long id) {
        return globalestockRepository.findById(id);
    }

    public ResponseEntity<Globalestock>  createGlobalestock(GlobaleRequest globaleRequest) {


        Globalestock globalestock = new Globalestock();
        globalestock.setOpeningQuantity(globaleRequest.getOpeningQuantity());
        globalestock.setQuantityUsed(globaleRequest.getQuantityUsed());

        Optional<Article> articleOptional = articleRepository.findById(globaleRequest.getArticleId());
        if (!articleOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Article article = articleOptional.get();
        globalestock.setArticle(article);
        article.getStocks().add(globalestock);

        globalestockRepository.save(globalestock); // Save Globalestock entity

        return ResponseEntity.ok(globalestock);
    }


    public long  createGlobalestockandGetId(Globalestock globalestock) {
        return globalestockRepository.save(globalestock).getId();
    }

    public Globalestock updateGlobalestock(Long id, Globalestock updatedGlobalestock) {
        Optional<Globalestock> existingGlobalestockOptional = globalestockRepository.findById(id);
        if (existingGlobalestockOptional.isPresent()) {
            Globalestock existingGlobalestock = existingGlobalestockOptional.get();
            existingGlobalestock.setQuantityUsed(updatedGlobalestock.getQuantityUsed());
            existingGlobalestock.setArticle(updatedGlobalestock.getArticle());
            existingGlobalestock.setOpeningQuantity(updatedGlobalestock.getOpeningQuantity());
            return globalestockRepository.save(existingGlobalestock);
        }
        return null;
    }

    public boolean deleteGlobalestock(Long id) {
        Optional<Globalestock> globalestockOptional = globalestockRepository.findById(id);
        if (globalestockOptional.isPresent()) {
            globalestockRepository.delete(globalestockOptional.get());
            return true;
        }
        return false;
    }
}
