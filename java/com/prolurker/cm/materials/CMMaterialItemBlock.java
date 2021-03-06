package com.prolurker.cm.materials;

import java.util.List;

import com.prolurker.cm.helpers.StringHelper;
import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class CMMaterialItemBlock extends ItemBlockWithMetadata {
	public CMElementalProperties properties;
	public String[] isotopes;
	public String name;
	
    public CMMaterialItemBlock(Block block) {
    	super(block, block);
    	
    	CMElementalProperties properties = ((CMMaterialBlock) block).properties;
    	
    	this.properties = properties;
    	this.name = properties.name;
    	this.isotopes = properties.isotopes;
    	
    	if(properties.isotopes.length > 0){
    		this.setHasSubtypes(true);
    	}
    }
    
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if(isotopes.length != 0){
			return this.getUnlocalizedName() + "." + isotopes[stack.getItemDamage()];
		}
		return super.getUnlocalizedName(stack);
	}
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bl) {
    	if(properties.displayAtomicNotation){
	        CMMaterial material = CMPeriodicTable.register.get(name);
	        if(isotopes.length != 0){
	        	list.add(StringHelper.formatAtomicNotation("info." + name + "." + isotopes[itemStack.getItemDamage()] + ".atm"));
	        }else{
	        	list.add(StringHelper.formatAtomicNotation("info." + name + ".atm"));
	        }
    	}
    }
}
