package io.github.cylindr306.cfsa.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CFSAClient implements ClientModInitializer {
    public static KeyBinding RFS = null;//Reset Flight Speed
    public static KeyBinding OFSA = null;//Open Flight Speed Adjustment
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        RFS = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cfsr.rfs",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.cfsr.main"
        ));
        OFSA = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cfsr.ofsa",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                "category.cfsr.main"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (RFS.wasPressed()){
                if(client.player != null) client.player.getAbilities().setFlySpeed(0.05F);
            }
        });
    }
}
