/*
 * Ancient
 * Created at: 03-07-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.capability.inventory;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

public class NedaireManagedItemHandler extends NedaireItemHandler 
{
	protected List<NItemStorage> inputSlots;
	protected List<NItemStorage> outputSlots;
	
	protected NedaireItemHandler inputHandler = new NedaireItemHandler();
	protected NedaireItemHandler outputHandler = new NedaireItemHandler();
	protected NedaireItemHandler fullHandler = new NedaireItemHandler();
	
	protected final LazyOptional<IItemHandler> input = LazyOptional.of(() -> inputHandler);
	protected final LazyOptional<IItemHandler> output = LazyOptional.of(() -> outputHandler);
	protected final LazyOptional<IItemHandler> full = LazyOptional.of(() -> fullHandler);
	
	public NedaireManagedItemHandler() 
	{
		this(Lists.newArrayList(), Lists.newArrayList());
	}
	
	public NedaireManagedItemHandler(@Nonnull List<NItemStorage> inputSlots, @Nonnull List<NItemStorage> outputSlots) 
	{
		super();
		this.inputSlots = inputSlots;
		this.outputSlots = outputSlots;
		
		this.slots.addAll(inputSlots);
		this.slots.addAll(outputSlots);
	}
	
	public NedaireManagedItemHandler addInputSlot(@Nonnull NItemStorage slot)
	{
		this.inputSlots.add(slot);
		
		this.slots.add(inputSlots.size() - 1, slot);
		
		return this;
	}
	
	public NedaireManagedItemHandler addInputSlot(int capacity, Predicate<ItemStack> validator)
	{
		return addInputSlot(new NItemStorage(capacity, validator));
	}

	public NedaireManagedItemHandler addOutputSlot(@Nonnull NItemStorage slot)
	{
		this.outputSlots.add(slot);
		
		this.slots.add(slot);
		
		return this;
	}
	
	public NedaireManagedItemHandler addOutputSlot(int capacity)
	{
		return addOutputSlot(new NItemStorage(capacity, val -> false));
	}
	
	public NedaireManagedItemHandler build()
	{
		inputHandler.addSlots(inputSlots);
		outputHandler.addSlots(outputSlots);
		fullHandler.addSlots(slots);

		return this;
	}
	
	public void invalidate()
	{
		input.invalidate();
		output.invalidate();
		full.invalidate();
	}
	
	public NedaireItemHandler getFullHandler() 
	{
		return fullHandler;
	}
	
	public NedaireItemHandler getInputHandler() 
	{
		return inputHandler;
	}
	
	public NedaireItemHandler getOutputHandler() 
	{
		return outputHandler;
	}
	
	public LazyOptional<IItemHandler> getHandler(AccessType type)
	{
		switch (type)
		{
			case NONE:
				return LazyOptional.empty();
			case INPUT:
				return input;
			case OUTPUT:
				return output;
			case FULL:
				return full;
			default:
				return full;
		}
	}
	
	@Override
	public CompoundNBT serializeNBT() 
	{
		CompoundNBT tag = new CompoundNBT();
		
		tag.put("input", inputHandler.serializeNBT());	
		tag.put("output", outputHandler.serializeNBT());
		tag.put("full", fullHandler.serializeNBT());
		
		return tag;
	}
	
	@Override
	public void deserializeNBT(CompoundNBT nbt) 
	{
		this.inputHandler.deserializeNBT(nbt.getCompound("input"));
		this.outputHandler.deserializeNBT(nbt.getCompound("output"));
		this.fullHandler.deserializeNBT(nbt.getCompound("full"));
	}
}
