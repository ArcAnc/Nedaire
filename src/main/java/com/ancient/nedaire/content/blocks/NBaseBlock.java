package com.ancient.nedaire.content.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;

public class NBaseBlock extends Block
{
	
	private RenderType renderLayer = null;
	
	public NBaseBlock(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public String getTranslationKey() 
	{
		return getRegistryName().toString().replace(':', '.').replace(':', '.').replace('/', '.');
	}
	
	public RenderType getRenderLayer() 
	{
		return renderLayer;
	}
	
	public void setRenderLayer(RenderType renderLayer) 
	{
		this.renderLayer = renderLayer;
	}
}
