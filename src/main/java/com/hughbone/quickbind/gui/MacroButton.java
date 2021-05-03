package com.hughbone.quickbind.gui;

import com.hughbone.quickbind.KeyExt;
import com.hughbone.quickbind.Main;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.hotkeys.*;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class MacroButton extends WButton {

    public int buttonColor = 0xFFFFFF;
    public final String buttonID;
    public String customName = "";
    public String toolText = "";
    public String chatCommand = "";
    public KeyBinding keyBinding;
    public IHotkey malibKeyBinding;

    public static MacroButton clickedBtn;

    public MacroButton(String buttonID) {
        this.buttonID = buttonID;
        this.setLabel(Text.of(""));
        addTooltip(new TooltipBuilder()); // display hovertext

        loadButton(); // loads saved values

        this.setOnClick(() -> {

            if (MacroGUI.configToggle.getToggle()) {
                clickedBtn = this;
                MinecraftClient.getInstance().openScreen(new GUIScreen(new ConfigGUI(false)));
            }
            else {
                if (!chatCommand.equals("")) {
                    MinecraftClient.getInstance().player.closeScreen();
                    MinecraftClient.getInstance().player.sendChatMessage(chatCommand);
                }
                if (malibKeyBinding != null) {
                    MinecraftClient.getInstance().player.closeScreen();
                    KeybindMulti kbm = (KeybindMulti) malibKeyBinding.getKeybind();
                    kbm.getCallback().onKeyAction(KeyAction.PRESS, kbm);
                }
                else if (keyBinding != null) {
                    MinecraftClient.getInstance().player.closeScreen();
                    ((KeyExt) keyBinding).pressKey();
                }
            }
        });
    }

    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        boolean enabled = this.isEnabled();
        Icon icon = this.getIcon();
        Text label = this.getLabel();

        boolean hovered = (mouseX >= 0 && mouseY >= 0 && mouseX < getWidth() && mouseY < getHeight());
        int state = 1; //1=regular. 2=hovered. 0=disabled.
        if (!enabled) {
            state = 0;
        } else if (hovered || isFocused()) {
            state = 2;
        }

        float px = 1 / 256f;
        float buttonLeft = 0 * px;
        float buttonTop = (46 + (state * 20)) * px;
        int halfWidth = getWidth() / 2;
        if (halfWidth > 198) halfWidth = 198;
        float buttonWidth = halfWidth * px;
        float buttonHeight = 20 * px;

        float buttonEndLeft = (200 - (getWidth() / 2)) * px;

        ScreenDrawing.texturedRect(x, y, getWidth() / 2, 20, AbstractButtonWidget.WIDGETS_LOCATION, buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonHeight, buttonColor);
        ScreenDrawing.texturedRect(x + (getWidth() / 2), y, getWidth() / 2, 20, AbstractButtonWidget.WIDGETS_LOCATION, buttonEndLeft, buttonTop, 200 * px, buttonTop + buttonHeight, buttonColor);

        if (icon != null) {
            icon.paint(matrices, x + 1, y + 1, 16);
        }


        if (label != null) {
            int color = 0xE0E0E0;
            if (!enabled) {
                color = 0xA0A0A0;
            }
            // text hover color
            else if (hovered) {
				//color = 16711680;
			}

            int xOffset = (icon != null && alignment == HorizontalAlignment.LEFT) ? 18 : 0;
            ScreenDrawing.drawStringWithShadow(matrices, label.asOrderedText(), alignment, x + xOffset, y + ((20 - 8) / 2), width, color); //LibGuiClient.config.darkMode ? darkmodeColor : color);
        }
    }

    @Override
    public void addTooltip(TooltipBuilder tooltip) {
        if (!toolText.equals("")) {
            tooltip.add(new LiteralText(toolText));
        }
    }

    private void loadButton() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("config" + File.separator + "quickbind.json"));

            Iterator<JSONObject> iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
                JSONObject jobj = (JSONObject) iterator.next().get(this.buttonID);
                if (jobj != null) {
                    this.toolText = (String) jobj.get("hover-text");
                    this.chatCommand = (String) jobj.get("chat-command");
                    this.buttonColor = (int) ((long)jobj.get("color"));
                    this.customName = (String) jobj.get("customname");
                    this.setLabel(Text.of(customName));
                    // Get normal hotkey
                    String hotkey = (String) jobj.get("hotkey");
                    if (hotkey != null) {
                        Iterator var0 = Main.keyBindings.values().iterator();
                        while(var0.hasNext()) {
                            KeyBinding keyBinding = (KeyBinding) var0.next();
                            if (keyBinding.getTranslationKey().equals(hotkey)) {
                                this.keyBinding = keyBinding;
                                return;
                            }
                        }
                    }
                    // Get malilib hotkey
                    String malibKey = (String) jobj.get("malibkey");
                    if (malibKey != null) {
                        for (int i = 0; i < InputEventHandler.getKeybindManager().getKeybindCategories().size(); i++) {
                            for (int j = 0; j < InputEventHandler.getKeybindManager().getKeybindCategories().get(i).getHotkeys().size(); j++ ) {
                                if (InputEventHandler.getKeybindManager().getKeybindCategories().get(i).getHotkeys().get(j).getName().equals(malibKey)) {
                                    this.malibKeyBinding = InputEventHandler.getKeybindManager().getKeybindCategories().get(i).getHotkeys().get(j);
                                    return;
                                }
                            }
                        }
                    }

                }
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
