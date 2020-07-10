package com.ancient.nedaire.content.block;

import net.minecraft.block.SoundType;
import net.minecraft.client.renderer.RenderType;

public class NOreBlock extends NBaseBlock
{

	public NOreBlock(Properties properties) 
	{
		super(properties.hardnessAndResistance(3.0f, 5.0f).sound(SoundType.STONE).setLightLevel(state -> 0));
	
		setRenderLayer(RenderType.getCutout());
	}

}
