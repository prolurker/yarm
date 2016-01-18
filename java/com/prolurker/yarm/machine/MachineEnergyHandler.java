package com.prolurker.yarm.machine;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class MachineEnergyHandler extends TileYarm implements IEnergyHandler{
	protected MachineEnergyStorage storage;

	public MachineEnergyHandler(String name) {
		this(name, 32000);
	}
	
	public MachineEnergyHandler(String name, int energyCapacity) {
		super(name);
		storage = new MachineEnergyStorage(energyCapacity);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}

	/* IEnergyConnection */
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	/* IEnergyReceiver */
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {

		return storage.receiveEnergy(maxReceive, simulate);
	}

	/* IEnergyProvider */
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {

		return storage.extractEnergy(maxExtract, simulate);
	}

	/* IEnergyReceiver and IEnergyProvider */
	@Override
	public int getEnergyStored(ForgeDirection from) {

		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getMaxEnergyStored();
	}
}
