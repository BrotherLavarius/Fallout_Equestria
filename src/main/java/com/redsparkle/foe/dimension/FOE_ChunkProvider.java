package com.redsparkle.foe.dimension;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by hoijima on 29.09.17.
 */
public class FOE_ChunkProvider implements IChunkGenerator {
    /**
     * Generates the chunk at the specified position, from scratch
     *
     * @param x
     * @param z
     */
    @Override
    public Chunk generateChunk(int x, int z) {
        return null;
    }

    /**
     * Generate initial structures in this chunk, e.g. mineshafts, temples, lakes, and dungeons
     *
     * @param x
     * @param z
     */
    @Override
    public void populate(int x, int z) {

    }

    /**
     * Called to generate additional structures after initial worldgen, used by ocean monuments
     *
     * @param chunkIn
     * @param x
     * @param z
     */
    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    /**
     * Recreates data about structures intersecting given chunk (used for example by getPossibleCreatures), without
     * placing any blocks. When called for the first time before any chunk is generated - also initializes the internal
     * state needed by getPossibleCreatures.
     *
     * @param chunkIn
     * @param x
     * @param z
     */
    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
