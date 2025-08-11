package com.example.demo.service.Impl;

import com.example.demo.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class CurrencyServiceImpl implements CurrencyService {



    private final RestTemplate restTemplate = new RestTemplate();
    private final Deque<String[]> history = new ArrayDeque<>();

    public Map<String, Double> fetchRates(String base) {
        String url = "https://open.er-api.com/v6/latest/" + base;
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> body = response.getBody();

        Map<String, Object> rawRates = (Map<String, Object>) body.get("rates");
        Map<String, Double> selectedRates = new HashMap<>();

        for (String currency : List.of("USD", "EUR", "JPY")) {
            Object val = rawRates.get(currency);
            if (val instanceof Number) {
                selectedRates.put(currency, ((Number) val).doubleValue());
            } else {
                throw new IllegalArgumentException("Invalid rate type for " + currency);
            }
        }




        return selectedRates;
    }

    public void addHistory(String from, String to, double amount, double result) {
        if (history.size() >= 5) {
            history.removeFirst();
        }
        history.addLast(new String[]{from, to, String.valueOf(amount), String.valueOf(result)});
    }

    public List<String[]> getHistory() {
        return new ArrayList<>(history);
    }
}
