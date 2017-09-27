package com.redsparkle.api.Capability.Items.Ammo;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/**
 * Created by hoijima on 24.07.17.
 */
public class AmmoFactoryProvider implements IAmmoInterface, ICapabilitySerializable<NBTTagCompound> {
    @CapabilityInject(IAmmoInterface.class)
    public static Capability<IAmmoInterface> AMMO_STORAGE = null;
    public int Ammo, MaxAmmo;
    public AmmoFactoryProvider() {
        this(0, 0);
    }
    public AmmoFactoryProvider(int Ammo, int MaxAmmo) {
        this.MaxAmmo = MaxAmmo;
        this.Ammo = Ammo;
    }
    //    public static IAmmoInterface instanceFor(ItemStack stack) {
//        return  stack.getCapability(AMMO_STORAGE, null);
//    }
    public static IAmmoInterface instanceFor(ItemStack stack) {
        return stack.getCapability(AMMO_STORAGE, null);
    }
    @Override
    public int getMaxAmmo() {
        return MaxAmmo;
    }
    @Override
    public void setMaxAmmo(int Ammo) {
        this.MaxAmmo = Ammo;
    }
    @Override
    public int getAmmo() {
        return Ammo;
    }
    @Override
    public void setAmmo(int Ammo) {
        this.Ammo = Ammo;
    }
    @Override
    public void addAmmo(int Ammo) {
        if (Ammo < MaxAmmo) {
            this.Ammo = this.Ammo + Ammo;
        }
    }

    @Override
    public void setToMax() {
        this.Ammo = MaxAmmo;
    }

    public NBTTagCompound get() {
        return serializeNBT();
    }
    public void set(NBTTagCompound nbt) {
        deserializeNBT(nbt);
    }
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == AMMO_STORAGE;
    }
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == AMMO_STORAGE ? (T) this : null;
    }
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("ammo", Ammo);
        nbt.setInteger("max_ammo", MaxAmmo);
        return nbt;
    }
    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setAmmo(nbt.getInteger("ammo"));
        setMaxAmmo(nbt.getInteger("max_ammo"));
    }
}
