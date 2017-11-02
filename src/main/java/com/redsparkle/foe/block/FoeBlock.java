package com.redsparkle.foe.block;


import com.redsparkle.foe.main;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * Created by hoijima on 28.07.17.
 */
public class FoeBlock extends Block {
    public FoeBlock(final Material material, final MapColor mapColor, final String blockName) {
        super(material, mapColor);
        setBlockName(this, blockName);
    }

    public FoeBlock(final Material materialIn, final String blockName) {
        this(materialIn, materialIn.getMaterialMapColor(), blockName);
    }


    /**
     * Set the registry name of {@code block} to {@code blockName} and the unlocalised name to the full registry name.
     *  @param block     The block
     * @param blockName The block's name
     */
    /**
     * Set the registry name of {@code block} to {@code blockName} and the unlocalised name to the full registry name.
     *
     * @param block     The block
     * @param blockName The block's name
     */
    public static void setBlockName(final Block block, final String blockName) {
        block.setRegistryName(main.MODID, blockName);
        block.setUnlocalizedName(block.getRegistryName().toString());
    }
}
