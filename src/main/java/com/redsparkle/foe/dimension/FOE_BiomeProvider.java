package com.redsparkle.foe.dimension;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoijima on 29.09.17.
 */
public class FOE_BiomeProvider extends BiomeProvider {

    private GenLayer genBiomes;
    /** A GenLayer containing the indices into Biome.biomeList[] */
    private GenLayer biomeIndexLayer;
    /** The BiomeCache object for this world. */
    private BiomeCache biomeCache;
    /** A list of biomes that the player can spawn in. */
    private List<Biome> biomesToSpawnIn;



    /**
     * Gets the list of valid biomes for the player to spawn in.
     */
    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return this.biomesToSpawnIn;
    }

    /**
     * Returns the Biome related to the x, z position on the world.
     */

    public Biome getBiomeGenerator(BlockPos pos) {
        return this.getBiomeGenerator(pos, (Biome) null);
    }

    public Biome getBiomeGenerator(BlockPos pos, Biome biomeGenBaseIn) {
        return this.biomeCache.getBiome(pos.getX(), pos.getZ(), biomeGenBaseIn);
    }

    /**
     * Return an adjusted version of a given temperature based on the y
     * height
     */
    @Override
    @SideOnly(Side.CLIENT)
    public float getTemperatureAtHeight(float par1, int par2) {
        return par1;
    }

    /**
     * Returns an array of biomes for the location input.
     */
    @Override
    public Biome[] getBiomesForGeneration(Biome[] par1ArrayOfBiome, int par2, int par3, int par4, int par5) {
        IntCache.resetIntCache();

        if (par1ArrayOfBiome == null || par1ArrayOfBiome.length < par4 * par5) {
            par1ArrayOfBiome = new Biome[par4 * par5];
        }

        int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);

        try {
            for (int i = 0; i < par4 * par5; ++i) {
                par1ArrayOfBiome[i] = Biome.getBiome(aint[i]);
            }

            return par1ArrayOfBiome;
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
            crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(par1ArrayOfBiome.length));
            crashreportcategory.addCrashSection("x", Integer.valueOf(par2));
            crashreportcategory.addCrashSection("z", Integer.valueOf(par3));
            crashreportcategory.addCrashSection("w", Integer.valueOf(par4));
            crashreportcategory.addCrashSection("h", Integer.valueOf(par5));
            throw new ReportedException(crashreport);
        }
    }

    /**
     * Returns biomes to use for the blocks and loads the other data like
     * temperature and humidity onto the WorldChunkManager Args:
     * oldBiomeList, x, z, width, depth
     */
    @Override
    public Biome[] getBiomes(Biome[] oldBiomeList, int x, int z, int width, int depth) {
        return this.getBiomes(oldBiomeList, x, z, width, depth, true);
    }

    /**
     * Return a list of biomes for the specified blocks. Args: listToReuse,
     * x, y, width, length, cacheFlag (if false, don't check biomeCache to
     * avoid infinite loop in BiomeCacheBlock)
     */
    @Override
    public Biome[] getBiomes(Biome[] listToReuse, int x, int y, int width, int length, boolean cacheFlag) {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length) {
            listToReuse = new Biome[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (y & 15) == 0) {
            Biome[] aBiome1 = this.biomeCache.getCachedBiomes(x, y);
            System.arraycopy(aBiome1, 0, listToReuse, 0, width * length);
            return listToReuse;
        } else {
            int[] aint = this.biomeIndexLayer.getInts(x, y, width, length);

            for (int i = 0; i < width * length; ++i) {
                listToReuse[i] = Biome.getBiome(aint[i]);
            }
            return listToReuse;
        }
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    @Override
    @SuppressWarnings("rawtypes")
    public boolean areBiomesViable(int x, int y, int z, List par4List) {
        IntCache.resetIntCache();
        int l = x - z >> 2;
        int i1 = y - z >> 2;
        int j1 = x + z >> 2;
        int k1 = y + z >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.getInts(l, i1, l1, i2);

        try {
            for (int j2 = 0; j2 < l1 * i2; ++j2) {
                Biome biome = Biome.getBiome(aint[j2]);

                if (!par4List.contains(biome)) {
                    return false;
                }
            }

            return true;
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(y));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(z));
            crashreportcategory.addCrashSection("allowed", par4List);
            throw new ReportedException(crashreport);
        }
    }

    public static class GenLayerFix extends GenLayer {

        public GenLayerFix(long seed) {
            super(seed);
        }

        public static GenLayer[] makeTheWorld(long seed, WorldType type) {
            GenLayer biomes = new GenLayerBiomesCustom(1L);
            biomes = new GenLayerZoom(1000L, biomes);
            biomes = new GenLayerZoom(1001L, biomes);
            biomes = new GenLayerZoom(1002L, biomes);
            biomes = new GenLayerZoom(1003L, biomes);
            biomes = new GenLayerZoom(1004L, biomes);
            biomes = new GenLayerZoom(1005L, biomes);
            GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
            biomes.initWorldGenSeed(seed);
            genlayervoronoizoom.initWorldGenSeed(seed);
            return new GenLayer[]{biomes, genlayervoronoizoom};
        }

        @Override
        public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
            return null;
        }
    }
    public static class GenLayerBiomesCustom extends GenLayer {

        protected Biome[] allowedBiomes = {Biome.REGISTRY.getObject(new ResourceLocation("hell")),
                Biome.REGISTRY.getObject(new ResourceLocation("forest")),};

        public GenLayerBiomesCustom(long seed) {
            super(seed);
        }

        public GenLayerBiomesCustom(long seed, GenLayer genlayer) {
            super(seed);
            this.parent = genlayer;
        }

        @Override
        public int[] getInts(int x, int z, int width, int depth) {
            int[] dest = IntCache.getIntCache(width * depth);
            for (int dz = 0; dz < depth; dz++) {
                for (int dx = 0; dx < width; dx++) {
                    this.initChunkSeed(dx + x, dz + z);
                    dest[(dx + dz * width)] = Biome.getIdForBiome(this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
                }
            }
            return dest;
        }
    }
}
