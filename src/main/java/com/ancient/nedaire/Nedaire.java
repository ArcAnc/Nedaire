/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ancient.nedaire.api.NedaireMaterials;
import com.ancient.nedaire.content.itemGroup.NItemGroup;
import com.ancient.nedaire.data.NedaireBlockTagsProvider;
import com.ancient.nedaire.data.NedaireItemTagsProvider;
import com.ancient.nedaire.data.NedaireRecipesProvider;
import com.ancient.nedaire.util.database.NedaireDatabase;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NedaireDatabase.MOD_ID)
//@Mod.EventBusSubscriber(modid = NedaireDatabase.MOD_ID, bus = Bus.MOD)
public class Nedaire 
{
	public static Nedaire instance;
	
	private final Logger LOGGER;
	
	public final ItemGroup TAB;
	
	public Nedaire() 
	{
		
		instance = this;
	
		LOGGER = LogManager.getLogger(NedaireDatabase.MOD_ID);
		
		TAB = new NItemGroup("main");
	    
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
	    modEventBus.addListener(this::serverSetup);
	    modEventBus.addListener(this::clientSetup);
	    modEventBus.addListener(this::gatherData);
	    
	    NedaireMaterials.BLOCKS.register(modEventBus);
	    NedaireMaterials.ITEMS.register(modEventBus);
	    
	    MinecraftForge.EVENT_BUS.register(this);
	}

/*	@SubscribeEvent
	public static void onItemRegisteter (final RegistryEvent.Register<Item> event)
	{
		final IForgeRegistry<Item> registry = event.getRegistry();

		NedaireBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).
	    forEach	(block -> 
	    {
	    	final Item.Properties props = new Item.Properties().group(Nedaire.instance.TAB);
	    	final BlockItem item = new BlockItem(block, props);
	    	item.setRegistryName(block.getRegistryName());
	    	registry.register(item);
	    });
	}
*/	
    private void serverSetup(final FMLCommonSetupEvent event)
    {
    
    }
	
	private void clientSetup (final FMLClientSetupEvent event)
	{
		
	}
	
    public void gatherData(GatherDataEvent event)
    {
    	DataGenerator gen = event.getGenerator();
        
    	if (event.includeServer())
    	{
        	gen.addProvider(new NedaireBlockTagsProvider(gen));
            gen.addProvider(new NedaireItemTagsProvider(gen));    	
            gen.addProvider(new NedaireRecipesProvider(gen));
    	}
    }
	public Logger getLOGGER() 
	{
		return LOGGER;
	}
}
