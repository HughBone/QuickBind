package com.hughbone.quickselect.gui;

import com.hughbone.quickselect.KeyExt;
import com.hughbone.quickselect.Main;
import com.hughbone.quickselect.commands.TestCommand;
import com.hughbone.quickselect.SaveJson;
import io.github.cottonmc.cotton.gui.widget.TooltipBuilder;
import io.github.cottonmc.cotton.gui.widget.WButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.text.LiteralText;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HughButton extends WButton {

    public String toolText;
    public String chatCommand;
    public KeyBinding keyBinding;
    public static List<HughButton> hughButtonList = new ArrayList<HughButton>();

    public HughButton(LiteralText test, String hoverText) {
        super(test);
        this.toolText = hoverText;
        addTooltip(new TooltipBuilder());
        hughButtonList.add(this);
        loadButton();

        this.setOnClick(() -> {
            if (ButtonGUI.configToggle.getToggle()) {
                this.keyBinding = TestCommand.kb;
                this.toolText = TestCommand.kb.getTranslationKey();

                SaveJson.save();
                configButton();
            }
            else {
                if (keyBinding != null) {
                    MinecraftClient.getInstance().player.closeScreen();
                    ((KeyExt) keyBinding).pressKey();
                }
            }
        });
    }

    @Override
    public void addTooltip(TooltipBuilder tooltip) {
        tooltip.add(new LiteralText(toolText));
    }

    private void configButton() {
    }

    private void loadButton() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("config" + File.separator + "quickbind.json"));

            Iterator<JSONObject> iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
                JSONObject jobj = (JSONObject) iterator.next().get(this.getLabel().getString());
                if (jobj != null) {
                    this.toolText = (String) jobj.get("hover-text");
                    this.chatCommand = (String) jobj.get("chat-command");

                    String hotkey = (String) jobj.get("hotkey");
                    Iterator var0 = Main.keyBindings.values().iterator();
                    while(var0.hasNext()) {
                        KeyBinding keyBinding = (KeyBinding) var0.next();
                        if (keyBinding.getTranslationKey().equals(hotkey)) {
                            this.keyBinding = keyBinding;
                            break;
                        }
                    }
                    break;

                }
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }

}
