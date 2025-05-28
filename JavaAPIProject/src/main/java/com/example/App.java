package com.example;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;
        System.out.println("Welcome to the Stock Market Data App!");

        while (keepRunning) {
            // Display menu options
            System.out.println("\nSelect an option:");
            System.out.println("1. Check stock value");
            System.out.println("2. Compare two stocks");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                checkStock(scanner);
            } else if (choice.equals("2")) {
                compareStocks(scanner);
            } else if (choice.equals("3")) {
                keepRunning = false;
                System.out.println("\nThank you for using the Stock Market Data App! 📈");
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }

    private static void checkStock(Scanner scanner) {
        System.out.print("Enter a stock ticker (e.g., IBM, AAPL, TSLA): ");
        String stockSymbol = scanner.nextLine().toUpperCase();

        try {
            String jsonData = API.getData(stockSymbol);
            Stock stock = StockDataParser.extractStockData(jsonData);
            System.out.println("\nStock Information Retrieved Successfully:\n");
            System.out.println("________________________________");
            System.out.println("📊 Here are the results for: " + stockSymbol);
            System.out.println("________________________________");
            System.out.println(stock);
            System.out.println("________________________________");
        } catch (Exception e) {
            System.out.println("Error retrieving stock data: " + e.getMessage());
        }
    }

    private static void compareStocks(Scanner scanner) {
        System.out.print("\nEnter first stock ticker: ");
        String stock1Symbol = scanner.nextLine().toUpperCase();
        System.out.print("Enter second stock ticker: ");
        String stock2Symbol = scanner.nextLine().toUpperCase();

        try {
            Stock stock1 = StockDataParser.extractStockData(API.getData(stock1Symbol));
            Stock stock2 = StockDataParser.extractStockData(API.getData(stock2Symbol));

            System.out.println("________________________________");
            System.out.println("| Stock Comparison: |");
            System.out.println("________________________________");
            System.out.println("Here are the results for: "+ stock1Symbol + "\n"+ stock1);
            System.out.println("________________________________");
            System.out.println("\nComparing with:");
            System.out.println("________________________________");
            System.out.println("Here are the results for: "+ stock2Symbol+ "\n"+ stock2);
            System.out.println("________________________________");

            double percentageDifference = Math.abs(stock1.getChangePercentage() - stock2.getChangePercentage());
            System.out.println("Difference in percentage change between both: " + percentageDifference + "%");
            System.out.println("________________________________");

            //Investment Recommendation Between Two Stocks
            System.out.println("\nInvestment Recommendation:");
            if (stock1.getChangePercentage() > stock2.getChangePercentage()) {
                System.out.println(stock1Symbol + " is performing better as an investment.");
            } else if (stock2.getChangePercentage() > stock1.getChangePercentage()) {
                System.out.println(stock2Symbol + " is performing better as an investment.");
            } else {
                System.out.println("Both stocks have similar performance. Research further before investing.");
            }
            System.out.println("________________________________");
        } catch (Exception e) {
            System.out.println("Error retrieving stock data: " + e.getMessage());
        }
    }
}
