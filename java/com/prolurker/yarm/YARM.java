package com.prolurker.yarm;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prolurker.yarm.common.YarmComponentBlock;
import com.prolurker.yarm.common.YarmComponentBlocks;
import com.prolurker.yarm.common.YarmComponentItems;
import com.prolurker.yarm.common.YarmCreativeTab;
import com.prolurker.yarm.helpers.TCHelper;
import com.prolurker.yarm.helpers.TE4Helper;
import com.prolurker.yarm.machine.MachineGuiHandler;
import com.prolurker.yarm.materials.YarmMaterials;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import javafx.scene.control.TableFocusModel;

@Mod(modid = YARM.MODID, name = YARM.MODNAME, version = YARM.VERSION, dependencies = "required-after:CoFHCore;required-after:ThermalFoundation;required-after:ThermalExpansion;required-after:ThermalDynamics")
public class YARM{
    public static final String MODID = "YARM";
    public static final String MODNAME = "YARM";
    public static final String VERSION = "0.1";
    public static final Logger logger = LogManager.getFormatterLogger(MODID);
    
    //creative tabs
    public static YarmCreativeTab resourcesTab;
    public static YarmCreativeTab componentTab;
    public static YarmCreativeTab toolsTab;
    
    @Instance(value = YARM.MODID) //Tell Forge what instance to use.
    public static YARM instance;
        
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	YarmMaterials.registerMaterials();
    	YarmMaterials.performAddingStage();
    	resourcesTab = new YarmCreativeTab(CreativeTabs.getNextID(), "resources", YarmMaterials.uranium.ingot);
    	componentTab = new YarmCreativeTab(CreativeTabs.getNextID(), "components", YarmMaterials.boronSteel.gear);
    	toolsTab = new YarmCreativeTab(CreativeTabs.getNextID(), "tools", new ItemStack(YarmMaterials.boronSteel.pickaxe));
    }
        
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	//register all the basic materials in the following order
    	YarmMaterials.performOreStage();
    	YarmMaterials.performDustStage();
    	YarmMaterials.performIngotStage();
    	YarmMaterials.performNuggetStage();
    	YarmMaterials.performBlockStage();
    	YarmMaterials.performGearStage();
    	YarmMaterials.performToolsAndArmorStage();
    	
    	YarmComponentItems.registerItems();
    	YarmComponentBlocks.registerBlocks();
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(YARM.instance, new MachineGuiHandler());
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	YarmMaterials.performRecipeStage();
    	YarmComponentItems.registerRecipes();
    	YarmComponentBlocks.registerRecipes();
    	
    	TE4Helper.addAlloyIsotopeSeperationRecipes();
    	if(Loader.isModLoaded("TConstruct")){
    		TCHelper.addAlloyIsotopeSeperationRecipes();
    	}
    }
}
