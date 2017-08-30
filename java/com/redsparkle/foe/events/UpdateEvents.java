package com.redsparkle.foe.events;

import com.redsparkle.foe.Init.FluidsInit;
import com.redsparkle.foe.Init.PotionInit;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by hoijima on 04.08.17.
 */
public class UpdateEvents {




    @SubscribeEvent
    public void in_Gas(GetCollisionBoxesEvent event) {


        if (event.getEntity() instanceof EntityLivingBase) {

            EntityLivingBase player = (EntityLivingBase) event.getEntity();
            AxisAlignedBB axisalignedbb = event.getEntity().getEntityBoundingBox();
            double d0 = axisalignedbb.maxY + 0.002D;
            int i = MathHelper.floor(axisalignedbb.minX);
            int j = MathHelper.ceil(axisalignedbb.maxX);
            int k = MathHelper.floor(axisalignedbb.maxY);
            int l = MathHelper.ceil(d0);
            int i1 = MathHelper.floor(axisalignedbb.minZ);
            int j1 = MathHelper.ceil(axisalignedbb.maxZ);
            boolean flag = false;
            BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

            try {
                for (int k1 = i; k1 < j; ++k1) {
                    for (int l1 = k; l1 < l; ++l1) {
                        for (int i2 = i1; i2 < j1; ++i2) {
                            blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
                            IBlockState iblockstate = event.getWorld().getBlockState(blockpos$pooledmutableblockpos);

                            if (iblockstate.getBlock() == FluidsInit.PINKCLOUD.getBlock()) {
                                if (iblockstate.getValue(BlockLiquid.LEVEL).intValue() != 0 && !player.getActivePotionEffects().contains(PotionInit.STATICPOISON)) {

                                    player.addPotionEffect(new PotionEffect(PotionInit.STATICPOISON, 100));
                                }
                            }
                        }
                    }
                }
            } finally {
                blockpos$pooledmutableblockpos.release();
            }


        }
    }
}
