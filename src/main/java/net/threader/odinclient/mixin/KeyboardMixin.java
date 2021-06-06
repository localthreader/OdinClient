package net.threader.odinclient.mixin;

import net.minecraft.client.Keyboard;
import net.threader.odinclient.OdinClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method = "onKey", at = @At("TAIL"))
    public void onKey(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        OdinClient.INSTANCE.getKeybindManager().onKey(key);
    }
}
