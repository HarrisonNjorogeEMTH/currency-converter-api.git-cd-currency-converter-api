package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface CurrencyService {
    public Map<String, Double> fetchRates(String base);
    public void addHistory(String from, String to, double amount, double result);
    public List<String[]> getHistory();


}
