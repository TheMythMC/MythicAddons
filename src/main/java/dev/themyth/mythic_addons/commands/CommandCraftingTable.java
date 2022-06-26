package dev.themyth.mythic_addons.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.themyth.mythic_addons.MythicAddonsSettings;

import net.minecraft.block.Blocks;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.apache.logging.log4j.core.jmx.Server;


import static net.minecraft.server.command.CommandManager.literal;

public class CommandCraftingTable {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(registerCommand(literal("craftingtable")));
        dispatcher.register(registerCommand(literal("cf")));

    }
    private static int open(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {

        if (!ctx.getSource().getPlayer().getInventory().contains(Blocks.CRAFTING_TABLE.asItem().getDefaultStack())){
           ctx.getSource().sendError(Text.literal("You do not have a crafting table in your inventory."));
           return 1;
        }

        ServerPlayerEntity player = ctx.getSource().getPlayer();
        player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inv, player1) -> new CraftingScreenHandler(syncId, inv), Text.translatable("container.crafting")));
        return 0;
    }

    private static LiteralArgumentBuilder<ServerCommandSource> registerCommand(LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder) {
        literalArgumentBuilder
                .requires( player -> SettingsManager.canUseCommand(player, MythicAddonsSettings.commandCraftingTable))
                .executes(CommandCraftingTable::open);
        return literalArgumentBuilder;
    }
}
