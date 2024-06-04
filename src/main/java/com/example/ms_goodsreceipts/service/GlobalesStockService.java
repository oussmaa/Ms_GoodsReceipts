package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Globalestock;
import com.example.ms_goodsreceipts.Repository.GlobalestockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GlobalesStockService {

    @Autowired
    private GlobalestockRepository globalestockRepository;

    public List<Globalestock> getAllGlobalestocks() {
        return globalestockRepository.findAll();
    }

    public Optional<Globalestock> getGlobalestockById(Long id) {
        return globalestockRepository.findById(id);
    }

    public Globalestock createGlobalestock(Globalestock globalestock) {
        return globalestockRepository.save(globalestock);
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
