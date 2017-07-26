package com.redsparkle.api.Capability.Player.spechial;

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
            LuckLevel; // real level

    public SpechialFactoryProvider() {
    }

    public static ISpechialCapability instanceFor(EntityPlayer player) {
        return player.getCapability(SPECHIAL_CAPABILITY, null);
    }

    /*
    ##################################
    */


    public Integer setStreinght(Integer newStreinghtLevel) {
        return StreinghtLevel = newStreinghtLevel;
    }


    public Integer getStreinght() {
        return StreinghtLevel;
    }

    /*
    ##################################
    */


    public Integer setPerception(Integer newPerceptionLevel) {
        return PerceptionLevel = newPerceptionLevel;
    }


    public Integer getPerception() {
        return PerceptionLevel;
    }

    /*
    ##################################
    */


    public Integer setEndurance(Integer newEnduranceLevel) {
        return EnduranceLevel = newEnduranceLevel;
    }


    public Integer getEndurance() {
        return EnduranceLevel;
    }

    /*
    ##################################
    */


    public Integer setCharisma(Integer newCharismaLevel) {
        return CharismaLevel = newCharismaLevel;
    }


    public Integer getCharisma() {
        return CharismaLevel;
    }

    /*
    ##################################
    */


    public Integer setIntelligence(Integer newIntelligenceLevel) {
        return IntelligenceLevel = newIntelligenceLevel;
    }


    public Integer getIntelligence() {
        return IntelligenceLevel;
    }

    /*
    ##################################
    */


    public Integer setAgility(Integer newAgilityLevel) {
        return AgilityLevel = newAgilityLevel;
    }


    public Integer getAgility() {
        return AgilityLevel;
    }

    /*
    ##################################
    */


    public Integer setLuck(Integer newLuckLevel) {
        return LuckLevel = newLuckLevel;
    }


    public Integer getLuck() {
        return LuckLevel;
    }

    @Override
    public void setAll(Integer all) {
        setStreinght(all);
        setPerception(all);
        setEndurance(all);
        setCharisma(all);
        setIntelligence(all);
        setAgility(all);
        setLuck(all);
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


    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == SPECHIAL_CAPABILITY;
    }


    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
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
