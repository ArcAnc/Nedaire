/*
 * Ancient
 * Created at: 17-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.api;

import com.ancient.nedaire.content.block.tileEntity.NTileGrinder;
import com.ancient.nedaire.content.block.tileEntity.generators.NTileSolarGenerator;
import com.ancient.nedaire.content.gui.containers.tiles.NGeneratorContainer;
import com.ancient.nedaire.content.gui.containers.tiles.NGrinderContainer;
import com.ancient.nedaire.util.database.NedaireDatabase;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NedaireTileEntities
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, NedaireDatabase.MOD_ID);
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, NedaireDatabase.MOD_ID);

	
	/**
	 * TILE_ENTITIES
	 */
	public static final RegistryObject<TileEntityType<NTileGrinder>> GRINDER = TILE_ENTITIES.register(
			NedaireRegistration.GRINDER.getId().getPath(), 
			() -> TileEntityType.Builder.create(NedaireRegistration.GRINDER.get().getTile(), NedaireRegistration.GRINDER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<NTileSolarGenerator>> GENERATOR_SOLAR = TILE_ENTITIES.register(
			NedaireRegistration.GENERATOR_SOLAR.getId().getPath(), 
			() -> TileEntityType.Builder.create(NedaireRegistration.GENERATOR_SOLAR.get().getTile(), NedaireRegistration.GENERATOR_SOLAR.get()).build(null));
	
	/**
	 * CONTAINERS
	 */
	public static final RegistryObject<ContainerType<NGrinderContainer>> GRINDER_CONTAINER = CONTAINERS.register(
			NedaireRegistration.GRINDER.getId().getPath(), 
			() -> IForgeContainerType.create(NGrinderContainer :: new)); 

	public static final RegistryObject<ContainerType<NGeneratorContainer>> GENERATOR_SOLAR_CONTAINER = CONTAINERS.register(
			NedaireRegistration.GENERATOR_SOLAR.getId().getPath(), 
			() -> IForgeContainerType.create(NGeneratorContainer :: new)); 
	
}
