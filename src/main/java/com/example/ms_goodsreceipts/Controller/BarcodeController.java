package com.example.ms_goodsreceipts.Controller;


import com.example.ms_goodsreceipts.service.BarcodeService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class BarcodeController {

    @Autowired
    private BarcodeService barcodeService;

    @GetMapping("/generate-barcode")
    public ResponseEntity<InputStreamResource> generateBarcode(@RequestParam String text) throws WriterException, IOException {
        byte[] barcodeBytes = barcodeService.generateBarcode(text);

        ByteArrayInputStream barcodeStream = new ByteArrayInputStream(barcodeBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=barcode.png");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(barcodeStream));
    }

    @GetMapping("/save-barcode")
    public ResponseEntity<String> saveBarcode(@RequestParam String text) {
        try {
            barcodeService.saveBarcode(text);
            return ResponseEntity.ok("Barcode saved successfully to Barcodes/");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save barcode: " + e.getMessage());
        }
    }
    @PostMapping("/scan-barcode")
    public ResponseEntity<String> scanBarcode(@RequestParam("file") MultipartFile file) {
        try {
            String decodedText = barcodeService.decodeBarcode(file);
            return ResponseEntity.ok("Decoded text: " + decodedText);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to scan barcode: " + e.getMessage());
        }
    }

}
