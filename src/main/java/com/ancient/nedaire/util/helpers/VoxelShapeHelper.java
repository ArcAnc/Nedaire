package com.ancient.nedaire.util.helpers; 

import net.minecraft.block.Block;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;

/**
 * @author Reispfannenfresser
 *
 * A class to help transforming VoxelShapes and AxisAlignedBoundingBoxes
 * 
 * Possible actions are scale, move, rotate and mirror.
 * In case there are any errors fix them yourself. You can do this! I believe in you!
 */
public class VoxelShapeHelper {
	
	/**
	 * Rotates an VS around the given axis at an angle of 90 degrees with the center of rotation being the center of the block
	 * 
	 * @param shape the VS to rotate
	 * @param axis the axis to rotate around
	 * @return the rotated VS
	 */
	public static VoxelShape rotate(VoxelShape shape, Axis axis) {
		return rotate(shape, axis, 1, new Vector3i(8, 8, 8));
	}
	
	/**
	 * Rotates an VS around the given axis at an angle of 90 degrees with the center of rotation being the center of rotation being the given position
	 * 
	 * @param shape the VS to rotate
	 * @param axis the axis to rotate around
	 * @param pos the center of rotation (e.g.: use "new Vec3i(8, 8, 8)" to rotate around the center of the block)
	 * @return the rotated VS
	 */
	public static VoxelShape rotate(VoxelShape shape, Axis axis, Vector3i pos) {
		return rotate(shape, axis, 1, pos);
	}
	
	/**
	 * Rotates an VS around the given axis at an angle of (90 * times) degrees with the center of rotation being the center of the block
	 * 
	 * @param shape the VS to rotate
	 * @param axis the axis to rotate around
	 * @param times
	 * @return the rotated VS
	 */
	public static VoxelShape rotate(VoxelShape shape, Axis axis, int times) {
		return rotate(shape, axis, times, new Vector3i(8, 8, 8));
	}
	
	/**
	 * Rotates an VS around the given axis at an angle of (90 * times) degrees with the center of rotation being the given position
	 * 
	 * @param shape the VS to rotate
	 * @param axis the axis to rotate around
	 * @param times 
	 * @param pos the center of rotation (e.g.: use "new Vec3i(8, 8, 8)" to rotate around the center of the block)
	 * @return the rotated VS
	 */
	public static VoxelShape rotate(VoxelShape shape, Axis axis, int times, Vector3i pos) {
		VoxelShape rotatedShape = VoxelShapes.empty();
		for (AxisAlignedBB box : shape.toBoundingBoxList()) {
			AxisAlignedBB rotatedBox = rotateAABB(box, axis, times, new Vector3d(pos.getX() / 16d, pos.getY() / 16d, pos.getZ() / 16d));
			rotatedShape = VoxelShapes.or(rotatedShape, voxelShapeFromAABB(rotatedBox));
		}
		return rotatedShape;
	}
	
	/**
	 * Mirrors an VS by the given axis with the mirror being at at the center of the block
	 * 
	 * @param shape the VS to mirror
	 * @param axis the axis to mirror along
	 * @return the mirrored VS
	 */
	public static VoxelShape mirror(VoxelShape shape, Axis axis) {
		return mirror(shape, axis, 8);
	}
	
	/**
	 * Mirrors an VS by the given axis with the mirror being at at the given position
	 * 
	 * @param shape the VS to mirror
	 * @param axis the axis to mirror along
	 * @param pos the position of the mirror (e.g.: use "8" to mirror over the center of the block)
	 * @return the mirrored VS
	 */
	public static VoxelShape mirror(VoxelShape shape, Axis axis, int pos) {
		VoxelShape mirroredShape = VoxelShapes.empty();
		for (AxisAlignedBB box : shape.toBoundingBoxList()) {
			AxisAlignedBB mirroredBox = mirrorAABB(box, axis, pos / 16d);
			mirroredShape = VoxelShapes.or(mirroredShape, voxelShapeFromAABB(mirroredBox));
		}
		return mirroredShape;
	}
	
	/**
	 * Moves the VS by the given offset
	 * 
	 * @param shape the VS to move
	 * @param offset the offset
	 * @return the moved VS
	 */
	public static VoxelShape move(VoxelShape shape, Vector3d offset) {
		VoxelShape movedShape = VoxelShapes.empty();
		for (AxisAlignedBB box : shape.toBoundingBoxList()) {
			AxisAlignedBB mirroredBox = moveAABB(box, new Vector3d(offset.x / 16, offset.y / 16, offset.z / 16));
			movedShape = VoxelShapes.or(movedShape, voxelShapeFromAABB(mirroredBox));
		}
		return movedShape;
	}
	
