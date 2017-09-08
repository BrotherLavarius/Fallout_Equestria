package com.redsparkle.api.Capability.Player.saddlegun_shooting;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Created by hoijima on 30.08.17.
 */
public class ITrigger_Item_Storage implements IStorage<ITrigger_item> {
    public NBTBase writeNBT(Capability<ITrigger_item> capability, ITrigger_item instance, EnumFacing side) {
        return ((ITrigger_item_Provider) instance).serializeNBT();
    }

    public void readNBT(Capability<ITrigger_item> capability, ITrigger_item instance, EnumFacing side, NBTBase nbt) {
        ((ITrigger_item_Provider) instance).deserializeNBT((NBTTagCompound) nbt);
    }
}