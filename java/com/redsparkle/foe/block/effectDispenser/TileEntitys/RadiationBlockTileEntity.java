package com.redsparkle.foe.block.effectDispenser.TileEntitys;

import com.redsparkle.foe.capabilities.BaseRadContainer;
import com.redsparkle.foe.capabilities.CapabilityRadiation;
import com.redsparkle.foe.sounds.ModSoundEvents;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;

import java.util.List;

/**
 * Created by hoijima desu on 31.07.16 desu.
 */
public class RadiationBlockTileEntity extends TileEntity implements ITickable {
    int collide_rad;
    private int levels = 1;

    private BaseRadContainer container;

    public RadiationBlockTileEntity() {

        // Initializes the container. Very straight forward.
        this.container = new BaseRadContainer();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {

        super.readFromNBT(compound);

        // It is important for the rads being stored to be persistent. The BaseTeslaContainer
        // includes a method to make reading one from a compound tag very easy. This method is
        // completely optional though, you can handle saving however you prefer. You could even
        // choose not to, but then power won't be saved when you close the game.
        this.container = new BaseRadContainer(compound.getCompoundTag("RadsContainer"));
    }


    @Override
    public NBTTagCompound writeToNBT (NBTTagCompound compound) {

        // It is important for the rads being stored to be persistent. The BaseTeslaContainer
        // includes a method to make writing one to a compound tag very easy. This method is
        // completely optional though, you can handle saving however you prefer. You could even
        // choose not to, but then power won't be saved when you close the game.
        compound.setTag("RadsContainer", this.container.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {

        // This method is where other things will try to access your TileEntity's Rads
        // capability. In the case of the analyzer, is a consumer, producer and holder so we
        // can allow requests that are looking for any of those things. This example also does
        // not care about which side is being accessed, however if you wanted to restrict which
        // side can be used, for example only allow power input through the back, that could be
        // done here.
        if (capability == CapabilityRadiation.CAPABILITY_RADS )
            return (T) this.container;

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {

        // This method replaces the instanceof checks that would be used in an interface based
        // system. It can be used by other things to see if the TileEntity uses a capability or
        // not. This example is a Consumer, Producer and Holder, so we return true for all
        // three. This can also be used to restrict access on certain sides, for example if you
        // only accept power input from the bottom of the block, you would only return true for
        // Consumer if the facing parameter was down.
        if (capability == CapabilityRadiation.CAPABILITY_RADS)
            return true;

        return super.hasCapability(capability, facing);
    }

    public void update() {
        if (this.worldObj.getTotalWorldTime() % 80L == 0L)
        {
            this.updateRB();
        }
    }


    private void updateRB() {
        if (this.worldObj != null)
        {
            this.addEffectsToPlayers();
        }
    }


    private void addEffectsToPlayers() {
        double d0 = (double)(this.levels * 10 + 10);
        double d1 = (double)(this.levels * 10 + 5);
        double d2 = (double)(this.levels * 10 + 1);
        int k = this.pos.getX();
        int l = this.pos.getY();
        int i1 = this.pos.getZ();
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).expandXyz(d0).addCoord(0.0D, (double)this.worldObj.getHeight(), 0.0D);
        List<EntityPlayerMP> list = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbb);
        for (EntityPlayerMP entityplayer : list)
        {
            //System.out.println(entityplayer.getCapability(CapabilityRadiation.RADS,UP).toString());
            entityplayer.worldObj.playSound(null , k, l, i1, ModSoundEvents.RadMeter, SoundCategory.PLAYERS , 2.0F, 1.0F);
        }

        AxisAlignedBB axisalignedbbd1 = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).expandXyz(d1).addCoord(0.0D, (double)this.worldObj.getHeight(), 0.0D);
        List<EntityPlayerMP> listd1 = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbbd1);
        for (EntityPlayerMP entityplayer : listd1)
        {
            //entityplayer.worldObj.playSound(null , k, l, i1, ModSoundEvents.RadMeter, SoundCategory.PLAYERS , 2.0F, 1.0F);
            System.out.println("+"+collide_rad+"rads");

        }
        AxisAlignedBB axisalignedbbd3 = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).expandXyz(d2).addCoord(0.0D, (double)this.worldObj.getHeight(), 0.0D);
        List<EntityPlayerMP> liste2 = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbbd3);
        for (EntityPlayerMP entityplayer : liste2)
        {
            int collide_rad = 0 + 3;
            //entityplayer.worldObj.playSound(null , k, l, i1, ModSoundEvents.RadMeter, SoundCategory.PLAYERS , 2.0F, 1.0F);
            System.out.println("+"+collide_rad+"rads");

        }


    }

}
