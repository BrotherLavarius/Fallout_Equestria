package com.redsparkle.foe.items.guns;

import com.redsparkle.api.capa.skills.SkillsFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.utils.GlobalWeaponsStats;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by hoijima on 20.01.17.
 */
public class LaserPistol extends Item_Firearm {

    public boolean isGun;
    public int clipRounds = GlobalWeaponsStats.LaserBatterRounds;


    public LaserPistol() {
        this.setMaxStackSize(1);
        this.setMaxDamage(clipRounds);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        isGun = true;

    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        if (nbt == null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setBoolean("isgun", isGun);
            stack.setTagCompound(tag);
        }
        return super.initCapabilities(stack, nbt);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        this.shot_var1 = SoundInit.laser_fire_var_One;
        this.shot_var2 = SoundInit.laser_fire_var_Two;
        this.shot_var3 = SoundInit.laser_fire_var_Tree;
        this.dry = SoundInit.laser_dry;
        ItemStack itemstack = playerIn.getHeldItem(hand);
        this.damage = GlobalWeaponsStats.LaserDamage + playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getEnergyWeapons();
        if (!playerIn.capabilities.isCreativeMode) {
            if (itemstack.getItemDamage() >= (GlobalWeaponsStats.LaserBatterRounds - 1)) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    // ---------------_EMPTY CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), dry, SoundCategory.HOSTILE, 0.5F, 0.4F);
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }
            } else {
                int num = (int) Math.random() * 100 % 3;
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
                    laser(worldIn, playerIn);


                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                playerIn.cameraYaw = -0.1F;
                return new ActionResult<>(EnumActionResult.PASS, itemstack);
            }
        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), shot_var1, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                laser(worldIn, playerIn);

        }
        return new ActionResult<>(EnumActionResult.PASS, itemstack);
    }


    @Override
    public boolean isAmmo(ItemStack stack) {

        return stack.getItem() instanceof Battery;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Laser pistol");
        tooltip.add("Clip size: " + (clipRounds - 1));
        tooltip.add("Base Damage: " + GlobalWeaponsStats.LaserDamage);
        tooltip.add("Your Damage: " + damage);


    }

}
