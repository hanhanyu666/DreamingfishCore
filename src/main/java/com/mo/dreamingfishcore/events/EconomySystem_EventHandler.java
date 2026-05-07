package com.mo.dreamingfishcore.events;

import com.mo.dreamingfishcore.EconomySystem;
import com.mo.dreamingfishcore.commands.check_system.Command_Check;
import com.mo.dreamingfishcore.commands.check_system.Command_Info;
import com.mo.dreamingfishcore.commands.tpa_system.Command_Tpa;
import com.mo.dreamingfishcore.core.update_checker_system.UpdateChecker;
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
