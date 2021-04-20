package com.hughbone.quickselect.commands;

import com.hughbone.quickselect.KeyExt;
import com.hughbone.quickselect.datamanager.SaveData;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.server.command.CommandManager;

public class TestCommand {

    public static KeyBinding kb;

    public static void init() {
        // Kills one player pig within 4 blocks of the player
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(CommandManager.literal("test").executes(ctx -> {
            SaveData.save();
            //((KeyExt) kb).pressKey();
            return 1;
        })));
    }

}
