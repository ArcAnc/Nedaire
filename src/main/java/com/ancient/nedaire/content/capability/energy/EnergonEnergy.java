/*
 * Ancient
 * Created at: 15-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.capability.energy;

import com.ancient.nedaire.api.capabilities.IEnergonEnergy;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.EnergonHelper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class EnergonEnergy implements IEnergonEnergy, ICapabilityProvider
{
	private final LazyOptional<IEnergonEnergy> holder = LazyOptional.of(() -> this);

	private int energy;
	private int energyMax;
	private int extracting;
	private int input;
	
	public EnergonEnergy() 
	{
		this(0,0, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
	
	public EnergonEnergy(int maxEnergon) 
	{
		this(0, maxEnergon, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
	
	public EnergonEnergy(int curEnergon, int maxEnergon) 
	{
		this(curEnergon, maxEnergon, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
	
	public EnergonEnergy(int curEnergon, int maxEnergon, int extract, int input) 
	{
		this.energy = curEnergon;
		this.energyMax = maxEnergon;
		
		this.extracting = extract;
		this.input = input;
	}
	
	@Override
	public int add(int count, boolean isSimulated) 
	{
		int returned = 0;
		
		if (energy < energyMax)
		{
			if (isAllowedReceiving())
			{
				returned = Math.min(energyMax - energy, Math.min(input, count));
				if (!isSimulated)
				{
					energy += returned;
				}
			}
		}
		return returned;
	}

	@Override
	public int extract(int count, boolean isSimulated) 
	{
		int returned = 0;
		
		if (energy > 0)
		{
			if (isAllowedExtraction())
			{
				returned = Math.min(energy, Math.min(extracting, count));
				if (!isSimulated)
				{
					energy -= returned;
				}
			}
		}
		return returned;

	}

	@Override
	public IEnergonEnergy setEnergon(int count) 
	{
		this.energy = count;
		if (this.energy < 0)
		{
			this.energy = 0;
		}
		else if (this.energy > this.energyMax) 
		{
			this.energy = this.energyMax;
		}
		return this;
	}

	@Override
	public int getEnergon() 
	{
		return this.energy;
	}

	@Override
	public IEnergonEnergy setMaxEnergon(int count) 
	{
		this.energyMax = count;
		if (getEnergon() > getMaxEnergon())
		{
			setEnergon(count);
		}
		return this;
	}

	@Override
	public int getMaxEnergon() 
	{
		return this.energyMax;
	}

	@Override
	public IEnergonEnergy setReceive(int value) 
	{
		this.input = value;
		return this;
	}

	@Override
	public IEnergonEnergy setExtract(int value) 
	{
		this.extracting = value;
		return this;
	}

	@Override
	public boolean isAllowedExtraction() 
	{
		return extracting > 0;
	}

	@Override
	public boolean isAllowedReceiving() 
	{
		return input > 0;
	}

	@Override
	public CompoundNBT serializeNBT() 
	{
		CompoundNBT tag = new CompoundNBT();
		tag.putInt(NedaireDatabase.Capabilities.Energon.ADDRESS, getEnergon());
		tag.putInt(NedaireDatabase.Capabilities.Energon.MAX_ADDRESS, getMaxEnergon());
		tag.putInt(NedaireDatabase.Capabilities.Energon.ENERGY_EXTRACTION, extracting);
		tag.putInt(NedaireDatabase.Capabilities.Energon.ENERGY_RECEIVING, input);
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) 
	{
		setEnergon(nbt.getInt(NedaireDatabase.Capabilities.Energon.ADDRESS));
		setMaxEnergon(nbt.getInt(NedaireDatabase.Capabilities.Energon.MAX_ADDRESS));

		this.extracting = nbt.getInt(NedaireDatabase.Capabilities.Energon.ENERGY_EXTRACTION);
		this.input = nbt.getInt(NedaireDatabase.Capabilities.Energon.ENERGY_RECEIVING);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if (cap == EnergonHelper.energonHandler)
		{
			return holder.cast();
		}
		return LazyOptional.empty();
	}
}
