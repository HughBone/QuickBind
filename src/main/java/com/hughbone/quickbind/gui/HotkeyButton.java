package com.hughbone.quickbind.gui;

import fi.dy.masa.malilib.hotkeys.IHotkey;
import io.github.cottonmc.cotton.gui.widget.WButton;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class HotkeyButton extends WButton {

    public Text bText;
    public final KeyBinding keyBinding;
    public final IHotkey malibKeyBinding;
    public static List<HotkeyButton> hotkeyButtonList = new ArrayList<HotkeyButton>();

    public HotkeyButton(Text text, KeyBinding keyBinding, IHotkey malibKeyBinding) {
        super(text);
        this.bText = text;
        this.keyBinding = keyBinding;
        this.malibKeyBinding = malibKeyBinding;
        hotkeyButtonList.add(this);

        this.setOnClick(() -> {
            HotkeyGUI.selectText.setText(this.bText);
            HotkeyGUI.selectedButton = this;
        });
    }

}
