package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Picking;
import com.example.ms_goodsreceipts.Entity.PickingPosition;
import com.example.ms_goodsreceipts.Repository.PickingPositionRepository;
import com.example.ms_goodsreceipts.Repository.PickingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickingService {

    @Autowired
    PickingRepository pickingRepository;

    @Autowired
    PickingPositionRepository pickingPositionRepository;

    @Transactional
    public List<Picking> getAllPickings() {
        return pickingRepository.findpickingBySatus("INPROGRESS");
    }

    @Transactional
    public List<PickingPosition> getpickingpositionbyid(Long id) {
     return    pickingPositionRepository.findpickingByPosition(id);
    }
}
