package com.unitedremote.codingchallenge.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Repositories {

    @SerializedName("items")
    private List<Repository> items;
    public List<Repository> getItems() {
        return items;
    }
}
