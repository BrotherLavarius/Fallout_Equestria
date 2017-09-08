package com.redsparkle.api.Capability.Player.Inventory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
/**
 * Created by hoijima on 18.07.17.
 */
public class IAdvStorage implements IStorage<IAdvInventory> {
    public NBTBase writeNBT(Capability<IAdvInventory> capability, IAdvInventory instance, EnumFacing side) {
        return ((IAdvProvider) instance).serializeNBT();
    }
    public void readNBT(Capability<IAdvInventory> capability, IAdvInventory instance, EnumFacing side, NBTBase nbt) {
        ((IAdvProvider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}
