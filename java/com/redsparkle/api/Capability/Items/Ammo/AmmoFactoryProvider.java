package com.redsparkle.api.Capability.Items.Ammo;

import net.minecraft.entity.player.EntityPlayer;
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
    public static IAmmoInterface instanceFor(EntityPlayer player) {
        return player.getCapability(AMMO_STORAGE, null);
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

    /**
     * Determines if this object has support for the capability in question on the specific side.
     * The return value of this MIGHT change during runtime if this object gains or looses support
     * for a capability.
     * <p>
     * Example:
     * A Pipe getting a cover placed on one side causing it loose the Inventory attachment function for that side.
     * <p>
     * This is a light weight version of getCapability, intended for metadata uses.
     *
     * @param capability The capability to check
     * @param facing     The Side to check from:
     *                   CAN BE NULL. Null is defined to represent 'internal' or 'self'
     * @return True if this object supports the capability.
     */
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == AMMO_STORAGE;
    }

    /**
     * Retrieves the handler for the capability requested on the specific side.
     * The return value CAN be null if the object does not support the capability.
     * The return value CAN be the same for multiple faces.
     *
     * @param capability The capability to check
     * @param facing     The Side to check from:
     *                   CAN BE NULL. Null is defined to represent 'internal' or 'self'
     * @return The requested capability. Returns null when {@link #hasCapability(Capability, EnumFacing)} would return false.
     */
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == AMMO_STORAGE ? (T) this : null;
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
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setAmmo(nbt.getInteger("ammo"));
        setMaxAmmo(nbt.getInteger("max_ammo"));

    }
}
