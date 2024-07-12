package com.example.ms_goodsreceipts.service;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class BarcodeService {

    public byte[] generateBarcode(String text) throws WriterException, IOException {
        int width = 300;
        int height = 150;
        String format = "png";

        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_39, width, height);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);

        return outputStream.toByteArray();
    }

    public void saveBarcode(String text) throws WriterException, IOException {
        int width = 300;
        int height = 100;
        String format = "png";

        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, width, height);
        LocalDateTime myDateObj = LocalDateTime.now();
        Path path = FileSystems.getDefault().getPath("Barcodes", text+ new Random(500000000) +".png");
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);
    }
    public String decodeBarcode(MultipartFile file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));

        try {
            Result result = new MultiFormatReader().decode(binaryBitmap);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error decoding barcode: " + e.getMessage();
        }
    }
}
