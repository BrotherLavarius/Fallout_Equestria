package com.redsparkle.foe.items.guns.entitys.bulletFired;

import com.redsparkle.foe.items.guns.entitys.bulletFired.models.ModelBullet;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * Created by hoijima on 22.06.17.
 */
public class RenderFactoryBullet implements IRenderFactory {
    public RenderFactoryBullet(RenderManager renderManager) {
        createRenderFor(renderManager);
    }

    @Override
    public Render createRenderFor(RenderManager renderManager) {
        return new RenderBullet(renderManager, new ModelBullet(), 0.0D);
    }
}
