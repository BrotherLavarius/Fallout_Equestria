package com.redsparkle.foe.items.guns.Ammo.Entity;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by hoijima desu on 29.07.16 desu.
 */
public class BuckShot_entity extends EntityThrowable {
    public BuckShot_entity(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }
}
