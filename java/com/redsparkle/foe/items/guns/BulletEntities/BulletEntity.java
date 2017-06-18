package com.redsparkle.foe.items.guns.BulletEntities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * Created by hoijima on 18.06.17.
 */
public class BulletEntity extends GenericBulletEntity {
    public BulletEntity(World worldIn) {
        super(worldIn);
    }

    public BulletEntity(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }
}
