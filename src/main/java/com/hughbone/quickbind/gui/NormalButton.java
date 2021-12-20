package com.hughbone.quickbind.gui;

import io.github.cottonmc.cotton.gui.widget.TooltipBuilder;
import io.github.cottonmc.cotton.gui.widget.WButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class NormalButton extends WButton {

    public NormalButton(Text text) {
        super(text);

        this.setOnClick(() -> {

            if (this.getLabel().getString().equals("[Select]")) {
                MinecraftClient.getInstance().setScreenAndRender(new GUIScreen(new HotkeyGUI()));
            }

        });
    }

    @Override
    public void addTooltip(TooltipBuilder tooltip) {
        if (this.getLabel().getString().equals("←")) {
            tooltip.add(new LiteralText("Go back"));
        }
    }

}
