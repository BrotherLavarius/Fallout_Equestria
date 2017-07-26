package com.redsparkle.foe.items.guns;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
/**
 * Created by hoijima on 24.06.17.
 */
public class SB_shoutgun extends Item_Firearm {
    public SB_shoutgun() {
        this.clipRounds = GlobalsGunStats.DB_SHOUTGUN.Clipsize();
        this.setMaxStackSize(1);
        this.setMaxDamage(clipRounds);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.cameraYaw = 2.1F;
        this.gunName = "Pre war Double Barrel Shotgun";
        this.projectile = "pellet";
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
        this.shot = SoundInit.db_shotgun_shot;
        this.dry = SoundInit.db_shotgun_dry;
        ItemStack caseStack = ItemStack.EMPTY;
        ItemStack itemstack = playerIn.getHeldItem(hand);
        IGunInterface igun = itemstack.getCapability(GunFactoryProvider.GUN, null);
        this.damage = GlobalsGunStats.DB_SHOUTGUN.getDamage() + playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms();
        this.BaseDamage = GlobalsGunStats.DB_SHOUTGUN.getDamage();
        return shoot(playerIn, igun, worldIn, caseStack, itemstack);
    }
    @Override
    public boolean isAmmo(ItemStack stack) {
        return stack.getItem() instanceof TenMMClip;
    }
}
