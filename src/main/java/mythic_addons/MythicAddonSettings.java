package mythic_addons;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

public class MythicAddonSettings {

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
            category = { MYTHIC, SURVIVAL, CREATIVE, FEATURE }
    )
    public static boolean editableSigns;
}
