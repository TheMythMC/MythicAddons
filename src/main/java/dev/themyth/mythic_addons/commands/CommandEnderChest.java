package dev.themyth.mythic_addons.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.themyth.mythic_addons.MythicAddonsSettings;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

import static net.minecraft.server.command.CommandManager.literal;

public class CommandEnderChest {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("enderchest")
                .requires( player -> SettingsManager.canUseCommand(player, MythicAddonsSettings.commandEnderChest))
                .executes(CommandEnderChest::open));
        // If anyone knows how to do this better please tell me (or open a PR)
        dispatcher.register(literal("ec")
                .requires(player -> SettingsManager.canUseCommand(player, MythicAddonsSettings.commandEnderChest))
                .executes(CommandEnderChest::open));
    }
    private static int open(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {

        if (!ctx.getSource().getPlayer().getInventory().contains(Blocks.ENDER_CHEST.asItem().getDefaultStack())){
            ctx.getSource().sendError(new TranslatableText("You do not have a EnderChest in your inventory."));
            return 1;
        }

        ServerPlayerEntity player = ctx.getSource().getPlayer();
        EnderChestInventory enderChest = player.getEnderChestInventory();
        player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInv, playerEntity) -> GenericContainerScreenHandler.createGeneric9x3(syncId, playerInv, enderChest), new TranslatableText("container.enderchest")));
        return 0;
    }
}
