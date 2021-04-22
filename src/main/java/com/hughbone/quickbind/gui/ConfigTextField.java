package com.hughbone.quickbind.gui;

import io.github.cottonmc.cotton.gui.widget.WTextField;

public class ConfigTextField extends WTextField {

    public String name;

    ConfigTextField(String name) {
        this.name = name;
        this.setMaxLength(100);


        if (name.equals("name")) {
            this.setText(MacroButton.clickedBtn.getLabel().getString());
        }
        if (name.equals("hovertext")) {
            if (MacroButton.clickedBtn.toolText != null) {
                this.setText(MacroButton.clickedBtn.toolText);
            }
        }
        if (name.equals("color")) {
            this.setText(Integer.toHexString(MacroButton.clickedBtn.buttonColor));
        }
        if (name.equals("chatCommand")) {
            if (MacroButton.clickedBtn.chatCommand != null) {
                this.setText(MacroButton.clickedBtn.chatCommand);
            }
        }

    }
}
