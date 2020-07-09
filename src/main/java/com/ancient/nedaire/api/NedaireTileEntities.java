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

	public static final RegistryObject<TileEntityType<NTileGrinder>> GRINDER = TILE_ENTITIES.register(
			NedaireMaterials.GRINDER.getId().getPath(), 
			() -> TileEntityType.Builder.create(NedaireMaterials.GRINDER.get().getTile(), NedaireMaterials.GRINDER.get()).build(null));
	
	public static final RegistryObject<ContainerType<NGrinderContainer>> GRINDER_CONTAINER = CONTAINERS.register(
			NedaireMaterials.GRINDER.getId().getPath(), 
			() -> IForgeContainerType.create(NGrinderContainer :: new)); 

	
/*	public static final NComplexTileEntityInfo<NBlockMachine<NTileGrinder>, NTileGrinder, NGrinderContainer, NGrinderScreen> GRINDER =
			new NComplexTileEntityInfo<>(
					StringHelper.slashPlacer(NedaireDatabase.Blocks.Names.Machines.GRINDER, NedaireDatabase.Blocks.Names.MACHINE), 
					() -> BlockHelper.setRenderLayer(
							new NBlockMachine<NTileGrinder>(Block.Properties.create(Material.IRON), NTileGrinder :: new), 
							RenderType.getCutout()), 
					NTileGrinder :: new,
					null,
					NGrinderContainer :: new, 
					NGrinderScreen :: new);
	
	public static final Map<String, NComplexTileEntityInfo<?, ?, ?, ?>> TILE_INFO = Stream.of(GRINDER).collect(Collectors.toMap(NComplexTileEntityInfo :: getName, value -> value));
*/}
