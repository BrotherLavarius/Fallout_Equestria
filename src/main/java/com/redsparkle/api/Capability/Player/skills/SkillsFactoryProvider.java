package com.redsparkle.api.Capability.Player.skills;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSkills;
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
public class SkillsFactoryProvider implements ISkillsCapability, ICapabilitySerializable<NBTTagCompound> {
    @CapabilityInject(ISkillsCapability.class)
    public static Capability<ISkillsCapability> SKILLS_CAPABILITY = null;
    private boolean dirty = true;
    private Integer
            Magic,
            Melee_Weapons,
            Firearms,
            EneryWeapons,
            Saddlebag_Guns,
            Explosives,
            Repair,
            Medicine,
            Lockpicking,
            Science,
            Sneak,
            Barter,
            Survival;

    public SkillsFactoryProvider() {
        this(
                0,
                0,
                0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public SkillsFactoryProvider(int magic, int melee, int firearms, int eneryWeapons, int saddlebag_Guns, int explosives, int repair, int medicine, int lockpicking,
                                 int science, int sneak, int barter, int survival) {
        this.Magic = magic;
        this.Melee_Weapons = melee;
        this.Firearms = firearms;
        this.EneryWeapons = eneryWeapons;
        this.Saddlebag_Guns = saddlebag_Guns;
        this.Explosives = explosives;
        this.Repair = repair;
        this.Medicine = medicine;
        this.Lockpicking = lockpicking;
        this.Science = science;
        this.Sneak = sneak;
        this.Barter = barter;
        this.Survival = survival;
    }

    public static ISkillsCapability instanceFor(EntityPlayer player) {
        return player.getCapability(SKILLS_CAPABILITY, null);
    }

    @Override
    public Integer getMagic() {
        return Magic;
    }

    /* ################################## */
    @Override
    public void setMagic(Integer newMagic) {
        this.Magic = newMagic;
    }

    @Override
    public Integer getMelee() {
        return Melee_Weapons;
    }

    @Override
    public void setMelee(Integer newMelee) {
        this.Melee_Weapons = newMelee;
    }

    @Override
    public Integer getFirearms() {
        return Firearms;
    }

    @Override
    public void setFirearms(Integer newFirearms) {
        this.Firearms = newFirearms;
    }

    @Override
    public Integer getEnergyWeapons() {
        return EneryWeapons;
    }

    @Override
    public void setEnergyWeapons(Integer newEnergyWeapons) {
        this.EneryWeapons = newEnergyWeapons;
    }

    @Override
    public Integer getSaddlebag_guns() {
        return Saddlebag_Guns;
    }

    @Override
    public void setSaddlebag_guns(Integer newSaddlebag_guns) {
        this.Saddlebag_Guns = newSaddlebag_guns;
    }

    @Override
    public Integer getExplosives() {
        return Explosives;
    }

    @Override
    public void setExplosives(Integer newExplosives) {
        this.Explosives = newExplosives;
    }

    @Override
    public Integer getRepair() {
        return Repair;
    }

    @Override
    public void setRepair(Integer newRepair) {
        this.Repair = newRepair;
    }

    @Override
    public Integer getMedicine() {
        return Medicine;
    }

    @Override
    public void setMedicine(Integer newMedicine) {
        this.Medicine = newMedicine;
    }

    @Override
    public Integer getLockpick() {
        return Lockpicking;
    }

    @Override
    public void setLockpick(Integer newLockpick) {
        this.Lockpicking = newLockpick;
    }

    @Override
    public Integer getScience() {
        return Science;
    }

    @Override
    public void setScience(Integer newScience) {
        this.Science = newScience;
    }

    @Override
    public Integer getSneak() {
        return Sneak;
    }

    @Override
    public void setSneak(Integer newSneak) {
        this.Sneak = newSneak;
    }

    @Override
    public Integer getBarter() {
        return Barter;
    }

    @Override
    public void setBarter(Integer newBarter) {
        this.Barter = newBarter;
    }

    @Override
    public Integer getSurvival() {
        return Survival;
    }

    @Override
    public void setSurvival(Integer newSurvival) {
        this.Survival = newSurvival;
    }

    @Override
    public void setAll(Integer all) {
        /**
         Magic
         Melee Weapons
         Firearms
         EneryWeapons
         Saddlebag_Guns
         Explosives
         Repair
         Medicine
         Lockpicking
         Science
         Sneak
         Barter
         Survival
         */
        setMagic(all);
        setMelee(all);
        setFirearms(all);
        setEnergyWeapons(all);
        setSaddlebag_guns(all);
        setExplosives(all);
        setRepair(all);
        setMedicine(all);
        setLockpick(all);
        setScience(all);
        setSneak(all);
        setBarter(all);
        setSurvival(all);
    }

    /* ################################## */
    public void update(EntityPlayer player, World world, TickEvent.Phase phase) {
        // dn if i will ever need it
    }

    public void updateClient(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            if (dirty)
                main.simpleNetworkWrapper.sendTo(new MessageUpdateClientServerSkills(this), (EntityPlayerMP) player);
            //dirty = false;
        }
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == SKILLS_CAPABILITY;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == SKILLS_CAPABILITY ? (T) this : null;
    }

    public NBTTagCompound serializeNBT() {
        /**
         Magic
         Melee Weapons
         Firearms
         EneryWeapons
         Saddlebag_Guns
         Explosives
         Repair
         Medicine
         Lockpicking
         Science
         Sneak
         Barter
         Survival
         */
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("magic", Magic);
        nbt.setInteger("melee", Melee_Weapons);
        nbt.setInteger("firearms", Firearms);
        nbt.setInteger("energy_wep", EneryWeapons);
        nbt.setInteger("saddle_guns", Saddlebag_Guns);
        nbt.setInteger("explosives", Explosives);
        nbt.setInteger("repair", Repair);
        nbt.setInteger("medicine", Medicine);
        nbt.setInteger("lock", Lockpicking);
        nbt.setInteger("science", Science);
        nbt.setInteger("sneak", Sneak);
        nbt.setInteger("barter", Barter);
        nbt.setInteger("survival", Survival);
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        setMagic(nbt.getInteger("magic"));
        setMelee(nbt.getInteger("melee"));
        setFirearms(nbt.getInteger("firearms"));
        setEnergyWeapons(nbt.getInteger("energy_wep"));
        setSaddlebag_guns(nbt.getInteger("saddle_guns"));
        setExplosives(nbt.getInteger("explosives"));
        setRepair(nbt.getInteger("repair"));
        setMedicine(nbt.getInteger("medicine"));
        setLockpick(nbt.getInteger("lock"));
        setScience(nbt.getInteger("science"));
        setSneak(nbt.getInteger("sneak"));
        setBarter(nbt.getInteger("barter"));
        setSurvival(nbt.getInteger("survival"));
    }
}
