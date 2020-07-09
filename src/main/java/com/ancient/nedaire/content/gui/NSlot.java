package com.ancient.nedaire.content.gui;

import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class NSlot extends SlotItemHandler
{

	private Container container;
	protected int slotIndex;
	
	public NSlot(Container container, IItemHandler itemHandler, int index, int xPosition, int yPosition) 
	{
		super(itemHandler, index, xPosition, yPosition);
		
		this.container = container;
		this.slotIndex = index;
	}
	
	public Container getContainer() 
	{
		return container;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) 
	{
		return getItemHandler().isItemValid(slotIndex, stack);
	}
	
	public static class NOutPut extends NSlot
	{

		public NOutPut(Container container, IItemHandler itemHandler, int index, int xPosition, int yPosition) 
		{
			super(container, itemHandler, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) 
		{
			return false;
		}
	}
	
/*	public static class NFurnace extends NSlot
	{

		private String furnaceType;
		
		public NFurnace(Container container, IItemHandler itemHandler, String furnaceType, int index, int xPosition, int yPosition) 
		{
			super(container, itemHandler, index, xPosition, yPosition);
			this.furnaceType = furnaceType;
		}
		
		public String getFurnaceType() 
		{
			return furnaceType;
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) 
		{
			return RecipesHelper.isRightFurnaceInput(stack, furnaceType);
		}
		
	}
*/	
/*	public static class NGlassFactory extends NSlot
	{

		public NGlassFactory(Container container, IItemHandler itemHandler, int index, int xPosition, int yPosition) 
		{
			super(container, itemHandler, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) 
		{
			return true;
		}
		
	}
	
	public static class NEnchanterSlot extends NSlot
	{

		public NEnchanterSlot(Container container, IItemHandler itemHandler, int index, int xPosition, int yPosition) 
		{
			super(container, itemHandler, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) 
		{
			return !stack.isEnchanted() && stack.isEnchantable();
		}
		
	}
	
	public static class NJeweleryToolsSlot extends NSlot
	{

		public NJeweleryToolsSlot(Container container, IItemHandler itemHandler, int index, int xPosition, int yPosition) 
		{
			super(container, itemHandler, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) 
		{
			return stack.getItem() instanceof NJewerelyTools;
		}
		
	}
	
	public static class NFilterSlot extends NSlot
	{

		public NFilterSlot(Container container, IItemHandler itemHandler, int index, int xPosition, int yPosition) 
		{
			super(container, itemHandler, index, xPosition, yPosition);
		}
		
		@Override
		public int getSlotStackLimit() 
		{
			return 1;
		}
		
		@Override
		public boolean canTakeStack(PlayerEntity playerIn) 
		{
			return false;
		}
		
	}
	
	public static class NGemSlot extends Slot
	{

		private final Container container;
		private final IGemHandler handler;
		private final boolean meta;
		protected final int index;

		private ResourceLocation background = null;
		private ResourceLocation foreground = null;
		
		public NGemSlot(Container container, boolean isMeta, IGemHandler gemHandler, int index, int xPosition, int yPosition) 
		{
			super(new Inventory(0), index, xPosition, yPosition);
			
			this.container = container;
			this.handler = gemHandler;
			this.meta = isMeta;
			this.index = index;
		}
		
		public Container getContainer() 
		{
			return container;
		}
		
		public ResourceLocation getBackground() 
		{
			return background;
		}
		
		public ResourceLocation getForeground() 
		{
			return foreground;
		}
		
		public NGemSlot setBackground(ResourceLocation background) 
		{
			this.background = background;
			return this;
		}
		
		public NGemSlot setForeground(ResourceLocation foreground) 
		{
			this.foreground = foreground;
			return this;
		}
		
		public NGemColor getColor() 
		{
			return handler.getSlotColor(index);
		}
		
		@Override
		public ItemStack getStack() 
		{
			return handler.getGemInSlot(index);
		}
		
		@Override
		public void putStack(ItemStack stack) 
		{
			handler.insertGemInSlot(index, stack, false);
			onSlotChanged();
		}
		
		@Override
		public int getSlotStackLimit() 
		{
			return 1;
		}
		
		@Override
		public int getItemStackLimit(ItemStack stack) 
		{
			return 1;
		}
		
		@Override
		public boolean canTakeStack(PlayerEntity playerIn) 
		{
			return !handler.getGemInSlot(index).isEmpty();
		}
		
		@Override
		public ItemStack decrStackSize(int amount) 
		{
			return handler.extractGemFromSlot(index, false);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) 
		{
			if (stack.getItem() instanceof INGem)
			{
				INGem item = (INGem)stack.getItem();
				return (meta ? item.isMetaGem(stack) : !item.isMetaGem(stack)) && item.isFaceted(stack);
			}
			return false;
		}
		
	}
*/
}
