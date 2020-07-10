/*
 * Ancient
 * Created at: 24-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data.recipes.builders;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.ancient.nedaire.data.IngredientWithSize;
import com.ancient.nedaire.data.recipes.serializers.NedaireRecipeSerializer;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.StringHelper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class NedaireFinishedRecipe <T extends NedaireFinishedRecipe<T>> implements IFinishedRecipe 
{

	private final NedaireRecipeSerializer<?> serializer;
	private final List<Consumer<JsonObject>> writerFunctions;
	
	private ResourceLocation id;
	protected JsonArray inputArray = null;
	protected int inputCount = 0;
	protected int maxInputCount = 1;

	protected JsonArray resultArray = null;
	protected int resultCount = 0;
	protected int maxResultCount = 1;

	protected JsonArray conditions = null;
	
	public NedaireFinishedRecipe(NedaireRecipeSerializer<?> serializer) 
	{
		this.serializer = serializer;
		this.writerFunctions = Lists.newArrayList();
	}
	
	protected boolean isComplete()
	{
		return true;
	}
	
	protected abstract T getSelf();
	
	public void build (Consumer<IFinishedRecipe> out, ResourceLocation id)
	{
		Preconditions.checkArgument(isComplete(), "This recipe is incomplete");
		this.id = id;
		out.accept(this);
	}
	
	public T addWriter(Consumer<JsonObject> writer)
	{
		Preconditions.checkArgument(id==null, "This recipe has already been finalized");
		this.writerFunctions.add(writer);
		return getSelf();
	}
	
	public T addCondition(ICondition condition)
	{
		if(this.conditions==null)
		{
			this.conditions = new JsonArray();
			addWriter(jsonObject -> jsonObject.add("conditions", conditions));
		}
		this.conditions.add(CraftingHelper.serialize(condition));
		return getSelf();
	}
	
	/* =============== Common Objects =============== */

	public T setTime(int time)
	{
		return addWriter(jsonObject -> jsonObject.addProperty(NedaireDatabase.Recipes.Serializers.TIME, time));
	}

	public T setEnergy(int energy)
	{
		return addWriter(jsonObject -> jsonObject.addProperty(NedaireDatabase.Recipes.Serializers.ENERGY, energy));
	}

	/* =============== Result Handling =============== */
	
	public T setMultipleResults(int maxResultCount)
	{
		this.resultArray = new JsonArray();
		this.maxResultCount = maxResultCount;
		return addWriter(jsonObject -> jsonObject.add(StringHelper.plural(NedaireDatabase.Recipes.Serializers.OUTPUT), resultArray));
	}

	public T addMultiResult(JsonElement obj)
	{
		Preconditions.checkArgument(maxResultCount > 1, "This recipe does not support multiple results");
		Preconditions.checkArgument(resultCount < maxResultCount, "Recipe can only have " + maxResultCount + " results");
		resultArray.add(obj);
		resultCount++;
		return getSelf();
	}

	public T addResult(IItemProvider itemProvider)
	{
		return addResult(new ItemStack(itemProvider));
	}

	public T addResult(ItemStack itemStack)
	{
		if(resultArray!=null)
			return addMultiResult(serializeItemStack(itemStack));
		else
			return addItem(NedaireDatabase.Recipes.Serializers.OUTPUT, itemStack);
	}

	public T addResult(Ingredient ingredient)
	{
		if(resultArray!=null)
			return addMultiResult(ingredient.serialize());
		else
			return addWriter(jsonObject -> jsonObject.add(NedaireDatabase.Recipes.Serializers.OUTPUT, ingredient.serialize()));
	}

	public T addResult(IngredientWithSize ingredientWithSize)
	{
		if(resultArray!=null)
			return addMultiResult(ingredientWithSize.serialize());
		else
			return addWriter(jsonObject -> jsonObject.add(NedaireDatabase.Recipes.Serializers.OUTPUT, ingredientWithSize.serialize()));
	}
	
	/* =============== Input Handling =============== */

	public T setUseInputArray(int maxInputCount, String key)
	{
		this.inputArray = new JsonArray();
		this.maxInputCount = maxInputCount;
		return addWriter(jsonObject -> jsonObject.add(key, inputArray));
	}

	public T setUseInputArray(int maxInputCount)
	{
		return setUseInputArray(maxInputCount, StringHelper.plural(NedaireDatabase.Recipes.Serializers.INPUT));
	}

	public T addMultiInput(JsonElement obj)
	{
		Preconditions.checkArgument(maxInputCount > 1, "This recipe does not support multiple inputs");
		Preconditions.checkArgument(inputCount < maxInputCount, "Recipe can only have " + maxInputCount + " inputs");
		inputArray.add(obj);
		inputCount++;
		return getSelf();
	}

	public T addMultiInput(Ingredient ingredient)
	{
		return addMultiInput(ingredient.serialize());
	}

	public T addMultiInput(IngredientWithSize ingredient)
	{
		return addMultiInput(ingredient.serialize());
	}

	protected String generateSafeInputKey()
	{
		Preconditions.checkArgument(inputCount < maxInputCount, "Recipe can only have " + maxInputCount + " inputs");
		String key = maxInputCount == 1 ? "input": "input" + inputCount;
		inputCount++;
		return key;
	}

	public T addInput(IItemProvider... itemProviders)
	{
		if(inputArray!=null)
			return addMultiInput(Ingredient.fromItems(itemProviders));
		else
			return addIngredient(generateSafeInputKey(), itemProviders);
	}

	public T addInput(ItemStack... itemStacks)
	{
		if(inputArray!=null)
			return addMultiInput(Ingredient.fromStacks(itemStacks));
		else
			return addIngredient(generateSafeInputKey(), itemStacks);
	}

	public T addInput(INamedTag<Item> tag)
	{
		if(inputArray!=null)
			return addMultiInput(Ingredient.fromTag(tag));
		else
			return addIngredient(generateSafeInputKey(), tag);
	}

	public T addInput(Ingredient input)
	{
		if(inputArray!=null)
			return addMultiInput(input);
		else
			return addIngredient(generateSafeInputKey(), input);
	}

	public T addInput(IngredientWithSize input)
	{
		if(inputArray!=null)
			return addMultiInput(input);
		else
			return addIngredient(generateSafeInputKey(), input);
	}
	
	
	/* =============== ItemStacks =============== */

	public JsonObject serializeItemStack(ItemStack stack)
	{
		JsonObject obj = new JsonObject();
		obj.addProperty(NedaireDatabase.Recipes.Serializers.ItemStack.ITEM, stack.getItem().getRegistryName().toString());
		if(stack.getCount() > 1)
			obj.addProperty(NedaireDatabase.Recipes.Serializers.AMOUNT, stack.getCount());
		if(stack.hasTag())
			obj.addProperty(NedaireDatabase.Recipes.Serializers.NBT, stack.getTag().toString());
		return obj;
	}

	public T addItem(String key, IItemProvider item)
	{
		return addItem(key, new ItemStack(item));
	}

	public T addItem(String key, ItemStack stack)
	{
		Preconditions.checkArgument(!stack.isEmpty(), "May not add empty ItemStack to recipe");
		return addWriter(jsonObject -> jsonObject.add(key, serializeItemStack(stack)));
	}

	/* =============== Ingredients =============== */

	public T addIngredient(String key, IItemProvider... itemProviders)
	{
		return addIngredient(key, Ingredient.fromItems(itemProviders));
	}

	public T addIngredient(String key, ItemStack... itemStacks)
	{
		return addIngredient(key, Ingredient.fromStacks(itemStacks));
	}

	public T addIngredient(String key, INamedTag <Item> tag)
	{
		return addIngredient(key, Ingredient.fromTag(tag));
	}

	public T addIngredient(String key, Ingredient ingredient)
	{
		return addWriter(jsonObject -> jsonObject.add(key, ingredient.serialize()));
	}

	public T addIngredient(String key, IngredientWithSize ingredient)
	{
		return addWriter(jsonObject -> jsonObject.add(key, ingredient.serialize()));
	}

	/* =============== Fluids =============== */

	public T addFluid(String key, FluidStack fluidStack)
	{
		return addWriter(jsonObject -> jsonObject.add(key, jsonSerializeFluidStack(fluidStack)));
	}

	public T addFluid(FluidStack fluidStack)
	{
		return addFluid(NedaireDatabase.Recipes.Serializers.FluidStack.FLUID, fluidStack);
	}

	public T addFluid(Fluid fluid, int amount)
	{
		return addFluid(NedaireDatabase.Recipes.Serializers.FluidStack.FLUID, new FluidStack(fluid, amount));
	}
	
	public static JsonElement jsonSerializeFluidStack(FluidStack fluidStack)
	{
		if(fluidStack==null)
			return JsonNull.INSTANCE;
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(NedaireDatabase.Recipes.Serializers.FluidStack.FLUID, fluidStack.getFluid().getRegistryName().toString());
		jsonObject.addProperty(NedaireDatabase.Recipes.Serializers.AMOUNT, fluidStack.getAmount());
		if(fluidStack.hasTag())
			jsonObject.addProperty(NedaireDatabase.Recipes.Serializers.NBT, fluidStack.getTag().toString());
		return jsonObject;
	}

	public static FluidStack jsonDeserializeFluidStack(JsonObject jsonObject)
	{
		Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(JSONUtils.getString(jsonObject, NedaireDatabase.Recipes.Serializers.FluidStack.FLUID)));
		int amount = JSONUtils.getInt(jsonObject, NedaireDatabase.Recipes.Serializers.AMOUNT);
		FluidStack fluidStack = new FluidStack(fluid, amount);
		if(JSONUtils.hasField(jsonObject, NedaireDatabase.Recipes.Serializers.NBT))
			fluidStack.setTag(JsonUtils.readNBT(jsonObject, NedaireDatabase.Recipes.Serializers.NBT));
		return fluidStack;
	}
	
	/* =============== IFinishedRecipe =============== */

	@Override
	public void serialize(JsonObject jsonObject)
	{
		for(Consumer<JsonObject> writer : this.writerFunctions)
			writer.accept(jsonObject);
	}

	@Override
	public ResourceLocation getID()
	{
		return id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer()
	{
		return serializer;
	}

	@Nullable
	@Override
	public JsonObject getAdvancementJson()
	{
		return null;
	}

	@Nullable
	@Override
	public ResourceLocation getAdvancementID()
	{
		return null;
	}

}
