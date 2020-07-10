/*
 * Ancient
 * Created at: 12-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data;

import com.ancient.nedaire.api.NedaireRegistration;
import com.ancient.nedaire.content.materials.NComplexMaterial;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;

public class NedaireItemTagsProvider extends ItemTagsProvider
{

	public NedaireItemTagsProvider(DataGenerator gen, BlockTagsProvider blockTagsProvider) 
	{
		super(gen, blockTagsProvider);
	}
	
	@Override
	protected void registerTags() 
	{
	
		func_240521_a_/*copy*/(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
		func_240521_a_(Tags.Blocks.ORES, Tags.Items.ORES);

		for (NComplexMaterial mat : NedaireRegistration.MATERIALS)
		{
			func_240521_a_(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getStorageBlock(), NedaireTags.Items.MATERIALS.get(mat.getName()).getStorageBlock());
			
			func_240521_a_(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getOre(), NedaireTags.Items.MATERIALS.get(mat.getName()).getOre());
		
			func_240522_a_/*getBuilder*/(Tags.Items.INGOTS).func_240531_a_/*add*/(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot());
			func_240522_a_(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot()).func_240532_a_/*add*/(mat.getIngot().get());
			
			func_240522_a_(Tags.Items.NUGGETS).func_240531_a_(NedaireTags.Items.MATERIALS.get(mat.getName()).getNugget());
			func_240522_a_(NedaireTags.Items.MATERIALS.get(mat.getName()).getNugget()).func_240532_a_(mat.getNugget().get());
			
			func_240522_a_(Tags.Items.DUSTS).func_240531_a_(NedaireTags.Items.MATERIALS.get(mat.getName()).getDust());
			func_240522_a_(NedaireTags.Items.MATERIALS.get(mat.getName()).getDust()).func_240532_a_(mat.getDust().get());
		}
	}
	
	@Override
	public String getName() 
	{
		return "Nedaire Item Tags";
	}
}
