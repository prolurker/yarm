package com.prolurker.yarm.materials;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import com.prolurker.yarm.YARM;
import com.prolurker.yarm.helpers.RegisterHelper;
import com.prolurker.yarm.helpers.StackHelper;
import com.prolurker.yarm.helpers.TE4Helper;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public abstract class YarmMaterial{
	//material components
	public static final int ORE = 0;
	public static final int DUST = 1;
	public static final int INGOT = 2;
	public static final int NUGGET = 3;
	public static final int BLOCK = 4;
	public static final int GEAR = 5;
	
	//tools
	public static final int PICKAXE = 6;
	public static final int AXE = 7;
	public static final int SHOVEL = 8;
	public static final int HOE = 9;
	public static final int SWORD = 10;
	
	//armor
	public static final int HELMET = 11;
	public static final int CHESTPLATE = 12;
	public static final int LEGGINGS = 13;
	public static final int BOOTS = 14;
	
	/**
	 * the properties of the material
	 */
	public YarmMaterialProperties properties;
	
	//basic ore and processed stuff
	/**
	 * Ore block instance
	 */
	public YarmOre ore;
	
	/**
	 * Alternative source block then an ore
	 */
	public ItemStack source;
	
	/**
	 * Ingot item instance
	 */
	public ItemStack dust;
	
	/**
	 * Block instance (of material)
	 */
	public Block block;
	
	/**
	 * Ingot item instance
	 */
	public ItemStack ingot;
	
	/**
	 * Nugget item instance
	 */
	public ItemStack nugget;
	
	//machine components
	/**
	 * Gear instance
	 */
	public ItemStack gear;

	//tools
	public ToolMaterial toolMaterial;
	public YarmMaterialPickaxe pickaxe;
	public YarmMaterialAxe axe;
	public YarmMaterialShovel shovel;
	public YarmMaterialHoe hoe;
	public YarmMaterialSword sword;
	
	//armor
	public ArmorMaterial armorMaterial;
	public YarmMaterialArmor helmet;
	public YarmMaterialArmor chestplate;
	public YarmMaterialArmor leggings;
	public YarmMaterialArmor boots;
	
	/**
	 * alloy base materials
	 */
	public YarmAlloy alloy;
	
	/**
	 * base name of the material
	 */
	public String name;
	
	/**
	 * is a TE material
	 */
	public boolean isTEMaterial;
	
	protected HashMap<String, Item> addedItems;
	protected HashMap<String, Block> addedBlocks;
	
	/**
	 * preinit material names
	 * @param defaultName
	 */
	public YarmMaterial(){
		this.properties = defineProperties();
		
		YARM.logger.info("init material "+ properties.name);
		name = properties.name;
		this.isTEMaterial = properties.isTEMaterial; //backwards compatibility
		
		this.addedItems = new HashMap<String, Item>();
		this.addedBlocks = new HashMap<String, Block>();
	}
	

	/**
	 * define propeties of the material
	 * @return YarmMaterialProperties
	 */
	protected abstract YarmMaterialProperties defineProperties();
	
	/**
	 * each material needs to define its consistency
	 */
	public void addingStage(){}
	public void oreDictStage() {
		register();
	}
	
	public void recipeStage(){
		YARM.logger.info("adding recipes for material "+ name);
		registerRecipes();
	}
	
	protected void addOre(ItemStack source){
		this.source = source;
	}
	
	//basic ore stuff
	/**
	 * register ore for a certain material
	 */
	protected void addOre(){
		ore = new YarmOre(properties, YarmMaterial.ORE);
		this.addedBlocks.put(getComponentName(ORE), ore);
	}
	
	/**
	 * register dust for a certain material
	 */
	protected void addDust(){
		dust = new ItemStack(new YarmMaterialItem(properties, YarmMaterial.DUST));		
		this.addedItems.put(getComponentName(DUST), dust.getItem());
	}
	
	/**
	 * register ingot for a certain material
	 */
	protected void addIngot(){
		ingot = new ItemStack(new YarmMaterialItem(properties, YarmMaterial.INGOT));
		this.addedItems.put(getComponentName(INGOT), ingot.getItem());
	}
	
	/**
	 * register a nugget item
	 */
	protected void addNugget(){
		nugget = new ItemStack(new YarmMaterialItem(properties, YarmMaterial.NUGGET));
		this.addedItems.put(getComponentName(NUGGET), nugget.getItem());
	}
	
	
	protected void addGear(){
		gear = new ItemStack(new YarmMaterialItem(properties, YarmMaterial.GEAR));
		this.addedItems.put(getComponentName(GEAR), gear.getItem());
	}
	
	/**
	 * register a solid block of this material
	 */
	protected void addBlock(){
		block = new YarmMaterialBlock(properties, YarmMaterial.BLOCK);
		this.addedBlocks.put(getComponentName(BLOCK), block);
	}
	
	protected void addAlloy(YarmAlloy alloy){
		this.alloy = alloy;
	}
	
	/**
	 * add all tools in one go
	 */
	protected void addTools(){
		toolMaterial = EnumHelperClient.addToolMaterial(
			properties.name, 
			properties.toolHarvestLevel, 
			properties.toolDurability, 
			properties.toolMiningSpeed, 
			properties.toolDamage, 
			properties.toolEnchantability
		);
		sword = new YarmMaterialSword(properties, toolMaterial);
		hoe = new YarmMaterialHoe(properties, toolMaterial);
		axe = new YarmMaterialAxe(properties, toolMaterial);
		pickaxe = new YarmMaterialPickaxe(properties, toolMaterial);
		shovel = new YarmMaterialShovel(properties, toolMaterial);
		
		addedItems.put(getComponentName(AXE), axe);
		addedItems.put(getComponentName(SWORD), sword);
		addedItems.put(getComponentName(PICKAXE), pickaxe);
		addedItems.put(getComponentName(HOE), hoe);
		addedItems.put(getComponentName(SHOVEL), shovel);
	}
	
	/**
	 * add all armor at once
	 */
	protected void addArmor(){
		armorMaterial = EnumHelperClient.addArmorMaterial(
			properties.name, 
			properties.armorDurability, 
			properties.armorDamageReduction, 
			properties.armorEnchantability
		);
		
		helmet = new YarmMaterialArmor(properties, armorMaterial, 0);
		chestplate = new YarmMaterialArmor(properties, armorMaterial, 1);
		leggings = new YarmMaterialArmor(properties, armorMaterial, 2);
		boots = new YarmMaterialArmor(properties, armorMaterial, 3);
		
		addedItems.put(getComponentName(HELMET), helmet);
		addedItems.put(getComponentName(CHESTPLATE), chestplate);
		addedItems.put(getComponentName(LEGGINGS), leggings);
		addedItems.put(getComponentName(BOOTS), boots);
	}
	
	public void registerOre(){
		String key = getComponentName(ORE);
		if(addedBlocks.containsKey(key)){
			this.registerBlock(addedBlocks.get(key), key);
		}
	}
	
	public void registerDust(){
		String key = getComponentName(DUST);
		if(addedItems.containsKey(key)){
			this.registerItem(addedItems.get(key), key);
		}
	}
	
	public void registerIngot(){
		String key = getComponentName(INGOT);
		if(addedItems.containsKey(key)){
			this.registerItem(addedItems.get(key), key);
		}
	}
	
	public void registerNugget(){
		String key = getComponentName(NUGGET);
		if(addedItems.containsKey(key)){
			this.registerItem(addedItems.get(key), key);
		}
	}
	
	public void registerBlock(){
		String key = getComponentName(BLOCK);
		if(addedBlocks.containsKey(key)){
			this.registerBlock(addedBlocks.get(key), key);
		}
	}
	
	public void registerGear(){
		String key = getComponentName(GEAR);
		if(addedItems.containsKey(key)){
			this.registerItem(addedItems.get(key), key);
		}
	}
	
	public void registerToolsAndArmor(){
		int[] order = { PICKAXE, AXE, SHOVEL, HOE, SWORD, HELMET, CHESTPLATE, LEGGINGS, BOOTS };
		String[] components = new String[order.length];
		
		for(int i = 0; i < order.length; i++){
			components[i] = getComponentName(order[i]);
		}
		
		for(int i = 0; i < components.length; i++){
			if(addedItems.containsKey(components[i])){
				registerItem(addedItems.get(components[i]), components[i]);
			}
		}
	}
	
	protected void register(){
		YARM.logger.debug("YARM Material item count "+addedItems.size());
	    for(Entry<String, Item> e : addedItems.entrySet()) {
	    	registerItem(e.getValue(), e.getKey());
	    }
	    YARM.logger.debug("YARM Material block count "+addedBlocks.size());
	    for(Entry<String, Block> e : addedBlocks.entrySet()) {
	    	registerBlock(e.getValue(), e.getKey());
	    }
	}
	
	protected void registerItem(Item item, String name){
		//register resource tab
		String[] toolComponents = {
			getComponentName(AXE),
			getComponentName(SWORD),
			getComponentName(PICKAXE),
			getComponentName(SHOVEL),
			getComponentName(HOE),
			getComponentName(HELMET),
			getComponentName(CHESTPLATE),
			getComponentName(LEGGINGS),
			getComponentName(BOOTS),
		};
		if(Arrays.asList(toolComponents).contains(name)){
			item.setCreativeTab(YARM.toolsTab);
		}else if(name.equals(getComponentName(GEAR))){
			item.setCreativeTab(YARM.resourcesTab);
		}
    	RegisterHelper.registerOreDictItem(name, item);
	}
	
	protected void registerBlock(Block block, String name){
		//register resource tab
    	block.setCreativeTab(YARM.resourcesTab);
    	if(properties.isotopes.length != 0){
    		RegisterHelper.registerOreDictBlock(name, block, new YarmMaterialItemBlock(block));
    	}else{
    		RegisterHelper.registerOreDictBlock(name, block);
    	}
	}
	
	/**
	 * register basic smelting recipe for a material
	 */
	protected void registerRecipes(){
		registerOreRecipe();
		registerDustRecipe();
		registerBlockRecipe();
		registerNuggetRecipe();
		registerAlloyRecipe();
		registerGearRecipe();
		registerToolRecipes();
		registerArmorRecipes();
	}

	public void registerAlloyRecipe(){
		if(alloy != null){
			alloy.addRecipes();
		}
	}
	
	protected void registerOreRecipe(){
		// ore smelting
		if((ore != null || source != null) && ingot != null){
			ItemStack oreStack = source != null ? source : new ItemStack(ore);
			GameRegistry.addSmelting(oreStack, ingot, 0.1f);
		}	
	}
	
	public void registerDustRecipe(){
		this.registerDustRecipe(dust, ingot);
	}
	
	protected void registerDustRecipe(ItemStack dust, ItemStack ingot){
		if(properties.isotopes.length != 0){
			for(int i = 0; i < properties.isotopes.length; i++){
				this.registerDustRecipe(dust, ingot, i);
			}
		}else{
			this.registerDustRecipe(dust, ingot, 0);
		}
	}
	
	protected void registerDustRecipe(ItemStack dust, ItemStack ingot, int meta){
		//dust smelting
		if(dust != null && ingot != null){
			ItemStack dustClone = StackHelper.clone(dust, 1, meta);
			ItemStack ingotClone = StackHelper.clone(dust, 1, meta);
			
			GameRegistry.addSmelting(dust, ingot, 0.1f);
		}
	}
	
	public void registerBlockRecipe(){
		this.registerBlockRecipe(ingot, block);
	}
	
	protected void registerBlockRecipe(ItemStack ingot, Block block){
		if(properties.isotopes.length != 0){
			for(int i = 0; i < properties.isotopes.length; i++){
				this.registerBlockRecipe(ingot, block, i);
			}
		}else{
			this.registerBlockRecipe(ingot, block, 0);
		}
	}
	
	protected void registerBlockRecipe(ItemStack ingot, Block block, int meta){
		//nugget to block, block to ingots
		if(ingot != null && block != null){
			ItemStack ingotClone = StackHelper.clone(ingot, 9, meta);
			ItemStack blockClone = new ItemStack(block, 1, meta);
			
			GameRegistry.addRecipe(new ShapelessOreRecipe(ingotClone, blockClone));
			
			ingotClone.stackSize = 1;
			GameRegistry.addRecipe(new ShapedOreRecipe(blockClone, new Object[]{ 
				"AAA",
				"AAA",
				"AAA",
				'A', ingotClone
			}));
		}
	}
	
	public void registerNuggetRecipe(){
		this.registerNuggetRecipe(nugget, ingot);
	}
	
	protected void registerNuggetRecipe(ItemStack nugget, ItemStack ingot){
		if(properties.isotopes.length != 0){
			for(int i = 0; i < properties.isotopes.length; i++){
				this.registerNuggetRecipe(nugget, ingot, i);
			}
		}else{
			this.registerNuggetRecipe(nugget, ingot, 0);
		}	
	}
	
	protected void registerNuggetRecipe(ItemStack nugget, ItemStack ingot, int meta){
		//nugget to ingot, ingot to nugget
		if(nugget != null && ingot != null){
			ItemStack nuggetClone = StackHelper.clone(nugget, 9, meta);
			ItemStack ingotClone = StackHelper.clone(ingot, 1, meta);
			
			GameRegistry.addRecipe(new ShapelessOreRecipe(nuggetClone, ingotClone));
			
			nuggetClone.stackSize = 1;
			GameRegistry.addRecipe(new ShapedOreRecipe(ingotClone, new Object[]{ 
				"AAA",
				"AAA",
				"AAA",
				'A', nuggetClone
			}));
		}
	}
	
	public void registerGearRecipe(){
		this.registerGearRecipe(ingot);
	}
	
	protected void registerGearRecipe(ItemStack ingot){
		if(properties.isotopes.length != 0){
			for(int i = 0; i < properties.isotopes.length; i++){
				this.registerGearRecipe(ingot, i);
			}
		}else{
			this.registerGearRecipe(ingot, 0);
		}	
	}
	
	protected void registerGearRecipe(ItemStack ingot, int meta){
		if(gear != null && ingot != null){
			ItemStack ingotClone = StackHelper.clone(ingot, 1, meta);
			GameRegistry.addRecipe(new ShapedOreRecipe(StackHelper.clone(gear, 1, meta), new Object[]{ 
				" A ",
				"ABA",
				" A ",
				'A', ingotClone, 'B', Items.iron_ingot
			}));
		}
	}
	
	public void registerToolRecipes(){
		this.registerToolRecipes(ingot);
	}
	
	
	protected void registerToolRecipes(ItemStack ingot){
		if(properties.isotopes.length != 0){
			for(int i = 0; i < properties.isotopes.length; i++){
				this.registerToolRecipes(ingot, i);
			}
		}else{
			this.registerToolRecipes(ingot, 0);
		}	
	}
	
	protected void registerToolRecipes(ItemStack ingot, int meta){
		if(toolMaterial != null && ingot != null){
			ItemStack ingotClone = StackHelper.clone(ingot, 1, meta);
			GameRegistry.addRecipe(new ShapedOreRecipe(shovel, new Object[]{ 
				" A ",
				" B ",
				" B ",
				'A', ingotClone, 'B', Items.stick
			}));
			GameRegistry.addRecipe(new ShapedOreRecipe(axe, new Object[]{ 
				"AA ",
				"AB ",
				" B ",
				'A', ingotClone, 'B', Items.stick
			}));
			GameRegistry.addRecipe(new ShapedOreRecipe(hoe, new Object[]{ 
				"AA ",
				" B ",
				" B ",
				'A', ingotClone, 'B', Items.stick
			}));
			GameRegistry.addRecipe(new ShapedOreRecipe(pickaxe, new Object[]{ 
				"AAA",
				" B ",
				" B ",
				'A', ingotClone, 'B', Items.stick
			}));
			GameRegistry.addRecipe(new ShapedOreRecipe(sword, new Object[]{ 
				" A ",
				" A ",
				" B ",
				'A', ingotClone, 'B', Items.stick
			}));
		}
	}
	
	public void registerArmorRecipes(){
		this.registerArmorRecipes(ingot);
	}
	
	protected void registerArmorRecipes(ItemStack ingot){
		if(properties.isotopes.length != 0){
			for(int i = 0; i < properties.isotopes.length; i++){
				this.registerArmorRecipes(ingot, i);
			}
		}else{
			this.registerArmorRecipes(ingot, 0);
		}	
	}
	
	protected void registerArmorRecipes(ItemStack ingot, int meta){
		if(armorMaterial != null && ingot != null){
			ItemStack ingotClone = StackHelper.clone(ingot, 1, meta);
			GameRegistry.addRecipe(new ShapedOreRecipe(helmet, new Object[]{ 
				"AAA",
				"A A",
				"   ",
				'A', ingotClone
			}));
			GameRegistry.addRecipe(new ShapedOreRecipe(chestplate, new Object[]{ 
				"A A",
				"AAA",
				"AAA",
				'A', ingotClone
			}));
			GameRegistry.addRecipe(new ShapedOreRecipe(leggings, new Object[]{ 
				"AAA",
				"A A",
				"A A",
				'A', ingotClone
			}));
			GameRegistry.addRecipe(new ShapedOreRecipe(boots, new Object[]{ 
				"   ",
				"A A",
				"A A",
				'A', ingotClone
			}));
		}
	}
	
	/**
	 * get the texture name for a material
	 * @param component
	 * @return
	 */
	public String getTextureName(int component){
		return YARM.MODID + ":" + getComponentName(component);
	}
	
	/**
	 * get name of a material component
	 */
	public String getComponentName(int component){
		String name = "";
		String materialName = Character.toUpperCase(this.name.charAt(0)) + this.name.substring(1);
		
		switch(component){
			case ORE: 
				name = "ore" + materialName;
				break;
			case DUST: 
				name = "dust" + materialName;
				break;
			case INGOT:
				name = "ingot" + materialName;
				break;
			case NUGGET:
				name = "nugget" + materialName;
				break;
			case BLOCK:
				name = "block" + materialName;
				break;
			case GEAR:
				name = "gear" + materialName;
				break;
				
			//tools
			case PICKAXE:
				name = "pickaxe" + materialName;
				break;
			case AXE:
				name = "axe" + materialName; 
				break;
			case SHOVEL:
				name = "shovel" + materialName;
				break;
			case HOE:
				name = "hoe" + materialName;
				break;
			case SWORD:
				name = "sword" + materialName;
				break;
				
			//armor
			case HELMET:
				name = "helmet" + materialName;
				break;
			case CHESTPLATE:
				name = "chestplate" + materialName; 
				break;
			case LEGGINGS:
				name = "leggings" + materialName;
				break;
			case BOOTS:
				name = "boots" + materialName;
				break;
		};
		
		return name;
	}
	
	protected ItemStack getComponent(int component, int size){
		return getComponent(component, size, 0);
	}
	
	protected ItemStack getComponent(int component, int size, int metadata){
		switch(component){
			case ORE:
				return new ItemStack(ore, size, metadata);
			case DUST: 
				return StackHelper.clone(dust, size, metadata);
			case INGOT:
				return StackHelper.clone(ingot, size, metadata);
			case NUGGET:
				return StackHelper.clone(nugget, size, metadata);
			case BLOCK:
				return new ItemStack(block, size, metadata);
			case GEAR:
				return StackHelper.clone(gear, size, metadata);
			default:
				return null;
		} 
	}
	
	/**
	 * Shorthand function
	 * @return
	 */
	public YarmMaterial getMaterial(){
		return YarmMaterials.register.get(name);
	}
}
