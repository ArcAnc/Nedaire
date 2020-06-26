/*
 * Ancient
 * Created at: 17-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NRotableBlock extends NBaseBlock 
{
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	public NRotableBlock(Properties properties) 
	{
		super(properties);
	
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.SOUTH));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		world.setBlockState(pos, getDefaultState().with(FACING, placer.getHorizontalFacing().getOpposite()));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		builder.add(FACING);
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rot) 
	{
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirror) 
	{
		return state.with(FACING, mirror.mirror(state.get(FACING)));
	}

}
