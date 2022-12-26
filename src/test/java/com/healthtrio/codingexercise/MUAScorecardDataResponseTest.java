package com.healthtrio.codingexercise;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class MUAScorecardDataResponseTest {

    @Test
    public void testResponse_validRequest_returnsResponse_202() throws IOException, InterruptedException {
        // Set up a valid HttpRequest object
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create("https://example.com/"))
                .build();

        // Create a MUAScorecardDataResponse object
        MUAScorecardDataResponse response = new MUAScorecardDataResponse();

        // Call the response method with the valid request
        HttpResponse<String> result = response.response(request);

        // Verify that the response method returned a 200 response
        assertNotNull(result);
        assertEquals(200, result.statusCode());
        assertNotNull(result.body());
    }

    @Test
    public void testResponse_invalidRequest_returnsResponse_404() throws IOException, InterruptedException {
        // Set up a valid HttpRequest object
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create("https://example.com/api/scorecard"))
                .build();

        // Create a MUAScorecardDataResponse object
        MUAScorecardDataResponse response = new MUAScorecardDataResponse();

        // Call the response method with the invalid request
        HttpResponse<String> result = response.response(request);

        // Verify that the response method returned a 404 response
        assertNotNull(result);
        assertEquals(404, result.statusCode());
        assertNotNull(result.body());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResponse_nullRequest_throwsException() throws IOException, InterruptedException {
        // Create a MUAScorecardDataResponse object
        MUAScorecardDataResponse response = new MUAScorecardDataResponse();

        // Call the response method with a null request
        response.response(null);
    }
}
