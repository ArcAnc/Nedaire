/*
 * Ancient
 * Created at: 02-07-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.capability.inventory;

import net.minecraft.util.IStringSerializable;

public enum AccessType implements IStringSerializable 
{
	NONE ("none"),
	INPUT ("input"),
	OUTPUT ("output"),
	FULL ("full");

	private final String name;
	
	private AccessType (String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName() 
	{
		return name;
	}

}
