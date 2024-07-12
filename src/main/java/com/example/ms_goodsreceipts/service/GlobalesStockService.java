package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Article;
import com.example.ms_goodsreceipts.Entity.Globalestock;
import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Entity.Mouvement;
import com.example.ms_goodsreceipts.Repository.ArticleRepository;
import com.example.ms_goodsreceipts.Repository.GlobalestockRepository;
import com.example.ms_goodsreceipts.Request.GlobaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@Service
public class GlobalesStockService {

@Autowired
        private ArticleRepository articleRepository;

    @Autowired
    private GlobalestockRepository globalestockRepository;

    @Autowired
    private MouvementService mouvementService;


    public List<Globalestock> getAllGlobalestocks() {
        return globalestockRepository.findAll();
    }

    public Optional<Globalestock> getGlobalestockById(Long id) {
        return globalestockRepository.findById(id);
    }

    public List<GlobaleRequest> getGlobalestockByLocation(String location) {
        List<Globalestock>  globaleList = globalestockRepository.findlistStockByLocationArea(location);
        List<GlobaleRequest> globaleRequestList = new ArrayList<>();
        Iterator<Globalestock> iterator = globaleList.iterator();
        while (iterator.hasNext()) {
            Globalestock item = iterator.next();
            GlobaleRequest globaleRequest = new GlobaleRequest();
            globaleRequest.setOpeningQuantity(item.getOpeningQuantity());
            globaleRequest.setArticle(item.getArticle());
            globaleRequest.setLocationAreaStock(item.getLocationAreaStock());
            globaleRequestList.add(globaleRequest);
        }
        return globaleRequestList;
    }


    public ResponseEntity<Globalestock>  createGlobalestock(GlobaleRequest globaleRequest) {


        Globalestock globalestock = new Globalestock();
        globalestock.setOpeningQuantity(globaleRequest.getOpeningQuantity());

        Optional<Article> articleOptional = articleRepository.findById(globaleRequest.getArticleId());
        if (!articleOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Article article = articleOptional.get();
        globalestock.setArticle(article);
        article.getStocks().add(globalestock);

        long idtr = globalestockRepository.save(globalestock).getId(); // Save Globalestock entity

        Mouvement mv = new Mouvement();
        mv.setDescription("Save Globalestock entity");
        mv.setMouvement("Save Globalestock entity");
        mv.setIdtransaction(idtr);

        mouvementService.SaveMouvement(mv);
        return ResponseEntity.ok(globalestock);
    }


    public long  createGlobalestockandGetId(Globalestock globalestock) {
        return globalestockRepository.save(globalestock).getId();
    }

    public Globalestock updateGlobalestock(Long id, Globalestock updatedGlobalestock) {
        Optional<Globalestock> existingGlobalestockOptional = globalestockRepository.findById(id);
        if (existingGlobalestockOptional.isPresent()) {
            Globalestock existingGlobalestock = existingGlobalestockOptional.get();
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
