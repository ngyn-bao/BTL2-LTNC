package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor

        if (highThreshold <= lowThreshold) {
            throw new IllegalArgumentException("High threshold must be greater than low threshold.");
        }

        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;

    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions
        // Logger.notImplementedYet("alertAbove");

        String priceCode = stockPrice.getCode();
        Double price = stockPrice.getAvgPrice();

        if (price > alertThresholdHigh) {
            Double lastPrice = lastAlertedPrices.get(priceCode);
            if (lastPrice == null || lastPrice <= alertThresholdHigh) {
                alertAbove(priceCode, price);
                lastAlertedPrices.put(priceCode, price);
            }
        }

        if (price < alertThresholdLow) {
            Double lastPrice = lastAlertedPrices.get(priceCode);
            if (lastPrice == null || lastPrice >= alertThresholdLow) {
                alertBelow(priceCode, price);
                lastAlertedPrices.put(priceCode, price);
            }
        }
    }

    private void alertAbove(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        // Logger.notImplementedYet("alertAbove");

        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        // Logger.notImplementedYet("alertBelow");

        Logger.logAlert(stockCode, price);
    }
}
