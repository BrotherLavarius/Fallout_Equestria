package com.redsparkle.foe.capa.spechial;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by hoijima on 01.03.17.
 */
public class SpechialFactoryProvider implements ISpechialInterface, ICapabilitySerializable<NBTTagCompound> {
    @Override
    public Integer addStreinght(Integer addStreinght) {
        return null;
    }

    @Override
    public Integer addPerception(Integer addPerception) {
        return null;
    }

    @Override
    public Integer addEndurance(Integer addEndurance) {
        return null;
    }

    @Override
    public Integer addCharisma(Integer addCharisma) {
        return null;
    }

    @Override
    public Integer addIntelligence(Integer addIntelligence) {
        return null;
    }

    @Override
    public Integer addAgility(Integer addAgility) {
        return null;
    }

    @Override
    public Integer addLuck(Integer addLuck) {
        return null;
    }

    @Override
    public Integer removeStreinght(Integer removeStreinght) {
        return null;
    }

    @Override
    public Integer removePerception(Integer removePerception) {
        return null;
    }

    @Override
    public Integer removeEndurance(Integer removeEndurance) {
        return null;
    }

    @Override
    public Integer removeCharisma(Integer removeCharisma) {
        return null;
    }

    @Override
    public Integer removeIntelligence(Integer removeIntelligence) {
        return null;
    }

    @Override
    public Integer removeAgility(Integer removeAgility) {
        return null;
    }

    @Override
    public Integer removeLuck(Integer removeLuck) {
        return null;
    }

    @Override
    public Integer setStreinght(Integer setStreinght) {
        return null;
    }

    @Override
    public Integer setPerception(Integer setPerception) {
        return null;
    }

    @Override
    public Integer setEndurance(Integer setEndurance) {
        return null;
    }

    @Override
    public Integer setCharisma(Integer setCharisma) {
        return null;
    }

    @Override
    public Integer setIntelligence(Integer setIntelligence) {
        return null;
    }

    @Override
    public Integer setAgility(Integer setAgility) {
        return null;
    }

    @Override
    public Integer setLuck(Integer setLuck) {
        return null;
    }

    @Override
    public Integer getStreinght() {
        return null;
    }

    @Override
    public Integer getPerception() {
        return null;
    }

    @Override
    public Integer getEndurance() {
        return null;
    }

    @Override
    public Integer getCharisma() {
        return null;
    }

    @Override
    public Integer getIntelligence() {
        return null;
    }

    @Override
    public Integer getAgility() {
        return null;
    }

    @Override
    public Integer getLuck() {
        return null;
    }

    @Override
    public void update(EntityPlayer player, World world, TickEvent.Phase phase) {

    }

    @Override
    public void updateClient(EntityPlayer player) {

    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }
}
