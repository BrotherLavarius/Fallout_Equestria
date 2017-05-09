package com.redsparkle.foe.capa.spechial;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageUpdateClientServerSPECHIAL;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by hoijima on 01.03.17.
 */
public class SpechialFactoryProvider implements ISpechialCapability, ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(ISpechialCapability.class)
    public static Capability<ISpechialCapability> SPECHIAL_CAPABILITY = null;

    private boolean dirty = true;
    private Integer
            basicStreinght, //constant 0 to init
            StreinghtLevel, // real level
            prevStreinghtLevel; // prev before changing

    private Integer
            basicPerception, //constant 0 to init
            PerceptionLevel, // real level
            prevPerceptionLevel; // prev before changing

    private Integer
            basicEndurance, //constant 0 to init
            EnduranceLevel, // real level
            prevEnduranceLevel; // prev before changing

    private Integer
            basicCharisma, //constant 0 to init
            CharismaLevel, // real level
            prevCharismaLevel; // prev before changing

    private Integer
            basicIntelligence, //constant 0 to init
            IntelligenceLevel, // real level
            prevIntelligenceLevel; // prev before changing

    private Integer
            basicAgility, //constant 0 to init
            AgilityLevel, // real level
            prevAgilityLevel; // prev before changing

    private Integer
            basicLuck, //constant 0 to init
            LuckLevel, // real level
            prevLuckLevel; // prev before changing

    public SpechialFactoryProvider() {
        this(0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0);
    }

    public SpechialFactoryProvider(Integer basicStreinght, Integer StreinghtLevel, Integer prevStreinghtLevel,
                                   Integer basicPerception, Integer PerceptionLevel, Integer prevPerceptionLevel,
                                   Integer basicEndurance, Integer EnduranceLevel, Integer prevEnduranceLevel,
                                   Integer basicCharisma, Integer CharismaLevel, Integer prevCharismaLevel,
                                   Integer basicIntelligence, Integer IntelligenceLevel, Integer prevIntelligenceLevel,
                                   Integer basicAgility, Integer AgilityLevel, Integer prevAgilityLevel,
                                   Integer basicLuck, Integer LuckLevel, Integer prevLuckLevel) {
        this.basicStreinght = basicStreinght;
        this.StreinghtLevel = StreinghtLevel;
        this.prevStreinghtLevel = prevStreinghtLevel;
        this.basicPerception = basicPerception;
        this.PerceptionLevel = PerceptionLevel;
        this.prevPerceptionLevel = prevPerceptionLevel;
        this.basicEndurance = basicEndurance;
        this.EnduranceLevel = EnduranceLevel;
        this.prevEnduranceLevel = prevEnduranceLevel;
        this.basicCharisma = basicCharisma;
        this.CharismaLevel = CharismaLevel;
        this.prevCharismaLevel = prevCharismaLevel;
        this.basicIntelligence = basicIntelligence;
        this.IntelligenceLevel = IntelligenceLevel;
        this.prevIntelligenceLevel = prevIntelligenceLevel;
        this.basicAgility = basicAgility;
        this.AgilityLevel = AgilityLevel;
        this.prevAgilityLevel = prevAgilityLevel;
        this.basicLuck = basicLuck;
        this.LuckLevel = LuckLevel;
        this.prevLuckLevel = prevLuckLevel;

    }

    public static ISpechialCapability instanceFor(EntityPlayer player) {
        return player.getCapability(SPECHIAL_CAPABILITY, null);
    }

    /*
    ##################################
    */
    @Override
    public Integer addStreinght(Integer addStreinght) {
        return StreinghtLevel + basicStreinght;
    }

    @Override
    public Integer removeStreinght(Integer removeStreinght) {
        return StreinghtLevel = (StreinghtLevel - removeStreinght + basicStreinght);

    }

    @Override
    public Integer setStreinght(Integer newStreinghtLevel) {
        return StreinghtLevel = (basicStreinght + newStreinghtLevel);
    }

    @Override
    public Integer getStreinght() {
        return StreinghtLevel + basicStreinght;
    }

    /*
    ##################################
    */
    @Override
    public Integer addPerception(Integer addPerception) {
        return PerceptionLevel + basicPerception;
    }

    @Override
    public Integer removePerception(Integer removePerception) {
        return PerceptionLevel = (PerceptionLevel - removePerception + basicPerception);

    }

    @Override
    public Integer setPerception(Integer newPerceptionLevel) {
        return PerceptionLevel = (basicPerception + newPerceptionLevel);
    }

    @Override
    public Integer getPerception() {
        return PerceptionLevel + basicPerception;
    }

    /*
    ##################################
    */
    @Override
    public Integer addEndurance(Integer addEndurance) {
        return EnduranceLevel + basicEndurance;
    }

    @Override
    public Integer removeEndurance(Integer removeEndurance) {
        return EnduranceLevel = (EnduranceLevel - removeEndurance + basicEndurance);

    }

    @Override
    public Integer setEndurance(Integer newEnduranceLevel) {
        return EnduranceLevel = (basicEndurance + newEnduranceLevel);
    }

    @Override
    public Integer getEndurance() {
        return EnduranceLevel + basicEndurance;
    }

    /*
    ##################################
    */
    @Override
    public Integer addCharisma(Integer addCharisma) {
        return CharismaLevel + basicCharisma;
    }

    @Override
    public Integer removeCharisma(Integer removeCharisma) {
        return CharismaLevel = (CharismaLevel - removeCharisma + basicCharisma);

    }

    @Override
    public Integer setCharisma(Integer newCharismaLevel) {
        return CharismaLevel = (basicCharisma + newCharismaLevel);
    }

    @Override
    public Integer getCharisma() {
        return CharismaLevel + basicCharisma;
    }

    /*
    ##################################
    */
    @Override
    public Integer addIntelligence(Integer addIntelligence) {
        return IntelligenceLevel + basicIntelligence;
    }

    @Override
    public Integer removeIntelligence(Integer removeIntelligence) {
        return IntelligenceLevel = (IntelligenceLevel - removeIntelligence + basicIntelligence);

    }

    @Override
    public Integer setIntelligence(Integer newIntelligenceLevel) {
        return IntelligenceLevel = (basicIntelligence + newIntelligenceLevel);
    }

    @Override
    public Integer getIntelligence() {
        return IntelligenceLevel + basicIntelligence;
    }

    /*
    ##################################
    */
    @Override
    public Integer addAgility(Integer addAgility) {
        return AgilityLevel + basicAgility;
    }

    @Override
    public Integer removeAgility(Integer removeAgility) {
        return AgilityLevel = (AgilityLevel - removeAgility + basicAgility);

    }

    @Override
    public Integer setAgility(Integer newAgilityLevel) {
        return AgilityLevel = (basicAgility + newAgilityLevel);
    }

    @Override
    public Integer getAgility() {
        return AgilityLevel + basicAgility;
    }

    /*
    ##################################
    */
    @Override
    public Integer addLuck(Integer addLuck) {
        return LuckLevel + basicLuck;
    }

    @Override
    public Integer removeLuck(Integer removeLuck) {
        return LuckLevel = (LuckLevel - removeLuck + basicLuck);

    }

    @Override
    public Integer setLuck(Integer newLuckLevel) {
        return LuckLevel = (basicLuck + newLuckLevel);
    }

    @Override
    public Integer getLuck() {
        return LuckLevel + basicLuck;
    }

    /*
    ##################################
    */
    @Override
    public void update(EntityPlayer player, World world, TickEvent.Phase phase) {
        // dn if i will ever need it
    }

    public void updateClient(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            if (dirty)
                main.simpleNetworkWrapper.sendTo(new MessageUpdateClientServerSPECHIAL(this), (EntityPlayerMP) player);
            //dirty = false;
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == SPECHIAL_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == SPECHIAL_CAPABILITY ? (T) this : null;
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("STR", StreinghtLevel);
        nbt.setInteger("PER", PerceptionLevel);
        nbt.setInteger("END", EnduranceLevel);
        nbt.setInteger("CHR", CharismaLevel);
        nbt.setInteger("INT", IntelligenceLevel);
        nbt.setInteger("AGI", AgilityLevel);
        nbt.setInteger("LUC", LuckLevel);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setStreinght(nbt.getInteger("STR"));
        setPerception(nbt.getInteger("PER"));
        setEndurance(nbt.getInteger("END"));
        setCharisma(nbt.getInteger("CHR"));
        setIntelligence(nbt.getInteger("INT"));
        setAgility(nbt.getInteger("AGI"));
        setLuck(nbt.getInteger("LUC"));
    }
}
