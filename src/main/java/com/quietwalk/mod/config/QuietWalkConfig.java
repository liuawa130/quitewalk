package com.quietwalk.mod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class QuietWalkConfig {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.DoubleValue NORMAL_SPEED_FACTOR;
    public static final ForgeConfigSpec.DoubleValue SLOW_WALK_FACTOR;
    public static final ForgeConfigSpec.DoubleValue SNEAK_SPEED_FACTOR;
    public static final ForgeConfigSpec.BooleanValue REQUIRE_MOVEMENT_INPUT;
    public static final ForgeConfigSpec.BooleanValue SILENT_SNEAK;
    public static final ForgeConfigSpec.BooleanValue SILENT_QUIET_WALK;
    public static final ForgeConfigSpec.DoubleValue SNEAK_ACCURACY_FACTOR;
    public static final ForgeConfigSpec.BooleanValue SNEAK_KEEP_GUN_POSE;
    public static final ForgeConfigSpec.BooleanValue SHOW_DEBUG;

    static {
        var b = new ForgeConfigSpec.Builder();
        b.comment("Quiet Walk Mod - Client Settings").push("general");

        NORMAL_SPEED_FACTOR = b.comment("Normal walk speed multiplier (CS2 running speed). 1.3 = old sprint speed.")
                .defineInRange("normal_speed_factor", 1.3, 0.1, 3.0);
        SLOW_WALK_FACTOR = b.comment("Quiet walk speed multiplier (Ctrl). 0.52 = CS2 silent walk, relative to running speed.")
                .defineInRange("slow_walk_factor", 0.52, 0.05, 1.0);
        SNEAK_SPEED_FACTOR = b.comment("Sneak speed multiplier (Shift). 0.34 = CS2 crouch speed, relative to running speed.")
                .defineInRange("sneak_speed_factor", 0.34, 0.0, 1.0);
        REQUIRE_MOVEMENT_INPUT = b.comment("Only slow down while pressing WASD.")
                .define("require_movement_input", true);
        SILENT_SNEAK = b.comment("Silence footsteps while sneaking (Shift).")
                .define("silent_sneak", true);
        SILENT_QUIET_WALK = b.comment("Silence footsteps while quiet walking (Ctrl).")
                .define("silent_quiet_walk", true);
        SNEAK_ACCURACY_FACTOR = b.comment("TaCZ bullet spread multiplier while sneaking. 0.5 = half spread.")
                .defineInRange("sneak_accuracy_factor", 0.5, 0.0, 1.0);
        SNEAK_KEEP_GUN_POSE = b.comment("Keep gun pose straight (no tilt) while sneaking. Only affects TaCZ.")
                .define("sneak_keep_gun_pose", true);
        SHOW_DEBUG = b.comment("Print debug messages to the log.")
                .define("show_debug", false);

        b.pop();
        SPEC = b.build();
    }
}
