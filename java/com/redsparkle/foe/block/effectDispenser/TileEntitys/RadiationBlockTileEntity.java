package com.redsparkle.foe.block.effectDispenser.TileEntitys;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ITickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

/**
 * Created by hoijima desu on 31.07.16 desu.
 */
public class RadiationBlockTileEntity extends TileEntity implements ITickable {
    int collide_rad = 0;
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
        int j = (9 + this.levels * 2) * 20;
        int k = this.pos.getX();
        int l = this.pos.getY();
        int i1 = this.pos.getZ();
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).expandXyz(d0).addCoord(0.0D, (double)this.worldObj.getHeight(), 0.0D);
        List<EntityPlayer> list = this.worldObj.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);
        for (EntityPlayer entityplayer : list)
        {
            entityplayer.addExperience(10);
            collide_rad++;
            System.out.println("+"+collide_rad+"rads");
        }

    }

}
