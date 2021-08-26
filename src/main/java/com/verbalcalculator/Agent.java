package com.verbalcalculator;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import org.json.simple.parser.ParseException;

public class Agent {

    private final SpeechToText stt;
    private final Constants appConfig;

    public Agent() throws IOException, ParseException {
        appConfig = new Constants();
        stt = new SpeechToText(new IamAuthenticator.Builder()
                .apikey(appConfig.API_KEY)
                .build());
        stt.setServiceUrl(appConfig.SERVICE_URL);
    }

    public String[] parseAudio(String audio) throws FileNotFoundException {
        SpeechRecognitionResults input;
        RecognizeOptions options = new RecognizeOptions.Builder()
                .audio(new File(audio))
                .contentType(appConfig.AUDIO_TYPE)
                .model(appConfig.MODEL)
                .build();
        input = stt.recognize(options)
                .execute()
                .getResult();
        return parseResult((JSONObject) JSONValue.parse(input.toString()));
    }

    private String[] parseResult(JSONObject json) {
        JSONArray arr = (JSONArray) json.get("results");
        JSONObject obj = (JSONObject) arr.get(0);
        arr = (JSONArray) obj.get("alternatives");
        obj = (JSONObject) arr.get(0);
        return obj.get("transcript").toString().split(" ");
    }
}
