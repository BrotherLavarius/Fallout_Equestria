package com.redsparkle.foe.block;

import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;
import com.redsparkle.foe.main;
import net.minecraft.util.EnumParticleTypes;

public class generator_small_TE extends BaseTileEntity {
    protected static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(generator_small_TE.class);

    static {
        generator_small_TE.animHandler.addAnim(main.MODID, "generator_small_active", "generator_small", true);

    }

    public generator_small_TE() {
    }

    @Override
    public AnimationHandler getAnimationHandler() {
        return generator_small_TE.animHandler;
    }


    /**
     * Update method to launch animation of the animated block
     * It always loop, with the checking condition,
     * !this.getAnimationHandler().isAnimationActive()
     */
    @Override
    public void update() {
        super.update();
        if (this.world.isBlockPowered(this.pos) && !this.getAnimationHandler().isAnimationActive(main.MODID, "generator_small_active", this)) {
            this.getAnimationHandler().startAnimation(main.MODID, "generator_small_active", this);

        } else if (!this.world.isBlockPowered(this.pos) && this.getAnimationHandler().isAnimationActive(main.MODID, "generator_small_active", this)) {
            this.getAnimationHandler().stopAnimation(main.MODID, "generator_small_active", this);
        }

        if (this.getAnimationHandler().isAnimationActive(main.MODID, "generator_small_active", this)) {
            if ((int) (Math.random() * 3 + 1) == 2) {
                this.world.spawnParticle(EnumParticleTypes.CLOUD, this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5, 0, 0.05, 0);
            }
        }


    }
}