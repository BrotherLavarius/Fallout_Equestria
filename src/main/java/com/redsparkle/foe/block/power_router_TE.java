package com.redsparkle.foe.block;

import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.main;

public class power_router_TE extends BaseTileEntity {
    protected static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(power_router_TE.class);

    static {
        power_router_TE.animHandler.addAnim(main.MODID, "power_router_active", GlobalNames.power_router, true);

    }

    public power_router_TE() {
    }

    @Override
    public AnimationHandler getAnimationHandler() {
        return power_router_TE.animHandler;
    }


    /**
     * Update method to launch animation of the animated block
     * It always loop, with the checking condition,
     * !this.getAnimationHandler().isAnimationActive()
     */
    @Override
    public void update() {
        super.update();
        if (this.world.isBlockPowered(this.pos) && !this.getAnimationHandler().isAnimationActive(main.MODID, "power_router_active", this)) {
            this.getAnimationHandler().startAnimation(main.MODID, "power_router_active", this);
            this.world.getBlockState(this.pos).getBlock().setLightLevel(10);
        } else if (!this.world.isBlockPowered(this.pos) && this.getAnimationHandler().isAnimationActive(main.MODID, "power_router_active", this)) {
            this.getAnimationHandler().stopAnimation(main.MODID, "power_router_active", this);
            this.world.getBlockState(this.pos).getBlock().setLightLevel(0);
        }
    }
}