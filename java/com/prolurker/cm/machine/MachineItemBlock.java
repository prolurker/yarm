package com.prolurker.cm.machine;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;

public class MachineItemBlock extends ItemBlockWithMetadata{

	public MachineItemBlock(Block block) {
		super(block, block);
		
		setMaxStackSize(1);
		setHasSubtypes(true);
	}

}
