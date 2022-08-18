package com.webtech.anirudh.dealers.models;

import java.io.Serializable;

public class DealerEntry implements Serializable {
    private final String ptin;
    private final String dealerName;
    private final String gstin;
    private final String address;
    private final String entryNumber;
    private final String email;
    private final String mobile;
    private final String edr;
    private final String taxPaid2017_2018;
    private final String taxPaid2018_2019;
    private final String taxPaid2019_2020;
    private final String taxPaid2020_2021;
    private final String taxPaid2021_2022;
    private final String taxPaid2022_2023;

    public DealerEntry(String[] entry) {
        this.ptin = entry[1];
        this.dealerName = entry[2];
        this.gstin = entry[3];
        this.address = entry[4];
        this.entryNumber = entry[5];
        this.email = entry[6];
        this.mobile = entry[7];
        edr = entry[8];
        taxPaid2017_2018 =  entry[9];
        taxPaid2018_2019 = entry[10];
        taxPaid2019_2020 = entry[11];
        taxPaid2020_2021 = entry[12];
        taxPaid2021_2022 = entry[13];
        taxPaid2022_2023 = entry[14];
    }

    public String getDealerName() {
        return dealerName;
    }

    public String getGstin() {
        return gstin;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPtin() {
        return ptin;
    }

    public String getAddress() {
        return address;
    }

    public String getEntryNumber() {
        return entryNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getEdr() {
        return edr;
    }

    public String getTaxPaid2017_2018() {
        return taxPaid2017_2018;
    }

    public String getTaxPaid2018_2019() {
        return taxPaid2018_2019;
    }

    public String getTaxPaid2019_2020() {
        return taxPaid2019_2020;
    }

    public String getTaxPaid2020_2021() {
        return taxPaid2020_2021;
    }

    public String getTaxPaid2021_2022() {
        return taxPaid2021_2022;
    }

    public String getTaxPaid2022_2023() {
        return taxPaid2022_2023;
    }
}