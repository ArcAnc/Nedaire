/*
 * Ancient
 * Created at: 09-07-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.integration.jei.grinder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ancient.nedaire.Nedaire;
import com.ancient.nedaire.api.NedaireMaterials;
import com.ancient.nedaire.api.NedaireRecipes;
import com.ancient.nedaire.data.StackWithChance;
import com.ancient.nedaire.data.recipes.GrinderRecipe;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.mojang.blaze3d.systems.RenderSystem;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class RecipeGrinderJeiCategory implements IRecipeCategory<GrinderRecipe> 
{

	private final IDrawable background;
	private final IDrawable overlay;
	private final IDrawable icon;
	private final String name;
	
	public RecipeGrinderJeiCategory(IGuiHelper helper) 
	{
		background = helper.createBlankDrawable(168, 46);
		
		overlay = helper.createDrawable(NedaireDatabase.GUI.Locations.ELEMENTS, 0, 0, 64, 46);
		
		icon = helper.createDrawableIngredient(new ItemStack(NedaireMaterials.GRINDER.get().asItem()));
		
		name = new TranslationTextComponent(NedaireMaterials.GRINDER.get().getTranslationKey()).getFormattedText();
	}
	
	@Override
	public ResourceLocation getUid() 
	{
		return NedaireRecipes.GRIDER_SERIALIZER.getId();
	}

	@Override
	public Class<? extends GrinderRecipe> getRecipeClass() 
	{
		return GrinderRecipe.class;
	}

	@Override
	public String getTitle() 
	{
		return name;
	}

	@Override
	public IDrawable getBackground() 
	{
		return background;
	}

	@Override
	public IDrawable getIcon() 
	{
		return icon;
	}

	@Override
	public void setIngredients(GrinderRecipe recipe, IIngredients ingredients) 
	{
		
		Nedaire.instance.getLOGGER().info("recipe id {}", recipe.getId().toString());
		
		ingredients.setInputIngredients(Stream.of(recipe.getInput()).collect(Collectors.toList()));

		Nedaire.instance.getLOGGER().info("recipe input: ", recipe.getInput().toString());
	
		ingredients.setOutputs(VanillaTypes.ITEM, Stream.of(recipe.getOutput()).collect(Collectors.toList()));
		
		Nedaire.instance.getLOGGER().info("recipe output: ", recipe.getOutput().toString());
		
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputSecondary().stream().
				map(StackWithChance :: getStack).
				collect(Collectors.toList()));
	}

	@Override
	public void draw(GrinderRecipe recipe, double mouseX, double mouseY) 
	{
		RenderSystem.enableAlphaTest();
		RenderSystem.enableBlend();
		overlay.draw(48, 0);
		RenderSystem.disableBlend();
		RenderSystem.disableAlphaTest();
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, GrinderRecipe recipe, IIngredients ingredients) 
	{
		recipeLayout.getItemStacks().init(0, true, 40, 12);
		recipeLayout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));

		recipeLayout.getItemStacks().init(1, false, 60, 12);
		recipeLayout.getItemStacks().set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
		
		for (int q = 0; q < ingredients.getOutputs(VanillaTypes.ITEM).get(1).size(); q++)
		{
			ItemStack stack = ingredients.getOutputs(VanillaTypes.ITEM).get(1).get(q);
			
			recipeLayout.getItemStacks().init(q + 2, false, 40, q * 12 + 12);
			recipeLayout.getItemStacks().set(q + 2, stack);
		}
	}

}
