package com.redsparkle.foe.block;

import com.google.common.base.Optional;
import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.model.IModelPart;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.Models;
import net.minecraftforge.common.model.TRSRTransformation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoijima on 02.06.17.
 */
public class TileEntityDefault extends TileEntity implements IInventory {
    private final List<String> hidden = new ArrayList<String>();
    public final IModelState state = new IModelState() {
        private final Optional<TRSRTransformation> value = Optional.of(TRSRTransformation.identity());

        @Override
        public Optional<TRSRTransformation> apply(Optional<? extends IModelPart> part) {
            if (part.isPresent()) {
                // This whole thing is subject to change, but should do for now.
                UnmodifiableIterator<String> parts = Models.getParts(part.get());
                if (parts.hasNext()) {
                    String name = parts.next();
                    // only interested in the root level
                    if (!parts.hasNext() && hidden.contains(name)) {
                        return value;
                    }
                }
            }
            return Optional.absent();
        }
    };

    public int getSizeInventory() {

        return 10;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        return null;
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {

    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return "Name";
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

}