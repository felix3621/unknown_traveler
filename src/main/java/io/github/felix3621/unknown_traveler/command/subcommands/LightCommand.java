package io.github.felix3621.unknown_traveler.command.subcommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.felix3621.unknown_traveler.util.capabilities.Capabilities;
import io.github.felix3621.unknown_traveler.util.capabilities.light.ILightCapability;
import io.github.felix3621.unknown_traveler.util.savedata.SDControl;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class LightCommand implements baseCommand{
    private static int checkLight(CommandContext<CommandSourceStack> context) {
        if (SDControl.tardis_dimensions.contains(context.getSource().getLevel().dimension())) {
            ILightCapability cap = context.getSource().getLevel().getCapability(Capabilities.TARDIS_DIM).orElse(null);
            if (cap != null) {
                context.getSource().sendSuccess(Component.translatable("command.unknown_traveler.light.check", cap.getLight()), true);
                return Command.SINGLE_SUCCESS;
            } else {
                context.getSource().sendFailure(CABABILITY_ERROR);
                return 0;
            }
        } else {
            context.getSource().sendFailure(MUST_BE_IN_TARDIS);
            return 0;
        }
    }
    private static int setLight(CommandContext<CommandSourceStack> context) {
        if (SDControl.tardis_dimensions.contains(context.getSource().getLevel().dimension())) {
            ILightCapability cap = context.getSource().getLevel().getCapability(Capabilities.TARDIS_DIM).orElse(null);
            if (cap != null) {
                cap.setLight(IntegerArgumentType.getInteger(context, "value"));
                context.getSource().sendSuccess(Component.translatable("command.unknown_traveler.light.set", cap.getLight()), true);
                return Command.SINGLE_SUCCESS;
            } else {
                context.getSource().sendFailure(CABABILITY_ERROR);
                return 0;
            }
        } else {
            context.getSource().sendFailure(MUST_BE_IN_TARDIS);
            return 0;
        }
    }
    @Override
    public ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("light")
                .requires(context -> context.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("get")
                        .executes(LightCommand::checkLight))
                .then(Commands.literal("set")
                        .then(Commands.argument("value", IntegerArgumentType.integer(0, 15))
                                .executes(LightCommand::setLight)));
    }
}
