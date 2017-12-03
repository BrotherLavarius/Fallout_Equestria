package com.redsparkle.api.Capability.Items.Gun;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Created by hoijima on 25.07.17.
 */
public class GunFactoryProvider implements IGunInterface, ICapabilitySerializable<NBTTagCompound> {
    @CapabilityInject(IGunInterface.class)
    public static Capability<IGunInterface> GUN = null;
    public int Ammo, MaxAmmo;
    public boolean ClipInside;

    public GunFactoryProvider() {
        this(0, 0, true);
    }

    public GunFactoryProvider(int ammo, int maxammo, boolean clipInside) {
        this.Ammo = ammo;
        this.MaxAmmo = maxammo;
        this.ClipInside = clipInside;
    }

    public static IGunInterface instanceFor(ItemStack stack) {
        return stack.getCapability(GUN, null);
    }

    @Override
    public void addAmmo(int ammo) {
        this.Ammo = Ammo + ammo;
    }

    @Override
    public void removeAmmo(int ammo) {
        this.Ammo = Ammo - ammo;
    }

    @Override
    public int getAmmo() {
        return Ammo;
    }

    @Override
    public void setAmmo(int ammo) {
        this.Ammo = ammo;
    }

    @Override
    public int getMaxAmmo() {
        return MaxAmmo;
    }

    @Override
    public void setMaxAmmo(int ammo) {
        this.MaxAmmo = ammo;
    }

    @Override
    public void setClipStatus(boolean clipInside) {
        this.ClipInside = clipInside;
    }

    @Override
    public boolean clipInserted() {
        return ClipInside;
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == GUN;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == GUN ? (T) this : null;
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
        nbt.setInteger("ammo", Ammo);
        nbt.setInteger("max_ammo", MaxAmmo);
        nbt.setBoolean("clip_out", ClipInside);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setAmmo(nbt.getInteger("ammo"));
        setMaxAmmo(nbt.getInteger("max_ammo"));
        setClipStatus(nbt.getBoolean("clip_out"));
    }
}
