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

import com.ancient.nedaire.api.NedaireRecipes;
import com.ancient.nedaire.api.NedaireRegistration;
import com.ancient.nedaire.api.NedaireTileEntities;
import com.ancient.nedaire.content.block.NBaseBlock;
import com.ancient.nedaire.content.gui.screens.tiles.NGeneratorScreen;
import com.ancient.nedaire.content.gui.screens.tiles.NGrinderScreen;
import com.ancient.nedaire.content.itemGroup.NItemGroup;
import com.ancient.nedaire.data.NedaireBlockStatesProvider;
import com.ancient.nedaire.data.NedaireBlockTagsProvider;
import com.ancient.nedaire.data.NedaireItemModelProvider;
import com.ancient.nedaire.data.NedaireItemTagsProvider;
import com.ancient.nedaire.data.NedaireRecipesProvider;
import com.ancient.nedaire.data.loot.NedaireBlockLootProvider;
import com.ancient.nedaire.data.recipes.RecipeReloadListener;
import com.ancient.nedaire.init.CapabilitiesInit;
import com.ancient.nedaire.util.database.NedaireDatabase;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
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
		
	    modEventBus.addListener(this :: serverSetup);
	    modEventBus.addListener(this :: clientSetup);
	    modEventBus.addListener(this :: clientTextureStitch);
	    
	    modEventBus.addListener(this :: gatherData);
	    
	    NedaireRegistration.BLOCKS.register(modEventBus);
	    NedaireRegistration.ITEMS.register(modEventBus);
	    
	    NedaireTileEntities.TILE_ENTITIES.register(modEventBus);
	    NedaireTileEntities.CONTAINERS.register(modEventBus);
	    NedaireRecipes.RECIPE_SERIALIZERS.register(modEventBus);
	    
	    MinecraftForge.EVENT_BUS.register(this);

	    MinecraftForge.EVENT_BUS.addListener(this :: addReloadListenerEvent);
	}

	private void serverSetup(final FMLCommonSetupEvent event)
    {
    	CapabilitiesInit.initCapabilities();
    }
	
	private void clientSetup (final FMLClientSetupEvent event)
	{
		NedaireRegistration.BLOCKS.getEntries().stream().map(RegistryObject :: get).
		forEach(block -> 
		{
			if (block instanceof NBaseBlock)
			{
				NBaseBlock b = (NBaseBlock)block;
				RenderTypeLookup.setRenderLayer(b, b.getRenderLayer());
			}
		});
	
		ScreenManager.registerFactory(NedaireTileEntities.GRINDER_CONTAINER.get(), NGrinderScreen :: new);
		ScreenManager.registerFactory(NedaireTileEntities.GENERATOR_SOLAR_CONTAINER.get(), NGeneratorScreen :: new);
		
/*		NedaireTileEntities.TILE_INFO.values().stream().
		forEach(value ->
		{
			if (value.hasSpecialRenderer())
			{
				TileEntityType<NTile> type = (TileEntityType<NTile>) value.getTileEntityType().get();
				Function<? super TileEntityRendererDispatcher, ? extends TileEntityRenderer<? super NTile>> renderer = (Function<? super TileEntityRendererDispatcher, ? extends TileEntityRenderer<? super NTile>>) value.getTileRenderer();
				ClientRegistry.bindTileEntityRenderer(type, renderer);
			}
			if (value.hasContainer())
			{
				ContainerType<Container> type = (ContainerType<Container>) value.getContainerType().get();
				ScreenManager.IScreenFactory<Container, ?> screen = (IScreenFactory<Container, ?>) value.getGuiScreen();
				ScreenManager.registerFactory(type, screen);
			}
		});
*/	}
	
	private void clientTextureStitch (final TextureStitchEvent.Pre event)
	{
		if (event.getMap().getTextureLocation() == PlayerContainer.LOCATION_BLOCKS_TEXTURE)
		{
			event.addSprite(NedaireDatabase.GUI.Locations.SLOT);
		}
	}
	
	private void addReloadListenerEvent(final AddReloadListenerEvent event)
	{
		event.addListener(new RecipeReloadListener());
	}
	
    public void gatherData(GatherDataEvent event)
    {
    	DataGenerator gen = event.getGenerator();
        
    	if (event.includeServer())
    	{
        	NedaireBlockTagsProvider btp = new NedaireBlockTagsProvider(gen);
    		
    		gen.addProvider(btp);
            gen.addProvider(new NedaireItemTagsProvider(gen, btp));    	
            gen.addProvider(new NedaireRecipesProvider(gen));
            gen.addProvider(new NedaireBlockLootProvider(gen));
    	}
    	
    	if (event.includeClient())
    	{
            gen.addProvider(new NedaireBlockStatesProvider(gen, event.getExistingFileHelper()));
            gen.addProvider(new NedaireItemModelProvider(gen, event.getExistingFileHelper()));
    	}
    }
	public Logger getLOGGER() 
	{
		return LOGGER;
	}
}
