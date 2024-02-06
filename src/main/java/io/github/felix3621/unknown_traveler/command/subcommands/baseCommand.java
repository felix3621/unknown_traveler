package io.github.felix3621.unknown_traveler.command.subcommands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public interface baseCommand {
    Component MUST_BE_IN_TARDIS = Component.translatable("command.unknown_traveler.error.tardis_dim");
    Component CABABILITY_ERROR = Component.translatable("command.unknown_traveler.error.capability");
    ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher);
}
