package com.prolurker.cm.periodictable;

import java.awt.Color;

public class CMElementalFactory {
	public static CMMetal createMetal(String name){
		return createMetal(name, null, false);
	}
	
	public static CMMetal createMetal(String name, Color color){
		return createMetal(name, color, false);
	}
	
	public static CMMetal createMetal(String name, Color color, boolean isTEMaterial){
		return  createMetal(name, color, isTEMaterial, true);
	}
	
	public static CMMetal createMetal(String name, Color color, boolean isTEMaterial, boolean isWorldGen){
		CMElementalProperties properties = new CMElementalProperties();
		properties.name = name;
		properties.isTEMaterial = isTEMaterial;
		properties.isWorldGen = isWorldGen;
		
		if(color != null){
			properties.defaultColor = new Color[1];
			properties.defaultColor[0] = color;
		}
		return new CMMetal(properties);
	}
	
	public static CMMetal createMetal(String name, String[] isotopes, Color[] colors){
		CMElementalProperties properties = new CMElementalProperties();
		properties.name = name;
		properties.isotopes = isotopes;
		properties.defaultColor = colors;
		return new CMMetal(properties);
	}
}
