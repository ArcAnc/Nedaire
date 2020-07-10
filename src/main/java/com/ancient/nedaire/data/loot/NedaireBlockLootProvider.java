/*
 * Ancient
 * Created at: 23-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data.loot;

import com.ancient.nedaire.api.NedaireRegistration;
import com.ancient.nedaire.content.materials.NComplexMaterial;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;

public class NedaireBlockLootProvider extends NedaireLootProvider 
{

	public NedaireBlockLootProvider(DataGenerator dataGenerator) 
	{
		super(dataGenerator);
	}

	@Override
	protected void registerTables() 
	{
		for(NComplexMaterial mat : NedaireRegistration.MATERIALS)
		{
			registerSelfDrop(mat.getStorageBlock().get());
			registerSelfDrop(mat.getOreBlock().get());
		}
		
		registerSelfDrop(NedaireRegistration.GRINDER.get());
		registerSelfDrop(NedaireRegistration.GENERATOR_SOLAR.get());
	}
	
	private void registerSelfDrop(Block block)
	{
		register(block, singleItem(block));
	}

	private void register (Block block, LootPool.Builder pool)
	{
		register (block, LootTable.builder().addLootPool(pool));
	}
	
	private void register(Block block, LootTable.Builder table)
	{
		register (block.getRegistryName(), table);
	}	
	
	private void register(ResourceLocation name, LootTable.Builder table)
	{
		if(tables.put(toTableLoc(name), table.setParameterSet(LootParameterSets.BLOCK).build())!=null)
			throw new IllegalStateException("Duplicate loot table " + name);
	}	
	
	private LootPool.Builder singleItem(IItemProvider in)
	{
		return createPoolBuilder()
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(in));
	}
	
	private LootPool.Builder createPoolBuilder()
	{
		return LootPool.builder().acceptCondition(SurvivesExplosion.builder());
	}
	
	private ResourceLocation toTableLoc(ResourceLocation in)
	{
		return new ResourceLocation(in.getNamespace(), "blocks/"+in.getPath());
	}
	
	@Override
	public String getName() 
	{
		return "Nedaire Block Loot Provider";
	}



}
