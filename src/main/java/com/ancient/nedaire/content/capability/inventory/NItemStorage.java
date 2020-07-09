/*
 * Ancient
 * Created at: 03-07-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.capability.inventory;

import java.util.function.Predicate;

import javax.annotation.Nonnull;

import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.ItemHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class NItemStorage 
{
	protected Predicate<ItemStack> validator;
	
	@Nonnull
	protected ItemStack item = ItemStack.EMPTY;
	protected int capacity = -1;
	
	public NItemStorage() 
	{
		this(-1, e-> true);
	}
	
	public NItemStorage(int capacity) 
	{
		this(capacity, v -> true);
	}

	public NItemStorage(Predicate<ItemStack> validator) 
	{
		this(-1, validator);
	}
	
	public NItemStorage(int capacity, Predicate<ItemStack> validator) 
	{
		this.capacity = capacity;
		this.validator = validator;
	}
	
	public NItemStorage setValidator(Predicate<ItemStack> validator) 
	{
		if (validator != null)
		{
			this.validator = validator;
		}
		return this;
	}
	
	public NItemStorage setCapacity(int capacity) 
	{
		this.capacity = capacity;
		return this;
	}
	
	public void setItem(@Nonnull ItemStack item) 
	{
		this.item = item;
	}
	
	public ItemStack getItem() 
	{
		return item;
	}

	public int getCapacity() 
	{
		return capacity;
	}
	
	public Predicate<ItemStack> getValidator() 
	{
		return validator;
	}
	
	public boolean isItemValid(@Nonnull ItemStack stack)
	{
		return validator.test(stack);
	}
	
	
	public void read (CompoundNBT nbt)
	{
		this.capacity = nbt.getInt(NedaireDatabase.Capabilities.ItemHandler.Storage.CAPACITY);
		this.item = ItemStack.read(nbt);
	}
	
	
	public CompoundNBT write ()
	{
		CompoundNBT nbt = new CompoundNBT();
		
		nbt.putInt(NedaireDatabase.Capabilities.ItemHandler.Storage.CAPACITY, this.capacity);
		this.item.write(nbt);
		
		return nbt;
	}
	
	public int getCount()
	{
		return item.getCount();
	}
	
	public boolean isEmpty() 
	{
		return item.isEmpty();
	}

    @Nonnull
    public ItemStack insertItem(@Nonnull ItemStack stack, boolean simulate) 
    {
        if (stack.isEmpty()) 
        {
            return ItemStack.EMPTY;
        }
        if (!isItemValid(stack)) 
        {
            return stack;
        }
        if (item.isEmpty()) 
        {
            if (!simulate) 
            {
                setItem(stack);
            }
            return ItemStack.EMPTY;
        } 
        else if (ItemHelper.stacksIsEqualIgnoreCount(item, stack)) 
        {
            int totalCount = item.getCount() + stack.getCount();
            int limit = getLimit();
            if (totalCount <= limit) 
            {
                if (!simulate) 
                {
                    item.setCount(totalCount);
                }
                return ItemStack.EMPTY;
            }
            if (!simulate) 
            {
                item.setCount(limit);
            }
            return ItemHelper.copyStackWithAmount(stack, totalCount - limit);
        }
        return stack;
    }
    
    @Nonnull
    public ItemStack extractItem(int amount, boolean simulate) 
    {

        if (amount <= 0 || item.isEmpty()) 
        {
            return ItemStack.EMPTY;
        }
        int retCount = Math.min(item.getCount(), amount);
        ItemStack ret = ItemHelper.copyStackWithAmount(item, retCount);
        if (!simulate) 
        {
            item.shrink(retCount);
            if (item.isEmpty()) 
            {
                setItem(ItemStack.EMPTY);
            }
        }
        return ret;
    }
    
    public int getLimit()
    {
    	return capacity <= 0 ? item.getMaxStackSize() : capacity;
    }
}
