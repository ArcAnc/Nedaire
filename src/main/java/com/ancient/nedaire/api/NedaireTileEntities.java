/*
 * Ancient
 * Created at: 17-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.api;

import com.ancient.nedaire.content.blocks.tileEntities.NTileGrinder;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NedaireTileEntities 
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, NedaireDatabase.MOD_ID);
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, NedaireDatabase.MOD_ID);

	public static final RegistryObject<TileEntityType<NTileGrinder>> GRINDER = TILE_ENTITIES.register(
			StringHelper.slashPlacer(NedaireDatabase.Blocks.Names.Machines.GRINDER, NedaireDatabase.Blocks.Names.MACHINE), 
			() -> TileEntityType.Builder.create(NedaireMaterials.GRINDER.get().getTile(), NedaireMaterials.GRINDER.get()).build(null));
}
