/*
 * Ancient
 * Created at: 23-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data.recipes.serializers;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class NedaireRecipeSerializer<R extends IRecipe<?>> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<R> 
{
	public abstract ItemStack getIcon();
	
	@Override
	public R read(ResourceLocation recipeId, JsonObject json) 
	{
		if (CraftingHelper.processConditions(json, "conditions"))
		{
			return readFromJson(recipeId, json);
		}
		return null;
	}


	protected abstract R readFromJson(ResourceLocation recipeId, JsonObject json);
	
}
