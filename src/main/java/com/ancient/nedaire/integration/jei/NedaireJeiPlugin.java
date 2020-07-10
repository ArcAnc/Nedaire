/*
 * Ancient
 * Created at: 09-07-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.integration.jei;

import java.util.stream.Collectors;

import com.ancient.nedaire.api.NedaireRegistration;
import com.ancient.nedaire.api.NedaireRecipes;
import com.ancient.nedaire.integration.jei.grinder.RecipeGrinderJeiCategory;
import com.ancient.nedaire.util.helpers.StringHelper;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

//@JeiPlugin
public class NedaireJeiPlugin implements IModPlugin
{

	@Override
	public ResourceLocation getPluginUid() 
	{
		return StringHelper.getLocationFromString("main");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) 
	{
		registration.addRecipeCategories(new RecipeGrinderJeiCategory(registration.getJeiHelpers().getGuiHelper()));
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) 
	{
		registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipes().stream().
				filter(recipe -> recipe.getType() == NedaireRecipes.Types.GRINDER).
				collect(Collectors.toList()), NedaireRecipes.GRIDER_SERIALIZER.getId());
	}
	
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) 
	{
		registration.addRecipeCatalyst(new ItemStack(NedaireRegistration.GRINDER.get()), NedaireRecipes.GRIDER_SERIALIZER.getId());
	}
}
