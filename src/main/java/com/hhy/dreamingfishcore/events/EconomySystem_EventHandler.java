package com.hhy.dreamingfishcore.events;

import com.hhy.dreamingfishcore.EconomySystem;
import com.hhy.dreamingfishcore.commands.check_system.Command_Check;
import com.hhy.dreamingfishcore.commands.check_system.Command_Info;
import com.hhy.dreamingfishcore.commands.tpa_system.Command_Tpa;
import com.hhy.dreamingfishcore.core.update_checker_system.UpdateChecker;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@EventBusSubscriber(modid = EconomySystem.MODID)
public class EconomySystem_EventHandler {

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        Command_Tpa.register(event.getServer().getCommands().getDispatcher());
        Command_Info.register(event.getServer().getCommands().getDispatcher());
        Command_Check.register(event.getServer().getCommands().getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            UpdateChecker.checkForUpdates(serverPlayer);
        }
    }
}
