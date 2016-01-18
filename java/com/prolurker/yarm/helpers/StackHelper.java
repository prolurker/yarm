package com.prolurker.yarm.helpers;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackHelper {
	public static ItemStack clone(ItemStack stack){
		return ItemStack.copyItemStack(stack);
	}
	
	public static ItemStack clone(ItemStack stack, int size){
		ItemStack clone = ItemStack.copyItemStack(stack);
		clone.stackSize = size;
		return clone;
	}

	public static ItemStack clone(ItemStack stack, int size, int meta){
		 ItemStack itemstack = new ItemStack(stack.getItem(), size, meta);

        if (stack.stackTagCompound != null){
            itemstack.stackTagCompound = (NBTTagCompound)stack.stackTagCompound.copy();
        }

        return itemstack;
	}
	
	public static ItemStack cloneBlock(ItemStack stack, int size, int meta){
		Block block = Block.getBlockFromItem(stack.getItem());
		ItemStack itemstack = new ItemStack(stack.getItem(), size, meta);

		if (stack.stackTagCompound != null){
			itemstack.stackTagCompound = (NBTTagCompound)stack.stackTagCompound.copy();
		}

		return itemstack;
	}
}
