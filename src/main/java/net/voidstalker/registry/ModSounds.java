package net.voidstalker.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.voidstalker.VoidStalkerMod;

/**
 * Custom sound events. Each needs a matching entry in
 * assets/voidstalker/sounds.json and an .ogg file at the referenced path.
 * See README for the exact list of audio files to add.
 */
public class ModSounds {
    public static final SoundEvent STALKER_APPEAR = register("stalker_appear");
    public static final SoundEvent STALKER_AMBIENT = register("stalker_ambient");
    public static final SoundEvent STALKER_TELEPORT = register("stalker_teleport");
    public static final SoundEvent DISTANT_WHISPER = register("distant_whisper");
    public static final SoundEvent VOIDLING_AMBIENT = register("voidling_ambient");
    public static final SoundEvent VOID_BRUTE_AMBIENT = register("void_brute_ambient");
    public static final SoundEvent VOID_WATCHER_AMBIENT = register("void_watcher_ambient");
    public static final SoundEvent RANDOM_HORROR_STING = register("random_horror_sting");
    public static final SoundEvent STATIC_NOISE = register("static_noise");

    private static SoundEvent register(String path) {
        Identifier id = Identifier.of(VoidStalkerMod.MOD_ID, path);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void register() {
        // Static initializers above run on class load.
    }
}
