package com.healthtrio.codingexercise;

import java.io.IOException;
import java.util.List;

public class App {

    private static String API_URI="https://www.healthit.gov/data/open-api?source=Meaningful-Use-Acceleration-Scorecard.csv&period=2014";

    public static void main( String[] args ) throws IOException, InterruptedException {
        MUAScorecardDataRequest scorecardDataRequest = new MUAScorecardDataRequest(API_URI);
        MUAScorecardDataResponse scorecardResponse = new MUAScorecardDataResponse();
        ParseJsonResponse parsedJsonResponse = new ParseJsonResponse(scorecardResponse);

        List<PctHospitalsMuDataByState> pctHospitalsMuDataByStateList = parsedJsonResponse
                .createDataObject(scorecardResponse.response(scorecardDataRequest.request()));

        parsedJsonResponse.sortDescendingPct(pctHospitalsMuDataByStateList);
        List<PctHospitalsMuDataByState> results = parsedJsonResponse
                .formatDecimalToPercent(pctHospitalsMuDataByStateList);

        for (PctHospitalsMuDataByState result : results) {
            System.out.println(result.getRegion() + ": " + result.getPctHospitalsMu());
        }
    }
}
