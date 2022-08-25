package com.hughbone.quickbind.gui;

import com.hughbone.quickbind.SaveJson;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MacroGUI extends LightweightGuiDescription {

    private WGridPanel root = new WGridPanel();
    public static WToggleButton configToggle = new WToggleButton(Text.of("Config"));
    public static WToggleButton altViewToggle = new WToggleButton(Text.of("ShowAltView"));
    public static List<MacroButton> macroBtnList;

    public MacroGUI() {
        addButton();
        addConfigToggle();
        addAltViewToggle();
        addCloseButton();
        setRootPanel(root);
        if (configToggle.getToggle()) {
            root.add(altViewToggle, 6, 0, 3, 1);
        }

        root.setSize(300, 200);
    }

    private void addConfigToggle() {
        configToggle.setColor(16777215, 16777215);
        root.add(configToggle, 0, 0, 2, 1);
        configToggle.setOnToggle((e) -> {
            if (e) {
                root.add(altViewToggle, 6, 0);
            }
            else {
                root.remove(altViewToggle);
            }
        });
    }

    private void addButton() {
        macroBtnList = new ArrayList<>();

        for (int i = 1; i <= 24; i++) {
            try {
                MacroButton button = new MacroButton(""+i);
                macroBtnList.add(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            addToRoot();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    // Change background panel color to transparent black
    @Override
    public void addPainters() {
        super.addPainters();
        this.rootPanel.setBackgroundPainter(BackgroundPainter.createColorful(0x00000000));
    }

    private void addCloseButton() {
        WButton escButton = new WButton(Text.of("x"));
        escButton.setOnClick(() -> MinecraftClient.getInstance().player.closeScreen());
        root.add(escButton, 15, 0, 2, 2);
    }

    private void addAltViewToggle() {
        altViewToggle.setColor(16777215, 16777215);
        altViewToggle.setOnToggle((e) -> {
            SaveJson.save();
            MinecraftClient.getInstance().setScreenAndRender(new GUIScreen(new MacroGUI()));
        });
    }

    private void loadCircleView() {
        // outer circle
        root.add(macroBtnList.get(0), 7, 1, 1, 1);
        root.add(macroBtnList.get(1), 9, 1, 1, 1);
        root.add(macroBtnList.get(2), 11, 2, 1, 1);
        root.add(macroBtnList.get(3), 12, 4, 1, 1);
        root.add(macroBtnList.get(4), 12, 6, 1, 1);
        root.add(macroBtnList.get(5), 11, 8, 1, 1);
        root.add(macroBtnList.get(6), 9, 9, 1, 1);
        root.add(macroBtnList.get(7), 7, 9, 1, 1);
        root.add(macroBtnList.get(8), 5, 8, 1, 1);
        root.add(macroBtnList.get(9), 4, 6, 1, 1);
        root.add(macroBtnList.get(10), 4, 4, 1, 1);
        root.add(macroBtnList.get(11), 5, 2, 1, 1);
        // inner circle
        root.add(macroBtnList.get(12), 8, 4, 1, 1);
        root.add(macroBtnList.get(13), 9, 5, 1, 1);
        root.add(macroBtnList.get(14), 8, 6, 1, 1);
        root.add(macroBtnList.get(15), 7, 5, 1, 1);
        // left side
        root.add(macroBtnList.get(16), 1, 2, 1, 1);
        root.add(macroBtnList.get(17), 0, 4, 1, 1);
        root.add(macroBtnList.get(18), 0, 6, 1, 1);
        root.add(macroBtnList.get(19), 1, 8, 1, 1);
        // right side
        root.add(macroBtnList.get(20), 15, 2, 1, 1);
        root.add(macroBtnList.get(21), 16, 4, 1, 1);
        root.add(macroBtnList.get(22), 16, 6, 1, 1);
        root.add(macroBtnList.get(23), 15, 8, 1, 1);
    }

    private void addToRoot() throws Exception {

        try {
            // Load circle view
            JSONParser parser = new JSONParser();
            //try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("config" + File.separator + "quickbind.json"));

            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {

                JSONObject circleLayout = (JSONObject) iterator.next().get("0");
                // load rectangle view
                if (circleLayout != null) {
                    // Row 1
                    root.add(macroBtnList.get(0), 0, 1, 5, 1);
                    root.add(macroBtnList.get(1), 0, 2, 5, 1);
                    root.add(macroBtnList.get(2), 0, 3, 5, 1);
                    root.add(macroBtnList.get(3), 0, 4, 5, 1);
                    root.add(macroBtnList.get(4), 0, 6, 5, 1);
                    root.add(macroBtnList.get(5), 0, 7, 5, 1);
                    root.add(macroBtnList.get(6), 0, 8, 5, 1);
                    root.add(macroBtnList.get(7), 0, 9, 5, 1);
                    // Row 2
                    root.add(macroBtnList.get(8), 6, 1, 5, 1);
                    root.add(macroBtnList.get(9), 6, 2, 5, 1);
                    root.add(macroBtnList.get(10), 6, 3, 5, 1);
                    root.add(macroBtnList.get(11), 6, 4, 5, 1);
                    root.add(macroBtnList.get(12), 6, 6, 5, 1);
                    root.add(macroBtnList.get(13), 6, 7, 5, 1);
                    root.add(macroBtnList.get(14), 6, 8, 5, 1);
                    root.add(macroBtnList.get(15), 6, 9, 5, 1);
                    // Row 3
                    root.add(macroBtnList.get(16), 12, 1, 5, 1);
                    root.add(macroBtnList.get(17), 12, 2, 5, 1);
                    root.add(macroBtnList.get(18), 12, 3, 5, 1);
                    root.add(macroBtnList.get(19), 12, 4, 5, 1);
                    root.add(macroBtnList.get(20), 12, 6, 5, 1);
                    root.add(macroBtnList.get(21), 12, 7, 5, 1);
                    root.add(macroBtnList.get(22), 12, 8, 5, 1);
                    root.add(macroBtnList.get(23), 12, 9, 5, 1);
                    altViewToggle.setToggle(true);
                }
                // Load circle view
                else {
                    loadCircleView();
                }

                break;
            }
        } catch (Exception e) {
            loadCircleView();
        }


    }
}
