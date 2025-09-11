package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        IHttpClient client = new HttpClient();

        String res = client.get("https://jsonplaceholder.typicode.com/posts/1", null, null);
        System.out.println("GET result: " + res);

        String postRes = client.post("https://jsonplaceholder.typicode.com/posts",
                Map.of("Content-Type", "application/x-www-form-urlencoded"),
                Map.of("title", "foo", "body", "bar", "userId", "1"));
        System.out.println("POST result: " + postRes);
    }
}