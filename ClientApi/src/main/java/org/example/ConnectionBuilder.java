package org.example;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ConnectionBuilder {
    public static HttpURLConnection buildConnection(String httpMethod, URL urlObj, Map<String, String> headers) {
        try {
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod(httpMethod);

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    con.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            return con;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
