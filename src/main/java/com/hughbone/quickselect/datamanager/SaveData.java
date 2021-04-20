package com.hughbone.quickselect.datamanager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;

public class SaveData {
    public static void save() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 1; i <= 16; i++) {
            if (i <= 12) {
                JSONObject buttonField = new JSONObject();
                buttonField.put("name", ""+i);
                buttonField.put("chat-command", "test1");
                buttonField.put("hotkey","test2");

                JSONObject buttonObject = new JSONObject();
                buttonObject.put(""+i, buttonField);
                jsonArray.add(buttonObject);
            }
        }

        try {
            FileWriter file = new FileWriter("config" + File.pathSeparator + "quickbind.json");
            file.write(jsonArray.toJSONString());
            file.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
