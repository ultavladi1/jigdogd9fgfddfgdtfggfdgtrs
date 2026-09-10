# Void Stalker — Fabric mod for Minecraft 1.21.4

Rebuilt to target Minecraft **1.21.4** (Yarn mappings, Fabric Loader 0.16.9,
Loom 1.9, Fabric API 0.107.0+1.21.4, Java 21) instead of the bleeding-edge
26.2 version used in an earlier version of this project. 1.21.4 is a
long-stable, thoroughly documented release, so this code is written with
real confidence in the class/method names — not guesswork verified only
after several failed builds, like the 26.2 attempt was.

No weapon or armor items/classes are included, per request — everything
combat-adjacent (SwordItem, PickaxeItem, ArmorItem, tool/armor materials)
has been removed. Items are all non-combat: crafting materials and two
functional items (a buff-on-use relic, and a detector that senses nearby
horror mobs).

## What's real vs. what still needs finishing

**I cannot run Gradle myself** — my sandbox has no network access to
download the Minecraft/Fabric artifacts and actually compile this. So
"BUILD SUCCESSFUL" isn't something I can personally verify before handing
this to you, no matter how confident the code is. What I can say
honestly: 1.21.4's Yarn-mapped API is something I have extensive, reliable
training knowledge of (unlike 26.2, which required live searching for
nearly every class name), so the risk of a wrong method name here is much
lower.

**Binary assets** — same limitation as always: I can write Java and JSON,
not PNG textures or OGG audio. See the asset checklist below.

**Highest-risk spot if something doesn't compile:** the client renderer
classes (`client/render/*.java`). They use the classic
`MobEntityRenderer<T, Model<T>>` pattern, which was standard through at
least 1.20.x/most of 1.21.x — if 1.21.4 had already switched to the newer
split "EntityRenderState" pattern by the time you read this, that's the
part that would need adjusting. Everything else (items, blocks, entities,
registries, events) uses long-stable APIs I'm confident in.

## Build instructions

1. Install a JDK 21 (Temurin, from adoptium.net).
2. Open the project folder in IntelliJ IDEA — Gradle syncs automatically,
   downloading Minecraft 1.21.4, Yarn mappings, and Fabric Loader/API.
3. Run the `runClient` Gradle task (or the auto-generated "Minecraft
   Client" run configuration) to test in a dev environment.
4. Build the jar: `./gradlew build` from the project root.
5. Output: `build/libs/void-stalker-1.0.0.jar`.
6. Install: put that jar plus the matching Fabric API jar
   (`fabric-api-0.107.0+1.21.4` or newer for 1.21.4) into your
   `.minecraft/mods` folder, on top of Fabric Loader 0.16.9+ for 1.21.4.

GitHub Actions (`.github/workflows/build.yml`) builds this automatically on
every push to `main` using JDK 21, and uploads the jar as a workflow
artifact if the build succeeds.

## Assets you still need to add

### Textures (PNG)
- `assets/voidstalker/textures/item/{void_crystal,void_fragment,stalker_eye,strange_artifact,ancient_relic,horror_detector}.png` — 16×16
- `assets/voidstalker/textures/block/{void_stone,corrupted_stone,void_crystal_ore,void_bricks,ancient_void_block,void_lantern}.png` — 16×16
- `assets/voidstalker/textures/entity/{stalker,voidling,void_brute,void_watcher}.png` — 64×64, vanilla zombie/humanoid UV layout
- `assets/voidstalker/icon.png` — 128×128

### Sounds (OGG)
Referenced in `sounds.json`, not included:
`stalker/appear1`, `stalker/ambient1`, `stalker/ambient2`, `stalker/teleport1`,
`ambient/whisper1`, `ambient/whisper2`, `voidling/ambient1`,
`void_brute/ambient1`, `void_watcher/ambient1`, `ambient/sting1`,
`ambient/sting2`, `ambient/static1` (all under `assets/voidstalker/sounds/`).

### Structures
Not included in this pass, to keep scope and file count manageable —
happy to add the worldgen JSON scaffolding (structure/structure_set/pool)
in a follow-up if wanted; the actual room layouts still need to be built
in-game with a Structure Block either way, the same as before.

## Config
`config/voidstalker.json` is generated on first run: Stalker vanish
distance/aggression/max-nearby, monster spawn weights, and horror-event
chances/cooldown (with a top-level `horrorEventsEnabled` toggle).
