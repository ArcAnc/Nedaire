/*
 * Ancient
 * Created at: 02-07-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.block;

import com.ancient.nedaire.content.capability.inventory.AccessType;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;

public class NedaireBlockStateProperties 
{
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	
	public static class ACCESS_TYPE
	{
		public static final EnumProperty<AccessType> ACCESS_NORTH = EnumProperty.create("access_north", AccessType.class);
		public static final EnumProperty<AccessType> ACCESS_SOUTH = EnumProperty.create("access_south", AccessType.class);
		public static final EnumProperty<AccessType> ACCESS_EAST = EnumProperty.create("access_east", AccessType.class);
		public static final EnumProperty<AccessType> ACCESS_WEST = EnumProperty.create("access_west", AccessType.class);
		public static final EnumProperty<AccessType> ACCESS_UP = EnumProperty.create("access_up", AccessType.class);
		public static final EnumProperty<AccessType> ACCESS_DOWN = EnumProperty.create("access_down", AccessType.class);
	}
}
