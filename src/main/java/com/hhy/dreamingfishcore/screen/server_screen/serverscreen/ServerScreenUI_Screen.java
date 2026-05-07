package com.hhy.dreamingfishcore.screen.server_screen.serverscreen;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ServerScreenUI_Screen extends Screen {
    private static final String TITLE = "DreamingfishCore";
    private static final String SUBTITLE = "梦屿终端";
    private static final String[] LINES = {
            "> core status: online",
            "> server shell: ready",
            "> economy module: removed",
            "> territory module: removed",
            "> shop module: removed",
            "> press U or ESC to close"
    };

    private final Minecraft mc = Minecraft.getInstance();
    private long openTime;

    public ServerScreenUI_Screen() {
        super(Component.literal(SUBTITLE));
    }

    @Override
    protected void init() {
        super.init();
        openTime = Util.getMillis();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        int panelWidth = Math.min(420, this.width - 32);
        int panelHeight = Math.min(210, this.height - 32);
        int x = (this.width - panelWidth) / 2;
        int y = (this.height - panelHeight) / 2;
        float progress = Math.min(1.0f, (Util.getMillis() - openTime) / 240.0f);
        int revealWidth = Math.max(1, (int) (panelWidth * progress));

        guiGraphics.fill(0, 0, this.width, this.height, 0xAA05080C);
        guiGraphics.fill(x - 2, y - 2, x + panelWidth + 2, y + panelHeight + 2, 0xFF5BA6C8);
        guiGraphics.fill(x, y, x + panelWidth, y + panelHeight, 0xF0101822);
        guiGraphics.fill(x, y, x + revealWidth, y + 2, 0xFF8FD8F7);

        guiGraphics.drawString(mc.font, TITLE, x + 18, y + 18, 0xFF8FD8F7, false);
        guiGraphics.drawString(mc.font, SUBTITLE, x + 18, y + 34, 0xFFE8EDF2, false);
        guiGraphics.fill(x + 18, y + 54, x + panelWidth - 18, y + 55, 0x665BA6C8);

        int lineY = y + 72;
        for (String line : LINES) {
            guiGraphics.drawString(mc.font, line, x + 24, lineY, 0xFFBFD7E4, false);
            lineY += 16;
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == org.lwjgl.glfw.GLFW.GLFW_KEY_U) {
            onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void onClose() {
        if (ServerScreenUI.isShowUI()) {
            ServerScreenUI.toggleUI();
        }
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
