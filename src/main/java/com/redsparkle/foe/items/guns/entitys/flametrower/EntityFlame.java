package com.redsparkle.foe.items.guns.entitys.flametrower;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by hoijima on 19.06.17.
 */
//https://emxtutorials.wordpress.com/creating-a-gun/
public class EntityFlame extends EntityThrowable {
    public float damage;
    public EnumParticleTypes effect;
    public EntityLivingBase shootingEntity;
    public double x;
    public double y;
    public double z;

    public EntityFlame(World world) {
        super(world);
    }

    public EntityFlame(World worldIn, double x, double y, double z) {
        this(worldIn);
        this.setPosition(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public EntityFlame(World world, EntityLivingBase entity) {
        super(world, entity);
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
        if (ticksExisted > 90) {
            setDead();
        }
        for (int i = 0; i < 10; i++) {
            double x = (double) (rand.nextInt(3) - 5) / 8.0D;
            double y = (double) (rand.nextInt(3) - 5) / 8.0D;
            double z = (double) (rand.nextInt(3) - 5) / 8.0D;
            world.spawnParticle(EnumParticleTypes.FLAME, posX, posY, posZ, x, y, z);
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte p_handleStatusUpdate_1_) {
        if (p_handleStatusUpdate_1_ == 3) {
            for (int lvt_2_1_ = 0; lvt_2_1_ < 8; ++lvt_2_1_) {
                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0.005F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit != null) {
                if (result.entityHit != this.getThrower()) {
                    if (!result.entityHit.isImmuneToFire()) {
                        boolean flag = result.entityHit.attackEntityFrom(DamageSource.causeIndirectDamage(this, this.shootingEntity), 5.0F);
                        if (flag) {
                            this.applyEnchantments(this.shootingEntity, result.entityHit);
                            result.entityHit.setFire(5);
                            world.spawnParticle(EnumParticleTypes.FLAME, posX, posY, posZ, 0, 0, 0);
                        }
                    }
                }
            } else {
                BlockPos blockpos = result.getBlockPos().offset(result.sideHit);
                if (this.world.isAirBlock(blockpos)) {
                    this.world.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
                }
            }
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
