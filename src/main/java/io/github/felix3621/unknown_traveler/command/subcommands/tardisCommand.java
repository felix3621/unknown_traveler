package io.github.felix3621.unknown_traveler.command.subcommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.felix3621.unknown_traveler.block.ModBlocks;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

public class tardisCommand implements baseCommand{
    private int spawn(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getPlayer() == null) {
            context.getSource().sendFailure(Component.literal("UNAVAILIBLE"));
            return 0;
        } else {
            Vec3 pos = context.getSource().getPosition();
            ServerLevel level = context.getSource().getLevel();
            String direction = String.valueOf(context.getSource().getPlayer().getDirection());

            BlockPos blockPos = BlockPos.containing(pos);
            Direction blockDir;

            switch (direction) {
                case "north" -> {
                    blockDir = Direction.SOUTH;
                    blockPos = blockPos.north(2).above();
                }
                case "south" -> {
                    blockDir = Direction.NORTH;
                    blockPos = blockPos.south(2).above();
                }
                case "east" -> {
                    blockDir = Direction.WEST;
                    blockPos = blockPos.east(2).above();
                }
                case "west" -> {
                    blockDir = Direction.EAST;
                    blockPos = blockPos.west(2).above();
                }
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            }

            level.setBlockAndUpdate(blockPos, ModBlocks.TARDIS_EXTERIOR_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, blockDir));

            return Command.SINGLE_SUCCESS;
        }
    }

    @Override
    public ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("tardis")
                .requires(context -> context.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("spawn")
                        .executes(this::spawn)
                );
    }
}
