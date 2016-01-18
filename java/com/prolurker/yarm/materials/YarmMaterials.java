package com.prolurker.yarm.materials;

import java.util.HashMap;
import java.util.Map.Entry;

import com.prolurker.yarm.materials.common.Boron;
import com.prolurker.yarm.materials.common.BoronSteel;
import com.prolurker.yarm.materials.common.Chromium;
import com.prolurker.yarm.materials.common.Graphite;
import com.prolurker.yarm.materials.common.Lead;
import com.prolurker.yarm.materials.common.MOX;
import com.prolurker.yarm.materials.common.Monazite;
import com.prolurker.yarm.materials.common.Natrium;
import com.prolurker.yarm.materials.common.Nickel;
import com.prolurker.yarm.materials.common.NickelSteel;
import com.prolurker.yarm.materials.common.Plutonium;
import com.prolurker.yarm.materials.common.Silver;
import com.prolurker.yarm.materials.common.StainlessSteel;
import com.prolurker.yarm.materials.common.Steel;
import com.prolurker.yarm.materials.common.Thorium;
import com.prolurker.yarm.materials.common.Uranium;

import java.util.Random;

import net.minecraft.init.Blocks;

public class YarmMaterials{
	//base materials
	public static Lead lead;
	public static Natrium natrium;
	public static Chromium chromium;
	public static Nickel nickel;
	public static Monazite monazite;
	
	//moderators
	public static Graphite graphite;
	
	//control rod components
	public static Silver silver;
	public static Boron boron; // boron steel can also be used
	
	//radioactive materials
	public static Thorium thorium;
	public static Uranium uranium;
	public static Plutonium plutonium;
	
	//fuel alloys
	public static MOX mox;
	
	//steel alloys
	public static Steel steel;
	public static StainlessSteel stainlessSteel;
	public static BoronSteel boronSteel;
	public static NickelSteel nickelSteel;
	
	public static HashMap<String, YarmMaterial> register;
	
	public static void registerMaterials(){
		register = new HashMap<String, YarmMaterial>();
		
		monazite = new Monazite();
		lead = new Lead();
		boron = new Boron();
		thorium = new Thorium();
		
		uranium = new Uranium();
		plutonium = new Plutonium();
		
		mox = new MOX();
		
		chromium = new Chromium();
		graphite = new Graphite();
		silver = new Silver();
		natrium = new Natrium();
		nickel = new Nickel();
		
		//steel
		steel = new Steel();
		stainlessSteel = new StainlessSteel();
		boronSteel = new BoronSteel();
		nickelSteel = new NickelSteel();

		register(lead);
		register(boron);
		register(thorium);
		register(monazite);
		
		register(uranium);
		register(plutonium);
		register(mox);
	
		register(graphite);
		register(silver);
		register(natrium);
		register(chromium);
		register(nickel);
		
		//steel
		register(steel);
		register(stainlessSteel);
		register(boronSteel);
		register(nickelSteel);
	}
	
	private static void register(YarmMaterial material){
		register.put(material.name, material);
	}
	
	public static void performAddingStage(){
		for(Entry<String, YarmMaterial> e : register.entrySet()){
			e.getValue().addingStage();
		}
	}
	
	public static void performOreDictStage(){
		for(Entry<String, YarmMaterial> e : register.entrySet()){
			e.getValue().oreDictStage();
		}
	}
	
	public static void performOreStage(){
		for(Entry<String, YarmMaterial> e : register.entrySet()){
			e.getValue().registerOre();
		}
	}
	
	public static void performDustStage(){
		for(Entry<String, YarmMaterial> e : register.entrySet()){
			e.getValue().registerDust();
		}
	}
	
	public static void performIngotStage(){
		for(Entry<String, YarmMaterial> e : register.entrySet()){
			e.getValue().registerIngot();
		}
	}
	
	public static void performBlockStage(){
		for(Entry<String, YarmMaterial> e : register.entrySet()){
			e.getValue().registerBlock();
		}
	}
	
	public static void performNuggetStage(){
		for(Entry<String, YarmMaterial> e : register.entrySet()){
			e.getValue().registerNugget();
		}
	}
	
	public static void performGearStage(){
		for(Entry<String, YarmMaterial> e : register.entrySet()){
			e.getValue().registerGear();
		}
	}
	
	public static void performToolsAndArmorStage(){
		for(Entry<String, YarmMaterial> e : register.entrySet()){
			e.getValue().registerToolsAndArmor();
		}
	}
	
	public static void performRecipeStage(){
		for(Entry<String, YarmMaterial> e : register.entrySet()){
			e.getValue().recipeStage();
		}
	}
}
