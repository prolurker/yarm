package com.prolurker.cm.periodictable; 

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;

import com.prolurker.cm.CM;
import com.prolurker.cm.helpers.RegisterHelper;
import com.prolurker.cm.helpers.StackHelper;
import com.prolurker.cm.materials.CMAlloy;
import com.prolurker.cm.materials.CMArmor;
import com.prolurker.cm.materials.CMAxe;
import com.prolurker.cm.materials.CMHoe;
import com.prolurker.cm.materials.CMMaterialBlock;
import com.prolurker.cm.materials.CMMaterialFluid;
import com.prolurker.cm.materials.CMMaterialItem;
import com.prolurker.cm.materials.CMMaterialItemBlock;
import com.prolurker.cm.materials.CMOre;
import com.prolurker.cm.materials.CMPickaxe;
import com.prolurker.cm.materials.CMShovel;
import com.prolurker.cm.materials.CMSword;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public abstract class CMMaterial{
	//material components
	public static final int ORE = 0;
	public static final int CHUNK = 15;
	public static final int CHUNK_DIRTY = 20;
	public static final int CLUMP = 16;
	public static final int CLUMP_DIRTY = 21;
	public static final int CRYSTAL = 17;
	public static final int CRYSTAL_DIRTY = 19;
	public static final int DUST = 1;
	public static final int DUST_DIRTY = 18;
	public static final int INGOT = 2;
	public static final int NUGGET = 3;
	public static final int BLOCK = 4;
	public static final int GEAR = 5;
	
	//fluids
	public static final int LIQUID = 22;
	
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
	public CMElementalProperties properties;
	
	//basic ore and processed stuff
	/**
	 * Ore block instance
	 */
	public CMOre ore;
	
	/**
	 * Alternative source block then an ore
	 */
	public ItemStack source;

	//tools
	public ToolMaterial toolMaterial;
	public CMPickaxe pickaxe;
	public CMAxe axe;
	public CMShovel shovel;
	public CMHoe hoe;
	public CMSword sword;
	
	//armor
	public ArmorMaterial armorMaterial;
	public CMArmor helmet;
	public CMArmor chestplate;
	public CMArmor leggings;
	public CMArmor boots;
	
	/**
	 * alloy base materials
	 */
	public CMAlloy alloy;
	
	/**
	 * base name of the material
	 */
	public String name;
	
	protected HashMap<String, Item> addedItems;
	protected HashMap<String, Block> addedBlocks;
	protected HashMap<String, CMMaterialFluid[]> addedFluids;
	
	public CMMaterial(CMElementalProperties properties){
		this.properties = properties;
		
		CM.logger.info("init material "+ properties.name);
		name = properties.name;
		
		this.addedItems = new HashMap<String, Item>();
		this.addedBlocks = new HashMap<String, Block>();
		this.addedFluids = new HashMap<String, CMMaterialFluid[]>();
	}
	
	/**
	 * each material needs to define its consistency
	 */
	public void addingStage(){}
	public void oreDictStage() {
		register();
	}
	
	public void recipeStage(){
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
		ore = new CMOre(properties, CMMaterial.ORE);
		this.addedBlocks.put(getComponentName(ORE), ore);
	}
	
	protected void addAlloy(CMAlloy alloy){
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
		sword = new CMSword(properties, toolMaterial);
		hoe = new CMHoe(properties, toolMaterial); 
		axe = new CMAxe(properties, toolMaterial);
		pickaxe = new CMPickaxe(properties, toolMaterial);
		shovel = new CMShovel(properties, toolMaterial);
		
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
		
		helmet = new CMArmor(properties, armorMaterial, 0);
		chestplate = new CMArmor(properties, armorMaterial, 1);
		leggings = new CMArmor(properties, armorMaterial, 2);
		boots = new CMArmor(properties, armorMaterial, 3);
		
		addedItems.put(getComponentName(HELMET), helmet);
		addedItems.put(getComponentName(CHESTPLATE), chestplate);
		addedItems.put(getComponentName(LEGGINGS), leggings);
		addedItems.put(getComponentName(BOOTS), boots);
	}

	protected void addItem(int component){ 
		this.addItem(component, true);
	}
	
	protected void addItem(int component, boolean customRendering){
		ItemStack item = new ItemStack(new CMMaterialItem(properties, component, customRendering));		
		this.addedItems.put(getComponentName(component), item.getItem());
	}
	
	protected void addBlock(int component){
		Block block = new CMMaterialBlock(properties, component);		
		this.addedBlocks.put(getComponentName(component), block);
	}
	
	protected void addFluid(int component){
		CMMaterialFluid[] fluidIsotopes = new CMMaterialFluid[properties.isotopes.length+1];
		if(properties.isotopes.length > 0){
			for(int i = 0; i < properties.isotopes.length; i++){
				CMMaterialFluid fluid = new CMMaterialFluid(properties, component, i);
				fluidIsotopes[i] = fluid;
			}
		}else{
			//no isotopes
			CMMaterialFluid fluid = new CMMaterialFluid(properties, component, 0);
			fluidIsotopes[0] = fluid;
		}
				
		this.addedFluids.put(getComponentName(component), fluidIsotopes);
	}
	
	public void registerItem(int component){
		String key = getComponentName(component);
		if(addedItems.containsKey(key)){
			this.registerItem(addedItems.get(key), key);
		}
	}
	
	public void registerBlock(int component){
		String key = getComponentName(component);
		if(addedBlocks.containsKey(key)){
			this.registerBlock(addedBlocks.get(key), key);
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
	
	public void registerFluid(int component){
		
	}
	
	protected void register(){
	    for(Entry<String, Item> e : addedItems.entrySet()) {
	    	registerItem(e.getValue(), e.getKey());
	    }
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
			item.setCreativeTab(CM.toolsTab);
		}else if(name.equals(getComponentName(GEAR))){
			item.setCreativeTab(CM.itemsTab);
		}else{
			item.setCreativeTab(CM.resourcesTab);
		}
		
		//add renderer
		if(name != getComponentName(INGOT) && item instanceof CMMaterialItem){
			IItemRenderer renderer = ((CMMaterialItem) item).getSpecialRenderer();
			if(renderer != null){
				MinecraftForgeClient.registerItemRenderer(item, renderer);
			}
		}
    	RegisterHelper.registerOreDictItem(name, item);
	}
	
	protected void registerBlock(Block block, String name){
		//register resource tab
    	block.setCreativeTab(CM.resourcesTab);
    	if(properties.isotopes.length != 0){
    		RegisterHelper.registerOreDictBlock(name, block, new CMMaterialItemBlock(block));
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
		if((ore != null || source != null) && getItemStack(INGOT) != null){
			ItemStack oreStack = source != null ? source : new ItemStack(ore);
			GameRegistry.addSmelting(oreStack, getItemStack(INGOT), 0.1f);
		}	
	}
	
	public void registerDustRecipe(){
		this.registerDustRecipe(getItemStack(DUST), getItemStack(INGOT));
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
		this.registerBlockRecipe(getItemStack(INGOT), getBlock(BLOCK));
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
		this.registerNuggetRecipe(getItemStack(NUGGET), getItemStack(INGOT));
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
		this.registerGearRecipe(getItemStack(INGOT));
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
		if(getItemStack(GEAR) != null && ingot != null){
			ItemStack ingotClone = StackHelper.clone(ingot, 1, meta);
			GameRegistry.addRecipe(new ShapedOreRecipe(StackHelper.clone(getItemStack(GEAR), 1, meta), new Object[]{ 
				" A ",
				"ABA",
				" A ",
				'A', ingotClone, 'B', Items.iron_ingot
			}));
		}
	}
	
	public void registerToolRecipes(){
		this.registerToolRecipes(getItemStack(INGOT));
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
		this.registerArmorRecipes(getItemStack(INGOT));
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
		int[] toolComponents = this.listToolComponents();
		int[] armorComponents = this.listArmorComponents();
		if(ArrayUtils.contains(toolComponents, component)){
			return CM.MODID + ":tools/" + getComponentName(component);
		}else if(ArrayUtils.contains(armorComponents, component)){
			return CM.MODID + ":armor/" + getComponentName(component);
		}else if(component == GEAR){
			return CM.MODID + ":components/" + getComponentName(component);
		}
		return CM.MODID + ":materials/" + getComponentName(component);
	}
	
	/**
	 * get name of a material component
	 */
	public String getComponentName(int component){
		String name = "";
		String materialName = Character.toUpperCase(this.name.charAt(0)) + this.name.substring(1);
		
		switch(component){
			//solids
			case ORE: 
				name = "ore" + materialName;
				break;
			case CHUNK: 
				name = "chunk" + materialName;
				break;
			case CHUNK_DIRTY: 
				name = "chunk_dirty" + materialName;
				break;
			case CLUMP: 
				name = "clump" + materialName;
				break;
			case CLUMP_DIRTY: 
				name = "clump_dirty" + materialName;
				break;
			case CRYSTAL: 
				name = "crystalline" + materialName;
				break;
			case CRYSTAL_DIRTY: 
				name = "crystalline_dirty" + materialName;
				break;
			case DUST_DIRTY: 
				name = "dust_dirty" + materialName;
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
			
			//fluids
			case LIQUID:
				name = "liquid"+ materialName;
				
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
	
	protected static int[] listToolComponents(){
		int[] toolComponents = {
			AXE,
			SWORD,
			PICKAXE,
			SHOVEL,
			HOE,
		};
		return toolComponents;
	}
	
	protected static int[] listArmorComponents(){
		int[] armorComponents = {
			HELMET,
			CHESTPLATE,
			LEGGINGS,
			BOOTS,
		};
		return armorComponents;
	}
	
	public Block getBlock(int component){
		return addedBlocks.get(getComponentName(component));
	}
	
	public Item getItem(int component){
		return addedItems.get(getComponentName(component));
	}
	
	public ItemStack getItemStack(int component){
		return getItemStack(component, 1, 0);
	}
	
	public ItemStack getItemStack(int component, int meta){
		return getItemStack(component, 1, meta);
	}
	
	public ItemStack getItemStack(int component, int size, int meta){
		if(component == BLOCK || component == ORE){
			return new ItemStack(addedBlocks.get(getComponentName(component)), size, meta);
		}else{
			return new ItemStack(addedItems.get(getComponentName(component)), size, meta);
		}
	}
}
