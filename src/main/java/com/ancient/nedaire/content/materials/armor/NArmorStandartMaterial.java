/*
 * Ancient
 * Created at: 03-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.materials.armor;

public class NArmorStandartMaterial extends NAbstractArmorMaterial
{
	private NArmorStandartMaterial(Builder builder)
	{
		super(builder);
	}
	
	public static class Builder extends NAbstractArmorMaterial.AbstractBuilder<Builder>
	{
		public Builder() {}
		
		@Override
		public NArmorStandartMaterial build() 
		{
			return new NArmorStandartMaterial(getSelf());
		}
		
		@Override
		protected Builder getSelf() 
		{
			return this;
		}
	}
}
