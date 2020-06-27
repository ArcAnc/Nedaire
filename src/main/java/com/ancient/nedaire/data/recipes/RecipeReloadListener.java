/*
 * Ancient
 * Created at: 27-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data.recipes;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.ancient.nedaire.api.NedaireRecipes;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.thread.EffectiveSide;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;

public class RecipeReloadListener implements ISelectiveResourceReloadListener 
{

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) 
	{
		if (EffectiveSide.get().isServer())
		{
			buildRecipeLists(ServerLifecycleHooks.getCurrentServer().getRecipeManager());
		}
	}
	
	public static void buildRecipeLists(RecipeManager recipeManager)
	{
		Collection<IRecipe<?>> recipes = recipeManager.getRecipes();

		GrinderRecipe.recipes = filterRecipes(recipes, GrinderRecipe.class, NedaireRecipes.Types.GRINDER);
	
	}

	private static <T extends IRecipe<?>> Map<ResourceLocation, T> filterRecipes(Collection<IRecipe<?>> recipes, Class<T> clazz, IRecipeType<T> recipeType) 
	{
		return recipes.stream().
				filter(rec -> rec.getType() == recipeType).
				map(clazz :: cast).
				collect(Collectors.toMap(rec -> rec.getId(), rec -> rec));
	}
}
