package com.prolurker.cm.machine;

import java.util.List;

import com.prolurker.cm.CM;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MachineBlock extends BlockContainer{
    public static final int CM_CRUSHER = 0;
    public static final int CM_ORE_WASHER = 1;
    public static final int CM_CHEMICAL_DISSOLVER = 2;
    public static final int CM_CHEMICAL_REACTOR_VESSEL = 3;
    public static final int CM_CHEMICAL_DISTILLER = 4;
    public static final int CM_CHEMICAL_PURIFIER = 5;
    public static final int CM_ELECTROLITIC_SEPERATOR = 6;
    public static final int CM_CONDENSATOR = 7;
    public static final int CM_INDUSTRIAL_CENTRIFUGE = 8;
    public static final int CM_INDUSTRIAL_BLENDER = 9;
    public static final int TOTAL_MACHINE_TYPES = 10;
    
    public int meta = 0;
    
    public MachineBlock(){
		super(Material.iron);
		 
        setHardness(10.0f);
        setResistance(20.0f);
        this.setBlockName("chemicalmetalworks.machine");
        this.setCreativeTab(CM.blocksTab);
	}
    
    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < TOTAL_MACHINE_TYPES; ++i) {
        	list.add(new ItemStack(item, 1, i));
        }
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
    	this.meta = meta;
    	
    	CM.logger.info("CREATING TILE " + meta);
    	
		return new MachineBaseTile(this.getMachineName(meta), 4, 32000);
    }	
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int arg6, float p_149727_7_, float p_149727_8_, float p_149727_9_){
        if (!world.isRemote) {
            player.openGui(CM.instance, meta, world, x, y, z);
        }
        
        CM.logger.info("BLOCK HAS BEEN ACTIVATED "+meta);
        return true;
    }
    
    public static String getMachineName(int meta){
    	switch(meta){
    		case CM_CRUSHER: 					return "crusher";
    		case CM_ORE_WASHER: 				return "washer";
    		case CM_CHEMICAL_DISSOLVER: 		return "chemicalDissolver";
    		case CM_CHEMICAL_REACTOR_VESSEL: 	return "chemicalReactor";
    		case CM_CHEMICAL_DISTILLER: 		return "chemicalDistiller";
    		case CM_CHEMICAL_PURIFIER: 			return "chemicalPurifier";
    		case CM_ELECTROLITIC_SEPERATOR: 	return "electroliticSeperator";
    		case CM_CONDENSATOR: 				return "condensator";
    		case CM_INDUSTRIAL_CENTRIFUGE:		return "industrialCentrifuge";
    		case CM_INDUSTRIAL_BLENDER:			return "industrialBlender";
    	}
    	return null;
    }
}