	/**
	 * Scales the VS with the given factor
	 * 
	 * @param shape the VS to scale
	 * @param factor the factor
	 * @return the scaled VS
	 */
	public static VoxelShape scale(VoxelShape shape, double factor) {
		VoxelShape scaledShape = VoxelShapes.empty();
		for (AxisAlignedBB box : shape.toBoundingBoxList()) {
			AxisAlignedBB mirroredBox = scaleAABB(box, factor);
			scaledShape = VoxelShapes.or(scaledShape, voxelShapeFromAABB(mirroredBox));
		}
		return scaledShape;
	}
	
	/**
	 * Scales the AABB with the given factor
	 * 
	 * @param box the AABB to scale
	 * @param factor the factor
	 * @return the scaled AABB
	 */
	public static AxisAlignedBB scaleAABB(AxisAlignedBB box, double factor) {
		double minX = box.minX * factor;
		double minY = box.minY * factor;
		double minZ = box.minZ * factor;
		double maxX = box.maxX * factor;
		double maxY = box.maxY * factor;
		double maxZ = box.maxZ * factor;
		
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	/**
	 * Moves the AABB by the given offset
	 * 
	 * @param box the AABB to move
	 * @param offset the offset
	 * @return the moved AABB
	 */
	public static AxisAlignedBB moveAABB(AxisAlignedBB box, Vector3d offset) {
		double minX = box.minX + offset.x;
		double minY = box.minY + offset.y;
		double minZ = box.minZ + offset.z;
		double maxX = box.maxX + offset.x;
		double maxY = box.maxY + offset.y;
		double maxZ = box.maxZ + offset.z;
		
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	/**
	 * Creates a VS from an AABB
	 * 
	 * Since a VS usually contains values from 0 to 16 and an AABB usually contains values from 0 to 1 the result is scaled by a factor of 16.
	 * 
	 * @param box the AABB to convert
	 * @return the created VS
	 */
	public static VoxelShape voxelShapeFromAABB(AxisAlignedBB box) {
		box = scaleAABB(box, 16);
		return Block.makeCuboidShape(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
	}
	
	/**
	 * Mirrors an AABB by the given axis with the mirror being at at the given position
	 * 
	 * @param box the AABB to mirror
	 * @param axis the axis to mirror along
	 * @param pos the position of the mirror (e.g.: use "0.5" to mirror over the center of the block)
	 * @return the mirrored AABB
	 */
	public static AxisAlignedBB mirrorAABB(AxisAlignedBB box, Axis axis, double pos) {
		double minX = box.minX;
		double minY = box.minY;
		double minZ = box.minZ;
		double maxX = box.maxX;
		double maxY = box.maxY;
		double maxZ = box.maxZ;
		
		switch(axis) {
		case X:
			minX = 2 * pos - minX;
			maxX = 2 * pos - maxX;
			break;
		case Y:
			minY = 2 * pos - minY;
			maxY = 2 * pos - maxY;
			break;
		case Z:
			minZ = 2 * pos - minZ;
			maxZ = 2 * pos - maxZ;
			break;
		}
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	/**
	 * Rotates an AABB around the given axis at an angle of (90 * times) degrees with the center of rotation being the given position
	 * 
	 * @param box the AABB to rotate
	 * @param axis the axis to rotate around
	 * @param times 
	 * @param pos the center of rotation (e.g.: use "new Vec3d(0.5, 0.5, 0.5)" to rotate around the center of the block)
	 * @return the rotated AABB
	 */
	private static AxisAlignedBB rotateAABB(AxisAlignedBB box, Axis axis, int times, Vector3d pos) {
		double tmp;
		double minX = box.minX;
		double minY = box.minY;
		double minZ = box.minZ;
		double maxX = box.maxX;
		double maxY = box.maxY;
		double maxZ = box.maxZ;

		switch(axis) {
		case X:
			for(int i = 0; i < times; i++) {
				tmp = minY;
				minY = pos.getY() + minZ - pos.getZ();
				minZ = pos.getZ() - tmp + pos.getY();
				tmp = maxY;
				maxY = pos.getY() + maxZ - pos.getZ();
				maxZ = pos.getZ() - tmp + pos.getY();				
			}
			break;
		case Y:
			for(int i = 0; i < times; i++) {
				tmp = minZ;
				minZ = pos.getZ() + minX - pos.getX();
				minX = pos.getX() - tmp + pos.getZ();
				tmp = maxZ;
				maxZ = pos.getZ() + maxX - pos.getX();
				maxX = pos.getX() - tmp + pos.getZ();
			}
			break;
		case Z:
			for(int i = 0; i < times; i++) {
				tmp = minX;
				minX = pos.getX() + minY - pos.getY();
				minY = pos.getY() - tmp + pos.getX();
				tmp = maxX;
				maxX = pos.getX() + maxY - pos.getY();
				maxY = pos.getY() - tmp + pos.getX();
			}
			break;
		}
		
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}
}
