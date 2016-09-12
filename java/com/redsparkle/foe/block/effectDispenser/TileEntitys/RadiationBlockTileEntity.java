package com.redsparkle.foe.block.effectDispenser.TileEntitys;

import com.redsparkle.foe.FOECapabilitiesInit;
import com.redsparkle.foe.capa.IRadiationCapability;
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
    private int levels = 1;



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
            System.out.println(entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null).toString());
            //System.out.println(entityplayer.getCapability(CapabilityRadiation.RADS,UP).toString());
            entityplayer.worldObj.playSound(null , k, l, i1, ModSoundEvents.RadMeter, SoundCategory.PLAYERS , 2.0F, 1.0F);
        }

        AxisAlignedBB axisalignedbbd1 = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).expandXyz(d1).addCoord(0.0D, (double)this.worldObj.getHeight(), 0.0D);
        List<EntityPlayerMP> listd1 = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbbd1);
        for (EntityPlayerMP entityplayer : listd1)
        {
            //entityplayer.worldObj.playSound(null , k, l, i1, ModSoundEvents.RadMeter, SoundCategory.PLAYERS , 2.0F, 1.0F);

        }
        AxisAlignedBB axisalignedbbd3 = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).expandXyz(d2).addCoord(0.0D, (double)this.worldObj.getHeight(), 0.0D);
        List<EntityPlayerMP> liste2 = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbbd3);
        for (EntityPlayerMP entityplayer : liste2)
        {
            //entityplayer.worldObj.playSound(null , k, l, i1, ModSoundEvents.RadMeter, SoundCategory.PLAYERS , 2.0F, 1.0F);

        }


    }

}
