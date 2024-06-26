package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Mouvement;
import com.example.ms_goodsreceipts.Repository.MouvementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MouvementService {

  @Autowired
    MouvementRepository  mouvementRepository;


  public void SaveMouvement(Mouvement mouvement){
      mouvementRepository.save(mouvement);
  }


}
