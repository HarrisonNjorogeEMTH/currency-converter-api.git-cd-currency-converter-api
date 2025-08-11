package com.example.demo.controller;

import com.example.demo.service.CurrencyService;
import com.example.demo.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private PdfService emailService;

    @GetMapping("/convert")
    public String convert(@RequestParam String from,
                          @RequestParam String to,
                          @RequestParam double amount) throws Exception {

        Map<String, Double> rates = currencyService.fetchRates(from);
        double rate = rates.get(to);
        double result = amount * rate;

        currencyService.addHistory(from, to, amount, result);

        if (currencyService.getHistory().size() == 5) {
            byte[] pdf = emailService.generatePdf(currencyService.getHistory());
            emailService.sendEmailWithAttachment(pdf);
        }

        return amount + " " + from + " = " + result + " " + to;
    }
}
