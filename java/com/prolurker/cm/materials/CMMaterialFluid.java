package com.prolurker.cm.materials;

import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import net.minecraftforge.fluids.Fluid;

public class CMMaterialFluid extends Fluid{
	public CMElementalProperties properties;
	public CMColorScheme colorScheme;
	public int component;
	public int meta;
	
	public CMMaterialFluid(CMElementalProperties properties, int component, int meta) {
		super(CMPeriodicTable.register.get(properties.name).getComponentName(component));
		
		colorScheme = new CMColorScheme(properties, meta);
		
		this.properties = properties;
		this.component = component;
		this.meta = meta;
		
		setDensity(properties.fluidDensity);
		setViscosity(properties.fluidViscosity);
		setTemperature(properties.fluidTempreture);
	}

	public int getColor(){
		return colorScheme.color();
	}
	
	public CMMaterialFluidBlock generateFluidBlock(){
		return new CMMaterialFluidBlock(this, true);
	}
}
