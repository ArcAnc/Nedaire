/*
 * Ancient
 * Created at: 24-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.block.tileEntity;

import com.ancient.nedaire.api.NedaireTileEntities;
import com.ancient.nedaire.content.block.NedaireBlockStateProperties;
import com.ancient.nedaire.content.capability.energy.EnergonEnergy;
import com.ancient.nedaire.content.capability.inventory.AccessType;
import com.ancient.nedaire.content.capability.inventory.NedaireItemHandler;
import com.ancient.nedaire.content.capability.inventory.NedaireManagedItemHandler;
import com.ancient.nedaire.content.gui.containers.tiles.NGrinderContainer;
import com.ancient.nedaire.data.StackWithChance;
import com.ancient.nedaire.data.recipes.GrinderRecipe;
import com.ancient.nedaire.util.helpers.BlockHelper;
import com.ancient.nedaire.util.helpers.ItemHelper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.items.ItemHandlerHelper;

public class NTileGrinder extends NTileAbstractMachine 
{

	public NTileGrinder() 
	{
		super(NedaireTileEntities.GRINDER.get());
		inv = new NedaireManagedItemHandler().
				addInputSlot(-1, GrinderRecipe :: hasInput).
				addOutputSlot(-1).
				addOutputSlot(-1).
				build();
		
		energy = new EnergonEnergy(5000).setEnergon(5000);
	}

	@Override
	protected boolean pullOutput() 
	{
		BlockState state = getBlockState();
		
		NedaireItemHandler opt = inv.getOutputHandler();
		
		for (Direction dir : Direction.values())
		{
			AccessType type = BlockHelper.getAccess(state, dir); 
			if (type == AccessType.OUTPUT)
			{	
				ItemStack stack0 = opt.getStackInSlot(0).copy();
				ItemStack stack1 = opt.getStackInSlot(1).copy();

				if (!(stack0.isEmpty() && stack1.isEmpty()))
				{
					return ItemHelper.getNearbyItemHandler(this, dir).map(handler ->
					{
						ItemStack ret0 = ItemHandlerHelper.insertItemStacked(handler, stack0, false);
						ItemStack ret1 = ItemHandlerHelper.insertItemStacked(handler, stack1, false);
						opt.setStackInSlot(0, ret0);
						opt.setStackInSlot(1, ret1);
						return true;
					}).orElse(false);
				}
			}
		}
		return false;
	}

	@Override
	protected boolean gatherInput() 
	{
		BlockState state = getBlockState();
		
		NedaireItemHandler opt = inv.getInputHandler();
		
		for (Direction dir : Direction.values())
		{
			AccessType type = BlockHelper.getAccess(state, dir);
			if (type == AccessType.INPUT)
			{
				return ItemHelper.getNearbyItemHandler(this, dir).map(handler -> 
				{
					for (int q = 0; q < handler.getSlots(); q ++)
					{
						ItemStack input = opt.getStackInSlot(0).copy();
						ItemStack stack = handler.getStackInSlot(q).copy();

						if (opt.getSlot(0).isItemValid(stack) && (ItemHelper.isItemEqual(stack, input) || input.isEmpty()))
						{
							handler.extractItem(q, stack.getCount() - ItemHandlerHelper.insertItemStacked(opt, stack, false).getCount(), false);
						}
					}
					return true;
				}).orElse(false);
			}
		}
		return false;
	}

	@Override
	protected boolean tryWork() 
	{
		ItemStack stack = inv.getStackInSlot(0).copy();
		ItemStack output = inv.getStackInSlot(1).copy();
		ItemStack output2 = inv.getStackInSlot(2).copy();

		boolean isWork = false;
		
		isWork = GrinderRecipe.findRecipe(stack).map(rec -> 
		{
			energyForRecipe = rec.getEnergy();
			if (energyConsumed >= energyForRecipe)
			{
				inv.getInputHandler().extractItem(0, 1, false);
				
				ItemStack toOutputMain = output.isEmpty() ? rec.getOutput().copy() : ItemHelper.copyStackWithAmount(output, output.getCount() + rec.getOutput().copy().getCount());
				inv.setStackInSlot(1, toOutputMain);
				
				for(int q = 0; q <  rec.getOutputSecondary().size(); q++)
				{
					StackWithChance st = rec.getOutputSecondary().get(q);
					
					ItemStack toOutputSecondary = output2.isEmpty() ? st.getStack().copy() : ItemHelper.copyStackWithAmount(output2, output2.getCount() + st.getStack().copy().getCount());
					
					if (getWorld().getRandom().nextFloat() <= st.getChance())
					{
						inv.setStackInSlot(2, toOutputSecondary);
					}
				}
				energyConsumed = 0;
				energyForRecipe = Integer.MAX_VALUE;
				return true;
			}
			else
			{
				int forConsume = energyConsumePerTick * workSpeed;
				if (energy.getEnergon() >= forConsume)
				{
					energyConsumed += forConsume;
					energy.extract(forConsume, false);
					
					return true;
				}
				else
				{
					return false; 
				}
			}
		}).orElseGet( () -> 
		{
			energyConsumed = 0;
			energyForRecipe = Integer.MAX_VALUE;
			return false;
		});
		
		getWorld().setBlockState(getPos(), getWorld().getBlockState(getPos()).with(NedaireBlockStateProperties.ACTIVE, isWork), 2);

		return isWork;
	}

	@Override
	public boolean canUseGui(PlayerEntity player) 
	{
		return true;
	}
	
	@Override
	public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) 
	{
		return new NGrinderContainer(inventory, this, id);
	}
	
	@Override
	protected boolean canWork() 
	{
		return true;
	}
}
