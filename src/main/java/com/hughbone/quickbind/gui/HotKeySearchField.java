package com.hughbone.quickbind.gui;

import io.github.cottonmc.cotton.gui.widget.WTextField;

public class HotKeySearchField extends WTextField {

    private String prevText = "";

    @Override
    public void tick() {
        if (prevText != this.getText()) {
            prevText = this.getText();

            int y = 0;
            for (int i = 0; i < HotkeyButton.hotkeyButtonList.size(); i++) {
                HotkeyButton hkb = HotkeyButton.hotkeyButtonList.get(i);
                HotkeyGUI.gridPanel.remove(hkb);

                if (hkb.bText.getString().toLowerCase().contains(HotkeyGUI.searchField.getText().toLowerCase())) {
                    HotkeyGUI.gridPanel.add(hkb, 0, y, 14, 1);
                    y++;
                }
            }
        }
    }


}
