package io.github.cylindr306.cfsa.mixin;

import io.github.cylindr306.cfsa.client.CFSAClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Shadow
    @Final
    private MinecraftClient client;
    @Shadow
    private double eventDeltaVerticalWheel;

    @Inject(method = "onMouseScroll", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Mouse;eventDeltaHorizontalWheel:D", ordinal = 6), cancellable = true)
    public void onMouseScrollInject(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (client.player != null && client.player.isCreative() && CFSAClient.OFSA != null && CFSAClient.OFSA.isPressed()) {
            float l = MathHelper.clamp(this.client.player.getAbilities().getFlySpeed() + (float) ((int) eventDeltaVerticalWheel) * 0.005F, 0.0F, 0.5F);
            client.player.getAbilities().setFlySpeed(l);
            ci.cancel();
        }
    }
}
