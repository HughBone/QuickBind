package com.hughbone.quickbind.gui;

import com.hughbone.quickbind.SaveJson;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WScrollPanel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NormalButton extends WButton {

    public NormalButton(Text text) {
        super(text);

        this.setOnClick(() -> {

            if (this.getLabel().getString().equals("[Select]")) {
                MinecraftClient.getInstance().openScreen(new GUIScreen(new HotkeyGUI()));
            }

            else if (this.getLabel().getString().equals("SEARCH")) {
                HotkeyGUI.root.remove(HotkeyGUI.scrollPanel);
                WGridPanel panel = new WGridPanel();

                int y = 0;
                for (int i = 0; i < HotkeyButton.hotkeyButtonList.size(); i++) {
                    HotkeyButton hkb = HotkeyButton.hotkeyButtonList.get(i);

                    if (hkb.bText.getString().toLowerCase().contains(HotkeyGUI.searchField.getText().toLowerCase())) {
                        panel.add(hkb, 0, y, 14, 1);
                        y++;
                    }
                }
                HotkeyGUI.scrollPanel = new WScrollPanel(panel);
                HotkeyGUI.root.add(HotkeyGUI.scrollPanel, 0, 0, 17, 9);
            }
        });
    }

}
