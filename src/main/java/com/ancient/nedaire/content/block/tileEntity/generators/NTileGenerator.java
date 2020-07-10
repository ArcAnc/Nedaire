/*
 * Ancient
 * Created at: 10-07-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.block.tileEntity.generators;

import com.ancient.nedaire.api.capabilities.IEnergonEnergy;
import com.ancient.nedaire.content.block.NBlockInterfaces.IInteractionObject;
import com.ancient.nedaire.content.block.tileEntity.NTileSyncable;
import com.ancient.nedaire.content.capability.energy.EnergonEnergy;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.EnergonHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class NTileGenerator extends NTileSyncable implements IInteractionObject, ITickableTileEntity
{

	protected IEnergonEnergy energy = new EnergonEnergy(5000);
	protected final LazyOptional<IEnergonEnergy> energyHolder = LazyOptional.of(()-> energy);
	
	protected final int perTick = 3 * workSpeed;
	
	public NTileGenerator(TileEntityType<?> type) 
	{
		super(type);
	}

	@Override
	public void tick() 
	{
		if (canTick())
		{
			generateEnergy();
		}
	}
	
	protected abstract void generateEnergy();
	
	@Override
	public CompoundNBT write(CompoundNBT compound) 
	{
		super.write(compound);
		
		compound.put(NedaireDatabase.Capabilities.Energon.NBT_LOCATION, energy.serializeNBT());
		
		return compound;
	}
	
	@Override
	public void read(CompoundNBT compound) 
	{
		super.read(compound);
		
		energy.deserializeNBT(compound.getCompound(NedaireDatabase.Capabilities.Energon.NBT_LOCATION));
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if (cap == EnergonHelper.energonHandler)
		{
			return energyHolder.cast();
		}
		return super.getCapability(cap, side);
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return new TranslationTextComponent(getType().getRegistryName().toString().replace(':', '.'));
	}

	@Override
	public boolean canUseGui(PlayerEntity player) 
	{
		return ForgeRegistries.CONTAINERS.getValue(this.getType().getRegistryName()) != null;
	}

}
