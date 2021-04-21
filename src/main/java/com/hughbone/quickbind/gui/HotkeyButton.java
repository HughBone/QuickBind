package com.hughbone.quickbind.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class HotkeyButton extends WButton {

    public Text bText;
    public final KeyBinding keyBinding;
    public static List<HotkeyButton> hotkeyButtonList = new ArrayList<HotkeyButton>();

    public HotkeyButton(Text text, KeyBinding keyBinding) {
        super(text);
        this.bText = text;
        this.keyBinding = keyBinding;
        hotkeyButtonList.add(this);

        this.setOnClick(() -> {
            HotkeyGUI.selectText.setText(this.bText);
            HotkeyGUI.selectedButton = this;
        });
    }

}
