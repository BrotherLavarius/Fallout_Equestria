package com.redsparkle.foe.items.guns.entitys.spreadPellet_shotgun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * Created by hoijima on 24.06.17.
 */
public class Pellet extends EntityAbstractPellet {
    public Pellet(World world) {
        super(world);
    }

    public Pellet(World world, EntityLivingBase entity) {
        super(world, entity);
    }
}
