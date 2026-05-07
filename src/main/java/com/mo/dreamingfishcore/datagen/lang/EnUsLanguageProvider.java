package com.mo.dreamingfishcore.datagen.lang;

import com.mo.dreamingfishcore.item.EconomySystem_Items;
import com.mo.dreamingfishcore.utils.Util_MessageKeys;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EnUsLanguageProvider extends LanguageProvider {
    public EnUsLanguageProvider(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), locale, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.dreamingfishcore.tab", "DreamingfishCore");
        add("itemGroup.blueprint.tab", "Dreamingfish Blueprints");
        add(EconomySystem_Items.DREAMINGFISH.get(), "Dreaming Fish");
        add("item.dreamingfishcore.dreamingfish.tooltip", "A token from DreamingFish.");
        add(EconomySystem_Items.WORMHOLE_POTION.get(), "Wormhole Potion");
        add(EconomySystem_Items.RECALL_POTION.get(), "Recall Potion");
        add(EconomySystem_Items.BLUEPRINT_ITEM.get(), "Blueprint");
        add(EconomySystem_Items.FRAGMENT_PAGE.get(), "Fragment Page");
        add(EconomySystem_Items.STORY_BOOK.get(), "Story Book");
        add(EconomySystem_Items.EASY_AID_KIT.get(), "Easy Aid Kit");
        add(EconomySystem_Items.ADVANCED_AID_KIT.get(), "Advanced Aid Kit");
        add(EconomySystem_Items.PROFESSIONAL_AID_KIT.get(), "Professional Aid Kit");
        add(EconomySystem_Items.REVIVAL_CHARM.get(), "Revival Charm");
        add(EconomySystem_Items.GENE_RESURGENCE_POTION.get(), "Gene Resurgence Potion");
        add(EconomySystem_Items.SUPPORTER_HAT.get(), "Supporter Hat");

        add(Util_MessageKeys.TPA_SELF_ERROR, "You cannot send a teleport request to yourself.");
        add(Util_MessageKeys.TPA_NO_POTION, "You need a wormhole potion to send a teleport request.");
        add(Util_MessageKeys.TPA_REQUEST_SENT, "Sent a teleport request to %s.");
        add(Util_MessageKeys.TPA_ACCEPT, "Click to accept");
        add(Util_MessageKeys.TPA_DENY, "Click to deny");
        add(Util_MessageKeys.TPA_NO_REQUEST, "You have no pending teleport request.");
        add(Util_MessageKeys.TPA_SENDER_OFFLINE, "The teleport requester is offline.");
        add(Util_MessageKeys.TPA_SENDER_NO_POTION, "%s does not have a wormhole potion.");
        add(Util_MessageKeys.TPA_TELEPORTED, "Teleported to %s.");
        add(Util_MessageKeys.TPA_ACCEPTED, "Accepted %s's teleport request.");
        add(Util_MessageKeys.TPA_DENIED, "Teleport request denied.");
        add(Util_MessageKeys.TPA_TIMEOUT_SENDER, "Your teleport request to %s timed out.");
        add(Util_MessageKeys.TPA_TIMEOUT_TARGET, "%s's teleport request timed out.");
        add(Util_MessageKeys.RECALL_POTION_ERROR_DIMENSION_NOT_FOUND, "Could not find your respawn dimension.");
    }
}
