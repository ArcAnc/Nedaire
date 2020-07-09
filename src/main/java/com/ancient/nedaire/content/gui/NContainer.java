package com.ancient.nedaire.content.gui;

import com.ancient.nedaire.util.database.NedaireDatabase;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class NContainer extends Container 
{

	protected NContainer(ContainerType<?> type, int id) 
	{
		super(type, id);
	}

	protected void addPlayerInventory (PlayerInventory playerInv)
	{
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				addSlotWithBackGround(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}
	
		for (int x = 0; x < 9; x++)
		{
			addSlotWithBackGround(new Slot(playerInv, x, 8 + x * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity player) 
	{
		return !player.isSpectator();
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int fromSlot) 
	{
	    ItemStack previous = ItemStack.EMPTY;
	    Slot slot = this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) 
	    {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (fromSlot < this.inventorySlots.size() - 36) 
	        {
	            if (!this.mergeItemStack(current, this.inventorySlots.size() - 36, this.inventorySlots.size(), true))
	                return ItemStack.EMPTY;
	            slot.onSlotChange(current, previous);	
	        } 
	        else 
	        {
	        	if (!this.mergeItemStack(current, 0, this.inventorySlots.size() - 36, false))
	                return ItemStack.EMPTY;
	        }

	        if (current.isEmpty())
	            slot.putStack(ItemStack.EMPTY); 
	        else
	            slot.onSlotChanged();

	        if (current.getCount() == previous.getCount())
	            return ItemStack.EMPTY;
	        slot.onTake(player, current);
	    }
	    return previous;
	}

	protected <T extends Slot> T addSlotWithBackGround (T slot) 
	{
		addSlot(slot);
		
		slot.setBackground(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NedaireDatabase.GUI.Locations.SLOT);

		return slot;
	}
	
	protected <T extends Slot> T addSlot (T slot, ResourceLocation loc) 
	{
		addSlot(slot);
		
		slot.setBackground(PlayerContainer.LOCATION_BLOCKS_TEXTURE, loc);

		return slot;
	}	

}
