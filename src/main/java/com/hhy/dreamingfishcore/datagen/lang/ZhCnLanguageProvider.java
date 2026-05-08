package com.hhy.dreamingfishcore.datagen.lang;

import com.hhy.dreamingfishcore.item.DreamingFishCore_Items;
import com.hhy.dreamingfishcore.utils.Util_MessageKeys;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ZhCnLanguageProvider extends LanguageProvider {
    public ZhCnLanguageProvider(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), locale, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.dreamingfishcore.tab", "DreamingfishCore");
        add("itemGroup.blueprint.tab", "梦鱼蓝图");
        add(DreamingFishCore_Items.DREAMINGFISH.get(), "启程锦鲤");
        add("item.dreamingfishcore.dreamingfish.tooltip", "来自梦鱼服的纪念信物。");
        add(DreamingFishCore_Items.WORMHOLE_POTION.get(), "虫洞药水");
        add(DreamingFishCore_Items.RECALL_POTION.get(), "回忆药水");
        add(DreamingFishCore_Items.BLUEPRINT_ITEM.get(), "蓝图");
        add(DreamingFishCore_Items.FRAGMENT_PAGE.get(), "故事碎片");
        add(DreamingFishCore_Items.STORY_BOOK.get(), "随记本");
        add(DreamingFishCore_Items.EASY_AID_KIT.get(), "简易急救包");
        add(DreamingFishCore_Items.ADVANCED_AID_KIT.get(), "高级急救包");
        add(DreamingFishCore_Items.PROFESSIONAL_AID_KIT.get(), "专业急救包");
        add(DreamingFishCore_Items.REVIVAL_CHARM.get(), "复活护符");
        add(DreamingFishCore_Items.GENE_RESURGENCE_POTION.get(), "基因复苏药剂");
        add(DreamingFishCore_Items.SUPPORTER_HAT.get(), "赞助者帽子");

        add(Util_MessageKeys.TPA_SELF_ERROR, "你不能向自己发送传送请求。");
        add(Util_MessageKeys.TPA_NO_POTION, "你需要一个虫洞药水才能发送传送请求。");
        add(Util_MessageKeys.TPA_REQUEST_SENT, "已向 %s 发送传送请求。");
        add(Util_MessageKeys.TPA_ACCEPT, "点击同意");
        add(Util_MessageKeys.TPA_DENY, "点击拒绝");
        add(Util_MessageKeys.TPA_NO_REQUEST, "你没有待处理的传送请求。");
        add(Util_MessageKeys.TPA_SENDER_OFFLINE, "发送传送请求的玩家已离线。");
        add(Util_MessageKeys.TPA_SENDER_NO_POTION, "%s 没有虫洞药水。");
        add(Util_MessageKeys.TPA_TELEPORTED, "你已被传送到 %s。");
        add(Util_MessageKeys.TPA_ACCEPTED, "你接受了来自 %s 的传送请求。");
        add(Util_MessageKeys.TPA_DENIED, "你拒绝了传送请求。");
        add(Util_MessageKeys.TPA_TIMEOUT_SENDER, "你向 %s 发送的传送请求已超时。");
        add(Util_MessageKeys.TPA_TIMEOUT_TARGET, "%s 的传送请求已超时。");
        add(Util_MessageKeys.RECALL_POTION_ERROR_DIMENSION_NOT_FOUND, "错误：无法找到你的重生维度。");
    }
}
