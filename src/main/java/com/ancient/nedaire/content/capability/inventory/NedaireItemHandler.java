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

import com.ancient.nedaire.util.database.NedaireDatabase;
import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandlerModifiable;

public class NedaireItemHandler implements IItemHandlerModifiable, INBTSerializable<CompoundNBT> 
{
    protected List<NItemStorage> slots;
	
	public NedaireItemHandler() 
	{
		this.slots = Lists.newArrayList();
	}
    
    public NedaireItemHandler(@Nonnull List<NItemStorage> slots) 
	{
		this.slots = slots;
	}
    
    public NedaireItemHandler addSlot(int capacity, Predicate<ItemStack> validator)
    {
    	return addSlot(new NItemStorage(capacity, validator));
    }
    
    public NedaireItemHandler addSlot (@Nonnull NItemStorage slot)
    {
    	this.slots.add(slot);
    	return this;
    }
    
    public NedaireItemHandler addSlots(@Nonnull List<NItemStorage> slots)
    {
    	this.slots.addAll(slots);
    	return this;
    }
    
    public void clear()
    {
    	for (NItemStorage slot : slots)
    	{
    		slot.setItem(ItemStack.EMPTY);
    	}
    }
    
	public boolean hasSlots() 
	{
		return this.slots.size() > 0;
	}
	
	public boolean isEmpty() 
	{
		for (NItemStorage stor : slots)
		{
			if (!stor.isEmpty())
			{
				return false;
			}
		}
		return true;
	}
    
    @Override
	public int getSlots() 
	{
		return slots.size();
	}

    public NItemStorage getSlot(int slot)
    {
   		return slots.get(slot);
    }
    
	@Override
    public void setStackInSlot (int slot, @Nonnull ItemStack stack)
	{
		if (!validateSlot(slot))
		{
			slots.get(slot).setItem(stack);
		}
	}
    
    @Override
	public ItemStack getStackInSlot(int slot) 
	{
		if (validateSlot(slot))
		{
			return ItemStack.EMPTY;
		}
		return slots.get(slot).getItem();
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) 
	{
        if (validateSlot(slot)) 
        {
            return stack;
        }
        
        ItemStack ret = slots.get(slot).insertItem(stack, simulate);

        return ret;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) 
	{
        if (validateSlot(slot)) 
        {
            return ItemStack.EMPTY;
        }
        
        ItemStack ret = slots.get(slot).extractItem(amount, simulate);

        return ret;
	}

	@Override
	public int getSlotLimit(int slot) 
	{
        if (validateSlot(slot)) 
        {
            return 0;
        }
        return slots.get(slot).getLimit();
	}

	@Override
	public boolean isItemValid(int slot, ItemStack stack) 
	{

        if (validateSlot(slot)) 
        {
            return false;
        }
        return slots.get(slot).isItemValid(stack);
	}
	
	@Override
	public CompoundNBT serializeNBT() 
	{
		CompoundNBT tag = new CompoundNBT();
		
		if (!hasSlots())
		{
			return tag;
		}
		
		ListNBT list = new ListNBT();
		for (int q = 0; q < getSlots(); q++)
		{
			list.add(slots.get(q).write());
		}
		
		tag.put(NedaireDatabase.Capabilities.ItemHandler.SLOTS, list);
		
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) 
	{
		clear();
		
		ListNBT list = nbt.getList(NedaireDatabase.Capabilities.ItemHandler.SLOTS, Constants.NBT.TAG_COMPOUND);
	
		for (int q = 0; q < list.size(); q++)
		{
			slots.get(q).read(list.getCompound(q));
		}
	}
	
	protected boolean validateSlot (int slot)
	{
		return slot < 0 || slot > getSlots();
	}
}
