package com.prolurker.cm.machine;

import com.prolurker.cm.helpers.StackHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MachineInventory implements IInventory{
	public ItemStack[] inventory = {};
	public int stacklimit = 64;
	public boolean isUseable = true;
	public CMTile tile;
	
	public MachineInventory(CMTile tile, int slots) {
		this.tile = tile;
		inventory = new ItemStack[slots];
	}

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
    	for(int i = 0; i <= inventory.length; i++){
            NBTTagCompound stack = new NBTTagCompound();
            inventory[i].writeToNBT(stack);
            nbt.setTag("inventory_slot_"+i, stack);
    	}
    	return nbt;
    }

    public NBTTagCompound readFromNBT(NBTTagCompound nbt) {
    	for(int i = 0; i <= inventory.length; i++){
            inventory[i] = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("inventory_slot_"+i));
    	}
    	return nbt;
    }
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if(inventory[slot] == null){
			return null;
		}
		
		if(this.inventory[slot].stackSize <= amount){
			amount = this.inventory[slot].stackSize;
		}
		
        ItemStack stack = this.inventory[slot].splitStack(amount);

        if (inventory[slot].stackSize <= 0){
        	inventory[slot] = null;
        }
        
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inventory[slot] == null){
			return null;
		}
		ItemStack itemStack = this.inventory[slot];
	    this.inventory[slot] = null;

	    return itemStack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
        if(stack != null && stack.stackSize > stacklimit) {
        	stack.stackSize = stacklimit;
        }

        tile.getWorldObj().markTileEntityChunkModified(tile.xCoord, tile.yCoord, tile.zCoord, tile);
	}

	@Override
	public String getInventoryName() {
		return tile.getTileName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		return !tile.getTileName().isEmpty();
	}

	@Override
	public int getInventoryStackLimit() {
		return stacklimit;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return isUseable;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public void markDirty() {
		tile.markDirty();
	}
}
