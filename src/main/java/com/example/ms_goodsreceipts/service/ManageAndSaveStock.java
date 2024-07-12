package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Article;
import com.example.ms_goodsreceipts.Entity.Globalestock;
import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Repository.ArticleRepository;
import com.example.ms_goodsreceipts.Repository.GlobalestockRepository;
import com.example.ms_goodsreceipts.Repository.LocationAreaStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageAndSaveStock {

    @Autowired
    GlobalestockRepository globalestockRepository;

    @Autowired
    private LocationAreaStockRepository locationAreaStockRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public  void saveStock(String locationArea,String locationBin,String locationPlace, Double quantity,String article)
    {
        try {

            LocationAreaStock loc = locationAreaStockRepository.findLocationAreaStockByArea(locationArea);
            Article art = articleRepository.findByArticel(article).orElseThrow();
            Globalestock globalestock = new Globalestock();
            globalestock.setLocationAreaStock(loc);
            globalestock.setOpeningQuantity(quantity);
            globalestock.setArticle(art);
            globalestockRepository.save(globalestock);


        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
