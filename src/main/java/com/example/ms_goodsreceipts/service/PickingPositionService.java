package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Picking;
import com.example.ms_goodsreceipts.Entity.PickingPosition;
import com.example.ms_goodsreceipts.Repository.PickingPositionRepository;
import com.example.ms_goodsreceipts.Repository.PickingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PickingPositionService {

    @Autowired
    public PickingPositionRepository pickingPositionRepository;

    @Autowired
     PickingRepository pickingRepository;

public List<PickingPosition> GetALLPositionById(long id)
{
    return pickingPositionRepository.findpickingByPosition(id);
}
    public ResponseEntity<String> bookPosition(Long id, Double quantityBooked) {
        try {
            // Find the picking position by id
            PickingPosition pickingPosition = pickingPositionRepository.findById(id)
                    .orElseThrow(() -> new Exception("Position not found"));


            // Check if the open quantity is less than the quantity booked
            if (pickingPosition.getOpenquantity() < quantityBooked+pickingPosition.getBookedquantity()) {
                return new ResponseEntity<>("The Quantity Booked is more than the Open Quantity", HttpStatus.BAD_REQUEST);
            }
            else if (pickingPosition.getOpenquantity() > quantityBooked+pickingPosition.getBookedquantity())
            {
                pickingPosition.setBookedquantity(pickingPosition.getBookedquantity()+quantityBooked);
                pickingPositionRepository.save(pickingPosition);
                return new ResponseEntity<>("The Quantity Booked ", HttpStatus.OK);

            }

            else if(pickingPosition.getOpenquantity() == quantityBooked+pickingPosition.getBookedquantity()) {
                pickingPosition.setBookedquantity(pickingPosition.getBookedquantity()+quantityBooked);
                pickingPosition.setStatus("BOOKED");
                pickingPositionRepository.save(pickingPosition);
                checkposition(pickingPosition.getPicking().getId());

                return new ResponseEntity<>("The Position is Closed", HttpStatus.OK);
            }
        } catch (Exception e) {
            // Return the error message
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return null;
    }

    public void checkposition(Long id)
    { boolean Closed =true;
try {

    List<PickingPosition> list = pickingPositionRepository.findpickingByPosition(id);
    for (PickingPosition position : list) {
        if (!position.getStatus().equals("BOOKED")) {
            Closed= false;
            break;
        }
    }

    if(Closed)
    {
        Picking picking =pickingRepository.findById(id).orElseThrow();
        picking.setStatus("Closed");
        pickingRepository.save(picking);
    }


}catch (Exception e)
{
    e.getMessage().toString();
}




    }
}
