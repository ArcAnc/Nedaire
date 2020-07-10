package com.ancient.nedaire.content.gui.containers.tiles;

import com.ancient.nedaire.content.block.tileEntity.generators.NTileGenerator;
import com.ancient.nedaire.util.helpers.BlockHelper;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class NGeneratorContainer extends NTileContainer<NTileGenerator>
{

	public NGeneratorContainer(int id, PlayerInventory player, PacketBuffer buf) 
	{
		this (player, BlockHelper.castTileEntity(player.player.getEntityWorld(), buf.readBlockPos(), NTileGenerator.class), id);
	}
	
	public NGeneratorContainer(PlayerInventory player, NTileGenerator tile, int id) 
	{
		super (player, tile, id);
		
		addPlayerInventory(player);
	}
}
