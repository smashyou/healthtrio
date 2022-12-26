package com.healthtrio.codingexercise;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.*;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ParseJsonResponseTest {

    @Mock
    HttpResponse<String> response;
    MUAScorecardDataResponse scorecardResponse;

    @Before
    public void setUp() {
        initMocks(this);
        scorecardResponse = Mockito.mock(MUAScorecardDataResponse.class);
    }

    @Test
    public void testCreateDataObject() throws JsonProcessingException {
        // Set up the mock HttpResponse object to return a JSON string when its body method is called
        when(response.body()).thenReturn("{\"AL\":{\"region\":\"Alabama\",\"pct_hospitals_mu\":\"0.25\"},\"AK\":{\"region\":\"Alaska\",\"pct_hospitals_mu\":\"0.50\"}}");

        // Create a ParseJsonResponse object and call the createDataObject method
        ParseJsonResponse parseJsonResponse = new ParseJsonResponse(scorecardResponse);
        List<PctHospitalsMuDataByState> dataObject = parseJsonResponse.createDataObject(response);

        // Verify that the returned list has the correct size and contents
        assertEquals(2, dataObject.size());
        assertEquals("Alabama", dataObject.get(0).getRegion());
        assertEquals("0.25", dataObject.get(0).getPctHospitalsMu());
        assertEquals("Alaska", dataObject.get(1).getRegion());
        assertEquals("0.50", dataObject.get(1).getPctHospitalsMu());
    }

    @Test
    public void testSortDescendingPct() {
        // Create a list of PctHospitalsMuDataByState objects
        List<PctHospitalsMuDataByState> dataObject = Arrays.asList(
                new PctHospitalsMuDataByState("State 1", "0.25"),
                new PctHospitalsMuDataByState("State 2", "0.50"),
                new PctHospitalsMuDataByState("State 3", "0.10")
        );

        // Create a ParseJsonResponse object and call the sortDescendingPct method
        ParseJsonResponse parseJsonResponse = new ParseJsonResponse(scorecardResponse);
        parseJsonResponse.sortDescendingPct(dataObject);

        assertEquals("State 2", dataObject.get(0).getRegion());
        assertEquals("0.50", dataObject.get(0).getPctHospitalsMu());
        assertEquals("State 1", dataObject.get(1).getRegion());
        assertEquals("0.25", dataObject.get(1).getPctHospitalsMu());
        assertEquals("State 3", dataObject.get(2).getRegion());
        assertEquals("0.10", dataObject.get(2).getPctHospitalsMu());
    }

    @Test
    public void testFormatDecimalToPercent() {
        // Create a list of PctHospitalsMuDataByState objects
        List<PctHospitalsMuDataByState> actualResults = Arrays.asList(
                new PctHospitalsMuDataByState("State 1", "0.25"),
                new PctHospitalsMuDataByState("State 2", "0.50")
        );

        // Create a ParseJsonResponse object and call the formatDecimalToPercent method
        ParseJsonResponse parseJsonResponse = new ParseJsonResponse(scorecardResponse);
        parseJsonResponse.formatDecimalToPercent(actualResults);

        assertEquals("25%", actualResults.get(0).getPctHospitalsMu());
        assertEquals("50%", actualResults.get(1).getPctHospitalsMu());
    }
}