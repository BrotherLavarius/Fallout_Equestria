package com.redsparkle.foe.capa.skills;

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
            basicBigGuns,//constant 0 to init
            BigGunsLevel;

    private Integer
            basicSmallGuns,//constant 0 to init
            SmallGunsLevel;

    private Integer
            basicEnergyWeapons,//constant 0 to init
            EnergyWeaponsLevel;

    private Integer
            basicExplosives,//constant 0 to init
            ExplosivesLevel;

    private Integer
            basicMeleeWeapons,//constant 0 to init
            MeleeWeaponsLevel;

    private Integer
            basicUnarmed,//constant 0 to init
            UnarmedLevel;

    private Integer
            basicMedicine,//constant 0 to init
            MedicineLevel;

    private Integer
            basicLockpick,//constant 0 to init
            LockpickLevel;

    private Integer
            basicRepair,//constant 0 to init
            RepairLevel;

    private Integer
            basicScience,//constant 0 to init
            ScienceLevel;

    private Integer
            basicSneak,//constant 0 to init
            SneakLevel;

    private Integer
            basicBarter,//constant 0 to init
            BarterLevel;


    public SkillsFactoryProvider() {
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
        this.basicSmallGuns=basicSmallGuns;
        this.SmallGunsLevel=SmallGunsLevel;
        this.basicEnergyWeapons=basicEnergyWeapons;
        this.EnergyWeaponsLevel=EnergyWeaponsLevel;
        this.basicExplosives=basicExplosives;
        this.ExplosivesLevel=ExplosivesLevel;
        this.basicMeleeWeapons=basicMeleeWeapons;
        this.MeleeWeaponsLevel=MeleeWeaponsLevel;
        this.basicUnarmed=basicUnarmed;
        this.UnarmedLevel=UnarmedLevel;
        this.basicMedicine=basicMedicine;
        this.MedicineLevel=MedicineLevel;
        this.basicLockpick=basicLockpick;
        this.LockpickLevel=LockpickLevel;
        this.basicRepair=basicRepair;
        this.RepairLevel=RepairLevel;
        this.basicScience=basicScience;
        this.ScienceLevel=ScienceLevel;
        this.basicSneak=basicSneak;
        this.SneakLevel=SneakLevel;
        this.basicBarter=basicBarter;
        this.BarterLevel=BarterLevel;


    }

    public static ISkillsCapability instanceFor(EntityPlayer player) {
        return player.getCapability(SKILLS_CAPABILITY, null);
    }

    /* ################################## */

    public Integer setBigGuns(Integer newBigGunsLevel) {
        return BigGunsLevel = newBigGunsLevel;
    }

    public Integer getBigGuns() {
        return BigGunsLevel;
    }

    public Integer setSmallGuns(Integer newSmallGunsLevel) {
        return SmallGunsLevel = newSmallGunsLevel;
    }

    public Integer getSmallGuns() {
        return SmallGunsLevel;
    }
    /* ################################## */

    public Integer setEnergyWeapons(Integer newEnergyWeaponsLevel) {
        return EnergyWeaponsLevel = newEnergyWeaponsLevel;
    }

    public Integer getEnergyWeapons() {
        return EnergyWeaponsLevel;
    }
    /* ################################## */

    public Integer setExplosives(Integer newExplosivesLevel) {
        return ExplosivesLevel = newExplosivesLevel;
    }

    public Integer getExplosives() {
        return ExplosivesLevel;
    }
    /* ################################## */

    public Integer setMeleeWeapons(Integer newMeleeWeaponsLevel) {
        return MeleeWeaponsLevel = newMeleeWeaponsLevel;
    }

    public Integer getMeleeWeapons() {
        return MeleeWeaponsLevel;
    }
    /* ################################## */

    public Integer setUnarmed(Integer newUnarmedLevel) {
        return UnarmedLevel = +newUnarmedLevel;
    }

    public Integer getUnarmed() {
        return UnarmedLevel;
    }
    /* ################################## */

    public Integer setMedicine(Integer newMedicineLevel) {
        return MedicineLevel = newMedicineLevel;
    }

    public Integer getMedicine() {
        return MedicineLevel;
    }
    /* ################################## */

    public Integer setLockpick(Integer newLockpickLevel) {
        return LockpickLevel = newLockpickLevel;
    }

    public Integer getLockpick() {
        return LockpickLevel;
    }
    /* ################################## */

    public Integer setRepair(Integer newRepairLevel) {
        return RepairLevel = newRepairLevel;
    }

    public Integer getRepair() {
        return RepairLevel;
    }
    /* ################################## */

    public Integer setScience(Integer newScienceLevel) {
        return ScienceLevel = newScienceLevel;
    }

    public Integer getScience() {
        return ScienceLevel;
    }
    /* ################################## */

    public Integer setSneak(Integer newSneakLevel) {
        return SneakLevel = newSneakLevel;
    }

    public Integer getSneak() {
        return SneakLevel;
    }
    /* ################################## */

    public Integer setBarter(Integer newBarterLevel) {
        return BarterLevel = newBarterLevel;
    }

    public Integer getBarter() {
        return BarterLevel;
    }

    @Override
    public void setAll(Integer all) {
        setBigGuns(all);
        setSmallGuns(all);
        setEnergyWeapons(all);
        setExplosives(all);
        setMeleeWeapons(all);
        setUnarmed(all);
        setMedicine(all);
        setLockpick(all);
        setRepair(all);
        setScience(all);
        setSneak(all);
        setBarter(all);


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

