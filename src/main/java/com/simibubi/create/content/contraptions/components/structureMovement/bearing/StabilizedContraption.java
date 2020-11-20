package com.simibubi.create.content.contraptions.components.structureMovement.bearing;

import com.simibubi.create.content.contraptions.components.structureMovement.AllContraptionTypes;
import com.simibubi.create.content.contraptions.components.structureMovement.Contraption;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StabilizedContraption extends Contraption {

	private Direction facing;

	public StabilizedContraption() {}

	public StabilizedContraption(Direction facing) {
		this.facing = facing;
	}

	@Override
	public boolean assemble(World world, BlockPos pos) {
		BlockPos offset = pos.offset(facing);
		if (!searchMovedStructure(world, offset, null))
			return false;
		startMoving(world);
		expandBoundsAroundAxis(Axis.Y);
		if (blocks.isEmpty())
			return false;
		return true;
	}
	
	@Override
	protected boolean isAnchoringBlockAt(BlockPos pos) {
		return false;
	}

	@Override
	protected AllContraptionTypes getType() {
		return AllContraptionTypes.STABILIZED;
	}
	
	@Override
	public CompoundNBT writeNBT() {
		CompoundNBT tag = super.writeNBT();
		tag.putInt("Facing", facing.getIndex());
		return tag;
	}

	@Override
	public void readNBT(World world, CompoundNBT tag) {
		facing = Direction.byIndex(tag.getInt("Facing"));
		super.readNBT(world, tag);
	}
	
	@Override
	protected boolean canAxisBeStabilized(Axis axis) {
		return false;
	}
	
	public Direction getFacing() {
		return facing;
	}

}