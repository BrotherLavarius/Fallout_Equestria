package com.redsparkle.foe.items.guns;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.Capability.Player.skills.SkillsFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import com.redsparkle.api.utils.GlobalItemArray_For_init;
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
 * Created by NENYN on 1/5/2017.
 */
public class TenMM extends Item_Firearm {
    public TenMM() {
        this.setMaxStackSize(1);
        this.clipRounds = GlobalsGunStats.TEN_MM.Clipsize();
        this.setMaxDamage(clipRounds);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.cameraYaw = -0.1F;
        this.gunName = "Pre war 10mm pistol";
        this.projectile = "bullet";
    }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        IGunInterface iammo;
        if(stack.hasCapability(GunFactoryProvider.GUN,null)){
            iammo=stack.getCapability(GunFactoryProvider.GUN,null);
            if(iammo.getMaxAmmo() == 0) {
                iammo.setMaxAmmo(clipRounds);
            }
            stack.setItemDamage(iammo.getMaxAmmo() -iammo.getAmmo());
        }
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        this.shot = SoundInit.tenmm_shot;
        this.dry = SoundInit.tenmm_dry;
        ItemStack itemstack = playerIn.getHeldItem(hand);
        this.casing = GlobalItemArray_For_init.AllInit[28];
        ItemStack caseStack = new ItemStack(casing);
        IGunInterface igun = itemstack.getCapability(GunFactoryProvider.GUN, null);
        this.damage = GlobalsGunStats.TEN_MM.getDamage() + playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getFirearms();
        this.BaseDamage = GlobalsGunStats.TEN_MM.getDamage();
        return shoot(playerIn, igun, worldIn, caseStack, itemstack);
    }
    @Override
    public boolean isAmmo(ItemStack stack) {
        return stack.getItem() instanceof TenMMClip;
    }
}
