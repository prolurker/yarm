package com.prolurker.cm.materials;

import com.prolurker.cm.periodictable.CMElementalProperties;

import net.minecraftforge.fluids.Fluid;

public class CMMaterialFluidCollection {
	public CMMaterialFluid[] fluids;
	
	public CMMaterialFluidCollection(CMElementalProperties properties, int component){
		if(properties.isotopes.length > 0){
			fluids = new CMMaterialFluid[properties.isotopes.length];
			for(int i = 0; i < properties.isotopes.length; i++){
				fluids[i] = new CMMaterialFluid(properties, component, i);
			}
		}else{
			fluids = new CMMaterialFluid[1];
			fluids[0] = new CMMaterialFluid(properties, component, 0); 
		}
	}
	
	public CMMaterialFluidBlock[] generateFluidBlocks(){
		CMMaterialFluidBlock[] blocks = new CMMaterialFluidBlock[fluids.length];
		for(int i = 0; i < fluids.length; i++){
			blocks[i] = fluids[i].generateFluidBlock();
		}
		return blocks;
	}
}
