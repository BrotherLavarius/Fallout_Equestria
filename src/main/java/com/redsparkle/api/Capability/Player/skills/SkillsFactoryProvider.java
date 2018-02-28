package com.redsparkle.api.Capability.Player.skills;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSkills;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hoijima on 01.03.17.
 */
public class SkillsFactoryProvider implements ISkillsCapability, ICapabilitySerializable<NBTTagCompound>, Serializable {
    @CapabilityInject(ISkillsCapability.class)
    public static Capability<ISkillsCapability> SKILLS_CAPABILITY = null;
    private HashMap<String, Integer> skills = new HashMap<>();
    private HashMap<String, Integer> last_skills = new HashMap<>();


    public SkillsFactoryProvider() {
        this(0);
    }

    public SkillsFactoryProvider(int all) {

        for (Skill_names skillName : Skill_names.values()) {
            skills.put(skillName.getName(), all);
            last_skills.put(skillName.getName(), all);
        }
    }

    public static ISkillsCapability instanceFor(EntityPlayer player) {
        return player.getCapability(SKILLS_CAPABILITY, null);
    }

    @Override
    public Integer getAttribute(String name) {
        return skills.get(name);
    }

    @Override
    public void setAttribute(String name, Integer amount) {
        if (name.equalsIgnoreCase("all")) {
            for (Map.Entry<String, Integer> entry : skills.entrySet()) {
                last_skills.put(entry.getKey(), skills.get(entry.getKey()));
                skills.put(entry.getKey(), amount);
            }
        } else {
            last_skills.put(name, skills.get(name.toLowerCase()));
            skills.put(name.toLowerCase(), amount);
        }
    }
    @Override
    public void setAttribute(HashMap<String, Integer> new_map) {

        for (Map.Entry<String, Integer> entry : skills.entrySet()) {
            skills.put(entry.getKey(), new_map.get(entry.getKey()));
        }

    }

    @Override
    public HashMap<String, Integer> getFullMap() {
        return skills;
    }

    @Override
    public Boolean hasChanged() {
        boolean flag = new Boolean(Boolean.FALSE);
        for (Map.Entry<String, Integer> entry : skills.entrySet()) {
            if (!last_skills.get(entry.getKey()).equals(skills.get(entry.getKey()))) {
                last_skills.put(entry.getKey(), skills.get(entry.getKey()));
                flag = Boolean.TRUE;
            }
        }
        return flag;
    }

    public void updateClient(EntityPlayer player) {
        main.simpleNetworkWrapper.sendTo(new MessageUpdateClientServerSkills(this), (EntityPlayerMP) player);
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == SKILLS_CAPABILITY;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == SKILLS_CAPABILITY ? (T) this : null;
    }

    public NBTTagCompound serializeNBT() {

        NBTTagCompound nbt = new NBTTagCompound();

        for (Map.Entry<String, Integer> entry : skills.entrySet()) {
            nbt.setInteger(entry.getKey(), entry.getValue());
        }
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        for (Map.Entry<String, Integer> entry : skills.entrySet()) {
            setAttribute(entry.getKey(), nbt.getInteger(entry.getKey()));
        }
    }
}

