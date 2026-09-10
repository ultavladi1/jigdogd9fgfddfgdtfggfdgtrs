package net.voidstalker.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.voidstalker.config.VoidStalkerConfig;
import net.voidstalker.entity.custom.StalkerEntity;
import net.voidstalker.registry.ModEntities;
import net.voidstalker.registry.ModSounds;

import java.util.HashMap;
import java.util.Map;

public class HorrorEventManager {
    private static final Map<ServerPlayerEntity, Integer> COOLDOWNS = new HashMap<>();

    public static void register() {
        ServerTickEvents.END_WORLD_TICK.register(HorrorEventManager::onWorldTick);
    }

    private static void onWorldTick(ServerWorld world) {
        VoidStalkerConfig config = VoidStalkerConfig.get();
        if (!config.horrorEventsEnabled || world.getTime() % 20 != 0) return;
        Random random = world.getRandom();
        for (ServerPlayerEntity player : world.getPlayers()) {
            int cooldown = COOLDOWNS.getOrDefault(player, 0);
            if (cooldown > 0) {
                COOLDOWNS.put(player, cooldown - 20);
                continue;
            }
            if (random.nextDouble() < config.horrorEventChancePerMinute / 60.0) {
                triggerRandomEvent(world, player, config, random);
                COOLDOWNS.put(player, config.horrorEventCooldownTicks);
            }
        }
    }

    private static void triggerRandomEvent(ServerWorld world, ServerPlayerEntity player, VoidStalkerConfig config, Random random) {
        double total = config.darknessEventChance + config.stalkerEventChance + config.distantSoundEventChance + config.staticNoiseEventChance;
        if (total <= 0) return;
        double roll = random.nextDouble() * total;
        double acc = 0;
        if ((acc += config.darknessEventChance) > roll) darknessEvent(player);
        else if ((acc += config.stalkerEventChance) > roll) stalkerEvent(world, player, config, random);
        else if ((acc += config.distantSoundEventChance) > roll) distantSoundEvent(world, player, random);
        else staticNoiseEvent(world, player, random);
    }

    private static void darknessEvent(ServerPlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 0, false, false));
    }

    private static void stalkerEvent(ServerWorld world, ServerPlayerEntity player, VoidStalkerConfig config, Random random) {
        long nearbyStalkers = world.getEntitiesByClass(StalkerEntity.class, player.getBoundingBox().expand(64), e -> true).size();
        if (nearbyStalkers >= config.maxStalkersNearby) return;
        double angle = random.nextDouble() * Math.PI * 2;
        double dist = 16 + random.nextDouble() * 12;
        double x = player.getX() + Math.cos(angle) * dist;
        double z = player.getZ() + Math.sin(angle) * dist;
        BlockPos pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, BlockPos.ofFloored(x, player.getY(), z));
        StalkerEntity stalker = new StalkerEntity(ModEntities.STALKER, world);
        stalker.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, random.nextFloat() * 360f, 0f);
        world.spawnEntity(stalker);
        world.playSound(null, pos, ModSounds.STALKER_APPEAR, SoundCategory.HOSTILE, 0.5f, 1.0f);
    }

    private static void distantSoundEvent(ServerWorld world, ServerPlayerEntity player, Random random) {
        double angle = random.nextDouble() * Math.PI * 2;
        double dist = 12 + random.nextDouble() * 20;
        double x = player.getX() + Math.cos(angle) * dist;
        double z = player.getZ() + Math.sin(angle) * dist;
        world.playSound(null, x, player.getY(), z, ModSounds.DISTANT_WHISPER, SoundCategory.AMBIENT, 1.0f, 0.8f + random.nextFloat() * 0.4f);
    }

    private static void staticNoiseEvent(ServerWorld world, ServerPlayerEntity player, Random random) {
        world.spawnParticles(ParticleTypes.REVERSE_PORTAL, player.getX() + (random.nextDouble() - 0.5) * 6, player.getY() + 1 + random.nextDouble(), player.getZ() + (random.nextDouble() - 0.5) * 6, 8, 0.2, 0.4, 0.2, 0.01);
        world.playSound(null, player.getBlockPos(), ModSounds.STATIC_NOISE, SoundCategory.AMBIENT, 0.4f, 1.0f);
    }
}
