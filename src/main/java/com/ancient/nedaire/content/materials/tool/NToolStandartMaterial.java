/*
 * Ancient
 * Created at: 03-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.materials.tool;

public class NToolStandartMaterial extends NAbstractToolMaterial 
{

	private NToolStandartMaterial(Builder builder) 
	{
		super(builder);
	}
	
	public static class Builder extends NToolStandartMaterial.AbstractBuilder<Builder>
	{
		public Builder() {}
		
		@Override
		public NToolStandartMaterial build() 
		{
			return new NToolStandartMaterial(getSelf());
		}
		
		@Override
		protected Builder getSelf() 
		{
			return this;
		}
	}

}
