/*
 * Ancient
 * Created at: 15-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.capabilities;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Functions;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.Direction;

public class SidedAccessController 
{
	/**
	 * Left - input
	 * Right - output
	 */
	
	private final Map<Direction, Pair<Integer, Integer>> sides;

	public SidedAccessController(int input, int output) 
	{
		this.sides = Arrays.stream(Direction.values()).collect(Collectors.toMap(Functions.identity(), (dir) -> Pair.of(input, output)));
	}
	
	public SidedAccessController setInput(int input) 
	{
		this.sides.entrySet().stream().forEach( value -> 
		{
			Pair<Integer, Integer> s = sides.get(value.getKey());
			value.setValue(Pair.of(input, s.getSecond()));
		});
		
		return this;
	}
	
	public SidedAccessController setOutput(int output) 
	{
		this.sides.entrySet().stream().forEach( value -> 
		{
			Pair<Integer, Integer> s = sides.get(value.getKey());
			value.setValue(Pair.of(s.getFirst(), output));
		});
		
		return this;
	}
	
	public Set<Direction> getAllowedReceivingSides() 
	{
		return sides.entrySet().stream().
		filter(entry -> entry.getValue().getFirst() > 0).
		map(Entry :: getKey).
		collect(Collectors.toSet());
	}
	
	public Set<Direction> getAllowedExtractionSides() 
	{
		return sides.entrySet().stream().
		filter(entry -> entry.getValue().getSecond() > 0).
		map(Entry :: getKey).
		collect(Collectors.toSet());
	}
	
	public boolean isAllowedExtraction(Direction dir) 
	{
		return (dir != null ? sides.get(dir).getFirst() > 0 : true );
	}

	public boolean isAllowedReceiving(Direction dir) 
	{
		return (dir != null ? sides.get(dir).getSecond() > 0 : true );
	}
	
	public SidedAccessController setReceivingForSide (Direction side, int amount)
	{
		Pair<Integer, Integer> s = sides.get(side);
		sides.put(side, Pair.of(amount, s.getSecond()));
		return this;
	}
	
	public SidedAccessController setExtractionForSide (Direction side, int amount)
	{
		Pair<Integer, Integer> s = sides.get(side);
		sides.put(side, Pair.of(s.getFirst(), amount));
		return this;
	}
	
	public int getReceivingForSide(Direction side)
	{
		return sides.get(side).getSecond();
	}
	
	public int getExtractionForSide(Direction side)
	{
		return sides.get(side).getFirst();
	}
}
