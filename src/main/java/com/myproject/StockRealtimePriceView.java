package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement logic to check if price has changed and log it

        String priceCode = stockPrice.getCode();
        Double price = stockPrice.getAvgPrice();

        Double lastPrice = lastPrices.get(priceCode);
        if (lastPrice == null || Double.compare(lastPrice, price) != 0) {
            lastPrices.put(priceCode, price);
            Logger.logRealtime(priceCode, price);
        }
    }
}
