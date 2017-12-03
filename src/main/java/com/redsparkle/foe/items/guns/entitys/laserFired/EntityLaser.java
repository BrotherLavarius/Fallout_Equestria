package com.redsparkle.foe.items.guns.entitys.laserFired;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by hoijima on 22.06.17.
 */
public class EntityLaser extends EntityThrowable {
    public static boolean plasma;
    public float damage;
    public EnumParticleTypes effect;
    public double x;
    public double y;
    public double z;

    public EntityLaser(World world) {
        super(world);
    }

    public EntityLaser(World worldIn, double x, double y, double z) {
        this(worldIn);
        this.setPosition(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public EntityLaser(World world, EntityLivingBase entity, boolean plasma) {
        super(world, entity);
        EntityLaser.plasma = plasma;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float lvt_1_1_ = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * 57.2957763671875D);
            this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) lvt_1_1_) * 57.2957763671875D);
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }
        if (ticksExisted > 30) {
            setDead();
        }
        for (int i = 0; i < 25; i++) {
            double x = (double) (rand.nextInt(3) - 5) / 8.0D;
            double y = (double) (rand.nextInt(3) - 5) / 8.0D;
            double z = (double) (rand.nextInt(3) - 5) / 8.0D;
            if (!plasma) {
                world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, 1, 0, 0);
                world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, 1, 0, 0);
                world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, 1, 0, 0);
            } else if (plasma) {
                plasma = true;
                world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, posX, posY, posZ, 0, 0, 0);
                world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, posX, posY, posZ, 0, 0, 0);
                world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, posX, posY, posZ, 0, 0, 0);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte p_handleStatusUpdate_1_) {
        if (p_handleStatusUpdate_1_ == 3) {
            for (int lvt_2_1_ = 0; lvt_2_1_ < 8; ++lvt_2_1_) {
                if (!plasma) {
                    this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
                } else if (plasma) {
                    plasma = true;
                    this.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0D);
                }

            }
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0.005F;
    }

    @Override
    protected void onImpact(RayTraceResult rayTraceResult) {
        if (rayTraceResult.entityHit != null) {
            if (rayTraceResult.entityHit != this.getThrower()) {
                rayTraceResult.entityHit.attackEntityFrom(DamageSource.GENERIC, damage);
                //this.setDead();
            }
        }
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            //this.setDead();
        }
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setEffect(EnumParticleTypes eff) {
        this.effect = eff;
    }
}
