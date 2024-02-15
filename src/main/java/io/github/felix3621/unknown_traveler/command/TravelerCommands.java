package io.github.felix3621.unknown_traveler.command;

import com.mojang.brigadier.CommandDispatcher;
import io.github.felix3621.unknown_traveler.UnknownTraveler;
import io.github.felix3621.unknown_traveler.command.subcommands.LightCommand;
import io.github.felix3621.unknown_traveler.command.subcommands.SonicToolCommand;
import io.github.felix3621.unknown_traveler.command.subcommands.tardisCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class TravelerCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal(UnknownTraveler.MODID)
                        .then(new tardisCommand().register(dispatcher))
                        .then(new LightCommand().register(dispatcher))
                        .then(new SonicToolCommand().register(dispatcher))
        );
    }
}
