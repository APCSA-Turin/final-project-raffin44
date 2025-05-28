package com.example;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.TreeSet;

public class StockDataParser {
    public static Stock extractStockData(String jsonData) {
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject timeSeries = jsonObject.getJSONObject("Time Series (Daily)");

        // Store all dates in a sorted set (ensures we get the latest date)
        TreeSet<String> sortedDates = new TreeSet<>();
        Iterator<String> dates = timeSeries.keys();
        while (dates.hasNext()) {
            sortedDates.add(dates.next());
        }

        // Get the most recent date (last entry in the sorted set)
        String latestDate = sortedDates.last();
        JSONObject latestData = timeSeries.getJSONObject(latestDate);

        return new Stock(
            latestDate,
            latestData.getString("1. open"),
            latestData.getString("2. high"),
            latestData.getString("3. low"),
            latestData.getString("4. close")
        );
    }
}
