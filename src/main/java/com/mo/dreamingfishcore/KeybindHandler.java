package com.mo.dreamingfishcore;

import com.mo.dreamingfishcore.screen.server_screen.ServerInformationDisplay;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = EconomySystem.MODID, value = Dist.CLIENT)
public class KeybindHandler {

    // 创建按键映射，绑定到 "I" 键
    public static final KeyMapping INFORMATION_UI_KEY = new KeyMapping(
            "key.dreamingfishcore.open_screen_o",
            GLFW.GLFW_KEY_O,
            "key.categories.dreamingfishcore"
    );

    // 注册按键绑定到 Minecraft 系统
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(INFORMATION_UI_KEY);
    }

    // 监听按键事件
    @EventBusSubscriber(modid = EconomySystem.MODID, value = Dist.CLIENT)
    public static class KeyInputHandler {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Minecraft mc = Minecraft.getInstance();
            if (INFORMATION_UI_KEY.consumeClick()) {
                ServerInformationDisplay.toggleUI();
                if (mc.player != null) {
                    mc.player.sendSystemMessage(
                        ServerInformationDisplay.isShowUI() ?
                            Component.literal("§a[EconomySystem]信息面板已开启，再次按下O关闭！") :
                            Component.literal("§c[EconomySystem]信息面板已关闭，再次按下O开启！")
                    );
                }
            }
        }
    }
}
