package com.healthtrio.codingexercise;

import java.net.URI;
import java.net.http.HttpRequest;

public class MUAScorecardDataRequest {

    String API_URI;

    MUAScorecardDataRequest(String uri) {
        this.API_URI = uri;
    }

    public HttpRequest request() {
        // Add a null check to avoid a NullPointerException
        if (API_URI == null) {
            throw new IllegalArgumentException("API URI must not be null");
        }
        try {
            // Attempt to create a URI from the API_URI string
            URI uri = URI.create(API_URI);

            // If the URI is successfully created, build the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(uri)
                    .build();
            return request;
        } catch (IllegalArgumentException e) {
            // If an IllegalArgumentException is thrown, handle it appropriately
            // For example, you could log the error or display an error message
            throw new IllegalArgumentException("Invalid API URI: " + API_URI, e);
        }
    }
}

