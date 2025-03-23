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
        if (stock == null)
            return;

        stockList.add(stock);
        viewers.putIfAbsent(stock.getCode(), new ArrayList<>());

    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence

        if (!viewers.containsKey(code)) {
            Logger.errorRegister(code);
            return;
        }

        viewers.putIfAbsent(code, new ArrayList<>());
        viewers.get(code).add(stockViewer);
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

        String code = stockPrice.getCode();
        List<StockViewer> stockViewersList = viewers.get(code);
        if (stockViewersList == null || stockViewersList.isEmpty())
            return;

        for (StockViewer viewer : stockViewersList) {
            viewer.onUpdate(stockPrice);
        }

    }
}
