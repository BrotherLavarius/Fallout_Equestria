package com.redsparkle.foe.utils;

import com.google.common.io.Files;
import com.redsparkle.foe.gui.Inventory_Crafting.IInventory_AdditionalInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/*Thank you so much for the help azanor
 * for basically writing this class and releasing it open source
 * 
 * https://github.com/Azanor/Baubles
 * 
 * which is under Attribution-NonCommercial-ShareAlike 3.0 Unported (CC BY-NC-SA 3.0) license.
 * so i was able to use parts of that to make this
 * **/
public class UtilPlayerInventoryFilestorage {
    public static final String EXT = "foe";
    public static final String EXTBK = "foebk";
    public static HashSet<Integer> playerEntityIds = new HashSet<Integer>();
    private static HashMap<String, IInventory_AdditionalInventory> playerItems = new HashMap<String, IInventory_AdditionalInventory>();

    public static void playerSetupOnLoad(PlayerEvent.LoadFromFile event) {
        EntityPlayer player = event.getEntityPlayer();
        clearPlayerInventory(player);
        File playerFile = getPlayerFile(EXT, event.getPlayerDirectory(), event.getEntityPlayer());
        if (!playerFile.exists()) {
            File fileNew = event.getPlayerFile(EXT);
            if (fileNew.exists()) {
                try {
                    Files.copy(fileNew, playerFile);
                    //ModInv.logger.info("Using and converting UUID savefile for " + player.getDisplayNameString());
                    fileNew.delete();
                    File fb = event.getPlayerFile(EXTBK);
                    if (fb.exists())
                        fb.delete();
                } catch (IOException e) {
                }
            }
        }
        loadPlayerInventory(event.getEntityPlayer(), playerFile, getPlayerFile(EXTBK, event.getPlayerDirectory(), event.getEntityPlayer()));
        playerEntityIds.add(event.getEntityPlayer().getEntityId());
    }

    public static void clearPlayerInventory(EntityPlayer player) {
        playerItems.remove(player.getDisplayNameString());
    }

    public static IInventory_AdditionalInventory getPlayerInventory(EntityPlayer player) {
        if (!playerItems.containsKey(player.getDisplayNameString())) {
            IInventory_AdditionalInventory inventory = new IInventory_AdditionalInventory(player);
            playerItems.put(player.getDisplayNameString(), inventory);
        }
        return playerItems.get(player.getDisplayNameString());
    }

    public static ItemStack getPlayerInventoryStack(EntityPlayer player, int slot) {
        return getPlayerInventory(player).getStackInSlot(slot);
    }

    public static void setPlayerInventoryStack(EntityPlayer player, int slot, ItemStack itemStack) {
        if (slot == Constants.Slot_pipb) {
            getPlayerInventory(player).pipbuckStack = itemStack;
        } else if (slot == Constants.Slot_harn) {
            getPlayerInventory(player).harnes_stack = itemStack;
        } else if (slot == Constants.Slot_gun1) {
            getPlayerInventory(player).gun_stack_one = itemStack;
        } else if (slot == Constants.Slot_gun2) {
            getPlayerInventory(player).gun_stack_two = itemStack;
        } else if (slot == Constants.Slot_amm1) {
            getPlayerInventory(player).ammo_stack_one = itemStack;
        } else if (slot == Constants.Slot_amm2) {
            getPlayerInventory(player).ammo_stack_two = itemStack;
        } else if (slot == Constants.Slot_amm3) {
            getPlayerInventory(player).ammo_stack_tree = itemStack;
        } else if (slot == Constants.Slot_amm4) {
            getPlayerInventory(player).ammo_stack_four = itemStack;
        } else if (slot == Constants.Slot_dev1) {
            getPlayerInventory(player).device_stack_one = itemStack;
        } else if (slot == Constants.Slot_dev2) {
            getPlayerInventory(player).device_stack_two = itemStack;
        } else if (slot == Constants.Slot_dev3) {
            getPlayerInventory(player).device_stack_tree = itemStack;
        } else if (slot == Constants.Slot_dev4) {
            getPlayerInventory(player).device_stack_four = itemStack;
        } else
            getPlayerInventory(player).inventory.set(slot, itemStack);
    }

