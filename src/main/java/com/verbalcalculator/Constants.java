package com.verbalcalculator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Constants {
    public final String API_KEY;
    public final String SERVICE_URL;
    public final String MODEL;
    public final String AUDIO_TYPE;

    public Constants() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("config.json"));
        JSONObject json = (JSONObject) obj;
        API_KEY = json.get("APIKey").toString();
        SERVICE_URL = json.get("ServiceURL").toString();
        MODEL = json.get("Model").toString();
        AUDIO_TYPE = json.get("AudioType").toString();
    }
}