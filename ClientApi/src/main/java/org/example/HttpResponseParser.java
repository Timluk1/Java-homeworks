package org.example;

import java.net.HttpURLConnection;
import java.io.*;

public class HttpResponseParser {
    public static String parseResponse(HttpURLConnection connection) {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getResponseCode() >= 400
                                ? connection.getErrorStream()
                                : connection.getInputStream()
                ))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            return response.toString();
        } catch (IOException e) {
            System.out.println("PARSE RESPONSE ERROR: " + e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
