/*
 * Ancient
 * Created at: 17-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;

public class NBlockInterfaces 
{
	public interface IInteractionObject extends INamedContainerProvider 
	{
		boolean canUseGui(PlayerEntity player);
	}
}
