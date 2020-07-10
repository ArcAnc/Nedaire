/*
 * Ancient
 * Created at: 10-07-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.block.tileEntity.generators;

import com.ancient.nedaire.api.NedaireTileEntities;
import com.ancient.nedaire.content.gui.containers.tiles.NGeneratorContainer;
import com.ancient.nedaire.util.helpers.WorldHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.world.dimension.DimensionType;

public class NTileSolarGenerator extends NTileGenerator 
{

	public NTileSolarGenerator() 
	{
		super(NedaireTileEntities.GENERATOR_SOLAR.get());
	}
	
	@Override
	protected void generateEnergy() 
	{
		if (WorldHelper.isDayTime(getWorld()) && 
				!getWorld().isRaining() && 
				!getWorld().isThundering() && 
				getWorld().getDimension().getType() == DimensionType.OVERWORLD &&
				getWorld().canBlockSeeSky(getPos()))
		{
			energy.add(perTick, false);
		}
	
		sendUpdateToClient(true);	
	}

	@Override
	public boolean canUseGui(PlayerEntity player) 
	{
		return true;
	}
	
	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) 
	{
		return new NGeneratorContainer(inv, this, id);
	}

}
