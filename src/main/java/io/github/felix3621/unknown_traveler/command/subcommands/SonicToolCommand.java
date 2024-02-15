package io.github.felix3621.unknown_traveler.command.subcommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.felix3621.unknown_traveler.item.custom.ISonicPart;
import io.github.felix3621.unknown_traveler.item.custom.ISonicScrewdriver;
import io.github.felix3621.unknown_traveler.item.custom.SonicBasePart;
import io.github.felix3621.unknown_traveler.item.custom.SonicScrewdriver;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class SonicToolCommand implements baseCommand{
    private static int part_set(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getPlayer() != null) {
            ItemStack stack = context.getSource().getPlayer().getMainHandItem();
            if (stack.getItem().getClass() == SonicBasePart.class) {
                SonicBasePart.setType(stack, ISonicPart.SonicComponentTypes.getFromID(IntegerArgumentType.getInteger(context, "value")));

                context.getSource().sendSuccess(Component.translatable("command.unknown_traveler.sonic.part.set"), true);
                return Command.SINGLE_SUCCESS;
            } else {
                context.getSource().sendFailure(Component.translatable("command.unknown_traveler.error.sonic.part.item"));
                return 0;
            }
        } else {
            context.getSource().sendFailure(Component.translatable("command.unknown_traveler.error.sonic.part.player"));
            return 0;
        }
    }
    private static int part_get(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getPlayer() != null) {
            ItemStack stack = context.getSource().getPlayer().getMainHandItem();
            if (stack.getItem().getClass() == SonicBasePart.class) {
                int type = SonicBasePart.getType(stack);

                context.getSource().sendSuccess(Component.translatable("command.unknown_traveler.sonic.part.get", type), true);
                return Command.SINGLE_SUCCESS;
            } else {
                context.getSource().sendFailure(Component.translatable("command.unknown_traveler.error.sonic.part.item"));
                return 0;
            }
        } else {
            context.getSource().sendFailure(Component.translatable("command.unknown_traveler.error.sonic.part.player"));
            return 0;
        }
    }
    private static int tool_set(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getPlayer() != null) {
            ItemStack stack = context.getSource().getPlayer().getMainHandItem();
            if (stack.getItem().getClass() == SonicScrewdriver.class) {
                SonicScrewdriver.setType(stack, ISonicScrewdriver.SonicPartTypes.getFromID(IntegerArgumentType.getInteger(context, "value")));

                context.getSource().sendSuccess(Component.translatable("command.unknown_traveler.sonic.tool.set"), true);
                return Command.SINGLE_SUCCESS;
            } else {
                context.getSource().sendFailure(Component.translatable("command.unknown_traveler.error.sonic.tool.item"));
                return 0;
            }
        } else {
            context.getSource().sendFailure(Component.translatable("command.unknown_traveler.error.sonic.tool.player"));
            return 0;
        }
    }
    private static int tool_get(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getPlayer() != null) {
            ItemStack stack = context.getSource().getPlayer().getMainHandItem();
            if (stack.getItem().getClass() == SonicScrewdriver.class) {
                int type = SonicScrewdriver.getType(stack);

                context.getSource().sendSuccess(Component.translatable("command.unknown_traveler.sonic.tool.get", type), true);
                return Command.SINGLE_SUCCESS;
            } else {
                context.getSource().sendFailure(Component.translatable("command.unknown_traveler.error.sonic.tool.item"));
                return 0;
            }
        } else {
            context.getSource().sendFailure(Component.translatable("command.unknown_traveler.error.sonic.tool.player"));
            return 0;
        }
    }
    @Override
    public ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("sonic")
                .requires(context -> context.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("part")
                        .then(Commands.literal("set")
                                .then(Commands.argument("value", IntegerArgumentType.integer(0, 6))
                                        .executes(SonicToolCommand::part_set)))
                        .then(Commands.literal("get")
                                .executes(SonicToolCommand::part_get)))
                .then(Commands.literal("tool")
                        .then(Commands.literal("set")
                                .then(Commands.argument("value", IntegerArgumentType.integer(0, 6))
                                        .executes(SonicToolCommand::tool_set)))
                        .then(Commands.literal("get")
                                .then(Commands.literal("get")
                                        .executes(SonicToolCommand::tool_get))));
    }
}
