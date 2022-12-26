package com.healthtrio.codingexercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParseJsonResponse {

    MUAScorecardDataResponse scorecardResponse;

    ParseJsonResponse(MUAScorecardDataResponse response) {
        this.scorecardResponse = response;
    }

    public List<PctHospitalsMuDataByState> createDataObject (HttpResponse<String> response) throws JsonProcessingException {
        // Add a null check to avoid a NullPointerException
        if (response == null) {
            throw new IllegalArgumentException("response cannot be null");
        }

        // Create an ObjectMapper to parse the JSON data
        ObjectMapper mapper = new ObjectMapper();

        // Parse the JSON data into a Map of Maps, with the outer Map keyed by object id number and the inner Map containing the data for each state
        Map<String, Map<String, String>> data = mapper.readValue(response.body(), new TypeReference<>(){});

        // Create a list to store the PctHospitalsMuDataByState objects
        List<PctHospitalsMuDataByState> pctHospitalsMuDataByStateList = new ArrayList<>();

        // Loop through the data for each state & percentage and create a PctHospitalsMuDataByState object
        for (Map<String, String> dataMap : data.values()) {
            PctHospitalsMuDataByState pctHospitalsMuDataByState = new PctHospitalsMuDataByState();
            pctHospitalsMuDataByState.setRegion(dataMap.get("region"));
            pctHospitalsMuDataByState.setPctHospitalsMu(dataMap.get("pct_hospitals_mu"));
            pctHospitalsMuDataByStateList.add(pctHospitalsMuDataByState);
        }
        return pctHospitalsMuDataByStateList;
    }

    // Sort the list in descending order by pctHospitalsMu value
    public void sortDescendingPct (List<PctHospitalsMuDataByState> pctHospitalsMuDataByStateList) {
        pctHospitalsMuDataByStateList.sort((o1, o2) -> {
            double pct1 = Double.parseDouble(o1.getPctHospitalsMu());
            double pct2 = Double.parseDouble(o2.getPctHospitalsMu());
            return Double.compare(pct2, pct1);
        });
    }

    //Format decimal values into Percentage values and return the list
    public List<PctHospitalsMuDataByState> formatDecimalToPercent(List<PctHospitalsMuDataByState> pctHospitalsMuDataByStateList) {

        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);
        for (PctHospitalsMuDataByState pctHospitalsMuDataByState : pctHospitalsMuDataByStateList) {
            double pct = Double.parseDouble(pctHospitalsMuDataByState.getPctHospitalsMu());
            String formattedPct = percentFormat.format(pct);
            pctHospitalsMuDataByState.setPctHospitalsMu(formattedPct);
        }
        return pctHospitalsMuDataByStateList;
    }
}
