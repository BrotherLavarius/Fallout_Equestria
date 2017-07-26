package com.redsparkle.foe.items.guns;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
/**
 * Created by hoijima on 20.01.17.
 */
public class LaserPistol extends Item_Firearm {
    public LaserPistol() {
        this.clipRounds = GlobalsGunStats.LASER_PISTOL.Clipsize();
        this.setMaxStackSize(1);
        this.setMaxDamage(clipRounds);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.cameraYaw = -0.0F;
        this.gunName = "Pre war Laser pistol";
        this.projectile = "laser";
    }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        IGunInterface iammo;
        if (stack.hasCapability(GunFactoryProvider.GUN, null)) {
            iammo = stack.getCapability(GunFactoryProvider.GUN, null);
            if (iammo.getMaxAmmo() == 0) {
                iammo.setMaxAmmo(clipRounds);
            }
            stack.setItemDamage(iammo.getMaxAmmo() - iammo.getAmmo());
        }
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        this.shot_var1 = SoundInit.laser_fire_var_One;
        this.shot_var2 = SoundInit.laser_fire_var_Two;
        this.shot_var3 = SoundInit.laser_fire_var_Tree;
        this.shot = SoundInit.laser_fire_var_One;
        this.dry = SoundInit.laser_dry;
        ItemStack caseStack = ItemStack.EMPTY;
        ItemStack itemstack = playerIn.getHeldItem(hand);
        IGunInterface igun = itemstack.getCapability(GunFactoryProvider.GUN, null);
        this.damage = GlobalsGunStats.LASER_PISTOL.getDamage() + playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getEnergyWeapons();
        this.BaseDamage = GlobalsGunStats.LASER_PISTOL.getDamage();
        return shoot(playerIn, igun, worldIn, caseStack, itemstack);
    }
    @Override
    public boolean isAmmo(ItemStack stack) {
        return stack.getItem() instanceof Battery;
    }
}
