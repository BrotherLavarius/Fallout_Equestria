package com.redsparkle.foe.items.guns.inits;

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
public class EntityBullet extends EntityThrowable {

    public int damage = 0;

    public EntityBullet(World worldln) {
        super(worldln);
    }

    public EntityBullet(World worldln, double p_i1778_2_, double p_i1778_4_, double p_i1778_4_2) {
        super(worldln, p_i1778_2_, p_i1778_4_, p_i1778_4_2);
    }

    public EntityBullet(World worldln, EntityLivingBase entityLivingBase) {
        super(worldln, entityLivingBase);
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte statByte) {
        if (statByte == 3) {
            for (int lvt_2_1_ = 0; lvt_2_1_ < 8; ++lvt_2_1_) {
                this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }

    }

    @Override
    protected void onImpact(RayTraceResult rayTraceResult) {
        if (rayTraceResult.entityHit != null) {
            byte damage = 0;


            rayTraceResult.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) damage);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.setDead();
        }

    }

}
