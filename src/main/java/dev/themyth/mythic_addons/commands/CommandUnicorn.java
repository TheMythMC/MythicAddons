package dev.themyth.mythic_addons.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import dev.themyth.mythic_addons.MythicAddonsSettings;
import dev.themyth.mythic_addons.util.EnumArgumentType;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Objects;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandUnicorn {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("unicorn")
                .requires( (player) -> SettingsManager.canUseCommand(player, MythicAddonsSettings.commandUnicorn))
                .executes(ctx -> {
                    ServerPlayerEntity player = ctx.getSource().getPlayer();
                    ItemStack stack = Blocks.END_ROD.asItem().getDefaultStack();
                    stack.setCount(1);
                    stack.addEnchantment(Enchantments.BINDING_CURSE, 1);
                    stack.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier("generic.attack_speed", 100, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.HEAD);
                    Item equippedItem = player.getEquippedStack(EquipmentSlot.HEAD).getItem();

                    if(CommandUnicorn.isUnicorned(player)) {
                        player.equipStack(EquipmentSlot.HEAD, Blocks.AIR.asItem().getDefaultStack());
                        ctx.getSource().sendFeedback(new TranslatableText("You are no longer a unicorn"), true);
                        return 0;
                    }

                    if (equippedItem.getGroup() == ItemGroup.COMBAT) {
                        ctx.getSource().sendError(new TranslatableText("You must not be wearing a helmet"));
                        return 1;
                    }
                    player.equipStack(EquipmentSlot.HEAD, stack);
                    ctx.getSource().sendFeedback(new TranslatableText("You are now a unicorn!"), true);
                    return 0;

                }).then(argument("Item", EnumArgumentType.enumeration(CommandUnicorn.Items.class))
                        .executes( ctx -> {
                            ServerPlayerEntity player = ctx.getSource().getPlayer();
                            Item equippedItem = player.getEquippedStack(EquipmentSlot.HEAD).getItem();
                            ItemStack stack = Registry.ITEM.get(Identifier.tryParse(EnumArgumentType.getEnumeration(ctx, "Item", CommandUnicorn.Items.class).id)).getDefaultStack();
                            stack.setCount(1);
                            stack.addEnchantment(Enchantments.BINDING_CURSE, 1);
                            stack.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier("generic.attack_speed", 100, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.HEAD);


                            if (equippedItem.getGroup() == ItemGroup.COMBAT) {
                                ctx.getSource().sendError(new TranslatableText("You must not be wearing a helmet"));
                                return 1;
                            }
                            player.equipStack(EquipmentSlot.HEAD, stack);
                            ctx.getSource().sendFeedback(new TranslatableText("You are now a (different) unicorn!"), true);
                            return 0;
                        })
            )
        );


    }

    private enum Items{
        END_ROD("end_rod"),
        LEAD("lead"),
        BUTTON("oak_button"),
        BONE("bone"),
        ENDER_CHEST("ender_chest"),
        GLOWSTONE("glowstone"),
        GLASS("glass");
        public String id;

        Items(String id){
            this.id = id;
        }
    }

    public static boolean isUnicorned(ServerPlayerEntity player) {
        Item equippedItem = player.getEquippedStack(EquipmentSlot.HEAD).getItem();

        return Objects.equals(Registry.ITEM.getId(equippedItem).getPath(), Items.END_ROD.id) ||
                Objects.equals(Registry.ITEM.getId(equippedItem).getPath(), Items.BONE.id)   ||
                Objects.equals(Registry.ITEM.getId(equippedItem).getPath(), Items.BUTTON.id) ||
                Objects.equals(Registry.ITEM.getId(equippedItem).getPath(), Items.LEAD.id)   ||
                Objects.equals(Registry.ITEM.getId(equippedItem).getPath(), Items.GLASS.id)  ||
                Objects.equals(Registry.ITEM.getId(equippedItem).getPath(), Items.ENDER_CHEST.id) ||
                Objects.equals(Registry.ITEM.getId(equippedItem).getPath(), Items.GLOWSTONE.id);
    }

    public static boolean isHelmet(ServerPlayerEntity player) {
        boolean b1;
        Item item = player.getEquippedStack(EquipmentSlot.HEAD).getItem();
        if (item instanceof ArmorItem) {
            if (((ArmorItem)item).getSlotType() == EquipmentSlot.HEAD) {
                b1 = true;
            }
            else {
                b1=false;
            }
        } else
            b1 = false;

        return b1 || !isUnicorned(player);
    }
}
