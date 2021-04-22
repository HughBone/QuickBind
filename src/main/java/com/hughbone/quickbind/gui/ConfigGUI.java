package com.hughbone.quickbind.gui;

import com.hughbone.quickbind.SaveJson;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WText;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;


public class ConfigGUI extends LightweightGuiDescription {

    public static KeyBinding keyBinding;
    private static ConfigTextField nameTextField;
    private static ConfigTextField hoverTextField;
    private static ConfigTextField colorTextField;
    private static ConfigTextField chatCommandTextField;

    public ConfigGUI(boolean fillPrevForms) {

        WGridPanel root = new WGridPanel();

        root.add(new WText(Text.of("Name: ")).setColor(16777215), 0, 1, 3, 1);
        root.add(new WText(Text.of("Hover Text: ")).setColor(16777215), 0, 3, 3, 1);
        root.add(new WText(Text.of("Color: ")).setColor(16777215), 0, 5, 3, 1);
        root.add(new WText(Text.of("Hotkey: ")).setColor(16777215), 0, 7, 3, 1);
        root.add(new WText(Text.of("Chat Command: ")).setColor(16777215), 0, 9, 3, 1);

        if (!fillPrevForms) {
            nameTextField = new ConfigTextField("name");
            hoverTextField = new ConfigTextField("hovertext");
            colorTextField = new ConfigTextField("color");
            chatCommandTextField = new ConfigTextField("chatCommand");
        }

        root.add(nameTextField, 4, 1, 13, 1);
        root.add(hoverTextField, 4, 3, 13, 1);
        root.add(colorTextField, 4, 5, 13, 1);
        root.add(chatCommandTextField, 4, 9, 13, 1);

        root.add(new NormalButton(Text.of("[Select]")), 4, 7, 3, 1);


        WText keybindText = new WText(Text.of(""));
        keybindText.setColor(16777215);
        if (fillPrevForms) {
            keybindText.setText(Text.of(I18n.translate(keyBinding.getTranslationKey())));
            root.add(keybindText, 8, 7, 8, 1);
        }
        else if (MacroButton.clickedBtn.keyBinding != null) {
            keybindText.setText(Text.of(I18n.translate(MacroButton.clickedBtn.keyBinding.getTranslationKey())));
            root.add(keybindText, 8, 7, 8, 1);
        }


        WButton resetBtn = new WButton(Text.of("RESET"));
        root.add(resetBtn, 0, 11, 3, 2);
        resetBtn.setOnClick(() -> {
            nameTextField.setText(MacroButton.clickedBtn.defaultName);
            hoverTextField.setText("");
            chatCommandTextField.setText("");
            colorTextField.setText("ffffff");
            keyBinding = null;
            keybindText.setText(Text.of(""));
        });

        WButton applyButton = new WButton(Text.of("APPLY"));
        root.add(applyButton, 15, 11, 3,2);
        applyButton.setOnClick(() -> {
            MacroButton.clickedBtn.customName = nameTextField.getText();
            MacroButton.clickedBtn.setLabel(Text.of(nameTextField.getText()));

            MacroButton.clickedBtn.toolText = hoverTextField.getText();
            MacroButton.clickedBtn.chatCommand = chatCommandTextField.getText();
            MacroButton.clickedBtn.keyBinding = keyBinding;

            try {
                MacroButton.clickedBtn.buttonColor = (int)((long)(Long.decode("0x" + colorTextField.getText())));
            } catch (Exception e){
                e.printStackTrace();
            }
            SaveJson.save();
            MinecraftClient.getInstance().openScreen(new GUIScreen(new MacroGUI()));
        });

        setRootPanel(root);
        root.setSize(300, 200);
        rootPanel.validate(this);
    }

    // Change background panel color to transparent black
    @Override
    public void addPainters() {
        super.addPainters();
        this.rootPanel.setBackgroundPainter(BackgroundPainter.createColorful(0x4D000000));
    }

}
