package com.azure.iot.modelsrepository.implementation.models;

import java.util.Map;

public class TempCustomType {
    private FetchResult fetchResult;
    private Map<String, String> map;

    public TempCustomType(FetchResult fetchResult, Map<String, String> map) {
        this.fetchResult = fetchResult;
        this.map = map;
    }

    public FetchResult getFetchResult() {
        return fetchResult;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
