package com.redsparkle.foe.capa.skills;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageUpdateClientServerSkills;
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
            basicBigGuns,//constant 0 to init
            BigGunsLevel,// real level
            prevBigGunsLevel;// prev before changing

    private Integer
            basicSmallGuns,//constant 0 to init
            SmallGunsLevel,// real level
            prevSmallGunsLevel;// prev before changing

    private Integer
            basicEnergyWeapons,//constant 0 to init
            EnergyWeaponsLevel,// real level
            prevEnergyWeaponsLevel;// prev before changing

    private Integer
            basicExplosives,//constant 0 to init
            ExplosivesLevel,// real level
            prevExplosivesLevel;// prev before changing

    private Integer
            basicMeleeWeapons,//constant 0 to init
            MeleeWeaponsLevel,// real level
            prevMeleeWeaponsLevel;// prev before changing

    private Integer
            basicUnarmed,//constant 0 to init
            UnarmedLevel,// real level
            prevUnarmedLevel;// prev before changing

    private Integer
            basicMedicine,//constant 0 to init
            MedicineLevel,// real level
            prevMedicineLevel;// prev before changing

    private Integer
            basicLockpick,//constant 0 to init
            LockpickLevel,// real level
            prevLockpickLevel;// prev before changing

    private Integer
            basicRepair,//constant 0 to init
            RepairLevel,// real level
            prevRepairLevel;// prev before changing

    private Integer
            basicScience,//constant 0 to init
            ScienceLevel,// real level
            prevScienceLevel;// prev before changing

    private Integer
            basicSneak,//constant 0 to init
            SneakLevel,// real level
            prevSneakLevel;// prev before changing

    private Integer
            basicBarter,//constant 0 to init
            BarterLevel,// real level
            prevBarterLevel;// prev before changing


    public SkillsFactoryProvider() {
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

    public SkillsFactoryProvider(Integer basicBigGuns,
                                 Integer BigGunsLevel,
                                 Integer prevBigGunsLevel,
                                 Integer basicSmallGuns,
                                 Integer SmallGunsLevel,
                                 Integer prevSmallGunsLevel,
                                 Integer basicEnergyWeapons,
                                 Integer EnergyWeaponsLevel,
                                 Integer prevEnergyWeaponsLevel,
                                 Integer basicExplosives,
                                 Integer ExplosivesLevel,
                                 Integer prevExplosivesLevel,
                                 Integer basicMeleeWeapons,
                                 Integer MeleeWeaponsLevel,
                                 Integer prevMeleeWeaponsLevel,
                                 Integer basicUnarmed,
                                 Integer UnarmedLevel,
                                 Integer prevUnarmedLevel,
                                 Integer basicMedicine,
                                 Integer MedicineLevel,
                                 Integer prevMedicineLevel,
                                 Integer basicLockpick,
                                 Integer LockpickLevel,
                                 Integer prevLockpickLevel,
                                 Integer basicRepair,
                                 Integer RepairLevel,
                                 Integer prevRepairLevel,
                                 Integer basicScience,
                                 Integer ScienceLevel,
                                 Integer prevScienceLevel,
                                 Integer basicSneak,
                                 Integer SneakLevel,
                                 Integer prevSneakLevel,
                                 Integer basicBarter,
                                 Integer BarterLevel,
                                 Integer prevBarterLevel) {
        this.basicBigGuns=basicBigGuns;
        this.BigGunsLevel=BigGunsLevel;
        this.prevBigGunsLevel=prevBigGunsLevel;
        this.basicSmallGuns=basicSmallGuns;
        this.SmallGunsLevel=SmallGunsLevel;
        this.prevSmallGunsLevel=prevSmallGunsLevel;
        this.basicEnergyWeapons=basicEnergyWeapons;
        this.EnergyWeaponsLevel=EnergyWeaponsLevel;
        this.prevEnergyWeaponsLevel=prevEnergyWeaponsLevel;
        this.basicExplosives=basicExplosives;
        this.ExplosivesLevel=ExplosivesLevel;
        this.prevExplosivesLevel=prevExplosivesLevel;
        this.basicMeleeWeapons=basicMeleeWeapons;
        this.MeleeWeaponsLevel=MeleeWeaponsLevel;
        this.prevMeleeWeaponsLevel=prevMeleeWeaponsLevel;
        this.basicUnarmed=basicUnarmed;
        this.UnarmedLevel=UnarmedLevel;
        this.prevUnarmedLevel=prevUnarmedLevel;
        this.basicMedicine=basicMedicine;
        this.MedicineLevel=MedicineLevel;
        this.prevMedicineLevel=prevMedicineLevel;
        this.basicLockpick=basicLockpick;
        this.LockpickLevel=LockpickLevel;
        this.prevLockpickLevel=prevLockpickLevel;
        this.basicRepair=basicRepair;
        this.RepairLevel=RepairLevel;
        this.prevRepairLevel=prevRepairLevel;
        this.basicScience=basicScience;
        this.ScienceLevel=ScienceLevel;
        this.prevScienceLevel=prevScienceLevel;
        this.basicSneak=basicSneak;
        this.SneakLevel=SneakLevel;
        this.prevSneakLevel=prevSneakLevel;
        this.basicBarter=basicBarter;
        this.BarterLevel=BarterLevel;
        this.prevBarterLevel=prevBarterLevel;


    }

    public static ISkillsCapability instanceFor(EntityPlayer player) {
        return player.getCapability(SKILLS_CAPABILITY, null);
    }

    /* ################################## */
    public Integer addBigGuns(Integer addBigGuns) {
        return BigGunsLevel+basicBigGuns;
    }
    public Integer removeBigGuns(Integer removeBigGuns) {return BigGunsLevel=(BigGunsLevel-removeBigGuns+basicBigGuns);}
    public Integer setBigGuns(Integer newBigGunsLevel) {return BigGunsLevel=(basicBigGuns+newBigGunsLevel);}
    public Integer getBigGuns() {return BigGunsLevel+basicBigGuns;}
    /* ################################## */
    public Integer addSmallGuns(Integer addSmallGuns) {
        return SmallGunsLevel+basicSmallGuns;}
    public Integer removeSmallGuns(Integer removeSmallGuns) {return SmallGunsLevel=(SmallGunsLevel-removeSmallGuns+basicSmallGuns);}
    public Integer setSmallGuns(Integer newSmallGunsLevel) {return SmallGunsLevel=(basicSmallGuns+newSmallGunsLevel);}
    public Integer getSmallGuns() {return SmallGunsLevel+basicSmallGuns;}
    /* ################################## */
    public Integer addEnergyWeapons(Integer addEnergyWeapons) {
        return EnergyWeaponsLevel+basicEnergyWeapons;}
    public Integer removeEnergyWeapons(Integer removeEnergyWeapons) {return EnergyWeaponsLevel=(EnergyWeaponsLevel-removeEnergyWeapons+basicEnergyWeapons);}
    public Integer setEnergyWeapons(Integer newEnergyWeaponsLevel) {return EnergyWeaponsLevel=(basicEnergyWeapons+newEnergyWeaponsLevel);}
    public Integer getEnergyWeapons() {return EnergyWeaponsLevel+basicEnergyWeapons;}
    /* ################################## */
    public Integer addExplosives(Integer addExplosives) {
        return ExplosivesLevel+basicExplosives;}
    public Integer removeExplosives(Integer removeExplosives) {return ExplosivesLevel=(ExplosivesLevel-removeExplosives+basicExplosives);}
    public Integer setExplosives(Integer newExplosivesLevel) {return ExplosivesLevel=(basicExplosives+newExplosivesLevel);}
    public Integer getExplosives() {return ExplosivesLevel+basicExplosives;}
    /* ################################## */
    public Integer addMeleeWeapons(Integer addMeleeWeapons) {
        return MeleeWeaponsLevel+basicMeleeWeapons;}
    public Integer removeMeleeWeapons(Integer removeMeleeWeapons) {return MeleeWeaponsLevel=(MeleeWeaponsLevel-removeMeleeWeapons+basicMeleeWeapons);}
    public Integer setMeleeWeapons(Integer newMeleeWeaponsLevel) {return MeleeWeaponsLevel=(basicMeleeWeapons+newMeleeWeaponsLevel);}
    public Integer getMeleeWeapons() {return MeleeWeaponsLevel+basicMeleeWeapons;}
    /* ################################## */
    public Integer addUnarmed(Integer addUnarmed) {
        return UnarmedLevel+basicUnarmed;}
    public Integer removeUnarmed(Integer removeUnarmed) {return UnarmedLevel=(UnarmedLevel-removeUnarmed+basicUnarmed);}
    public Integer setUnarmed(Integer newUnarmedLevel) {return UnarmedLevel=(basicUnarmed+newUnarmedLevel);}
    public Integer getUnarmed() {return UnarmedLevel+basicUnarmed;}
    /* ################################## */
    public Integer addMedicine(Integer addMedicine) {
        return MedicineLevel+basicMedicine;}
    public Integer removeMedicine(Integer removeMedicine) {return MedicineLevel=(MedicineLevel-removeMedicine+basicMedicine);}
    public Integer setMedicine(Integer newMedicineLevel) {return MedicineLevel=(basicMedicine+newMedicineLevel);}
    public Integer getMedicine() {return MedicineLevel+basicMedicine;}
    /* ################################## */
    public Integer addLockpick(Integer addLockpick) {
        return LockpickLevel+basicLockpick;}
    public Integer removeLockpick(Integer removeLockpick) {return LockpickLevel=(LockpickLevel-removeLockpick+basicLockpick);}
    public Integer setLockpick(Integer newLockpickLevel) {return LockpickLevel=(basicLockpick+newLockpickLevel);}
    public Integer getLockpick() {return LockpickLevel+basicLockpick;}
    /* ################################## */
    public Integer addRepair(Integer addRepair) {
        return RepairLevel+basicRepair;}
    public Integer removeRepair(Integer removeRepair) {return RepairLevel=(RepairLevel-removeRepair+basicRepair);}
    public Integer setRepair(Integer newRepairLevel) {return RepairLevel=(basicRepair+newRepairLevel);}
    public Integer getRepair() {return RepairLevel+basicRepair;}
    /* ################################## */
    public Integer addScience(Integer addScience) {
        return ScienceLevel+basicScience;}
    public Integer removeScience(Integer removeScience) {return ScienceLevel=(ScienceLevel-removeScience+basicScience);}
    public Integer setScience(Integer newScienceLevel) {return ScienceLevel=(basicScience+newScienceLevel);}
    public Integer getScience() {return ScienceLevel+basicScience;}
    /* ################################## */
    public Integer addSneak(Integer addSneak) {
        return SneakLevel+basicSneak;}
    public Integer removeSneak(Integer removeSneak) {return SneakLevel=(SneakLevel-removeSneak+basicSneak);}
    public Integer setSneak(Integer newSneakLevel) {return SneakLevel=(basicSneak+newSneakLevel);}
    public Integer getSneak() {return SneakLevel+basicSneak;}
    /* ################################## */
    public Integer addBarter(Integer addBarter) {
        return BarterLevel+basicBarter;}
    public Integer removeBarter(Integer removeBarter) {return BarterLevel=(BarterLevel-removeBarter+basicBarter);}
    public Integer setBarter(Integer newBarterLevel) {return BarterLevel=(basicBarter+newBarterLevel);}
    public Integer getBarter() {return BarterLevel+basicBarter;}
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

    
    public boolean hasCapability( Capability<?> capability, EnumFacing facing) {
        return capability == SKILLS_CAPABILITY;
    }

public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == SKILLS_CAPABILITY ? (T) this : null;
    }


    
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("BigGuns", BigGunsLevel);
        nbt.setInteger("SmallGuns", SmallGunsLevel);
        nbt.setInteger("EnergyWeapons", EnergyWeaponsLevel);
        nbt.setInteger("Explosives", ExplosivesLevel);
        nbt.setInteger("MeleeWeapons", MeleeWeaponsLevel);
        nbt.setInteger("Unarmed", UnarmedLevel);
        nbt.setInteger("Medicine", MedicineLevel);
        nbt.setInteger("Lockpick", LockpickLevel);
        nbt.setInteger("Repair", RepairLevel);
        nbt.setInteger("Science", ScienceLevel);
        nbt.setInteger("Sneak", SneakLevel);
        nbt.setInteger("Barter", BarterLevel);
        return nbt;
    }


    
    public void deserializeNBT(NBTTagCompound nbt) {
        setBigGuns(nbt.getInteger("BigGuns"));
        setSmallGuns(nbt.getInteger("SmallGuns"));
        setEnergyWeapons(nbt.getInteger("EnergyWeapons"));
        setExplosives(nbt.getInteger("Explosives"));
        setMeleeWeapons(nbt.getInteger("MeleeWeapons"));
        setUnarmed(nbt.getInteger("Unarmed"));
        setMedicine(nbt.getInteger("Medicine"));
        setLockpick(nbt.getInteger("Lockpick"));
        setRepair(nbt.getInteger("Repair"));
        setScience(nbt.getInteger("Science"));
        setSneak(nbt.getInteger("Sneak"));
        setBarter(nbt.getInteger("Barter"));
    }
}

