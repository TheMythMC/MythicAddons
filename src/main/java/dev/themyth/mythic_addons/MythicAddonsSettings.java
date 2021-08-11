package dev.themyth.mythic_addons;

import carpet.settings.Rule;
import org.lwjgl.system.CallbackI;

import static carpet.settings.RuleCategory.*;

public class MythicAddonsSettings {

    private final static String MYTHIC = "Mythic";

    @Rule(
            desc = "Tells what region you are in.",
            options = {"true", "false"},
            category = { MYTHIC, COMMAND, FEATURE }
    )
    public static boolean commandRegion = false;

    @Rule(
            desc = "Enables shulkers to be stacked in player inventories using Tweakaroo",
            options = {"true", "false"},
            category = { MYTHIC, SURVIVAL, FEATURE }
    )
    public static boolean stackableShulkersInPlayerInventories = false;

    @Rule(
            desc = "Makes flint and steel act like in 1.12",
            options = {"true", "false"},
            category = {MYTHIC, SURVIVAL, CREATIVE, FEATURE}
    )
    public static boolean oldFlintAndSteel = false;

    @Rule(
            desc = "Allows you to edit signs",
            options = {"true", "false"},
            category = { MYTHIC, SURVIVAL,  FEATURE }
    )
    public static boolean editableSigns;

    @Rule(
        desc = "Allows you to use a command to open the crafting table",
        options = { "true", "false"},
        category = {MYTHIC, SURVIVAL, FEATURE}
    )
    public static boolean commandCraftingTable = false;

    @Rule(
            desc = "Allows you to open the enderchest via a command if you have it in your inventory",
            options = {"true", "false"},
            category = {MYTHIC, SURVIVAL, FEATURE}
    )
    public static boolean commandEnderChest = false;
    @Rule(
            desc = "Allows you to have a hat that gives you 1.8-style combat",
            options = {"true", "false"},
            category = {MYTHIC, SURVIVAL, FEATURE}
    )
    public static boolean commandUnicorn = false;

    @Rule(
            desc = "Tracks statistics from the player file",
            options = {"true", "false"},
            category = {MYTHIC, SURVIVAL, FEATURE}
    )
    public static boolean betterStatistics = true;

}
