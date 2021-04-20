package com.hughbone.quickselect.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.*;


public class ButtonGUI extends LightweightGuiDescription {

    private WGridPanel root = new WGridPanel();
    public static WToggleButton configToggle;

    public ButtonGUI() {
        addButton();
        addConfigToggle();
        addCloseButton();
        setRootPanel(root);

        root.setSize(300, 200);
    }

    private void addConfigToggle() {
        configToggle = new WToggleButton(new TranslatableText("Config"));
        root.add(configToggle, 0, 0, 2, 1);
    }

    private void addButton() {
        HughButton buttona = new HughButton(new LiteralText("A"), "");
        HughButton buttonb = new HughButton(new LiteralText("B"), "");
        HughButton buttonc = new HughButton(new LiteralText("C"), "");
        HughButton buttond = new HughButton(new LiteralText("D"), "");
        HughButton button1 = new HughButton(new LiteralText("1"), "");
        HughButton button2 = new HughButton(new LiteralText("2"), "");
        HughButton button3 = new HughButton(new LiteralText("3"), "");
        HughButton button4 = new HughButton(new LiteralText("4"), "");
        HughButton button5 = new HughButton(new LiteralText("5"), "");
        HughButton button6 = new HughButton(new LiteralText("6"), "");
        HughButton button7 = new HughButton(new LiteralText("7"), "");
        HughButton button8 = new HughButton(new LiteralText("8"), "");
        HughButton button9 = new HughButton(new LiteralText("9"), "");
        HughButton button10 = new HughButton(new LiteralText("10"), "");
        HughButton button11 = new HughButton(new LiteralText("11"), "");
        HughButton button12 = new HughButton(new LiteralText("12"), "");

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
