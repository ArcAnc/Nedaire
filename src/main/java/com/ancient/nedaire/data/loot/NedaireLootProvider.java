/*
 * Ancient
 * Created at: 23-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data.loot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import com.ancient.nedaire.Nedaire;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.ValidationTracker;

public abstract class NedaireLootProvider implements IDataProvider 
{
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create(); 
	private final DataGenerator generator;
	protected final Map<ResourceLocation, LootTable> tables = Maps.<ResourceLocation, LootTable>newHashMap();
	
	public NedaireLootProvider(DataGenerator gen) 
	{
		this.generator = gen;
	}
	
	protected abstract void registerTables();
	
	@Override
	public void act(DirectoryCache outCache) throws IOException 
	{
		tables.clear();
		Path outFolder = this.generator.getOutputFolder();

		registerTables();

	    ValidationTracker validationtracker = new ValidationTracker(LootParameterSets.GENERIC, (p_229442_0_) -> 
	    {
	    	return null;
	    }, tables::get);
	      
		tables.forEach((name, table) -> 
		{
			LootTableManager.func_227508_a_(validationtracker, name, table);
		});
		
		Multimap<String, String> problems = validationtracker.getProblems();
		
		if(!problems.isEmpty())
		{
			problems.forEach((name, table) -> 
			{
				Nedaire.instance.getLOGGER().warn("Found validation problem in "+name+": "+table);
			});
			throw new IllegalStateException("Failed to validate loot tables, see logs");
		}
		else
		{
			tables.forEach((name, table) -> 
			{
				Path out = getPath(outFolder, name);

				try
				{
					IDataProvider.save(GSON, outCache, LootTableManager.toJson(table), out);
				} 
				catch(IOException x)
				{
					Nedaire.instance.getLOGGER().error("Couldn't save loot table {}", out, x);
				}

			});
		}
	}
	
	private Path getPath(Path path, ResourceLocation loc)
	{
		return path.resolve("data/" + loc.getNamespace() + "/loot_tables/" + loc.getPath() + ".json");
	}

	@Override
	public String getName() 
	{
		return "Nedaire Loot Provider";
	}

}
