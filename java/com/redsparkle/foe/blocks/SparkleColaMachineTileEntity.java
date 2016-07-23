package com.redsparkle.foe.blocks;

import com.google.common.base.Optional;
import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.model.IModelPart;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.Models;
import net.minecraftforge.common.model.TRSRTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoijima on 09.07.16.
 */
public class SparkleColaMachineTileEntity extends TileEntity
{
    private String customName;
    private final List<String> hidden = new ArrayList<String>();
    public final IModelState state = new IModelState()
    {
        private final Optional<TRSRTransformation> value = Optional.of(TRSRTransformation.identity());

        @Override
        public Optional<TRSRTransformation> apply(Optional<? extends IModelPart> part)
        {
            if(part.isPresent())
            {
                // This whole thing is subject to change, but should do for now.
                UnmodifiableIterator<String> parts = Models.getParts(part.get());
                if(parts.hasNext())
                {
                    String name = parts.next();
                    // only interested in the root level
                    if(!parts.hasNext() && hidden.contains(name))
                    {
                        return value;
                    }
                }
            }
            return Optional.absent();
        }
    };
    public void setCustomName(String name)
    {

        this.customName = "Sparkle Cola Vending machine";
    }
    public int getSizeInventory()
    {

        return 10;
    }
}
