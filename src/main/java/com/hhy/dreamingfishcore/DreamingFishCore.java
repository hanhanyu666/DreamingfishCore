package com.hhy.dreamingfishcore;

import com.hhy.dreamingfishcore.enchant.DreamingFishCore_Enchants;
import com.hhy.dreamingfishcore.entity.DreamingFishCore_Entities;
import com.hhy.dreamingfishcore.init.Init;
import com.hhy.dreamingfishcore.item.DreamingFishCore_CreativeTabs;
import com.hhy.dreamingfishcore.item.DreamingFishCore_Items;
import com.hhy.dreamingfishcore.core.npc_system.NpcManager;
import com.hhy.dreamingfishcore.core.playerattributes_system.limb_health_system.LimbDamageConfig;
import com.hhy.dreamingfishcore.loot.DreamingFishCore_LootModifiers;
import com.hhy.dreamingfishcore.network.DreamingFishCore_NetworkManager;
import com.hhy.dreamingfishcore.server.notice.NoticeManager;
import com.hhy.dreamingfishcore.server.notice.PlayerNoticeDataManager;
import com.hhy.dreamingfishcore.sound.DreamingFishCore_Sounds;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DreamingFishCore.MODID)
public class DreamingFishCore {
    public static final boolean isDev = true;
    public static final String MODID = "dreamingfishcore";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DreamingFishCore(IEventBus modEventBus, ModContainer modContainer) {
        // 注册物品
        DreamingFishCore_Items.register(modEventBus);
        // 注册声音
        DreamingFishCore_Sounds.SOUND_EVENTS.register(modEventBus);
        // 注册附魔
        DreamingFishCore_Enchants.register(modEventBus);
        // 注册网络包
        DreamingFishCore_NetworkManager.register(modEventBus);
        // 注册创造物品栏
        DreamingFishCore_CreativeTabs.CREATIVE_TABS.register(modEventBus);
        // 注册实体
        DreamingFishCore_Entities.ENTITIES.register(modEventBus);
        DreamingFishCore_LootModifiers.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        new Init();

        // 初始化公告系统
        NoticeManager.loadFromConfig();
        PlayerNoticeDataManager.init();
        NpcManager.init();

        // 初始化肢体伤害系统
        LimbDamageConfig.init();

        // GeckoLib.initialize();

        // 日志信息
        LOGGER.info("DreamingfishCore Mod Initialized!");
    }
}
