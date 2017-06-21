package com.redsparkle.foe.items.guns.inits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by hoijima on 19.06.17.
 */
//https://emxtutorials.wordpress.com/creating-a-gun/
public class EntityBullet extends EntityThrowable {
    public static final float explosionPower = 0.75F;
    public static final int empRadius = 4;

    public EntityBullet(World world) {
        super(world);
    }

    public EntityBullet(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    private void explode() {
        int bx = (int) posX;
        int by = (int) posY;
        int bz = (int) posZ;
        world.createExplosion(this, posX, posY, posZ, 0.75F, true);
        world.spawnParticle(EnumParticleTypes.getByName("dragonbreath"), posX, posY, posZ, 0, 0, 0);
        //world.spawnParticle(EnumParticleTypes.getByName("largesmoke"), posX, posY, posZ, posX, posY, posZ);

        setDead();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (ticksExisted > 20) {
            explode();
        }

//        for (int i = 0; i < 10; i++)
//        {
//            double x = (double)(rand.nextInt(10) - 5) / 8.0D;
//            double y = (double)(rand.nextInt(10) - 5) / 8.0D;
//            double z = (double)(rand.nextInt(10) - 5) / 8.0D;
//            //world.spawnParticle(EnumParticleTypes.getByName("reddust"), posX, posY, posZ, posX, posY, posZ);
//        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0.005F;
    }

    @Override
    protected void onImpact(RayTraceResult rayTraceResult) {
        explode();
    }


}