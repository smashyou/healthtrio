package com.healthtrio.codingexercise;

public class PctHospitalsMuDataByState {
    private String region;
    private String pctHospitalsMu;

    public PctHospitalsMuDataByState(String region, String pctHospitalsMu) {
        this.region = region;
        this.pctHospitalsMu = pctHospitalsMu;
    }

    public PctHospitalsMuDataByState() {

    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPctHospitalsMu() {
        return pctHospitalsMu;
    }

    public void setPctHospitalsMu(String pctHospitalsMu) {
        this.pctHospitalsMu = pctHospitalsMu;
    }
}
