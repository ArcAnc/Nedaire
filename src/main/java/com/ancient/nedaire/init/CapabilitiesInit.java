/*
 * Ancient
 * Created at: 17-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.init;

import com.ancient.nedaire.Nedaire;
import com.ancient.nedaire.api.capabilities.IEnergonEnergy;
import com.ancient.nedaire.content.capability.energy.EnergonEnergy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilitiesInit 
{
	public static void initCapabilities()
	{
		Nedaire.instance.getLOGGER().info("Staring initialize nedaire capabilities");
		initEnergonCapability();
		Nedaire.instance.getLOGGER().info("Nedaire capabilies has been initializated succesfully");
	}
	
	private static void initEnergonCapability ()
	{
		CapabilityManager.INSTANCE.register(IEnergonEnergy.class, new Capability.IStorage<IEnergonEnergy>() 
		{
			@Override
			public void readNBT(Capability<IEnergonEnergy> capability, IEnergonEnergy instance, Direction side,	INBT nbt) 
			{
				instance.deserializeNBT((CompoundNBT)nbt);
			}
			
			@Override
			public INBT writeNBT(Capability<IEnergonEnergy> capability, IEnergonEnergy instance, Direction side) 
			{
				return instance.serializeNBT();
			}
		}, EnergonEnergy :: new);
	}
}
