package com.redsparkle.api.items.helpers.Item_Instances;

import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.utils.InventoryManager;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.entitys.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.entitys.flare.EntityFlare;
import com.redsparkle.foe.items.guns.entitys.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.entitys.spreadPellet_shotgun.*;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageGunFire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.redsparkle.api.utils.GunHelpers.getGunDamage;
/**
 * Created by NENYN on 1/21/2017.
 */
public abstract class Item_Firearm extends Item {
    public Item casing;
    public SoundEvent shot;
    public SoundEvent dry;
    public float cameraYaw;
    public SoundEvent shot_var1;
    public SoundEvent shot_var2;
    public SoundEvent shot_var3;
    public Object ammoItem;
    public int damage;
    public int BaseDamage;
    public int clipRounds;
    public EntityBullet bullet;
    public EnumParticleTypes effect;
    public Integer[] invArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    public String gunName;
    public String projectile;
    public Item_Firearm() {
        this.clipRounds = 32;
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
    }
    public ItemStack findAmmo(EntityPlayer player) {
        for (int i = 0; i < invArray.length; ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            if (this.isAmmo(itemstack)) {
                if (itemstack.getItemDamage() >= 12) {
                    return ItemStack.EMPTY;
                } else {
                    return itemstack;
                }
            }
            // }
        }
        return ItemStack.EMPTY;
    }
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return super.initCapabilities(stack, nbt);
    }
    public ActionResult<ItemStack> shoot(EntityPlayer playerIn, IGunInterface igun, World worldIn, ItemStack caseStack, ItemStack itemstack) {
        if (!playerIn.capabilities.isCreativeMode) {
            if (igun.getAmmo() == 0) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    // ---------------_EMPTY CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), dry, SoundCategory.HOSTILE, 0.5F, 0.4F);
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }
            } else {
                if (shot_var1 != null) {
                    int num = ThreadLocalRandom.current().nextInt(0, 2);
                    switch (num) {
                        case 0:
                            worldIn.playSound(playerIn, playerIn.getPosition(), shot_var1, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                            break;
                        case 1:
                            worldIn.playSound(playerIn, playerIn.getPosition(), shot_var2, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                            break;
                        case 2:
                            worldIn.playSound(playerIn, playerIn.getPosition(), shot_var3, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                            break;
                    }
                } else {
                    worldIn.playSound(playerIn, playerIn.getPosition(), shot, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                }
                firedType(projectile, worldIn, playerIn);
                igun.removeAmmo(1);
                playerIn.cameraYaw = cameraYaw;
                AddCase(playerIn, caseStack);
                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
            }
        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), shot, SoundCategory.HOSTILE, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            firedType(projectile, worldIn, playerIn);
            AddCase(playerIn, caseStack);
            playerIn.cameraYaw = cameraYaw;
        }
        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }
    public void firedType(String projectile, World worldIn, EntityPlayer playerIn) {
        if (projectile == "bullet") {
            bullet(worldIn, playerIn);
        }
        if (projectile == "laser") {
            laser(worldIn, playerIn);
        }
        if (projectile == "pellet") {
            pellet(worldIn, playerIn);
        }
        if (projectile == "flare") {
            flare(worldIn, playerIn);
        }
        if (projectile == "flame") {
            flame(worldIn, playerIn);
        }
    }
    public void bullet(World worldIn,EntityPlayer playerIn) {
        int damage_firearms = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY,null).getFirearms();
        EntityBullet bullet = new EntityBullet(worldIn, playerIn);
        bullet.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 1.5F);
        bullet.setRenderYawOffset(5F);
        bullet.setDamage(getGunDamage(playerIn) + damage_firearms);
        worldIn.spawnEntity(bullet);
        if(!worldIn.isRemote){main.simpleNetworkWrapper.sendToServer(new MessageGunFire("firearm"));}
    }
    public void pellet(World worldIn,EntityPlayer playerIn) {
        int damage_firearms = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY,null).getFirearms();
        Pellet[] pellets = new Pellet[]{new Pellet_one(worldIn, playerIn), new Pellet_two(worldIn, playerIn), new Pellet_tree(worldIn, playerIn), new Pellet_four(worldIn, playerIn), new Pellet_five(worldIn, playerIn), new Pellet_six(worldIn, playerIn)};
        for (int i = 0; i <= (pellets.length - 1); i++) {
            pellets[i].setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 15.5F);
            pellets[i].setRenderYawOffset(10F);
            pellets[i].setDamage(getGunDamage(playerIn) + damage_firearms);
            worldIn.spawnEntity(pellets[i]);
        }
        if(!worldIn.isRemote){main.simpleNetworkWrapper.sendToServer(new MessageGunFire("shotgun"));}
    }
    public void laser(World worldIn,EntityPlayer playerIn) {
        int damage_magic_modif = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY,null).getMagic();
        int damage_laser_weapons = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY,null).getEnergyWeapons();
        EntityLaser laser = new EntityLaser(worldIn, playerIn);
        laser.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 0.5F);
        laser.setRenderYawOffset(5F);
        laser.setDamage(getGunDamage(playerIn) + damage_laser_weapons + Math.round(damage_magic_modif/2));
        worldIn.spawnEntity(laser);
        if(!worldIn.isRemote){main.simpleNetworkWrapper.sendToServer(new MessageGunFire("laser"));}
    }
    public void flame(World worldIn,EntityPlayer playerIn)
    {
        EntityFlame flame = new EntityFlame(worldIn, playerIn);
        flame.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 3.0F);
        flame.setDamage(getGunDamage(playerIn));
        if(!worldIn.isRemote){main.simpleNetworkWrapper.sendToServer(new MessageGunFire("flame"));}
    }
    public void flare(World worldIn,EntityPlayer playerIn) {
        EntityFlare flare = new EntityFlare(worldIn, playerIn);
        flare.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1F, 3.0F);
        flare.setDamage(getGunDamage(playerIn));
        worldIn.spawnEntity(flare);
        if(!worldIn.isRemote){main.simpleNetworkWrapper.sendToServer(new MessageGunFire("flare"));}
    }
    public boolean isAmmo(ItemStack stack) {
        return false;
    }
    public void AddCase(EntityPlayer playerIn, ItemStack casing) {
        if (InventoryManager.AddItemToExistingStack(playerIn, casing) != ItemStack.EMPTY) {
            InventoryManager.AddItemToExistingStack(playerIn, casing).grow(1);
        } else {
            playerIn.inventory.addItemStackToInventory(casing);
        }
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        IGunInterface igun = stack.getCapability(GunFactoryProvider.GUN, null);
        tooltip.add(gunName);
        tooltip.add("Clip size: " + clipRounds);
        tooltip.add("Base Damage: " + BaseDamage);
        tooltip.add("Your Damage: " + damage);
        tooltip.add("Clip In: " + igun.clipInserted());
    }
}
