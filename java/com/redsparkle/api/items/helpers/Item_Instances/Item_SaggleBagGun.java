package com.redsparkle.api.items.helpers.Item_Instances;

import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.utils.GunHelpers;
import com.redsparkle.api.utils.InventoryManager;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.FoeItem;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.MessageGunFire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by hoijima on 27.06.17.
 */
public abstract class Item_SaggleBagGun extends FoeItem {
    public ItemStack caseStack;
    public ItemStack active_gun;
    public SoundEvent shot;
    public SoundEvent dry;
    public float cameraYaw;
    public SoundEvent[] shot_sounds;
    public SoundEvent shot_var2;
    public SoundEvent shot_var3;
    public Object ammoItem;
    public int damage;
    public int BaseDamage;
    public int clipRounds;
    public EntityBullet bullet;
    public EnumParticleTypes effect;
    public Integer[] invArray = {8, 9, 10, 11};
    public String gunName;
    public String side;

    public IGunInterface gun_cap;

    public String projectile;

    public Item_SaggleBagGun(String itemName, String side) {
        super(itemName);
        this.clipRounds = 32;
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
        this.side = side;
        this.caseStack = caseStack;
        this.shot_sounds = shot_sounds;
    }


    public void Shoot(World worldIn, EntityPlayer playerIn, String side) {

        if (side == "LS") {
            ItemStack active_gun = playerIn.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6);
        }
        if (side == "RS") {
            ItemStack active_gun = playerIn.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7);
        }

        gun_cap = active_gun.getCapability(GunFactoryProvider.GUN, null);

        if (!playerIn.capabilities.isCreativeMode) {
            if (gun_cap.getAmmo() == 0) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    worldIn.playSound(playerIn, playerIn.getPosition(), dry, SoundCategory.HOSTILE, 0.5F, 0.4F);
                }
            } else {

                int num = ThreadLocalRandom.current().nextInt(0, shot_sounds.length - 1);
                if (shot_sounds[num] != null) {
                    worldIn.playSound(playerIn, playerIn.getPosition(), shot_sounds[num], SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                } else {
                    System.out.println("Sorry i cant play rifle sound: " + side + "_" + active_gun.getItem().getUnlocalizedName());
                }
                firedType(projectile, worldIn, playerIn);
                gun_cap.removeAmmo(1);
                playerIn.cameraYaw = cameraYaw;
                AddCase(playerIn, caseStack);
            }

        }
    }


    public String side() {
        return side;
    }

    public void firedType(String projectile, World worldIn, EntityPlayer playerIn) {
        if (projectile == "bullet") {
            bullet(worldIn, playerIn);
        }

    }




    public ItemStack findAmmo(EntityPlayer player) {
        for (int i = 0; i < invArray.length; ++i) {
            ItemStack itemstack = player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(i);
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

    public void bullet(World worldIn, EntityPlayer playerIn) {
        int damage_firearms = playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms();
        EntityBullet bullet = new EntityBullet(worldIn, playerIn);
        bullet.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4.5F, 1.5F);
        bullet.setRenderYawOffset(5F);
        bullet.setDamage(Float.parseFloat(GunHelpers.GunParams(playerIn, 10).getItem(1)) + damage_firearms);
        worldIn.spawnEntity(bullet);
        if (!worldIn.isRemote) {
            main.simpleNetworkWrapper.sendToServer(new MessageGunFire("firearm"));
        }
    }
}
