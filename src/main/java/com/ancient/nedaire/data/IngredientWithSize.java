/*
 * Ancient
 * Created at: 25-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data;

import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.ItemHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.Tag;
import net.minecraft.util.JSONUtils;
import net.minecraftforge.common.crafting.CraftingHelper;

public class IngredientWithSize implements Predicate<ItemStack> 
{
	protected final Ingredient ingredient;
	protected final int count;

	public IngredientWithSize(Ingredient ingredient, int count)
	{
		this.ingredient = ingredient;
		this.count = count;
	}

	public IngredientWithSize(Ingredient ingredient)
	{
		this(ingredient, 1);
	}

	public IngredientWithSize(Tag<Item> ingredient, int count)
	{
		this(Ingredient.fromTag(ingredient), count);
	}

	public IngredientWithSize(Tag<Item> ingredient)
	{
		this(ingredient, 1);
	}

	public static IngredientWithSize deserialize(JsonElement input)
	{
		return parse(input);
	}

	public static IngredientWithSize read(PacketBuffer input)
	{
		return parse(input);
	}

	@Override
	public boolean test(@Nullable ItemStack itemStack)
	{
		if(itemStack==null)
			return false;
		return ingredient.test(itemStack)&&itemStack.getCount() >= this.count;
	}

	@Nonnull
	public ItemStack[] getMatchingStacks()
	{
		ItemStack[] baseStacks = ingredient.getMatchingStacks();
		ItemStack[] ret = new ItemStack[baseStacks.length];
		for(int i = 0; i < baseStacks.length; ++i)
			ret[i] = ItemHelper.copyStackWithAmount(baseStacks[i], this.count);
		return ret;
	}

	@Nonnull
	public JsonElement serialize()
	{
		return write(this);
	}

	public boolean hasNoMatchingItems()
	{
		return ingredient.hasNoMatchingItems();
	}

	public int getCount()
	{
		return count;
	}

	public Ingredient getBaseIngredient()
	{
		return ingredient;
	}

	public IngredientWithSize withSize(int size)
	{
		return new IngredientWithSize(this.ingredient, size);
	}

	public static IngredientWithSize of(ItemStack stack)
	{
		return new IngredientWithSize(Ingredient.fromStacks(stack), stack.getCount());
	}

	public ItemStack getRandomizedExampleStack(int rand)
	{
		ItemStack[] all = getMatchingStacks();
		return all[(rand/20)%all.length];
	}

	public boolean testIgnoringSize(ItemStack itemstack)
	{
		return ingredient.test(itemstack);
	}

	public void write(PacketBuffer out)
	{
		write(out, this);
	}
	
	/* =============== Serializing =============== */
	
	@Nonnull
	public static IngredientWithSize parse(@Nonnull PacketBuffer buffer)
	{
		final int count = buffer.readInt();
		final Ingredient base = Ingredient.read(buffer);
		return new IngredientWithSize(base, count);
	}

	public static void write(@Nonnull PacketBuffer buffer, @Nonnull IngredientWithSize ingredient)
	{
		buffer.writeInt(ingredient.getCount());
		CraftingHelper.write(buffer, ingredient.getBaseIngredient());
	}

	@Nonnull
	public static IngredientWithSize parse(@Nonnull JsonElement json)
	{
		if(json.isJsonObject()&&json.getAsJsonObject().has(NedaireDatabase.Recipes.Serializers.IngredientWithSize.INGREDIENT))
		{
			final int count = JSONUtils.getInt(json.getAsJsonObject(), NedaireDatabase.Recipes.Serializers.IngredientWithSize.AMOUNT, 1);
			final JsonElement baseJson = json.getAsJsonObject().get(NedaireDatabase.Recipes.Serializers.IngredientWithSize.INGREDIENT);
			final Ingredient base = Ingredient.deserialize(baseJson);
			return new IngredientWithSize(base, count);
		}
		else //fallback for normal ingredients
		{
			final Ingredient base = Ingredient.deserialize(json);
			return new IngredientWithSize(base, 1);
		}
	}

	public static JsonElement write(@Nonnull IngredientWithSize ingredient)
	{
		if(ingredient.getCount()==1)
			return ingredient.getBaseIngredient().serialize();
		JsonObject json = new JsonObject();
		json.addProperty(NedaireDatabase.Recipes.Serializers.IngredientWithSize.AMOUNT, ingredient.getCount());
		json.add(NedaireDatabase.Recipes.Serializers.IngredientWithSize.INGREDIENT, ingredient.getBaseIngredient().serialize());
		return json;
	}
}
