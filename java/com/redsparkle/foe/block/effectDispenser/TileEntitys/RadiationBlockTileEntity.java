package com.redsparkle.foe.block.effectDispenser.TileEntitys;

import com.redsparkle.foe.Init.FOECapabilitiesInit;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MyMessage;
import com.redsparkle.foe.sounds.ModSoundEvents;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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
            System.out.println(entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null).getRadiation());
            entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null).update(entityplayer,getWorld(), TickEvent.Phase.START);
            entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).addRadiation(3);
            entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY,null).update(entityplayer,getWorld(), TickEvent.Phase.END);
            entityplayer.worldObj.playSound(null, k, l, i1, ModSoundEvents.HighEntensityRad, SoundCategory.PLAYERS, 2.0F, 1.0F);
        }

        AxisAlignedBB axisalignedbbd1 = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).expandXyz(d1).addCoord(0.0D, (double)this.worldObj.getHeight(), 0.0D);
        List<EntityPlayerMP> listd1 = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbbd1);
        for (EntityPlayerMP entityplayer : listd1)
        {
            System.out.println(entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).getRadiation());
            entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).update(entityplayer, getWorld(), TickEvent.Phase.START);
            entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).addRadiation(2);
            entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).update(entityplayer, getWorld(), TickEvent.Phase.END);
            entityplayer.worldObj.playSound(null, k, l, i1, ModSoundEvents.MediumEntensityRad, SoundCategory.PLAYERS, 2.0F, 1.0F);


        }
        AxisAlignedBB axisalignedbbd3 = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).expandXyz(d2).addCoord(0.0D, (double)this.worldObj.getHeight(), 0.0D);
        List<EntityPlayerMP> liste2 = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbbd3);
        for (EntityPlayerMP entityplayer : liste2)
        {
            System.out.println(entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).getRadiation());
            entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).update(entityplayer, getWorld(), TickEvent.Phase.START);
            entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).addRadiation(1);
            entityplayer.getCapability(FOECapabilitiesInit.RADIATION_CAPABILITY, null).update(entityplayer, getWorld(), TickEvent.Phase.END);
            entityplayer.worldObj.playSound(null, k, l, i1, ModSoundEvents.LowEntensityRad, SoundCategory.PLAYERS, 2.0F, 1.0F);

        }


    }

}
