package com.redsparkle.foe.items.guns.inits;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * Created by hoijima on 22.06.17.
 */
public class RenderFactoryBullet implements IRenderFactory {
    @Override
    public Render createRenderFor(RenderManager renderManager) {
        return new RenderBullet(renderManager, new ModelBullet(), 0.0D);
    }
}
