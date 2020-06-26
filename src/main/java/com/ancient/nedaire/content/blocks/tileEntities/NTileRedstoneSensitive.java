package com.ancient.nedaire.content.blocks.tileEntities;

import com.ancient.nedaire.util.database.NedaireDatabase;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;

public class NTileRedstoneSensitive extends NTileSyncable 
{

	private int currentRedstoneMod = 2;
	
	public NTileRedstoneSensitive(TileEntityType<?> type) 
	{
		super(type);
	}
	
	public boolean isPowered ()
	{
		if (currentRedstoneMod == 0 && getWorld().isBlockPowered(getPos()))
			return true;
		if (currentRedstoneMod == 1 && !getWorld().isBlockPowered(getPos()))
			return true;
		if (currentRedstoneMod == 2)
			return true;
		return false;
	}
	
	@Override
	protected boolean canTick() 
	{
		return super.canTick() && isPowered();
	}
	
	public void setCurrentRedstoneMod(int currentRedstoneMod) 
	{
		this.currentRedstoneMod = currentRedstoneMod;
	}
	
	/**
	 * 0 - required redstone
	 * 1 - required disabled redstone
	 * 2 - ignore all redstone
	 */
	public int getCurrentRedstoneMod() 
	{
		return this.currentRedstoneMod;
	}
	
	@Override
	public void handleClientPacket(CompoundNBT tag) 
	{
		super.handleClientPacket(tag);
		
		if (tag.contains(NedaireDatabase.TileEntity.NBTAddress.Machines.RedstoneSensitive.REDSTONE_MOD))
		{
			this.currentRedstoneMod = tag.getInt(NedaireDatabase.TileEntity.NBTAddress.Machines.RedstoneSensitive.REDSTONE_MOD);
		}

	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound)
	{
		super.write(compound);
	
		compound.putInt(NedaireDatabase.TileEntity.NBTAddress.Machines.RedstoneSensitive.REDSTONE_MOD, this.currentRedstoneMod);
		
		return compound;
	}
	
	@Override
	public void read(CompoundNBT compound) 
	{
		super.read(compound);
	
		this.currentRedstoneMod = compound.getInt(NedaireDatabase.TileEntity.NBTAddress.Machines.RedstoneSensitive.REDSTONE_MOD);
	}

}
