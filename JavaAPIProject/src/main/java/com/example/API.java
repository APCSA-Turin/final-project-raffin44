package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    public static String getData(String stockSymbol) throws Exception {
        /* Construct the API URL using the provided stock symbol */
        String endpoint = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + stockSymbol + "&apikey=PVX8RQ8ZVP8OZ20X";
        
        URL url = new URL(endpoint);
        /* Connect to the URL */
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        /* Creates a GET request to the API.. Asking the server to retrieve information for our program */
        connection.setRequestMethod("GET");
        /* When you read data from the server, it will be in bytes, the InputStreamReader will convert it to text. 
        The BufferedReader wraps the text in a buffer so we can read it line by line */
        BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine; // Variable to store text, line by line
        /* A StringBuilder is similar to a String object but faster for larger strings. 
        You can concatenate to it and build a larger string. Loop through the buffer 
        (read line by line). Add it to the StringBuilder */
        StringBuilder content = new StringBuilder();
        while ((inputLine = buff.readLine()) != null) {
            content.append(inputLine);
        }
        buff.close(); // Close the BufferedReader
        connection.disconnect(); // Disconnect from server 
        return content.toString(); // Return the content as a string
    }
}


