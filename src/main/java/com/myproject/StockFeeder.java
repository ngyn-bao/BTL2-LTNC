package com.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // TODO: Implement Singleton pattern

    private StockFeeder() {
    }

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic

        if (instance == null) {
            synchronized (StockFeeder.class) {
                if (instance == null) {
                    instance = new StockFeeder();
                }
            }
        }
        return instance;
    }

    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList

        if (stock != null && !stockList.contains(stock)) {
            stockList.add(stock);
        }
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence

        for (Stock stock : this.stockList) {
            if (stock.getCode().equals(code))
                break;
            else {
                Logger.errorRegister(code);
            }
        }

        List<StockViewer> stockViewerList = viewers.get(code);

        if (stockViewerList != null) {
            stockViewerList.add(stockViewer);
        } else {
            stockViewerList = new ArrayList<>();
            stockViewerList.add(stockViewer);
            viewers.put(code, stockViewerList);
        }
    }

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging

        List<StockViewer> stockViewerList = viewers.get(code);

        if (stockViewerList != null) {
            if (stockViewerList.remove(stockViewer)) {
                if (stockViewerList.isEmpty()) {
                    viewers.remove(code);
                }
            } else {
                Logger.errorUnregister(code);
            }
        } else {
            Logger.errorUnregister(code);
        }
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates

        List<StockViewer> stockViewersList = viewers.get(stockPrice.getCode());
        if (stockViewersList != null) {
            for (StockViewer viewer : stockViewersList) {
                viewer.onUpdate(stockPrice);
            }
        }
    }
}
