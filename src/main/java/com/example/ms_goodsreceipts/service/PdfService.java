package com.example.ms_goodsreceipts.service;



import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public ByteArrayInputStream generatePdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 700);

                // Add multiple lines of text
                contentStream.showText("Picking Order : 236587 : ");
                contentStream.newLineAtOffset(0, -15); // Move down by 15 units
                contentStream.showText("Item 1 :");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Hello, this is a sample PDF document.");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Hello, this is a sample PDF document.");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Hello, this is a sample PDF document.");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Hello, this is a sample PDF document.");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Hello, this is a sample PDF document.");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Hello, this is a sample PDF document.");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Hello, this is a sample PDF document.");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Hello, this is a sample PDF document.");
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Hello, this is a sample PDF document.");
                contentStream.endText();
            }

            document.save(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