    public static void setPlayerInventory(EntityPlayer player, IInventory_AdditionalInventory inventory) {
        playerItems.put(player.getDisplayNameString(), inventory);
    }

    public static void loadPlayerInventory(EntityPlayer player, File file1, File file2) {
        if (player != null && !player.getEntityWorld().isRemote) {
            try {
                NBTTagCompound data = null;
                boolean save = false;
                if (file1 != null && file1.exists()) {
                    try {
                        FileInputStream fileinputstream = new FileInputStream(file1);
                        data = CompressedStreamTools.readCompressed(fileinputstream);
                        fileinputstream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (file1 == null || !file1.exists() || data == null || data.hasNoTags()) {
                    if (file2 != null && file2.exists()) {
                        try {
                            FileInputStream fileinputstream = new FileInputStream(file2);
                            data = CompressedStreamTools.readCompressed(fileinputstream);
                            fileinputstream.close();
                            save = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (data != null) {
                    IInventory_AdditionalInventory inventory = new IInventory_AdditionalInventory(player);
                    inventory.readFromNBT(data);
                    playerItems.put(player.getDisplayNameString(), inventory);
                    if (save)
                        savePlayerItems(player, file1, file2);
                }
            } catch (Exception e) {
                System.out.println("Error loading player extended inventory");
                e.printStackTrace();
            }
        }
    }

    public static void savePlayerItems(EntityPlayer player, File file1, File file2) {
        if (player != null && !player.getEntityWorld().isRemote) {
            try {
                if (file1 != null && file1.exists()) {
                    try {
                        Files.copy(file1, file2);
                    } catch (Exception e) {
                        System.out.println("Could not backup old file for player " + player.getDisplayNameString());
                    }
                }
                try {
                    if (file1 != null) {
                        IInventory_AdditionalInventory inventory = getPlayerInventory(player);
                        NBTTagCompound data = new NBTTagCompound();
                        inventory.writeToNBT(data);
                        FileOutputStream fileoutputstream = new FileOutputStream(file1);
                        CompressedStreamTools.writeCompressed(data, fileoutputstream);
                        fileoutputstream.close();
                    }
                } catch (Exception e) {
                    System.out.println("Could not save file for player " + player.getDisplayNameString());
                    e.printStackTrace();
                    if (file1.exists()) {
                        try {
                            file1.delete();
                        } catch (Exception e2) {
                        }
                    }
                }
            } catch (Exception exception1) {
                System.out.println("Error saving inventory");
                exception1.printStackTrace();
            }
        }
    }

    public static File getPlayerFile(String suffix, File playerDirectory, EntityPlayer player) {
        String playername = player.getUniqueID().toString();
        String file = "_" + playername + "." + suffix;
        System.out.println("Player File: " + file);
        return new File(playerDirectory, file);
    }

    public static void syncItems(EntityPlayer player) {
        IInventory_AdditionalInventory invo = getPlayerInventory(player);
        for (int a = 0; a < invo.getSizeInventory(); a++) {
            getPlayerInventory(player).syncSlotToClients(a);
        }
    }

    public static void putDataIntoInventory(IInventory_AdditionalInventory invo, EntityPlayer player) {
        IInventory_AdditionalInventory fromStorage = getPlayerInventory(player);
        invo.inventory = fromStorage.inventory;
        invo.pipbuckStack = fromStorage.pipbuckStack;
        invo.device_stack_one = fromStorage.device_stack_one;
        invo.device_stack_two = fromStorage.device_stack_two;
        invo.device_stack_tree = fromStorage.device_stack_tree;
        invo.device_stack_four = fromStorage.device_stack_four;
        invo.harnes_stack = fromStorage.harnes_stack;
        invo.gun_stack_one = fromStorage.gun_stack_one;
        invo.gun_stack_two = fromStorage.gun_stack_two;
        invo.ammo_stack_one = fromStorage.ammo_stack_one;
        invo.ammo_stack_two = fromStorage.ammo_stack_two;
        invo.ammo_stack_tree = fromStorage.ammo_stack_tree;
        invo.ammo_stack_four = fromStorage.ammo_stack_four;
    }
}
