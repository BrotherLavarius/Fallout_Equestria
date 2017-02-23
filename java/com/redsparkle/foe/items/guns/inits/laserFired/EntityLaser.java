package com.redsparkle.foe.items.guns.inits.laserFired;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by hoijima on 20.01.17.
 */
public class EntityLaser extends EntityThrowable {
    public int damage = 15;

    public EntityLaser(World worldIn) {
        super(worldIn);
    }


    public EntityLaser(World worldIn, EntityLivingBase livingBaseIn) {
        super(worldIn, livingBaseIn);

        this.setThrowableHeading(livingBaseIn.getLookVec().xCoord, livingBaseIn.getLookVec().yCoord, livingBaseIn.getLookVec().zCoord, 0.5f, 3.1f);
    }

    public EntityLaser(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }


    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; ++i) {
                this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, 1);
            }
        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null) {
            int i = damage;

            if (result.entityHit instanceof EntityBlaze) {
                i = damage - 2;
            }
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) i);

        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.setDead();
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0F;
    }
}