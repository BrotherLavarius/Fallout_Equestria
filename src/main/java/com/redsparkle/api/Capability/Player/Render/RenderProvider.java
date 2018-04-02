package com.redsparkle.api.Capability.Player.Render;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderProvider implements IRenders, ICapabilitySerializable<NBTTagCompound> {
    @CapabilityInject(IRenders.class)
    public static Capability<IRenders> RENDER_CAP = null;

    private Boolean gunRender, armorRender = false;

    public RenderProvider() {
        this(false, false);
    }

    public RenderProvider(Boolean gunRender, Boolean armorRender) {
        this.gunRender = gunRender;
        this.armorRender = armorRender;

    }

    @Override
    public Boolean getGunRender() {
        return gunRender;
    }

    @Override
    public void setGunRender(Boolean status) {
        this.gunRender = status;

    }

    @Override
    public Boolean getArmorRender() {
        return armorRender;
    }

    @Override
    public void setArmorRender(Boolean status) {
        this.armorRender = status;

    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == RENDER_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == RENDER_CAP ? (T) this : null;
    }


    public NBTTagCompound get() {
        return serializeNBT();
    }

    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("gun_render", gunRender);
        nbt.setBoolean("armor_render", armorRender);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setGunRender(nbt.getBoolean("gun_render"));
        setArmorRender(nbt.getBoolean("armor_render"));

    }
}
