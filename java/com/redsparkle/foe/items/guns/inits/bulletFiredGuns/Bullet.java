package com.redsparkle.foe.items.guns.inits.bulletFiredGuns;

import com.redsparkle.foe.items.guns.BulletEntities.BulletEntity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Bullet extends Item{
    public Bullet() {

        this.setCreativeTab(CreativeTabs.COMBAT);
    }

    public BulletEntity createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        BulletEntity bulletEntity = new BulletEntity(worldIn, shooter);
        return bulletEntity;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player) {
        return false;
    }
}















    //    public int damage = 15;
//
//    public EntityBullet(World worldIn) {
//        super(worldIn);
//    }
//
//
//    public EntityBullet(World worldIn, EntityLivingBase livingBaseIn) {
//        super(worldIn, livingBaseIn);
//    }
//
//    public EntityBullet(World worldIn, double x, double y, double z) {
//        super(worldIn, x, y, z);
//    }
//
//
//    @SideOnly(Side.CLIENT)
//    public void handleStatusUpdate(byte id) {
//        if (id == 3) {
//            for (int i = 0; i < 8; ++i) {
//                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
//            }
//        }
//    }
//
//    /**
//     * Called when this EntityThrowable hits a block or entity.
//     */
//
//    protected void onImpact(RayTraceResult result) {
//        if (result.entityHit != null) {
//            int i = damage;
//
//            if (result.entityHit instanceof EntityBlaze) {
//                i = damage - 2;
//            }
//
//            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) i);
//        }
//
//        if (!this.world.isRemote) {
//            this.world.setEntityState(this, (byte) 3);
//            this.setDead();
//        }
//    }
//
//    @Override
//    protected float getGravityVelocity() {
//        return 0F;
//    }
//}
