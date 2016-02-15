package com.prolurker.cm.gui;

import com.prolurker.cm.CM;
import com.prolurker.cm.machine.MachineBaseTile;
import com.prolurker.cm.machine.MachineBlock;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MachineGuiHandler implements IGuiHandler{	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		MachineBaseTile tile = (MachineBaseTile) world.getTileEntity(x, y, z);
		
		if(tile == null){
			CM.logger.info("tile is NULL!");
		}
		
		if (ID == MachineBlock.CM_INDUSTRIAL_CENTRIFUGE){
			return new MachineContainer(player.inventory, tile);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		MachineBaseTile tile = (MachineBaseTile) world.getTileEntity(x, y, z);
		
		if(tile == null){
			CM.logger.info("tile is NULL!");
		}
		
		if (ID == MachineBlock.CM_INDUSTRIAL_CENTRIFUGE){
		       return new MachineGuiContainer(player.inventory, tile);
		}
		
		return null;
	}

}
