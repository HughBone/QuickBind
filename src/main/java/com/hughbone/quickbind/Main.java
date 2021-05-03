package com.hughbone.quickbind;

import com.hughbone.quickbind.gui.MacroGUI;
import com.hughbone.quickbind.gui.GUIScreen;
import fi.dy.masa.malilib.event.InputEventHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.Map;

public class Main implements ModInitializer {

    public static Map<String, KeyBinding> keyBindings;

    public static void main(String[] args) {}

    @Override
    public void onInitialize() {
        assignGuiToKey();
    }

    private void assignGuiToKey() {
        // Currently assigns to the G key
        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.quickbind.opengui", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_G, // The keycode of the key
                "gui.quickbind.buttons" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                MinecraftClient.getInstance().openScreen(new GUIScreen(new MacroGUI()));
            }
        });
    }

}
