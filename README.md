# Quiet Walk

A Minecraft 1.20.1 Forge mod that brings CS2-style movement mechanics to Minecraft.

## Features

- **Sprint completely disabled** — sprint is always off, regardless of keybinds
- **Normal walk = CS2 running speed** — default 1.3x vanilla walk speed (configurable)
- **Ctrl = quiet walk** — silent footsteps, speed at 52% of running (configurable)
- **Shift = crouch** — CS2 crouch speed, 34% of running (configurable)
- **Silent footsteps** — no footstep sounds while sneaking or quiet walking
- **TaCZ gun accuracy boost** — 50% less spread while sneaking (configurable)
- **TaCZ gun pose** — gun stays straight (no tilt) while sneaking
- **PresenceFootsteps compatible** — silently cancels PF footstep generation

## Requirements

- Minecraft 1.20.1
- Forge 47.x
- Java 17

## Optional Dependencies

- [TaCZ (Timeless and Classics Zero)](https://github.com/MCModderAnchor/TACZ) — for gun accuracy and pose features
- [PresenceFootsteps](https://github.com/Sollace/Presence-Footsteps) — for footstep sound compatibility

## Configuration

All settings are in `config/quietwalk-common.toml` (auto-generated after first launch, on both client and server):

```toml
[general]
    # Normal walk speed multiplier (CS2 running speed). 1.3 = old sprint speed.
    # Range: 0.1 ~ 3.0
    normal_speed_factor = 1.3

    # Quiet walk speed multiplier (Ctrl). 0.52 = CS2 silent walk, relative to running speed.
    # Range: 0.05 ~ 1.0
    slow_walk_factor = 0.52

    # Sneak speed multiplier (Shift). 0.34 = CS2 crouch speed, relative to running speed.
    # Range: 0.0 ~ 1.0
    sneak_speed_factor = 0.34

    # Only slow down while pressing WASD.
    require_movement_input = true

    # Silence footsteps while sneaking (Shift).
    silent_sneak = true

    # Silence footsteps while quiet walking (Ctrl).
    silent_quiet_walk = true

    # TaCZ bullet spread multiplier while sneaking. 0.5 = half spread.
    # Range: 0.0 ~ 1.0
    sneak_accuracy_factor = 0.5

    # Keep gun pose straight (no tilt) while sneaking. Only affects TaCZ.
    sneak_keep_gun_pose = true

    # Print debug messages to the log.
    show_debug = false
```

## Speed Calculation

Speed modifiers use `MULTIPLY_TOTAL` (multiplicative stacking). The vanilla sneak slowdown is cancelled via Mixin.

| State | Active Modifiers | Effective Speed |
|---|---|---|
| Normal walk | Run (1.3x) | base x 1.3 = CS2 running |
| Quiet walk (Ctrl) | Run (1.3x) + Quiet (0.52x) | base x 1.3 x 0.52 = CS2 silent walk |
| Sneak (Shift) | Run (1.3x) + Crouch (0.34x) | base x 1.3 x 0.34 = CS2 crouch |

## Build

```bash
./gradlew build
```

Output: `build/libs/quietwalk-1.5.1.jar`

## Installation

- **Client**: install like any Forge mod (put jar in `mods/` folder)
- **Server**: can be installed on server too — prevents errors and ensures sound cancellation & projectile spread work consistently

## License

[CC BY 4.0](https://creativecommons.org/licenses/by/4.0/)
