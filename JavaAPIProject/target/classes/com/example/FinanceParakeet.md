# Stock Market Data App

#1 Project Overview

- To program this project, I used the Alpha Vantage Time_Series_Daily API which provides data ranging from the open, close, high, and low of any U.S. stock. 
- This project utilized all four types of data. My program consists of two features: checking a specific stock value and comparing two stocks. Regardless if one is checking a specific stock value or comparing two stocks (in terms of price), the program will also recommend whether or not the user should invest in this stock. 
- The program is very user-friendly as it can be used by anyone who has minimal knowledge about stocks. The program can help people find stock prices and also give advice as to whether or not they should purchase the stock to make profit. My program would most likely appeal to stock market analyzers as it provides helpful data for them to study.   

#2 Code Breakdown

(App Class)

* main(String[] args) 
  - Initializes the Stock Market Data App.  
  - Runs a loop to present users with options: check stock value, compare stocks, or exit the program.

* checkStock(Scanner scanner)  
  - Asks user for a stock ticker (e.g., AAPL, TSLA).  
  - Fetches real-time stock data using API.getData(stockSymbol).  
  - It calls StockDataParser.extractStockData(jsonData) to process the response.  
  - Displays stock information along with investment recommendation.

* compareStocks(Scanner scanner)  
  - Asks user for two stock tickers.  
  - Fetches stock data for both tickers.  
  - Calls StockDataParser.extractStockData() for both stocks.  
  - Displays percentage change comparison and suggests the better investment option for the user.

(Stock Class)

* Stock(String date, String open, String high, String low, String close)  
  - Constructor that initializes a stock's attributes using API data.  
  - Calls calculateChangePercentage() to compute percentage movement between open and close prices.  

* calculateChangePercentage()  
  - Converts open and close prices from String to a double.  
  - Uses ((closePrice - openPrice) / openPrice) * 100 to compute percentage change.  
  - Returns rounded percentage change as final.  

* getChangePercentage() 
  - Getter method that returns the stock's percentage change value.  

* getInvestmentRecommendation() 
  - Determines if the stock is worth investing in based on its percentage change.  
  - If change > 2%, recommends investing.  
  - If change < -2%, advises caution.  
  - If between -2% and 2%, suggests further research.  

* toString()  
  - Formats stock details nicely for terminal display.  
  - Includes prices, percentage change, and recommendation.

(StockDataParser Class)

* extractStockData(String jsonData)  
  - Parses the API's JSON response to extract stock data.  
  - Uses a TreeSet to get the latest available date dynamically.  
  - Gets back key values: open, high, low, close prices.  
  - Creates and returns a Stock object.  

* getStockName(String ticker)  
  - Matches a stock ticker (AAPL, TSLA, MSFT) to its full company name.  
  - Uses a switch-case statement to return corresponding company name.  
  - If not found, defaults to returning the ticker itself. 

(API Class)

* getData(String stockSymbol)  
  - Constructs API request URL dynamically based on stock ticker.  
  - Sends an HTTP GET request to Alpha Vantage.  
  - Gets back the raw JSON response containing stock data.   

#3 Features Implemented

(Base Project) 88%
- Connected to Alpha Vantage API** to retrieve stock data.
- Used multiple Java classes (App.java, Stock.java, StockDataParser.java, API.java).
- Parsed JSON data from the API to extract stock details.
- Displayed at least 3 meaningful pieces of information including open, close, high, low prices, as well as percentage change.

(Statistics / Basic Computation) 6%
- Implemented percentage change calculation using basic statistical formulas.
- Applied logic for investment recommendation based on computed percentage change.
- Compared two stocks dynamically using their percentage change values.

(Filter/Sort Data) 2%
- Used TreeSet to retrieve the latest available date dynamically.
- Ensured stock data is sorted chronologically within the JSON response.
- This technically qualifies as sorting data based on criteria (latest stock entry).  

#4 Sample Terminal Output

(Checking a single stock)
- Used Tesla and Apple stocks
https://ctrlv.link/g4ms 

(Comparing two stocks)
- Used Ford stock
https://ctrlv.link/jPEJ 

#5 What I Learned
- How to fetch and process real-time stock data using APIs  
- How to parse JSON in Java for financial data extraction  
- How to implement basic statistics for percentage change calculations  
- How to structure a user-friendly terminal UI with colors and animations  
- How to design an interactive application with a number of features  