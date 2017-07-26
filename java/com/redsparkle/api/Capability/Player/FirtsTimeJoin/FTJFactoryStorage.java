package com.redsparkle.api.Capability.Player.FirtsTimeJoin;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
/**
 * Created by hoijima on 09.09.16.
 */
public class FTJFactoryStorage implements IStorage<IFTJCapability> {
    public NBTBase writeNBT(Capability<IFTJCapability> capability, IFTJCapability instance, EnumFacing side) {
        return ((FTJFactoryProvider) instance).serializeNBT();
    }
    public void readNBT(Capability<IFTJCapability> capability, IFTJCapability instance, EnumFacing side, NBTBase nbt) {
        ((FTJFactoryProvider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}
