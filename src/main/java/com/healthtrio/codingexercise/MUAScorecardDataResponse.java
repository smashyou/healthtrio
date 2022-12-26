package com.healthtrio.codingexercise;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MUAScorecardDataResponse {

    public MUAScorecardDataResponse() {
    }

    public HttpResponse<String> response(HttpRequest request) throws IOException, InterruptedException {
        // Add a null check to avoid a NullPointerException
        if (request == null) {
            throw new IllegalArgumentException("HTTP request must not be null");
        }
        try {
            HttpClient client = HttpClient.newHttpClient();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            // Catch and handle the IOException and InterruptedException
            // For example, you could log the error or display an error message
            throw e;
        }
    }
}

