package com.hughbone.quickselect.mixin;

import com.hughbone.quickselect.KeyExt;
import com.hughbone.quickselect.commands.TestCommand;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeyBindingMixin implements KeyExt {

    @Shadow private boolean pressed;
    @Shadow private int timesPressed;

    public void pressKey() {
        this.pressed = true;
        ++this.timesPressed;
    }

    @Inject(method = "<init>(Ljava/lang/String;Lnet/minecraft/client/util/InputUtil$Type;ILjava/lang/String;)V", at = @At("TAIL"))
    public void KeyBinding(String translationKey, InputUtil.Type type, int code, String category, CallbackInfo ci) {
        System.out.println(translationKey);
        if (translationKey.contains("inventory")) {
            TestCommand.kb = (KeyBinding)(Object)this;
        }
    }
}
