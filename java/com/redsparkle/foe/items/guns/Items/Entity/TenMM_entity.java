package com.redsparkle.foe.items.guns.Items.Entity;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by hoijima desu on 29.07.16 desu.
 */
public class TenMM_entity extends EntityThrowable {
    public TenMM_entity(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }
}
