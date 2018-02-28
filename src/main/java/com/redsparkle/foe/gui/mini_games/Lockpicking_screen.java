package com.redsparkle.foe.gui.mini_games;

import com.redsparkle.api.Capability.Player.skills.Skill_names;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.Capability.block.Locks.LockFactoryProvider;
import com.redsparkle.api.Capability.block.Locks.LockInterface;
import com.redsparkle.api.utils.GlobalNames;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class Lockpicking_screen extends GuiScreen {
    final ResourceLocation BG = new ResourceLocation(GlobalNames.Domain,
            "textures/gui/LP_minigame.png");

    public int lockLevel = 0;
    public int lockingSkill = 0;
    public Block lockedBlock = Blocks.AIR;

    //Debug method for common use
    public Lockpicking_screen(EntityPlayer player) {
    }

    public Lockpicking_screen(EntityPlayer player, TileEntity block) {
        lockingSkill = player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getAttribute(Skill_names.LOCK_PICKING.getName());

        if (block.hasCapability(LockFactoryProvider.LOCK_CAPABILITY, null)) {
            LockInterface lockstats = block.getCapability(LockFactoryProvider.LOCK_CAPABILITY, null);

            lockLevel = lockstats.getComplex();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

    }
}

