package com.myproject;

import java.util.ArrayList;
import java.util.List;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;

    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        // TODO: Implement constructor
        if (hoseLib == null || stockCodes == null || stockCodes.isEmpty())
            throw new IllegalArgumentException("hoseLib and stockCodes cannot be null or empty.");

        this.hoseLib = hoseLib;
        this.stockCodes = new ArrayList<>(stockCodes);
    }

    @Override
    public List<StockPrice> fetch() {
        // TODO: Fetch stock data and convert it to StockPrice list

        List<StockPrice> stockPricesList = new ArrayList<>();

        List<HoseData> hoseDataList = hoseLib.getPrices(stockCodes);

        if (hoseDataList == null || hoseDataList.isEmpty()) {
            System.err.println("\"fetch - No stock data available\"");
            return stockPricesList;
        }

        for (HoseData hoseData : hoseDataList) {
            stockPricesList.add(convertToStockPrice(hoseData));
        }

        return stockPricesList;
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        // TODO: Convert HoseData to StockPrice

        if (hoseData == null) {
            throw new IllegalArgumentException("hoseData cannot be null.");
        }

        StockPrice stockPrice = new StockPrice(hoseData.getStockCode(), hoseData.getPrice(), hoseData.getVolume(),
                hoseData.getTimestamp());
        return stockPrice;
    }
}
