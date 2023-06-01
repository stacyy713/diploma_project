package com.example.helpsite.models;

public enum Category {
    HUM("ГУМАНІТАРНА ДОПОМОГА"),
    TRANS("ТРАНСПОРТНА ДОПОМОГА"),
    HOUS("ЖИТЛОВА ДОПОМОГА");

    private String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
