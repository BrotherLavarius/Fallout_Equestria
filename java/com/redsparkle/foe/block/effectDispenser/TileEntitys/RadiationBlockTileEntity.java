package com.redsparkle.foe.block.effectDispenser.TileEntitys;

import com.redsparkle.api.Capability.Player.rad.RadsFactoryProvider;
import com.redsparkle.foe.Init.SoundInit;
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
        if (this.world.getTotalWorldTime() % 80L == 0L) {
            this.updateRB();
        }
    }
    private void updateRB() {
        if (this.world != null) {
            this.addEffectsToPlayers();
        }
    }
    private void addEffectsToPlayers() {
        double d0 = (double) (this.levels * 10 + 10);
        double d1 = (double) (this.levels * 10 + 5);
        double d2 = (double) (this.levels * 10 + 1);
        int k = this.pos.getX();
        int l = this.pos.getY();
        int i1 = this.pos.getZ();
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB((double) k, (double) l, (double) i1, (double) (k + 1), (double) (l + 1), (double) (i1 + 1))).expandXyz(d0).addCoord(0.0D, (double) this.world.getHeight(), 0.0D);
        List<EntityPlayerMP> list = this.world.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbb);
        for (EntityPlayerMP entityplayer : list) {
            if (entityplayer == null || !entityplayer.capabilities.isCreativeMode) {
                System.out.println(entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation());
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).update(entityplayer, getWorld(), TickEvent.Phase.START);
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).addRadiation(3);
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).update(entityplayer, getWorld(), TickEvent.Phase.END);
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).updateClient(entityplayer);
                entityplayer.world.playSound(null, k, l, i1, SoundInit.highentensity_rad, SoundCategory.PLAYERS, 2.0F, 1.0F);
            }
        }
        AxisAlignedBB axisalignedbbd1 = (new AxisAlignedBB((double) k, (double) l, (double) i1, (double) (k + 1), (double) (l + 1), (double) (i1 + 1))).expandXyz(d1).addCoord(0.0D, (double) this.world.getHeight(), 0.0D);
        List<EntityPlayerMP> listd1 = this.world.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbbd1);
        for (EntityPlayerMP entityplayer : listd1) {
            if (entityplayer == null || !entityplayer.capabilities.isCreativeMode) {
                System.out.println(entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation());
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).update(entityplayer, getWorld(), TickEvent.Phase.START);
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).addRadiation(2);
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).update(entityplayer, getWorld(), TickEvent.Phase.END);
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).updateClient(entityplayer);
                entityplayer.world.playSound(null, k, l, i1, SoundInit.mediumentensity_rad, SoundCategory.PLAYERS, 2.0F, 1.0F);
            }
        }
        AxisAlignedBB axisalignedbbd3 = (new AxisAlignedBB((double) k, (double) l, (double) i1, (double) (k + 1), (double) (l + 1), (double) (i1 + 1))).expandXyz(d2).addCoord(0.0D, (double) this.world.getHeight(), 0.0D);
        List<EntityPlayerMP> liste2 = this.world.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbbd3);
        for (EntityPlayerMP entityplayer : liste2) {
            if (entityplayer == null || !entityplayer.capabilities.isCreativeMode) {
                System.out.println(entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation());
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).update(entityplayer, getWorld(), TickEvent.Phase.START);
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).addRadiation(1);
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).update(entityplayer, getWorld(), TickEvent.Phase.END);
                entityplayer.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).updateClient(entityplayer);
                entityplayer.world.playSound(null, k, l, i1, SoundInit.lowentensity_rad, SoundCategory.PLAYERS, 2.0F, 1.0F);
            }
        }
    }
}
