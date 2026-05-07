package com.mo.dreamingfishcore.armor;

import com.mo.dreamingfishcore.EconomySystem;
import com.mo.dreamingfishcore.armor.armors.SupporterHat;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = EconomySystem.MODID, value = Dist.CLIENT)
public class ArmorTickHandler {
    // 每次玩家tick时，检查是否穿戴头盔（仅在客户端）
    @SubscribeEvent
    public static void onPlayerTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player) {
            SupporterHat.checkAndEnableRender(player);
        }
    }
}
