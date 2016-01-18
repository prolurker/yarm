package com.prolurker.yarm.machine;

import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.IReconfigurableFacing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class TileYarmMachine extends MachineEnergyHandler implements IInventory{
	public boolean isRunning = false;
	public MachineInventory inventory;
	
	public TileYarmMachine(String name) {
		this(name, 1, 32000);
	}
	
	public TileYarmMachine(String name, int inventorySlots) {
		this(name, 1, 32000);
	}
	
	public TileYarmMachine(String name, int inventorySlots, int energyCapacity) {
		super(name, energyCapacity);
		inventory = new MachineInventory(this, inventorySlots);
	}

	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory.getStackInSlot(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return inventory.decrStackSize(slot, amount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return inventory.getStackInSlot(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory.setInventorySlotContents(slot, stack);
	}

	@Override
	public String getInventoryName() {
		return inventory.getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		return inventory.hasCustomInventoryName();
	}

	@Override
	public int getInventoryStackLimit() {
		return inventory.getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return inventory.isUseableByPlayer(player);
	}

	@Override
	public void openInventory() {
		inventory.openInventory();
	}

	@Override
	public void closeInventory() {
		inventory.closeInventory();
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return isItemValidForSlot(slot, stack);
	}
}
