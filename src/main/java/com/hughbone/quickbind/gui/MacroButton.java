package com.hughbone.quickbind.gui;

import com.hughbone.quickbind.KeyExt;
import com.hughbone.quickbind.Main;
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

public class MacroButton extends WButton {

    public String toolText;
    public String chatCommand;
    public KeyBinding keyBinding;
    public static List<MacroButton> macroButtonList = new ArrayList<MacroButton>();

    public MacroButton(LiteralText text, String hoverText) {
        super(text);
        this.toolText = hoverText;
        addTooltip(new TooltipBuilder());
        macroButtonList.add(this);
        loadButton();

        this.setOnClick(() -> {
            if (ButtonGUI.configToggle.getToggle()) {
                MinecraftClient.getInstance().openScreen(new ButtonGUIScreen(new HotkeyGUI()));
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
