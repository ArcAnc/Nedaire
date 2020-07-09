package com.ancient.nedaire.content.gui.containers.tiles;

import com.ancient.nedaire.content.block.tileEntity.NTileGrinder;
import com.ancient.nedaire.content.gui.NSlot;
import com.ancient.nedaire.util.helpers.BlockHelper;
import com.ancient.nedaire.util.helpers.ItemHelper;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class NGrinderContainer extends NTileContainer<NTileGrinder> 
{

	public NGrinderContainer(int id, PlayerInventory player, PacketBuffer buf) 
	{
		this (player, BlockHelper.castTileEntity(player.player.getEntityWorld(), buf.readBlockPos(), NTileGrinder.class), id);
	}
	
	public NGrinderContainer(PlayerInventory player, NTileGrinder tile, int id) 
	{
		super (player, tile, id);
		
		ItemHelper.getItemHandler(tile).ifPresent(handler -> 
		{
			addSlotWithBackGround(new NSlot(this, handler, 0, 46, 35));
			addSlotWithBackGround(new NSlot.NOutPut(this, handler, 1, 106, 35));
			addSlotWithBackGround(new NSlot.NOutPut(this, handler, 2, 76, 55));
		});

		addPlayerInventory(player);
	}

}
