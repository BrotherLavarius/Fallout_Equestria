package com.redsparkle.foe.block;

import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;
import com.redsparkle.foe.main;

public class alarm_lamp_TE extends BaseTileEntity {
    protected static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(alarm_lamp_TE.class);

    static {
        alarm_lamp_TE.animHandler.addAnim(main.MODID, "alarm_lamp_active", "alarm_lamp", true);

    }

    public alarm_lamp_TE() {
    }

    @Override
    public AnimationHandler getAnimationHandler() {
        return alarm_lamp_TE.animHandler;
    }


    /**
     * Update method to launch animation of the animated block
     * It always loop, with the checking condition,
     * !this.getAnimationHandler().isAnimationActive()
     */
    @Override
    public void update() {
        super.update();
        if (this.world.isBlockPowered(this.pos) && !this.getAnimationHandler().isAnimationActive(main.MODID, "alarm_lamp_active", this)) {
            this.getAnimationHandler().startAnimation(main.MODID, "alarm_lamp_active", this);
            this.world.getBlockState(this.pos).getBlock().setLightLevel(10);
        } else if (!this.world.isBlockPowered(this.pos) && this.getAnimationHandler().isAnimationActive(main.MODID, "alarm_lamp_active", this)) {
            this.getAnimationHandler().stopAnimation(main.MODID, "alarm_lamp_active", this);
            this.world.getBlockState(this.pos).getBlock().setLightLevel(0);


        }
        if (!this.world.isBlockPowered(this.pos)) {
            this.world.getBlockState(this.pos).getBlock().setLightLevel(0);
        }


    }
}