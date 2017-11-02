package com.redsparkle.foe.items.guns.entitys.bass;

import com.redsparkle.foe.items.guns.entitys.laserFired.models.ModelLaser;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * Created by hoijima on 22.06.17.
 */
public class RenderFactoryBass implements IRenderFactory {
    public RenderFactoryBass(RenderManager renderManager) {
        createRenderFor(renderManager);
    }

    @Override
    public Render createRenderFor(RenderManager renderManager) {
        return new RenderBass(renderManager, new ModelLaser(), 0.0D);
    }
}
