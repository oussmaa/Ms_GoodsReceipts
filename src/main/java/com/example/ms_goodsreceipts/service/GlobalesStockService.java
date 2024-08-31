package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Article;
import com.example.ms_goodsreceipts.Entity.Globalestock;
import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Entity.Mouvement;
import com.example.ms_goodsreceipts.Repository.ArticleRepository;
import com.example.ms_goodsreceipts.Repository.GlobalestockRepository;
import com.example.ms_goodsreceipts.Request.GlobaleRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GlobalesStockService {

@Autowired
        private ArticleRepository articleRepository;

    @Autowired
    private GlobalestockRepository globalestockRepository;

    @Autowired
    private MouvementService mouvementService;



    public List<GlobaleRequest> getAllGlobalestocks() {
        List<Globalestock>  globaleList = globalestockRepository.findAll();
         Map<String, GlobaleRequest> globaleRequestMap = new HashMap<>();

        for (Globalestock item : globaleList) {
            String key = item.getLocationArea() + "-" + item.getLocationBin() + "-" + item.getLocationPlace() + "-" + item.getArticle().getId();

            if (globaleRequestMap.containsKey(key)) {
                GlobaleRequest existingRequest = globaleRequestMap.get(key);
                existingRequest.setOpeningQuantity(existingRequest.getOpeningQuantity() + item.getOpeningQuantity());
            } else {
                GlobaleRequest globaleRequest = new GlobaleRequest();
                globaleRequest.setOpeningQuantity(item.getOpeningQuantity());
                globaleRequest.setArticleID(item.getArticle().getArticel());
                globaleRequest.setLocationPlace(item.getLocationPlace());
                globaleRequest.setLocationBin(item.getLocationBin());
                globaleRequest.setLocationArea(item.getLocationArea());
                globaleRequest.setReservedStock(item.getReservedStock());
                globaleRequest.setId(item.getId());
                globaleRequestMap.put(key, globaleRequest);
            }
        }

        return new ArrayList<>(globaleRequestMap.values());
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
            globaleRequest.setLocationPlace(item.getLocationPlace());
            globaleRequest.setLocationBin(item.getLocationBin());
            globaleRequest.setLocationArea(item.getLocationArea());
            globaleRequestList.add(globaleRequest);

        }
        return globaleRequestList;
    }

   @Transactional
    public ResponseEntity<Globalestock>  createGlobalestock(GlobaleRequest globaleRequest) {
        try {

            Optional<Article> articleOptional = articleRepository.findByArticel(globaleRequest.getArticleID());

            if (!articleOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Globalestock globalestock1 = globalestockRepository.findlistStockByLocationAndArticle(globaleRequest.getLocationArea(),globaleRequest.getLocationBin(),globaleRequest.getLocationPlace(),globaleRequest.getArticleID());

            if(globalestock1!=null)
            {
                globalestock1.setOpeningQuantity(globalestock1.getOpeningQuantity()+globaleRequest.getOpeningQuantity());
                if(globalestock1.getReservedStock()==null)

                return ResponseEntity.ok(globalestock1);
            }
            else
            {
                Globalestock globalestock = new Globalestock();
                globalestock.setOpeningQuantity(globaleRequest.getOpeningQuantity());
                Article article = articleOptional.get();
                globalestock.setArticle(article);
                globalestock.setLocationArea(globaleRequest.getLocationArea());
                globalestock.setLocationBin(globaleRequest.getLocationBin());
                globalestock.setLocationPlace(globaleRequest.getLocationPlace());
                long idtr = globalestockRepository.save(globalestock).getId(); // Save Globalestock entity
                Mouvement mv = new Mouvement();
                mv.setDescription("Save Globalestock entity");
                mv.setMouvement("Save Globalestock entity");
                mv.setIdtransaction(idtr);
                mouvementService.SaveMouvement(mv);
                return ResponseEntity.ok(globalestock);

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;

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
