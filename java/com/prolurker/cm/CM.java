package com.prolurker.cm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prolurker.cm.gui.MachineGuiHandler;
import com.prolurker.cm.helpers.TCHelper;
import com.prolurker.cm.helpers.TE4Helper;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;
import com.prolurker.cm.tabs.CMCreativeTab;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = CM.MODID, name = CM.MODNAME, version = CM.VERSION, dependencies = "required-after:CoFHCore;required-after:ThermalFoundation;required-after:ThermalExpansion;required-after:ThermalDynamics")
public class CM{
    public static final String MODID = "CM";
    public static final String MODNAME = "Chemical Metalworks";
    public static final String VERSION = "0.1";
    public static final Logger logger = LogManager.getFormatterLogger(MODID);
    
    //creative tabs
    public static CMCreativeTab resourcesTab;
    public static CMCreativeTab blocksTab;
    public static CMCreativeTab itemsTab;
    public static CMCreativeTab toolsTab;
    
    @Instance(value = CM.MODID) //Tell Forge what instance to use.
    public static CM instance;
        
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	CMPeriodicTable.init();
    	CMPeriodicTable.performAddingStage();
    	
    	resourcesTab = new CMCreativeTab(CreativeTabs.getNextID(), "resources", CMPeriodicTable.register.get("uranium").getItemStack(CMMaterial.INGOT));
    	//blocksTab = new CMCreativeTab(CreativeTabs.getNextID(), "blocks", CMMaterials.boronSteel.getItemStack(CMMaterial.GEAR));
    	//itemsTab = new CMCreativeTab(CreativeTabs.getNextID(), "items", new ItemStack(CMMaterials.boronSteel.pickaxe));
    	//toolsTab = new CMCreativeTab(CreativeTabs.getNextID(), "tools", new ItemStack(CMMaterials.boronSteel.pickaxe));
    }
        
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	//register all the basic materials in the following order
    	
    	CMPeriodicTable.performBlockStage(CMMaterial.ORE);
    	CMPeriodicTable.performItemStage(CMMaterial.CHUNK);
    	CMPeriodicTable.performItemStage(CMMaterial.CHUNK_DIRTY);
    	CMPeriodicTable.performItemStage(CMMaterial.CLUMP);
    	CMPeriodicTable.performItemStage(CMMaterial.CLUMP_DIRTY);
    	CMPeriodicTable.performItemStage(CMMaterial.CRYSTAL);
    	CMPeriodicTable.performItemStage(CMMaterial.CRYSTAL_DIRTY);
    	CMPeriodicTable.performItemStage(CMMaterial.DUST);
    	CMPeriodicTable.performItemStage(CMMaterial.DUST_DIRTY);
    	CMPeriodicTable.performItemStage(CMMaterial.INGOT);
    	CMPeriodicTable.performItemStage(CMMaterial.NUGGET);
    	CMPeriodicTable.performBlockStage(CMMaterial.BLOCK);
    	CMPeriodicTable.performItemStage(CMMaterial.GEAR);
    	CMPeriodicTable.performToolsAndArmorStage();
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(CM.instance, new MachineGuiHandler());
    	
    	//CMItems.registerItems();
    	//CMBlocks.registerBlocks();
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	CMPeriodicTable.performRecipeStage();
    	//CMItems.registerRecipes();
    	//CMBlocks.registerRecipes();
    	
    	TE4Helper.addAlloyIsotopeSeperationRecipes();
    	if(Loader.isModLoaded("TConstruct")){
    		TCHelper.addAlloyIsotopeSeperationRecipes();
    	}
    }
}
