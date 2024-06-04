package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Supplier;
import com.example.ms_goodsreceipts.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Supplier supplier) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(supplier.getId());
        if (existingSupplier.isPresent()) {
            Supplier toUpdate = existingSupplier.get();
            toUpdate.setName(supplier.getName());
            toUpdate.setAddress(supplier.getAddress());

            return supplierRepository.save(toUpdate);
        } else {
            return null; // Handle case where supplier to update is not found
        }
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
