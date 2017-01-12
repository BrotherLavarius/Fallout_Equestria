package com.redsparkle.foe.items.guns.inits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable {
    public int damage = 15;

    public EntityBullet(World worldIn) {
        super(worldIn);
    }

    public EntityBullet(World worldIn, EntityLivingBase livingBaseIn) {
        super(worldIn, livingBaseIn);

        this.setThrowableHeading(livingBaseIn.getLookVec().xCoord, livingBaseIn.getLookVec().yCoord, livingBaseIn.getLookVec().zCoord, 3.5f, 0.1f);
    }

    public EntityBullet(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult rayTraceResult) {
        if (!this.world.isRemote) {
            if (rayTraceResult.entityHit != null) {
                rayTraceResult.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
            }

            this.setDead();
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.motionX < 0.001 && this.motionY < 0.001 && this.motionZ < 0.001) {
            this.setDead();
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0F;
    }

}
