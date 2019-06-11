package com.backend.api.helper.response;

public enum CitiesStaticList {
    NEW_DELHI("New Delhi"),
    CHANDIGARH("Chandigarh"),
    BENGALURU("Bengaluru"),
    HYDERABAD("Hyderabad"),
    AGRA("Agra"),
    LUCKNOW("Lucknow"),
    KANPUR("Kanpur"),
    MUMBAI("Mumbai"),
    CHENNAI("Chennai");

    String name;

    CitiesStaticList(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
