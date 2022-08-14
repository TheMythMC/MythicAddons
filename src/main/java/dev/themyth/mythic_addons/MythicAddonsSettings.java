package dev.themyth.mythic_addons;

import carpet.settings.Rule;

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

    @Rule(
            desc = "Adds stackable bows, useful for dispensers",
            options = {"true", "false"},
            category = { MYTHIC, SURVIVAL, FEATURE }
    )
    public static boolean stackableBows = false;

    @Rule(
            desc = "Allows shulker boxes to stack in hoppers (this is to allow shulker box stacking in inventories but not hoppers)",
            options = { "true", "false"},
            category = { MYTHIC, SURVIVAL, FEATURE}
    )
    public static boolean shulkerBoxesStackingInHoppers = false;

    @Rule(
            desc = "Allows empty shulkerboxes to be inside other shulkerboxes",
            options = {"true", "false"},
            category = { MYTHIC, SURVIVAL, FEATURE}
          )
    public static boolean emptyShulkerCeption = false;

    @Rule(
            desc = "Implements the /c and /s commands from Quick Carpet",
            options = {"true", "false"},
            category = { MYTHIC, SURVIVAL, FEATURE}
    )
    public static boolean spectatorCommands = false;

    @Rule(
            desc = "Allows Rotten Flesh to be composted",
            options = { "true", "false" },
            category = { MYTHIC, SURVIVAL, FEATURE }
    )
    public static boolean rottenFleshCompostable = false;

    @Rule(
            desc = "Backs up player data every hour and stores it from last two backups",
            options = {"true", "false"},
            category = { MYTHIC, SURVIVAL }
    )
    public static boolean playerDataBackup = false;

    @Rule(
            desc = "Allows you to put mending and infinity on bows",
            options = { "true", "false" },
            category = { MYTHIC, SURVIVAL, FEATURE }
    )
    public static boolean mendFinity = false;
}
