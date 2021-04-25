package com.hughbone.quickbind;

import com.hughbone.quickbind.gui.MacroButton;
import com.hughbone.quickbind.gui.MacroGUI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;

public class SaveJson {
    public static void save() {
        JSONArray jsonArray = new JSONArray();
        for (MacroButton mb : MacroGUI.macroBtnList) {
            try {
                JSONObject buttonField = new JSONObject();
                buttonField.put("customname", mb.customName);
                buttonField.put("hover-text", mb.toolText);
                buttonField.put("chat-command", mb.chatCommand);
                buttonField.put("color", mb.buttonColor);
                if (mb.keyBinding != null) {
                    buttonField.put("hotkey", mb.keyBinding.getTranslationKey());
                }

                JSONObject buttonObject = new JSONObject();
                buttonObject.put(mb.buttonID, buttonField);
                jsonArray.add(buttonObject);

            } catch (Exception e){
                e.printStackTrace();
            }
        }

        try {
            FileWriter file = new FileWriter("config" + File.separator + "quickbind.json");
            file.write(jsonArray.toJSONString());
            file.flush();
        }catch (Exception e){
        }

    }
}
