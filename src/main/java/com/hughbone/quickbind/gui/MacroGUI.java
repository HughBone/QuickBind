package com.hughbone.quickbind.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.*;

import java.util.ArrayList;
import java.util.List;


public class MacroGUI extends LightweightGuiDescription {

    private WGridPanel root = new WGridPanel();
    public static WToggleButton configToggle = new WToggleButton(new TranslatableText("Config"));
    public static List<MacroButton> macroBtnList;

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
        macroBtnList = new ArrayList<>();

        for (int i = 1; i <= 24; i++) {
            MacroButton button = new MacroButton(""+i);
            macroBtnList.add(button);
        }
        addToRoot();

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

    private void addToRoot() {
        // circle
        root.add(macroBtnList.get(0), 7, 1, 1, 1);
        root.add(macroBtnList.get(1), 9, 1, 1, 1);
        root.add(macroBtnList.get(2), 11, 2, 1, 1);
        root.add(macroBtnList.get(3), 12, 4, 1, 1);
        root.add(macroBtnList.get(4), 12, 6, 1, 1);
        root.add(macroBtnList.get(5), 11, 8, 1, 1);
        root.add(macroBtnList.get(6), 9, 9, 1, 1);
        root.add(macroBtnList.get(7), 7, 9, 1, 1);
        root.add(macroBtnList.get(8), 5, 8, 1, 1);
        root.add(macroBtnList.get(9), 4, 6, 1, 1);
        root.add(macroBtnList.get(10), 4, 4, 1, 1);
        root.add(macroBtnList.get(11), 5, 2, 1, 1);
        // inner circle
        root.add(macroBtnList.get(12), 8, 4, 1, 1);
        root.add(macroBtnList.get(13), 9, 5, 1, 1);
        root.add(macroBtnList.get(14), 8, 6, 1, 1);
        root.add(macroBtnList.get(15), 7, 5, 1, 1);
        // left side
        root.add(macroBtnList.get(16), 1, 2, 1, 1);
        root.add(macroBtnList.get(17), 0, 4, 1, 1);
        root.add(macroBtnList.get(18), 0, 6, 1, 1);
        root.add(macroBtnList.get(19), 1, 8, 1, 1);
        // right side
        root.add(macroBtnList.get(20), 15, 2, 1, 1);
        root.add(macroBtnList.get(21), 16, 4, 1, 1);
        root.add(macroBtnList.get(22), 16, 6, 1, 1);
        root.add(macroBtnList.get(23), 15, 8, 1, 1);

    }

}
