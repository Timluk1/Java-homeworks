package org.example;

import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws Exception {
        IHttpClient client = new HttpClient();
        ObjectMapper mapper = new ObjectMapper();

        // --- GET запрос ---
        String getRes = client.get("http://localhost:8080/ClientApi_1_0_SNAPSHOT_war/post", null, null);
        System.out.println("GET result: " + getRes);

        // --- POST запрос ---
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "Hello World");
        newPost.put("content", "This is my first post");

        String postJson = mapper.writeValueAsString(newPost);

        String postRes = client.post(
                "http://localhost:8080/ClientApi_1_0_SNAPSHOT_war/post",
                Map.of("Content-Type", "application/json"),
                Map.of("json", postJson)
        );
        System.out.println("POST result: " + postRes);

        // --- PUT запрос ---
        Map<String, Object> updatedPost = new HashMap<>();
        updatedPost.put("id", 3);
        updatedPost.put("title", "Updated Title");
        updatedPost.put("content", "Updated content");

        String putJson = mapper.writeValueAsString(updatedPost);

        String putRes = client.put(
                "http://localhost:8080/ClientApi_1_0_SNAPSHOT_war/post",
                Map.of("Content-Type", "application/json"),
                Map.of("json", putJson)
        );
        System.out.println("PUT result: " + putRes);

        // --- DELETE запрос ---
        String deleteRes = client.delete(
                "http://localhost:8080/ClientApi_1_0_SNAPSHOT_war/post",
                null,
                Map.of("id", "1")
        );
        System.out.println("DELETE result: " + deleteRes);
    }
}
