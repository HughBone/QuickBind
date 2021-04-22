package com.hughbone.quickbind.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.*;


public class MacroGUI extends LightweightGuiDescription {

    private WGridPanel root = new WGridPanel();
    public static WToggleButton configToggle = new WToggleButton(new TranslatableText("Config"));


    public MacroGUI() {
        addButton();
        addConfigToggle();
        addCloseButton();
        setRootPanel(root);

        root.setSize(300, 200);
    }

    private void addConfigToggle() {
        configToggle.setColor(16777215, 16777215);
        root.add(configToggle, 0, 0, 2, 1);
    }

    private void addButton() {
        MacroButton.macroButtonList.clear();
        MacroButton buttona = new MacroButton(new LiteralText("A"), "");
        MacroButton buttonb = new MacroButton(new LiteralText("B"), "");
        MacroButton buttonc = new MacroButton(new LiteralText("C"), "");
        MacroButton buttond = new MacroButton(new LiteralText("D"), "");
        MacroButton button1 = new MacroButton(new LiteralText("1"), "");
        MacroButton button2 = new MacroButton(new LiteralText("2"), "");
        MacroButton button3 = new MacroButton(new LiteralText("3"), "");
        MacroButton button4 = new MacroButton(new LiteralText("4"), "");
        MacroButton button5 = new MacroButton(new LiteralText("5"), "");
        MacroButton button6 = new MacroButton(new LiteralText("6"), "");
        MacroButton button7 = new MacroButton(new LiteralText("7"), "");
        MacroButton button8 = new MacroButton(new LiteralText("8"), "");
        MacroButton button9 = new MacroButton(new LiteralText("9"), "");
        MacroButton button10 = new MacroButton(new LiteralText("10"), "");
        MacroButton button11 = new MacroButton(new LiteralText("11"), "");
        MacroButton button12 = new MacroButton(new LiteralText("12"), "");

        root.add(buttona, 8, 4, 1, 1);
        root.add(buttonb, 9, 5, 1, 1);
        root.add(buttonc, 8, 6, 1, 1);
        root.add(buttond, 7, 5, 1, 1);
        root.add(button1, 7, 1, 1, 1);
        root.add(button2, 9, 1, 1, 1);
        root.add(button3, 11, 2, 1, 1);
        root.add(button4, 12, 4, 1, 1);
        root.add(button5, 12, 6, 1, 1);
        root.add(button6, 11, 8, 1, 1);
        root.add(button7, 9, 9, 1, 1);
        root.add(button8, 7, 9, 1, 1);
        root.add(button9, 5, 8, 1, 1);
        root.add(button10, 4, 6, 1, 1);
        root.add(button11, 4, 4, 1, 1);
        root.add(button12, 5, 2, 1, 1);
    }

    // Change background panel color to transparent black
    @Override
    public void addPainters() {
        super.addPainters();
        this.rootPanel.setBackgroundPainter(BackgroundPainter.createColorful(0x00000000));
    }

    private void addCloseButton() {
        WButton escButton = new WButton(new TranslatableText("x"));
        escButton.setOnClick(() -> MinecraftClient.getInstance().player.closeScreen());
        root.add(escButton, 15, 0, 2, 2);
    }

}
