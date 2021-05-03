package com.hughbone.quickbind.gui;

import com.hughbone.quickbind.Main;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;

import java.util.Iterator;


public class HotkeyGUI extends LightweightGuiDescription {

    public static HotkeyButton selectedButton;
    public static WTextField searchField;
    public static WText selectText = new WText(Text.of("Selected: None"));
    public static WScrollPanel scrollPanel;
    public static WGridPanel root;

    public HotkeyGUI() {
        WGridPanel panel = new WGridPanel();
        HotkeyButton.hotkeyButtonList.clear();
        int y = 0;
        // show regular hotkeys
        Iterator var0 = Main.keyBindings.values().iterator();
        while(var0.hasNext()) {
            KeyBinding keyBinding = (KeyBinding) var0.next();
            final String key = keyBinding.getTranslationKey();
            if (key.contains("key.hotbar.") || key.equals("key.right") || key.equals("key.left") || key.equals("key.forward") || key.equals("key.back")) {
                continue;
            }
            panel.add(new HotkeyButton(Text.of(I18n.translate(keyBinding.getTranslationKey())), keyBinding, null), 0, y, 14, 1);
            y++;
        }
        // show malib hotkeys
        for (int i = 0; i < InputEventHandler.getKeybindManager().getKeybindCategories().size(); i++) {
            for (int j = 0; j < InputEventHandler.getKeybindManager().getKeybindCategories().get(i).getHotkeys().size(); j++ ) {
                IHotkey mKey = InputEventHandler.getKeybindManager().getKeybindCategories().get(i).getHotkeys().get(j);
                panel.add(new HotkeyButton(Text.of(mKey.getConfigGuiDisplayName()), null, mKey), 0, y, 14, 1);
                y++;
            }
        }

        scrollPanel = new WScrollPanel(panel);
        root = new WGridPanel();
        root.add(scrollPanel, 0, 0, 16, 9);

        NormalButton backButton = new NormalButton(Text.of("←"));
        root.add(backButton, 17, 0);
        backButton.setOnClick(() -> {
            MinecraftClient.getInstance().openScreen(new GUIScreen(new ConfigGUI(false)));
        });

        root.add(new NormalButton(Text.of("Search")), 0, 9, 2,1);

        WButton resetBtn = new WButton(Text.of("Reset"));
        root.add(resetBtn, 4, 9, 2,1);

        resetBtn.setOnClick(() -> {
            selectText.setText(Text.of("None Selected."));
        });

        searchField = new WTextField();
        searchField.setMaxLength(16);
        root.add(searchField, 0, 10, 6,1);

        selectText.setText(Text.of("None Selected."));
        selectText.setColor(16777215);
        if (ConfigGUI.keyBinding != null) {
            selectText.setText(Text.of(I18n.translate(ConfigGUI.keyBinding.getTranslationKey())));
        }
        else if (ConfigGUI.malibKeyBinding != null) {
            selectText.setText(Text.of(ConfigGUI.malibKeyBinding.getConfigGuiDisplayName()));
        }
        root.add(selectText, 7, 10, 7, 1);

        WButton applyButton = new WButton(Text.of("APPLY"));
        root.add(applyButton, 15, 10, 2,1);

        applyButton.setOnClick(() -> {
            if (selectText.getText().getString().equals("None Selected.")) {
                ConfigGUI.keyBinding = null;
                ConfigGUI.malibKeyBinding = null;
            }
            else if (HotkeyGUI.selectedButton != null) {
                if (HotkeyGUI.selectedButton.keyBinding != null) {
                    ConfigGUI.keyBinding = HotkeyGUI.selectedButton.keyBinding;
                }
                else if (HotkeyGUI.selectedButton.malibKeyBinding != null) {
                    ConfigGUI.malibKeyBinding = HotkeyGUI.selectedButton.malibKeyBinding;
                }
            }
            MinecraftClient.getInstance().openScreen(new GUIScreen(new ConfigGUI(true)));
        });

        setRootPanel(root);
        root.setSize(300, 200);
        rootPanel.validate(this);

    }

    // Change background panel color to transparent black
    @Override
    public void addPainters() {
        super.addPainters();
        this.rootPanel.setBackgroundPainter(BackgroundPainter.createColorful(0x4D000000));
    }

}
