package com.hughbone.quickselect.gui;

import com.hughbone.quickselect.KeyExt;
import com.hughbone.quickselect.commands.TestCommand;
import io.github.cottonmc.cotton.gui.widget.TooltipBuilder;
import io.github.cottonmc.cotton.gui.widget.WButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.text.LiteralText;

public class HughButton extends WButton {

    public String toolText;
    private KeyBinding keyBinding;

    public HughButton(LiteralText test, String hoverText) {
        super(test);
        this.toolText = hoverText;
        addTooltip(new TooltipBuilder());

        this.setOnClick(() -> {
            if (ButtonGUI.configToggle.getToggle()) {
                this.keyBinding = TestCommand.kb;
                this.toolText = TestCommand.kb.getTranslationKey();
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

}
