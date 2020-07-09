package com.ancient.nedaire.content.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;

public class NBaseBlock extends Block
{
	
	private RenderType renderLayer = null;
	
	public NBaseBlock(Properties properties) 
	{
		super(properties);
		
		setRenderLayer(RenderType.getSolid());
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
