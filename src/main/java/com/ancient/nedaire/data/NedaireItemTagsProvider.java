/*
 * Ancient
 * Created at: 12-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data;

import com.ancient.nedaire.api.NedaireMaterials;
import com.ancient.nedaire.content.materials.NComplexMaterial;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;

public class NedaireItemTagsProvider extends ItemTagsProvider
{

	public NedaireItemTagsProvider(DataGenerator gen) 
	{
		super(gen);
	}
	
	@Override
	protected void registerTags() 
	{
	
		copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
		copy(Tags.Blocks.ORES, Tags.Items.ORES);

		for (NComplexMaterial mat : NedaireMaterials.MATERIALS)
		{
			copy(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getStorageBlock(), NedaireTags.Items.MATERIALS.get(mat.getName()).getStorageBlock());
			
			copy(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getOre(), NedaireTags.Items.MATERIALS.get(mat.getName()).getOre());
		
			getBuilder(Tags.Items.INGOTS).add(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot());
			getBuilder(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot()).add(mat.getIngot().get());
			
			getBuilder(Tags.Items.NUGGETS).add(NedaireTags.Items.MATERIALS.get(mat.getName()).getNugget());
			getBuilder(NedaireTags.Items.MATERIALS.get(mat.getName()).getNugget()).add(mat.getNugget().get());
			
			getBuilder(Tags.Items.DUSTS).add(NedaireTags.Items.MATERIALS.get(mat.getName()).getDust());
			getBuilder(NedaireTags.Items.MATERIALS.get(mat.getName()).getDust()).add(mat.getDust().get());
		}
	}
	
	@Override
	public String getName() 
	{
		return "Nedaire Item Tags";
	}
}
