package com.hughbone.quickbind;

import com.hughbone.quickbind.gui.MacroGUI;
import com.hughbone.quickbind.gui.GUIScreen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.Map;

public class Main implements ModInitializer {

    public static Map<String, KeyBinding> keyBindings;

    public static void main(String[] args) {}

    @Override
    public void onInitialize() {
        assignGuiToKey();
    }

    private void assignGuiToKey() {

        // Open quickbind keybind
        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.quickbind.opengui",
                InputUtil.UNKNOWN_KEY.getCode(),
                "quickbind.category" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                MinecraftClient.getInstance().setScreenAndRender(new GUIScreen(new MacroGUI()));
            }
        });
    }

}
