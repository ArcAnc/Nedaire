/*
 * Ancient
 * Created at: 03-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class NedaireTags 
{
	public static class Blocks 
	{
		public static final MaterialTag SILVER = new NedaireTags.Blocks.MaterialTag(NedaireDatabase.Materials.SILVER);
		public static final MaterialTag COBALT = new NedaireTags.Blocks.MaterialTag(NedaireDatabase.Materials.COBALT);
		public static final MaterialTag IRIDIUM = new NedaireTags.Blocks.MaterialTag(NedaireDatabase.Materials.IRIDIUM);
		
		public static final Map<String, NedaireTags.Blocks.MaterialTag> MATERIALS = Stream.of(SILVER, COBALT, IRIDIUM).collect(Collectors.toMap(NedaireTags.Blocks.MaterialTag :: getName, mat -> mat));
		
		private static Tag<Block> tag (String name)
		{
			return new BlockTags.Wrapper(StringHelper.getLocationFromString(name));
		}
		
		private static Tag<Block> forgeTag(String name)
		{
			return new BlockTags.Wrapper(new ResourceLocation("forge", name));
		}

		protected static class MaterialTag
		{
			private final String name;
			
			private final Tag<Block> storageBlock;
			//private final Tag<T> ore;
		
			public MaterialTag(String name) 
			{
				this.name = name;
				
				this.storageBlock = forgeTag(StringHelper.plural(NedaireDatabase.Blocks.Names.STORAGE_BLOCK) + "/" + name);
				//this.ore = forgeTag(name);
			}
			
			public String getName() 
			{
				return name;
			}
			
			public Tag<Block> getStorageBlock() 
			{
				return storageBlock;
			}
		}
	}
	
	public static class Items
	{
	
		public static final MaterialTag SILVER = new NedaireTags.Items.MaterialTag(NedaireDatabase.Materials.SILVER);
		public static final MaterialTag COBALT = new NedaireTags.Items.MaterialTag(NedaireDatabase.Materials.COBALT);
		public static final MaterialTag IRIDIUM = new NedaireTags.Items.MaterialTag(NedaireDatabase.Materials.IRIDIUM);
		
		public static final Map<String, NedaireTags.Items.MaterialTag> MATERIALS = Stream.of(SILVER, COBALT, IRIDIUM).collect(Collectors.toMap(NedaireTags.Items.MaterialTag :: getName, mat -> mat));

		protected static class MaterialTag
		{
			private final String name;
			
			private final Tag<Item> ingot;
			private final Tag<Item> nugget;
			private final Tag<Item> dust;
			
			private final Tag<Item> storageBlock;
			//private final Tag<Item> ore;
		
			public MaterialTag(String name) 
			{
				this.name = name;
				
				this.ingot = forgeTag(StringHelper.plural(NedaireDatabase.Items.Names.INGOT) + "/" + name);
				this.nugget = forgeTag(StringHelper.plural(NedaireDatabase.Items.Names.NUGGET) + "/" + name);
				this.dust = forgeTag(StringHelper.plural(NedaireDatabase.Items.Names.DUST) + "/" + name);

				this.storageBlock = forgeTag(StringHelper.plural(NedaireDatabase.Blocks.Names.STORAGE_BLOCK) + "/" + name);
				//this.ore = forgeTag(name);
			}
			
			public String getName() 
			{
				return name;
			}
			
			public Tag<Item> getIngot() 
			{
				return ingot;
			}
			
			public Tag<Item> getDust() 
			{
				return dust;
			}
			
			public Tag<Item> getNugget() 
			{
				return nugget;
			}
			
			public Tag<Item> getStorageBlock() 
			{
				return storageBlock;
			}
		}

		
		public static Tag<Item> tag(String name)
	    {
	        return new ItemTags.Wrapper(StringHelper.getLocationFromString(name));
	    }
		
		public static Tag<Item> forgeTag(String name)
		{
			return new ItemTags.Wrapper(new ResourceLocation("forge", name));
		}

	}
}
