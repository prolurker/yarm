package com.prolurker.yarm.machine;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MachineGuiHandler implements IGuiHandler{

    public static final int YARM_MACHINE_ISOTOPE_SEPERATOR = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == YARM_MACHINE_ISOTOPE_SEPERATOR){
			return new MachineContainer(player.inventory, (MachineBaseTile) world.getTileEntity(x, y, z));
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == YARM_MACHINE_ISOTOPE_SEPERATOR){
		       return new MachineGuiContainer(player.inventory, (MachineBaseTile) world.getTileEntity(x, y, z));
		}
		
		return null;
	}

}
