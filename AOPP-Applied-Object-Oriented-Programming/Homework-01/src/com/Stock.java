package com;

import helper.Reader;
import helper.Validator;

public class Stock {
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

    public Stock() {
    }

    public Stock(String symbol, String name, double previousClosingPrice, double currentPrice) {
        setSymbol(symbol);
        setName(name);
        setPreviousClosingPrice(previousClosingPrice);
        setCurrentPrice(currentPrice);
    }

    public Stock(Stock other) {
        this(other.symbol, other.name, other.previousClosingPrice, other.currentPrice);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        if (Validator.isEmptyString(symbol)) {
            symbol = Reader.readStringUntilNot(Validator::isEmptyString, "Enter a valid symbol:");
        }
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Validator.isEmptyString(name)) {
            name = Reader.readStringUntilNot(Validator::isEmptyString, "Enter a valid name:");
        }
        this.name = name;
    }

    public double getPreviousClosingPrice() {
        return previousClosingPrice;
    }

    public void setPreviousClosingPrice(double previousClosingPrice) {
        if (Validator.isNonPositive(previousClosingPrice)) {
            previousClosingPrice = Reader.readDoubleUntilNot(Validator::isNonPositive, "Enter a positive previous closing price:");
        }
        this.previousClosingPrice = previousClosingPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        if (Validator.isNonPositive(currentPrice)) {
            currentPrice = Reader.readDoubleUntilNot(Validator::isNonPositive, "Enter a positive current price:");
        }

        if (!Validator.isNonPositive(this.currentPrice)) {
            setPreviousClosingPrice(this.currentPrice);
        }

        this.currentPrice = currentPrice;
    }

    public double changePercent() {
        return ((currentPrice - previousClosingPrice) / previousClosingPrice) * 100;
    }
}
