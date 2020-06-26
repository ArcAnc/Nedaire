/*
 * Ancient
 * Created at: 23-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data.recipes;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.ancient.nedaire.api.NedaireRecipes;
import com.ancient.nedaire.data.StackWithChance;
import com.ancient.nedaire.data.recipes.serializers.GrinderRecipeSerializer;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class GrinderRecipe extends NedaireSerializableRecipe 
{

	public static Map<ResourceLocation, GrinderRecipe> recipes;
	
	private final Ingredient input;
	private final ItemStack output;
	private final Set<StackWithChance> outputSecondary = Sets.newHashSet();
	private final int energy;
	
	public GrinderRecipe(ResourceLocation id, Ingredient input, ItemStack outputMain, int energy) 
	{
		super(id, NedaireRecipes.Types.GRINDER);
		this.input = input;
		this.output = outputMain;
		this.energy = energy;
	}

	@Override
	public ItemStack getRecipeOutput() 
	{
		return this.output;
	}

	public Ingredient getInput() 
	{
		return input;
	}
	
	public ItemStack getOutput() 
	{
		return output;
	}
	
	public int getEnergy() 
	{
		return energy;
	}
	
	public Set<StackWithChance> getOutputSecondary() 
	{
		return outputSecondary;
	}
	
	public GrinderRecipe addSecondaryOutput(StackWithChance stack)
	{
		Preconditions.checkNotNull(stack);
		outputSecondary.add(stack);
		return this;
	}
	
	@Override
	protected GrinderRecipeSerializer getNedaireSerializer() 
	{
		return NedaireRecipes.GRIDER_SERIALIZER.get();
	}
	
	public static Optional<GrinderRecipe> findRecipe(ItemStack input)
	{
		return recipes.entrySet().stream().
				map( Map.Entry :: getValue).
				filter(recipe -> recipe.getInput().test(input)).
				findFirst();
	}

}
