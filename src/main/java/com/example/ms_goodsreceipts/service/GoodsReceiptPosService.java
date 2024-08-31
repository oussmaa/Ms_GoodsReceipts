package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.*;
import com.example.ms_goodsreceipts.Exception.ResourceNotFoundException;
import com.example.ms_goodsreceipts.Repository.*;
import com.example.ms_goodsreceipts.Request.GoodsReceiptPosRequest;
import com.example.ms_goodsreceipts.Request.GoodsReceiptRequest;
import com.example.ms_goodsreceipts.Request.LocationAreaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsReceiptPosService {

    @Autowired
    private GoodsReceiptPosRepository goodsReceiptPosRepository;

    @Autowired
    private GlobalestockRepository globalestockRepository;

    @Autowired
    private OrderStockRepository orderStockRepository;

    @Autowired
    private MouvementService mouvementService;

    @Autowired
    private GoodsReceiptRepository goodsReceiptRepository;

    @Autowired
    private LocationAreaStockRepository locationAreaStockRepository;

    @Autowired
    private ManageAndSaveStock manageAndSaveStock;

    @Autowired
    private ArticleRepository articleRepository;


    @Transactional
    public ResponseEntity<String> createGoodsReceiptPos(GoodsReceiptPosRequest goodsReceiptPosRequest) {

        GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(goodsReceiptPosRequest.getGoodsReceiptid())
                .orElseThrow(() -> new ResourceNotFoundException("Goods receipt not found for this id :: " + goodsReceiptPosRequest.getGoodsReceiptid() ));

        Double countstock = goodsReceiptPosRepository.GetGoodsReceiptPosByIDGoodsReceip(goodsReceiptPosRequest.getGoodsReceiptid());

        if (countstock == null)
            countstock=0.0;

        if (goodsReceipt.getQuantityUsed() >= countstock+goodsReceiptPosRequest.getQuantityBooked()) {
            // Create a new GoodsReceiptPos instance
            GoodsReceiptPos goodsReceiptPos = new GoodsReceiptPos();
            goodsReceiptPos.setDescription(goodsReceiptPosRequest.getDescription());
            goodsReceiptPos.setQuantityBooked(goodsReceiptPosRequest.getQuantityBooked());

            // Find the location area stock by area
            LocationAreaStock locationAreaStock = locationAreaStockRepository.findLocationAreaStockByArea(goodsReceiptPosRequest.getLocation_area());

            if (locationAreaStock != null) {
                // Set the location area stock to the goods receipt position
                goodsReceiptPos.setLocation_area(goodsReceiptPosRequest.getLocation_area());

                goodsReceiptPos.setLocation_bin(goodsReceiptPosRequest.getLocation_bin());

                goodsReceiptPos.setLocation_place(goodsReceiptPosRequest.getLocation_place());

                // Set the goods receipt to the goods receipt position
                goodsReceiptPos.setGoodsReceipt(goodsReceipt);
                OrderStock orderStock = orderStockRepository.findArticlebyorderofstock(goodsReceipt.getId());
                goodsReceiptPos.setArticle(orderStock.getArticel());

                // Save the goods receipt position
                long idtr = goodsReceiptPosRepository.save(goodsReceiptPos).getId();


                Globalestock globalestock1 = globalestockRepository.findlistStockByLocationAndArticle(goodsReceiptPosRequest.getLocation_area(), goodsReceiptPosRequest.getLocation_bin(), goodsReceiptPosRequest.getLocation_place(), goodsReceiptPos.getArticle());
                if (globalestock1 != null) {
                    globalestock1.setOpeningQuantity(goodsReceiptPosRequest.getQuantityBooked() + globalestock1.getOpeningQuantity());
                    globalestockRepository.save(globalestock1);
                } else {

                    Globalestock globalestock = new Globalestock();
                    globalestock.setArticle(articleRepository.findByArticel(goodsReceiptPos.getArticle()).orElseThrow());
                    globalestock.setLocationArea(goodsReceiptPosRequest.getLocation_area());
                    globalestock.setLocationBin(goodsReceiptPosRequest.getLocation_bin());
                    globalestock.setLocationPlace(goodsReceiptPosRequest.getLocation_place());
                    globalestock.setOpeningQuantity(goodsReceiptPosRequest.getQuantityBooked());
                    globalestockRepository.save(globalestock);
                }

                Mouvement mv = new Mouvement();
                mv.setDescription("Save createGoodsReceiptPos entity");
                mv.setMouvement("Save createGoodsReceiptPos entity" + goodsReceiptPosRequest.getArticle());
                mv.setIdtransaction(idtr);
                mouvementService.SaveMouvement(mv);
                 if (countstock+goodsReceiptPosRequest.getQuantityBooked()==goodsReceipt.getQuantityUsed())
                {
                    goodsReceipt.setStatus("CLOSE");
                    goodsReceiptRepository.save(goodsReceipt);
                }



            } else {

                return new ResponseEntity<>("The location area does not exist.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Save successful", HttpStatus.OK);

        }
        else   {
            return new ResponseEntity<>("The Quantity was booked You can't book another position.", HttpStatus.BAD_REQUEST);
        }

    }

    public void CheckStatus(long idgoodesreciept)
    {
        Double countstock = goodsReceiptPosRepository.GetGoodsReceiptPosByIDGoodsReceip(idgoodesreciept);
        GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(idgoodesreciept).orElseThrow();

        if (countstock==goodsReceipt.getQuantityUsed())
        {
            goodsReceipt.setStatus("Close");
            goodsReceiptRepository.save(goodsReceipt);
        }


    }



    public GoodsReceiptPos saveGoodsReceiptPos(GoodsReceiptPos goodsReceiptPos) {
        return goodsReceiptPosRepository.save(goodsReceiptPos);
    }

    public List<GoodsReceiptPos> getGoodsReceiptPosById(Long id) {
        return goodsReceiptPosRepository.findlistPositionbyIdGoods(id);
    }

    public List<GoodsReceiptPos> getAllGoodsReceiptPos() {
        return goodsReceiptPosRepository.findAll();
    }

    public void deleteGoodsReceiptPos(Long id) {
        goodsReceiptPosRepository.deleteById(id);
    }
}