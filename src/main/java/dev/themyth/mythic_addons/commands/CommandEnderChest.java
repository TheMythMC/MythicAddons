package dev.themyth.mythic_addons.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.themyth.mythic_addons.MythicAddonsSettings;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class CommandEnderChest {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(registerCommand(literal("enderchest")));
        dispatcher.register(registerCommand(literal("ec")));
    }

    private static int open(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {

        if (!ctx.getSource().getPlayer().getInventory().contains(Blocks.ENDER_CHEST.asItem().getDefaultStack())) {
            ctx.getSource().sendError(Text.translatable("You do not have a EnderChest in your inventory."));
            return 1;
        }

        ServerPlayerEntity player = ctx.getSource().getPlayer();
        EnderChestInventory enderChest = player.getEnderChestInventory();
        player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInv, playerEntity) -> GenericContainerScreenHandler.createGeneric9x3(syncId, playerInv, enderChest), Text.translatable("container.enderchest")));
        return 0;
    }

    private static LiteralArgumentBuilder<ServerCommandSource> registerCommand(LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder) {
        literalArgumentBuilder
                .requires(player -> SettingsManager.canUseCommand(player, MythicAddonsSettings.commandEnderChest))
                .executes(CommandEnderChest::open);

        return literalArgumentBuilder;
    }
}