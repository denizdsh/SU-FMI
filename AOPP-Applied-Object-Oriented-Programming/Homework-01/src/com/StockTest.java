package com;

public class StockTest {
    private static void printStock(Stock stock) { // Print stock and test "changePercent" method
        System.out.printf("%s %s | Current price = %.2f, Prev price = %.2f | Change = %.2f%%%n",
                stock.getSymbol(), stock.getName(), stock.getCurrentPrice(), stock.getPreviousClosingPrice(), stock.changePercent());
    }

    static void main(String[] args) {
        Stock stockDefault = new Stock(); // Test default constructor
        printStock(stockDefault);

        Stock googleStock = new Stock("G$", "Google", 10.2, 17.3); // Test regular constructor
        printStock(googleStock);

        googleStock.setCurrentPrice(16.5);

        Stock googleBasedStock = new Stock(googleStock); // Test copy constructor
        // Test validation and update fields from console
        googleBasedStock.setSymbol("");
        googleBasedStock.setName(null);
        googleBasedStock.setCurrentPrice(0);
        printStock(googleBasedStock);
    }
}
