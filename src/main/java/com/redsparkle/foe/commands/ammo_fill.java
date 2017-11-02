package com.redsparkle.foe.commands;

import com.redsparkle.api.Capability.Items.Ammo.AmmoFactoryProvider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_AmmoHolder;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoijima on 28.09.17.
 */
public class ammo_fill extends CommandBase {
    private final List<String> aliases;

    public ammo_fill() {
        aliases = new ArrayList<String>();
        aliases.add("ammo_fill");
    }

    /**
     * Gets the name of the command
     */
    @Override
    public String getName() {
        return "ammo_fill";
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender
     */
    @Override
    public String getUsage(ICommandSender sender) {
        return "/ammo_fill";
    }

    /**
     * Callback for when the command is executed
     *
     * @param server
     * @param sender
     * @param args
     */
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1
                ) {
            throw new WrongUsageException("Thats now you use that command silly!");
        } else {
            EntityPlayer player = getPlayer(server, sender, sender.getName());
            ItemStack stack = player.getHeldItemMainhand();
            Item item = player.getHeldItemMainhand().getItem();
            if (item instanceof Item_AmmoHolder && player.isCreative()) {
                stack.getCapability(AmmoFactoryProvider.AMMO_STORAGE, null).setToMax();
            }
        }
    }
}
