package com.ancient.nedaire.content.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.client.renderer.RenderType;

public class NOreBlock extends NBaseBlock
{

	public NOreBlock(Properties properties) 
	{
		super(properties.hardnessAndResistance(3.0f, 5.0f).sound(SoundType.STONE).lightValue(0));
	
		setRenderLayer(RenderType.getCutout());
	}

}
