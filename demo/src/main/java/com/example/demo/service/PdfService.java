package com.example.demo.service;

import com.example.demo.dto.ConversionDTO;
import jakarta.mail.MessagingException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PdfService {
    public byte[] generatePdf(List<String[]> history) throws IOException;

    public void sendEmailWithAttachment(byte[] pdfBytes) throws MessagingException;
}
