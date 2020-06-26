/*
 * Ancient
 * Created at: 15-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.api.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class NedaireCapabilities 
{
	@CapabilityInject(IEnergonEnergy.class)
	public static Capability<IEnergonEnergy> ENERGON_ENERGY_CAPABILITY = null;
}
