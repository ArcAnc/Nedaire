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
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class NedaireTags 
{
	public static class Blocks 
	{
		public static final MaterialTag SILVER = new NedaireTags.Blocks.MaterialTag(NedaireDatabase.Materials.SILVER);
		public static final MaterialTag COBALT = new NedaireTags.Blocks.MaterialTag(NedaireDatabase.Materials.COBALT);
		public static final MaterialTag IRIDIUM = new NedaireTags.Blocks.MaterialTag(NedaireDatabase.Materials.IRIDIUM);
		
		public static final Map<String, NedaireTags.Blocks.MaterialTag> MATERIALS = Stream.of(SILVER, COBALT, IRIDIUM).collect(Collectors.toMap(NedaireTags.Blocks.MaterialTag :: getName, mat -> mat));
		
		protected static class MaterialTag
		{
			private final String name;
			
			private final ITag.INamedTag<Block> storageBlock;
			private final ITag.INamedTag<Block> ore;
		
			public MaterialTag(String name) 
			{
				this.name = name;
				
				this.storageBlock = forgeTag(StringHelper.plural(NedaireDatabase.Blocks.Names.STORAGE_BLOCK) + "/" + name);
				this.ore = forgeTag(StringHelper.plural(NedaireDatabase.Blocks.Names.ORE) + "/" + name);
			}
			
			public String getName() 
			{
				return name;
			}
			
			public ITag.INamedTag<Block> getStorageBlock() 
			{
				return storageBlock;
			}
			
			public ITag.INamedTag<Block> getOre() 
			{
				return ore;
			}
		}
		
		private static ITag.INamedTag<Block> tag (String name)
		{
			return BlockTags.makeWrapperTag(StringHelper.getStrLocationFromString(name));
		}
		
		private static ITag.INamedTag<Block> forgeTag(String name)
		{
			return BlockTags.makeWrapperTag(new ResourceLocation("forge", name).toString());
		}
		
		private static ITag.INamedTag<Block> minecraftTag(String name)
		{
			return BlockTags.makeWrapperTag(new ResourceLocation("minecraft", name).toString());
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
			
			private final ITag.INamedTag<Item> ingot;
			private final ITag.INamedTag<Item> nugget;
			private final ITag.INamedTag<Item> dust;
			
			private final ITag.INamedTag<Item> storageBlock;
			private final ITag.INamedTag<Item> ore;
		
			public MaterialTag(String name) 
			{
				this.name = name;
				
				this.ingot = forgeTag(StringHelper.plural(NedaireDatabase.Items.Names.INGOT) + "/" + name);
				this.nugget = forgeTag(StringHelper.plural(NedaireDatabase.Items.Names.NUGGET) + "/" + name);
				this.dust = forgeTag(StringHelper.plural(NedaireDatabase.Items.Names.DUST) + "/" + name);

				this.storageBlock = forgeTag(StringHelper.plural(NedaireDatabase.Blocks.Names.STORAGE_BLOCK) + "/" + name);
				this.ore = forgeTag(StringHelper.plural(NedaireDatabase.Blocks.Names.ORE) + "/" + name);
			}
			
			public String getName() 
			{
				return name;
			}
			
			public ITag.INamedTag<Item> getIngot() 
			{
				return ingot;
			}
			
			public ITag.INamedTag<Item> getDust() 
			{
				return dust;
			}
			
			public ITag.INamedTag<Item> getNugget() 
			{
				return nugget;
			}
			
			public ITag.INamedTag<Item> getStorageBlock() 
			{
				return storageBlock;
			}
			
			public ITag.INamedTag<Item> getOre() 
			{
				return ore;
			}
		}

		private static ITag.INamedTag<Item> tag(String name)
	    {
	        return ItemTags.makeWrapperTag(StringHelper.getStrLocationFromString(name));
	    }
		
		private static ITag.INamedTag<Item> forgeTag(String name)
		{
			return ItemTags.makeWrapperTag(new ResourceLocation("forge", name).toString());
		}
		
		private static ITag.INamedTag<Item> minecraftTag(String name)
		{
			return ItemTags.makeWrapperTag(new ResourceLocation("minecraft", name).toString());
		}

	}
}
