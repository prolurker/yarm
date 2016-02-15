package com.prolurker.cm.blocks;

import com.prolurker.cm.helpers.RegisterHelper;
import com.prolurker.cm.machine.MachineBlock;
import com.prolurker.cm.machine.MachineItemBlock;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class CMBlocks {
	public static MachineBlock machineBlock;
	
	public static void registerBlocks(){
		machineBlock = new MachineBlock();
		RegisterHelper.registerOreDictBlock("chemicalmetalworks.machine", machineBlock, new MachineItemBlock(machineBlock));
	}
	
	public static void registerRecipes(){
		
	}
}
