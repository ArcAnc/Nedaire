/*
 * Ancient
 * Created at: 23-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data.recipes;

import com.ancient.nedaire.data.recipes.serializers.NedaireRecipeSerializer;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class NedaireSerializableRecipe implements IRecipe<IInventory> 
{

	protected final ResourceLocation id;
	protected final IRecipeType<?> type;
	
	public NedaireSerializableRecipe(ResourceLocation id, IRecipeType<?> type) 
	{
		this.id = id;
		this.type = type;
	}
	
	@Override
	public ItemStack getIcon() 
	{
		return getNedaireSerializer().getIcon();
	}
	
	@Override
	public boolean isDynamic() 
	{
		return true;
	}
	
	@Override
	public boolean matches(IInventory inv, World worldIn) 
	{
		return false;
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) 
	{
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canFit(int width, int height) 
	{
		return false;
	}

	@Override
	public ResourceLocation getId() 
	{
		return this.id;
	}
	
	protected abstract NedaireRecipeSerializer getNedaireSerializer();

	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return getNedaireSerializer();
	}

	@Override
	public IRecipeType<?> getType() 
	{
		return this.type;
	}

}
