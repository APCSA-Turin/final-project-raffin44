package com.example;
import java.util.Scanner;

public class App {
    //ANSI color codes     https://www.w3schools.blog/ansi-colors-java#google_vignette 
    private static final String blue = "\u001B[34m";
    private static final String green = "\u001B[32m";
    private static final String red = "\u001B[31m";
    private static final String yellow = "\u001B[33m";
    private static final String reset = "\u001B[0m"; // Resets colors

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("\n══════════════════════════════════════");
        System.out.println(blue + "        STOCK MARKET DATA APP            " + reset);
        System.out.println("══════════════════════════════════════");

        while (keepRunning) {
            System.out.println("\n"+"Select an option:");
            System.out.println(green + "1 Check stock value" + reset);
            System.out.println(yellow + "2 Compare two stocks" + reset);
            System.out.println(red + "3 Exit" + reset);
            System.out.println("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                checkStock(scanner);
            } else if (choice.equals("2")) {
                compareStocks(scanner);
            } else if (choice.equals("3")) {
                keepRunning = false;
                System.out.println(green + "Thank you for using the Stock Market Data App! 💷" + reset);
            } else {
                System.out.println(red + "Invalid choice. Please enter 1, 2, or 3." + reset);
            }
        }

        scanner.close();
    }

    private static void checkStock(Scanner scanner) {
        System.out.print("\nEnter a stock ticker (e.g., AAPL, TSLA, MSFT): ");
        String stockSymbol = scanner.nextLine().toUpperCase();

        try {
            String jsonData = API.getData(stockSymbol);
            Stock stock = StockDataParser.extractStockData(jsonData);
            System.out.println("\n══════════════════════════════════════");
            System.out.println(green + " Stock Information: " + stockSymbol + reset);
            System.out.println("══════════════════════════════════════");
            System.out.println(stock);
            System.out.println("══════════════════════════════════════\n");
        } catch (Exception e) {
            System.out.println(red + "Error retrieving stock data: " + e.getMessage() + reset);
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

            System.out.println("\n══════════════════════════════════════");
            System.out.println(blue + "      STOCK COMPARISON RESULTS      " + reset);
            System.out.println("══════════════════════════════════════");

            System.out.println("\n Comparing: " + stock1Symbol + " vs. " + stock2Symbol);

            System.out.println("\n Stock 1: " + stock1Symbol);
            System.out.println("══════════════════════════════════════");
            System.out.println(stock1);
            System.out.println("══════════════════════════════════════");

            System.out.println("\n Stock 2: " + stock2Symbol);
            System.out.println("══════════════════════════════════════");
            System.out.println(stock2);
            System.out.println("══════════════════════════════════════");

            double percentageDifference = Math.abs(stock1.getChangePercentage() - stock2.getChangePercentage());
            System.out.println("\n " + yellow + "Difference in percentage change: " + percentageDifference + "%" + reset);
            System.out.println("══════════════════════════════════════");

            //Investment Recommendation Between Two Stocks
            System.out.println(green + "Investment Recommendation:" + reset);
            if (stock1.getChangePercentage() > stock2.getChangePercentage()) {
                System.out.println(stock1Symbol + " is the better investment.");
            } else if (stock2.getChangePercentage() > stock1.getChangePercentage()) {
                System.out.println(stock2Symbol + " is the better investment.");
            } else {
                System.out.println(yellow + "Both stocks have similar performance. Research further before investing." + reset);
            }
            System.out.println("══════════════════════════════════════\n");
        } catch (Exception e) {
            System.out.println("\n " + red + "Error retrieving stock data: " + e.getMessage() + reset);
        }
    }
}
