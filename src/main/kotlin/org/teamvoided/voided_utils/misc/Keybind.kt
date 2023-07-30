package org.teamvoided.voided_utils.misc

import com.mojang.blaze3d.platform.InputUtil
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBind
import org.lwjgl.glfw.GLFW
import org.teamvoided.voided_utils.VoidedUtils
import org.teamvoided.voided_utils.VoidedUtils.MODID

object Keybinds {

    private val configKey: KeyBind = KeyBindingHelper.registerKeyBinding(
        KeyBind(
            "key.$MODID.config",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "category.$MODID.generic"
        )
    )

    fun init() {
        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick
        { if (configKey.wasPressed()) VoidedUtils.LOGGER.info("pain") })
    }
}