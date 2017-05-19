package com.redsparkle.foe.capa.spechial;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSPECHIAL;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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
    
    public Integer addStreinght(Integer addStreinght) {
        return StreinghtLevel + basicStreinght;
    }

    
    public Integer removeStreinght(Integer removeStreinght) {
        return StreinghtLevel = (StreinghtLevel - removeStreinght + basicStreinght);

    }

    
    public Integer setStreinght(Integer newStreinghtLevel) {
        return StreinghtLevel = (basicStreinght + newStreinghtLevel);
    }

    
    public Integer getStreinght() {
        return StreinghtLevel + basicStreinght;
    }

    /*
    ##################################
    */
    
    public Integer addPerception(Integer addPerception) {
        return PerceptionLevel + basicPerception;
    }

    
    public Integer removePerception(Integer removePerception) {
        return PerceptionLevel = (PerceptionLevel - removePerception + basicPerception);

    }

    
    public Integer setPerception(Integer newPerceptionLevel) {
        return PerceptionLevel = (basicPerception + newPerceptionLevel);
    }

    
    public Integer getPerception() {
        return PerceptionLevel + basicPerception;
    }

    /*
    ##################################
    */
    
    public Integer addEndurance(Integer addEndurance) {
        return EnduranceLevel + basicEndurance;
    }

    
    public Integer removeEndurance(Integer removeEndurance) {
        return EnduranceLevel = (EnduranceLevel - removeEndurance + basicEndurance);

    }

    
    public Integer setEndurance(Integer newEnduranceLevel) {
        return EnduranceLevel = (basicEndurance + newEnduranceLevel);
    }

    
    public Integer getEndurance() {
        return EnduranceLevel + basicEndurance;
    }

    /*
    ##################################
    */
    
    public Integer addCharisma(Integer addCharisma) {
        return CharismaLevel + basicCharisma;
    }

    
    public Integer removeCharisma(Integer removeCharisma) {
        return CharismaLevel = (CharismaLevel - removeCharisma + basicCharisma);

    }

    
    public Integer setCharisma(Integer newCharismaLevel) {
        return CharismaLevel = (basicCharisma + newCharismaLevel);
    }

    
    public Integer getCharisma() {
        return CharismaLevel + basicCharisma;
    }

    /*
    ##################################
    */
    
    public Integer addIntelligence(Integer addIntelligence) {
        return IntelligenceLevel + basicIntelligence;
    }

    
    public Integer removeIntelligence(Integer removeIntelligence) {
        return IntelligenceLevel = (IntelligenceLevel - removeIntelligence + basicIntelligence);

    }

    
    public Integer setIntelligence(Integer newIntelligenceLevel) {
        return IntelligenceLevel = (basicIntelligence + newIntelligenceLevel);
    }

    
    public Integer getIntelligence() {
        return IntelligenceLevel + basicIntelligence;
    }

    /*
    ##################################
    */
    
    public Integer addAgility(Integer addAgility) {
        return AgilityLevel + basicAgility;
    }

    
    public Integer removeAgility(Integer removeAgility) {
        return AgilityLevel = (AgilityLevel - removeAgility + basicAgility);

    }

    
    public Integer setAgility(Integer newAgilityLevel) {
        return AgilityLevel = (basicAgility + newAgilityLevel);
    }

    
    public Integer getAgility() {
        return AgilityLevel + basicAgility;
    }

    /*
    ##################################
    */
    
    public Integer addLuck(Integer addLuck) {
        return LuckLevel + basicLuck;
    }

    
    public Integer removeLuck(Integer removeLuck) {
        return LuckLevel = (LuckLevel - removeLuck + basicLuck);

    }

    
    public Integer setLuck(Integer newLuckLevel) {
        return LuckLevel = (basicLuck + newLuckLevel);
    }

    
    public Integer getLuck() {
        return LuckLevel + basicLuck;
    }

    /*
    ##################################
    */
    
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

    
    public boolean hasCapability(Capability<?> capability,  EnumFacing facing) {
        return capability == SPECHIAL_CAPABILITY;
    }

    
    
    public <T> T getCapability(Capability<T> capability,  EnumFacing facing) {
        return capability == SPECHIAL_CAPABILITY ? (T) this : null;
    }


    
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
