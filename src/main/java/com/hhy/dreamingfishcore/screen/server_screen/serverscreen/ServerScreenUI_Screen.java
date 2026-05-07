package com.hhy.dreamingfishcore.screen.server_screen.serverscreen;

import com.hhy.dreamingfishcore.client.cache.ClientCacheManager;
import com.hhy.dreamingfishcore.core.playerattributes_system.courage.PlayerCourageManager;
import com.hhy.dreamingfishcore.core.playerattributes_system.infection.PlayerInfectionManager;
import com.hhy.dreamingfishcore.core.playerattributes_system.strength.PlayerStrengthClientSync;
import com.hhy.dreamingfishcore.server.chattitle.PlayerTitleManager;
import com.hhy.dreamingfishcore.server.chattitle.Title;
import com.hhy.dreamingfishcore.server.playerdata.PlayerData;
import com.hhy.dreamingfishcore.server.rank.PlayerRankManager;
import com.hhy.dreamingfishcore.server.rank.Rank;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.PlayerFaceRenderer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class ServerScreenUI_Screen extends Screen {
    private static final int BASE_WIDTH = 640;
    private static final int BASE_HEIGHT = 360;
    private static final int PANEL_BG = 0xE6121B24;
    private static final int PANEL_BG_SOFT = 0xD91B2530;
    private static final int PANEL_BORDER = 0xFF7AA8C7;
    private static final int TEXT = 0xFFE8EDF2;
    private static final int MUTED = 0xFFA7B2BE;
    private static final int ACCENT = 0xFF4FC3F7;

    private static final String[] BUTTON_ICONS = {"👤", "?", "!", "[]", "^", "*", "🎒", "⚙"};
    private static final String[] BUTTON_NAMES = {"个人档案", "新玩家帮助", "梦屿广播", "故事进展", "玩家与排行", "服务器成就", "背包", "设置"};

    private final Minecraft mc = Minecraft.getInstance();
    private final int[][] buttonAreas = new int[BUTTON_NAMES.length][4];
    private long openTime;
    private int selectedIndex = 0;
    private float uiScale = 1.0f;
    private int virtualWidth = BASE_WIDTH;
    private int virtualHeight = BASE_HEIGHT;

    public ServerScreenUI_Screen() {
        super(Component.literal("梦屿终端"));
    }

    @Override
    protected void init() {
        super.init();
        openTime = Util.getMillis();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }

        calculateVirtualSize();
        guiGraphics.fill(RenderType.gui(), 0, 0, this.width, this.height, 0x99050A10);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(uiScale, uiScale, 1.0f);

        int margin = 18;
        int leftWidth = 166;
        int rightWidth = 214;
        int centerX = margin + leftWidth + 14;
        int centerWidth = virtualWidth - margin * 2 - leftWidth - rightWidth - 28;
        int rightX = virtualWidth - margin - rightWidth;
        int panelTop = 18;
        int panelHeight = virtualHeight - 36;

        renderLeftPanel(guiGraphics, margin, panelTop, leftWidth, panelHeight, mouseX, mouseY);
        renderCenterPanel(guiGraphics, centerX, panelTop, centerWidth, panelHeight, player);
        renderRightPanel(guiGraphics, rightX, panelTop, rightWidth, panelHeight, player);
        renderSelectedPage(guiGraphics, centerX, panelTop, centerWidth, panelHeight);

        guiGraphics.pose().popPose();
    }

    private void calculateVirtualSize() {
        uiScale = Math.min((float) width / BASE_WIDTH, (float) height / BASE_HEIGHT);
        if (uiScale <= 0) {
            uiScale = 1.0f;
        }
        virtualWidth = (int) (width / uiScale);
        virtualHeight = (int) (height / uiScale);
    }

    private void renderLeftPanel(GuiGraphics guiGraphics, int x, int y, int width, int height, int mouseX, int mouseY) {
        drawPanel(guiGraphics, x, y, width, height, "DreamingFish");
        guiGraphics.drawString(mc.font, "梦屿终端", x + 14, y + 29, TEXT, false);
        guiGraphics.drawString(mc.font, "v0.1 Private", x + 14, y + 43, MUTED, false);

        int buttonY = y + 68;
        for (int i = 0; i < BUTTON_NAMES.length; i++) {
            int bx = x + 10;
            int by = buttonY + i * 25;
            int bw = width - 20;
            int bh = 21;
            boolean hovered = isVirtualMouseInside(mouseX, mouseY, bx, by, bw, bh);
            boolean selected = i == selectedIndex;
            int bg = selected ? 0x604FC3F7 : hovered ? 0x35FFFFFF : 0x20FFFFFF;
            guiGraphics.fill(RenderType.gui(), bx, by, bx + bw, by + bh, bg);
            guiGraphics.fill(RenderType.gui(), bx, by, bx + 2, by + bh, selected ? ACCENT : 0x55FFFFFF);
            guiGraphics.drawString(mc.font, BUTTON_ICONS[i], bx + 7, by + 6, selected ? 0xFFFFFFFF : ACCENT, false);
            guiGraphics.drawString(mc.font, BUTTON_NAMES[i], bx + 28, by + 6, selected ? 0xFFFFFFFF : TEXT, false);
            if (selected) {
                guiGraphics.drawString(mc.font, "<", bx + bw - 13, by + 6, 0xFFFFFFFF, false);
            }
            buttonAreas[i][0] = bx;
            buttonAreas[i][1] = by;
            buttonAreas[i][2] = bx + bw;
            buttonAreas[i][3] = by + bh;
        }
    }

    private void renderCenterPanel(GuiGraphics guiGraphics, int x, int y, int width, int height, LocalPlayer player) {
        drawPanel(guiGraphics, x, y, width, height, player.getScoreboardName());
        PlayerInfo playerInfo = mc.getConnection() != null ? mc.getConnection().getPlayerInfo(player.getUUID()) : null;
        if (playerInfo != null) {
            PlayerFaceRenderer.draw(guiGraphics, playerInfo.getSkin(), x + width / 2 - 20, y + 54, 40);
        }

        guiGraphics.drawString(mc.font, "幸存者档案", x + 14, y + 29, TEXT, false);
        guiGraphics.drawString(mc.font, player.getUUID().toString().substring(0, 8).toUpperCase(), x + 14, y + 43, MUTED, false);

        int nameWidth = mc.font.width(player.getScoreboardName());
        guiGraphics.drawString(mc.font, player.getScoreboardName(), x + width / 2 - nameWidth / 2, y + 104, 0xFFFFFFFF, false);
        renderVitals(guiGraphics, x + 12, y + 128, width - 24, player);
        renderSurvivalBox(guiGraphics, x + 12, y + height - 76, width - 24, player);
    }

    private void renderRightPanel(GuiGraphics guiGraphics, int x, int y, int width, int height, LocalPlayer player) {
        drawPanel(guiGraphics, x, y, width, height, "状态总览");

        Rank rank = PlayerRankManager.getPlayerRankClient(player);
        Title title = PlayerTitleManager.getPlayerTitleClient(player);
        PlayerData data = ClientCacheManager.getPlayerData(player.getUUID());
        int level = data != null ? data.getLevel() : 1;
        long exp = data != null ? data.getCurrentExperience() : 0;

        int cardY = y + 52;
        drawInfoCard(guiGraphics, x + 12, cardY, width - 24, 31, "Rank", rank.getRankName(), 0xFFFFD166);
        drawInfoCard(guiGraphics, x + 12, cardY + 39, width - 24, 31, "称号", title.getTitleName(), 0xFFB58CFF);
        drawInfoCard(guiGraphics, x + 12, cardY + 78, width - 24, 31, "等级", "Lv." + level + " / " + exp + " exp", 0xFF5BE49B);
        drawInfoCard(guiGraphics, x + 12, cardY + 117, width - 24, 31, "探索", ClientCacheManager.getExploredBiomesCount(player.getUUID()) + " 生物群系", 0xFF4FC3F7);
        drawInfoCard(guiGraphics, x + 12, cardY + 156, width - 24, 31, "蓝图", ClientCacheManager.getUnlockedRecipesCount(player.getUUID()) + " 已解锁", 0xFF8EA7FF);

        guiGraphics.drawString(mc.font, "已移除: 经济 / 领地 / 商店", x + 14, y + height - 34, MUTED, false);
    }

    private void renderSelectedPage(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        int pageX = x + 12;
        int pageY = y + height - 142;
        int pageW = width - 24;
        int pageH = 56;
        guiGraphics.fill(RenderType.gui(), pageX, pageY, pageX + pageW, pageY + pageH, 0x7024313E);
        guiGraphics.fill(RenderType.gui(), pageX, pageY, pageX + 2, pageY + pageH, ACCENT);

        String title = BUTTON_NAMES[selectedIndex];
        String body = switch (selectedIndex) {
            case 1 -> "欢迎来到梦屿。体力、勇气、感染值和等级会影响你的生存状态。";
            case 2 -> "梦屿广播入口已保留，公告数据会继续通过服务器同步。";
            case 3 -> "故事进展与任务系统保留，用于查看服务器共同推进的剧情。";
            case 4 -> "玩家与排行数据保留，但经济相关排行已剥离。";
            case 5 -> "服务器成就入口保留，后续可以接回专属成就页。";
            case 6 -> "点击左侧背包会打开原版背包。";
            case 7 -> "点击左侧设置会打开原版设置。";
            default -> "这里显示你的基础档案、属性状态、探索与蓝图进度。";
        };
        guiGraphics.drawString(mc.font, title, pageX + 10, pageY + 9, 0xFFFFFFFF, false);
        guiGraphics.drawString(mc.font, body, pageX + 10, pageY + 28, MUTED, false);
    }

    private void renderVitals(GuiGraphics guiGraphics, int x, int y, int width, LocalPlayer player) {
        drawBar(guiGraphics, x, y, width, "生命", player.getHealth(), player.getMaxHealth(), 0xFFFF8888);
        drawBar(guiGraphics, x, y + 18, width, "饥饿", player.getFoodData().getFoodLevel(), 20, 0xFFFFCC55);
        drawBar(guiGraphics, x, y + 36, width, "体力", PlayerStrengthClientSync.getCurrentStrengthClient(player), PlayerStrengthClientSync.getMaxStrengthClient(player), 0xFF55E06D);
        drawBar(guiGraphics, x, y + 54, width, "勇气", PlayerCourageManager.getCurrentCourageClient(player), PlayerCourageManager.getMaxCourageClient(player), 0xFFD275FF);
        drawBar(guiGraphics, x, y + 72, width, "感染", PlayerInfectionManager.getCurrentInfectionClient(player), 100, 0xFF87D97C);
    }

    private void renderSurvivalBox(GuiGraphics guiGraphics, int x, int y, int width, LocalPlayer player) {
        boolean infected = ClientCacheManager.isInfected(player.getUUID());
        float respawnPoint = ClientCacheManager.getRespawnPoint(player.getUUID());
        int respawnTimes = ClientCacheManager.getRespawnTimes(player.getUUID());
        int color = infected ? 0xFFFF6666 : 0xFF66FF99;
        guiGraphics.fill(RenderType.gui(), x, y, x + width, y + 54, 0x7024313E);
        guiGraphics.fill(RenderType.gui(), x, y, x + 2, y + 54, color);
        guiGraphics.drawString(mc.font, infected ? "感染者" : "幸存者", x + 10, y + 8, color, false);
        guiGraphics.drawString(mc.font, "分裂次数: " + String.format("%.1f", respawnPoint) + " / 100", x + 10, y + 24, TEXT, false);
        guiGraphics.drawString(mc.font, "可重生约 " + respawnTimes + " 次", x + 10, y + 38, MUTED, false);
    }

    private void drawPanel(GuiGraphics guiGraphics, int x, int y, int width, int height, String title) {
        guiGraphics.fill(RenderType.gui(), x + 4, y + 5, x + width + 4, y + height + 5, 0x66000000);
        guiGraphics.fill(RenderType.gui(), x, y, x + width, y + height, PANEL_BG);
        guiGraphics.fill(RenderType.gui(), x, y, x + width, y + 2, PANEL_BORDER);
        guiGraphics.fill(RenderType.gui(), x, y + height - 2, x + width, y + height, PANEL_BORDER);
        guiGraphics.fill(RenderType.gui(), x, y, x + 2, y + height, PANEL_BORDER);
        guiGraphics.fill(RenderType.gui(), x + width - 2, y, x + width, y + height, PANEL_BORDER);
        guiGraphics.fill(RenderType.gui(), x + 12, y + 58, x + width - 12, y + 59, 0x667AA8C7);
        guiGraphics.drawString(mc.font, title, x + 14, y + 12, ACCENT, false);
    }

    private void drawInfoCard(GuiGraphics guiGraphics, int x, int y, int width, int height, String label, String value, int color) {
        guiGraphics.fill(RenderType.gui(), x, y, x + width, y + height, PANEL_BG_SOFT);
        guiGraphics.fill(RenderType.gui(), x, y, x + 3, y + height, color);
        guiGraphics.drawString(mc.font, label, x + 9, y + 6, MUTED, false);
        guiGraphics.drawString(mc.font, value == null ? "" : value, x + 55, y + 6, TEXT, false);
    }

    private void drawBar(GuiGraphics guiGraphics, int x, int y, int width, String label, float current, float max, int color) {
        if (max <= 0) {
            max = 1;
        }
        float pct = Math.max(0.0f, Math.min(1.0f, current / max));
        guiGraphics.drawString(mc.font, label, x, y, MUTED, false);
        int barX = x + 34;
        int barY = y + 3;
        int barW = width - 86;
        guiGraphics.fill(RenderType.gui(), barX, barY, barX + barW, barY + 6, 0x55FFFFFF);
        guiGraphics.fill(RenderType.gui(), barX, barY, barX + (int) (barW * pct), barY + 6, color);
        String value = String.format("%.0f/%.0f", current, max);
        guiGraphics.drawString(mc.font, value, x + width - mc.font.width(value), y, TEXT, false);
    }

    private boolean isVirtualMouseInside(int mouseX, int mouseY, int x, int y, int width, int height) {
        double vx = mouseX / uiScale;
        double vy = mouseY / uiScale;
        return vx >= x && vx <= x + width && vy >= y && vy <= y + height;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        double vx = mouseX / uiScale;
        double vy = mouseY / uiScale;
        for (int i = 0; i < buttonAreas.length; i++) {
            int[] area = buttonAreas[i];
            if (vx >= area[0] && vx <= area[2] && vy >= area[1] && vy <= area[3]) {
                if (i == 6) {
                    this.onClose();
                    mc.setScreen(new net.minecraft.client.gui.screens.inventory.InventoryScreen(mc.player));
                } else if (i == 7) {
                    this.onClose();
                    mc.setScreen(new net.minecraft.client.gui.screens.options.OptionsScreen(mc.screen, mc.options));
                } else {
                    selectedIndex = i;
                }
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_U) {
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
