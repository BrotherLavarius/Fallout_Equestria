package com.redsparkle.foe.items.guns.entitys.bulletFired;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by hoijima on 19.06.17.
 */
//https://emxtutorials.wordpress.com/creating-a-gun/
public class EntityBullet extends EntityThrowable {
    public float damage;
    public EnumParticleTypes effect;

    public EntityBullet(World world) {
        super(world);
    }

    public EntityBullet(World worldIn, double x, double y, double z) {
        this(worldIn);
        this.setPosition(x, y, z);
    }

    public EntityBullet(World world, EntityLivingBase entity) {
        super(world, entity);
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
        if (ticksExisted > 30) {
            setDead();
        }
        for (int i = 0; i < 25; i++) {
            double x = (double) (rand.nextInt(10) - 5) / 8.0D;
            double y = (double) (rand.nextInt(10) - 5) / 8.0D;
            double z = (double) (rand.nextInt(10) - 5) / 8.0D;
            if (effect == null) {
//                effect = EnumParticleTypes.SMOKE_NORMAL;
//                world.spawnParticle(effect, posX, posY, posZ, x, y, z);
            } else {
                world.spawnParticle(effect, posX, posY, posZ, x, y, z);
            }

        }

    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte p_handleStatusUpdate_1_) {
        if (p_handleStatusUpdate_1_ == 3) {
            for (int lvt_2_1_ = 0; lvt_2_1_ < 8; ++lvt_2_1_) {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
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
            if(rayTraceResult.entityHit != this.getThrower()) {
                rayTraceResult.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
                this.setDead();
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.setDead();
        }


    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setEffect(EnumParticleTypes eff) {
        this.effect = eff;
    }
}

