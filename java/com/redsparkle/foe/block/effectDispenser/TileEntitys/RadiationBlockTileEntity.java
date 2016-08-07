package com.redsparkle.foe.block.effectDispenser.TileEntitys;

import com.redsparkle.foe.sounds.ModSoundEvents;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

/**
 * Created by hoijima desu on 31.07.16 desu.
 */
public class RadiationBlockTileEntity extends TileEntity implements ITickable {
    int collide_rad=0;
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
            collide_rad++;
            entityplayer.worldObj.playSound(null , k, l, i1, ModSoundEvents.RadMeter, SoundCategory.PLAYERS , 2.0F, 1.0F);
            System.out.println("+"+collide_rad+"rads");

        }

        AxisAlignedBB axisalignedbbd1 = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).expandXyz(d1).addCoord(0.0D, (double)this.worldObj.getHeight(), 0.0D);
        List<EntityPlayerMP> listd1 = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbbd1);
        for (EntityPlayerMP entityplayer : listd1)
        {
            collide_rad=collide_rad+2;
            //entityplayer.worldObj.playSound(null , k, l, i1, ModSoundEvents.RadMeter, SoundCategory.PLAYERS , 2.0F, 1.0F);
            System.out.println("+"+collide_rad+"rads");

        }
        AxisAlignedBB axisalignedbbd3 = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).expandXyz(d2).addCoord(0.0D, (double)this.worldObj.getHeight(), 0.0D);
        List<EntityPlayerMP> liste2 = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbbd3);
        for (EntityPlayerMP entityplayer : liste2)
        {
            collide_rad=collide_rad+3;
            //entityplayer.worldObj.playSound(null , k, l, i1, ModSoundEvents.RadMeter, SoundCategory.PLAYERS , 2.0F, 1.0F);
            System.out.println("+"+collide_rad+"rads");

        }


    }
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.collide_rad = compound.getInteger("Rads");

    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("Rads", collide_rad);

        return compound;
    }
    private void sendToClient(EntityPlayerMP player) {
        final SPacketUpdateTileEntity updatePacket = getUpdatePacket();
        if (updatePacket != null) {
            player.connection.sendPacket(updatePacket);
        }
    }

    private void setSendToClient(boolean b) {
    }


}
