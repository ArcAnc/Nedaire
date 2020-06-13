/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.materials.tool;

public class NToolRunicMaterial extends NAbstractToolMaterial
{

	private final int gemCountMeta;
	private final int gemCountStandart;

	public NToolRunicMaterial(Builder builder) 
	{
		super(builder);
	
		this.gemCountMeta = builder.gemCountMeta;
		this.gemCountStandart = builder.gemCountStandart;
	}
	
	public int getGemCountMeta() 
	{
		return gemCountMeta;
	}
	
	public int getGemCountStandart() 
	{
		return gemCountStandart;
	}

	public static class Builder extends NAbstractToolMaterial.AbstractBuilder<Builder>
	{
		private int gemCountMeta;
		private int gemCountStandart;
		
		public Builder() { }
		
		public Builder setGemCountMeta(int gemCountMeta) 
		{
			this.gemCountMeta = gemCountMeta;
			return getSelf();
		}
		
		public Builder setGemCountStandart(int gemCountStandart) 
		{
			this.gemCountStandart = gemCountStandart;
			return getSelf();
		}
		
		@Override
		public NToolRunicMaterial build() 
		{
			return new NToolRunicMaterial(getSelf());
		}
		
		@Override
		protected Builder getSelf() 
		{
			return this;
		}
		
	}
}
