package com.example.demo.service.Impl;

import com.example.demo.dto.ConversionDTO;
import com.example.demo.service.PdfService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {


    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.pdf.dir}")
    private String pdfDir;


    public byte[] generatePdf(List<String[]> history) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Last 5 Currency Conversions")
                .setBold()
                .setFontSize(14));

        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 3, 3, 3}))
                .useAllAvailableWidth();

        // Table header
        table.addHeaderCell("From Currency");
        table.addHeaderCell("To Currency");
        table.addHeaderCell("Amount");
        table.addHeaderCell("Result");

        // Table rows
        for (String[] row : history) {
            table.addCell(row[0]); // from currency
            table.addCell(row[1]); // to currency
            table.addCell(row[2]); // amount
            table.addCell(row[3]); // result
        }

        document.add(table);

        document.close();
        return baos.toByteArray();
    }

    public void sendEmailWithAttachment(byte[] pdfBytes) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("interviews@emtechhouse.co.ke");
        helper.setSubject("Currency Conversion Report");
        helper.setText("Please find attached the last 5 conversions.");

        helper.addAttachment("conversions.pdf", new ByteArrayResource(pdfBytes));

        mailSender.send(message);
    }
}
