package com.example;

public class Stock {
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private double changePercentage; 

    public Stock(String date, String open, String high, String low, String close) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.changePercentage = calculateChangePercentage(); 
    }

    private double calculateChangePercentage() {    
        double openPrice = Double.parseDouble(open);
        double closePrice = Double.parseDouble(close);
        double percentChange = ((closePrice - openPrice) / openPrice) * 100;
        return Math.round(percentChange * 100.0) / 100.0;
    }

    public double getChangePercentage() {
        return changePercentage;
    }

    //Investment recommendation method
    public String getInvestmentRecommendation() {
        if (changePercentage > 2) {
            return "Recommended: This stock is showing strong growth. You should invest!";
        } else if (changePercentage < -2) {
            return "Caution: This stock has dropped significantly.";
        } else {
            return "Neutral: This stock is stable. I would prefer to research further before investing.";
        }
    }

    @Override
    public String toString() {
        return "Stock Data for " + date + ":\n" +
               "Open: " + open + "\n" +
               "High: " + high + "\n" +
               "Low: " + low + "\n" +
               "Close: " + close + "\n" +
               "The Change in percent is: " + changePercentage + "%\n" +
               getInvestmentRecommendation();
    }
}
