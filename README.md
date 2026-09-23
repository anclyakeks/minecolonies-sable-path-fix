# MineColonies Sable Path Fix

A NeoForge mod for Minecraft 1.21.1 that prevents MineColonies pathfinding crashes when used with Sable and modded blocks.

## Fixes

- Clamps out-of-range `Path.getNodePos` indices so path handling remains safe.
- Skips a MineColonies movement option when a tagged block does not have the block-state property that MineColonies expects. This applies to any affected block; no block list is needed.
- Skips node-reference updates when a failed path calculation leaves the navigator without a path.

## Compatibility

Version 1.0.1 requires MineColonies `1.1.1396-1.21.1-snapshot`, Minecraft 1.21.1, NeoForge 21.1 or newer, and Java 21. Later MineColonies snapshots need separate verification.

## Build

Run `./gradlew build` (or `gradlew.bat build` on Windows). The JAR is written to `build/libs/`.