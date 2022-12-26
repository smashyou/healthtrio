package com.healthtrio.codingexercise;

import java.net.http.HttpRequest;

import org.junit.Test;
import static org.junit.Assert.*;

public class MUAScorecardDataRequestTest {

    @Test
    public void testRequest_validUri_returnsRequest() {
        // Set up a valid URI string
        String apiUri = "https://example.com/api/scorecard";

        // Create a MUAScorecardDataRequest object with the valid URI string
        MUAScorecardDataRequest request = new MUAScorecardDataRequest(apiUri);

        // Call the request method
        HttpRequest result = request.request();

        // Verify that the request method returned a valid HttpRequest object
        assertNotNull(result);
        assertEquals("GET", result.method());
        assertEquals("application/json", result.headers().firstValue("accept").orElse(null));
        assertEquals(apiUri, result.uri().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequest_nullUri_throwsException() {
        // Create a MUAScorecardDataRequest object with a null URI string
        MUAScorecardDataRequest request = new MUAScorecardDataRequest(null);

        // Call the request method
        request.request();
    }
}
