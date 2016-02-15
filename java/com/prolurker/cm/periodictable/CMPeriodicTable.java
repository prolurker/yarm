package com.prolurker.cm.periodictable;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map.Entry;

import com.prolurker.cm.CM;

public class CMPeriodicTable {
	public static HashMap<String, CMMaterial> register;
	
	public static void init(){
		register = new HashMap<String, CMMaterial>();
		
		registerDiatomicNonMetals();
		registerNobleGasses();
		registerAlkalineMetals();
		registerAlkalineEarthMetals();
		registerTransitionalMetals();
		registerPostTransitionalMetals();
		registerMetalloid();
		registerPolyatomicNonMetals();
		registerActinides();
		registerLanthanides();
	}
	
	public static void registerDiatomicNonMetals(){
		
	}
	
	public static void registerNobleGasses(){
		
	}
	
	public static void registerAlkalineMetals(){
		
	}
	
	public static void registerAlkalineEarthMetals(){

	}
	
	public static void registerTransitionalMetals(){
		//22, Ti
		register(CMElementalFactory.createMetal("titanium", new Color(165, 163, 215)));
		//24, Cr
		register(CMElementalFactory.createMetal("chromium", new Color(221, 251, 238)));
		//25, Mn
		register(CMElementalFactory.createMetal("manganese", new Color(255, 219, 251)));
		//26, Fe
		register(CMElementalFactory.createMetal("iron", new Color(216, 216, 216), true));
		//27, Co
		register(CMElementalFactory.createMetal("cobalt", new Color(50, 44, 176)));
		//28, Ni
		register(CMElementalFactory.createMetal("nickel", new Color(253, 251, 212), true));
		//29, Cu
		register(CMElementalFactory.createMetal("copper", new Color(255, 155, 11), true));
		//30, Zn
		register(CMElementalFactory.createMetal("zinc", new Color(249, 232, 244)));
		
		//-----------------------------------------------
		
		//40, Zr
		register(CMElementalFactory.createMetal("zirconium", new Color(200, 108, 128)));
		//46, Pd
		register(CMElementalFactory.createMetal("palladium", new Color(255, 251, 214)));
		//47, Ag
		register(CMElementalFactory.createMetal("silver", null, true));
		//48, Cd
		register(CMElementalFactory.createMetal("cadmium", new Color(249, 213, 155)));
		
		//-----------------------------------------------
		
		//72, Hf
		register(CMElementalFactory.createMetal("hafnium", new Color(248, 255, 206)));
		//74, W
		register(CMElementalFactory.createMetal("tungsten", new Color(97, 96, 100)));
		//76, Os
		register(CMElementalFactory.createMetal("osmium", new Color(80, 107, 143)));
		//77, Ir
		register(CMElementalFactory.createMetal("iridium", new Color(203, 215, 137)));
		//78, Pt
		register(CMElementalFactory.createMetal("platinum", new Color(227, 227, 227)));
		//79, Au
		register(CMElementalFactory.createMetal("gold", new Color(255 , 255 , 11), true));
	}
	
	public static void registerPostTransitionalMetals(){
		//13, Al
		register(CMElementalFactory.createMetal("aluminium", new Color(174 , 179 , 181)));
		
		//-----------------------------------------------
		
		//50, Sn
		register(CMElementalFactory.createMetal("tin", null, true));
		
		//-----------------------------------------------
		
		//82, Pb
		register(CMElementalFactory.createMetal("lead", null, true));
		//83, Pb
		register(CMElementalFactory.createMetal("bismuth", new Color(143 , 171 , 149)));
	}
	
	public static void registerMetalloid(){
		//5, B
		register(CMElementalFactory.createMetal("boron", new Color(23, 155, 98)));
	}
	
	public static void registerPolyatomicNonMetals(){
		
	}
	
	public static void registerLanthanides(){
		
	}
	
	public static void registerActinides(){
		//90, Th
		register(CMElementalFactory.createMetal("thorium", new Color(108, 182, 255)));
		//92, Ur
		String[] isotopes = {
			"natural", "depleted", "enriched"
		};
		register(CMElementalFactory.createMetal("uranium", isotopes, null));
		//94, Pu
		register(CMElementalFactory.createMetal("plutonium", new Color(230, 85, 232), false, false));
	}
	
	public static void registerAlloys(){
		
	}
	
	private static void register(CMMaterial material){
		CM.logger.info("registering material " + material.name);
		register.put(material.name, material);
	}
	
	public static void performAddingStage(){
		for(Entry<String, CMMaterial> e : register.entrySet()){
			e.getValue().addingStage();
		}
	}
	
	public static void performOreDictStage(){
		for(Entry<String, CMMaterial> e : register.entrySet()){
			e.getValue().oreDictStage();
		}
	}
	
	public static void performBlockStage(int component){
		for(Entry<String, CMMaterial> e : register.entrySet()){
			e.getValue().registerBlock(component);
		}
	}
	
	public static void performItemStage(int component){
		for(Entry<String, CMMaterial> e : register.entrySet()){
			e.getValue().registerItem(component);
		}
	}
	
	public static void performToolsAndArmorStage(){
		for(Entry<String, CMMaterial> e : register.entrySet()){
			e.getValue().registerToolsAndArmor();
		}
	}
	
	public static void performRecipeStage(){
		for(Entry<String, CMMaterial> e : register.entrySet()){
			e.getValue().recipeStage();
		}
	}
}

