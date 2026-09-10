package net.voidstalker.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.voidstalker.config.VoidStalkerConfig;
import net.voidstalker.registry.ModSounds;

public class StalkerEntity extends HostileEntity {

    private int watchTicks;
    private int nextDecisionTick;

    public StalkerEntity(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);
        this.experiencePoints = 0;
        this.setPersistent();
    }

    public static DefaultAttributeContainer.Builder createStalkerAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 40.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.18)
                .add(EntityAttributes.ATTACK_DAMAGE, VoidStalkerConfig.get().stalkerAggressive ? 8.0 : 0.0)
                .add(EntityAttributes.FOLLOW_RANGE, 48.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 32.0f));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            this.watchTicks++;
            if (this.getWorld() instanceof ServerWorld serverWorld) {
                serverAiTick(serverWorld);
            }
        }
    }

    private void serverAiTick(ServerWorld world) {
        if (this.age < this.nextDecisionTick) return;
        Random random = this.random;
        this.nextDecisionTick = this.age + 60 + random.nextInt(120);
        PlayerEntity nearest = world.getClosestPlayer(this, 40.0);
        if (nearest == null) return;
        double distance = this.distanceTo(nearest);
        if (distance < VoidStalkerConfig.get().stalkerVanishDistance && random.nextFloat() < 0.6f) {
            vanish(world);
            return;
        }
        if (random.nextFloat() < 0.12f) teleportNear(world, nearest);
        if (random.nextFloat() < VoidStalkerConfig.get().darknessEventChance) triggerDarkness(nearest);
        if (random.nextFloat() < 0.05f) {
            world.playSound(null, this.getBlockPos(), ModSounds.STALKER_AMBIENT, SoundCategory.HOSTILE, 0.8f, 0.9f + random.nextFloat() * 0.2f);
        }
    }

    private void teleportNear(ServerWorld world, PlayerEntity target) {
        Random random = this.random;
        for (int attempt = 0; attempt < 8; attempt++) {
            double angle = random.nextDouble() * Math.PI * 2;
            double radius = 8 + random.nextDouble() * 10;
            double x = target.getX() + Math.cos(angle) * radius;
            double z = target.getZ() + Math.sin(angle) * radius;
            BlockPos pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, BlockPos.ofFloored(x, target.getY(), z));
            if (this.teleport(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, false)) {
                world.spawnParticles(ParticleTypes.PORTAL, this.getX(), this.getY() + 1.0, this.getZ(), 20, 0.3, 0.5, 0.3, 0.02);
                world.playSound(null, this.getBlockPos(), ModSounds.STALKER_TELEPORT, SoundCategory.HOSTILE, 0.7f, 1.0f);
                return;
            }
        }
    }

    private void triggerDarkness(PlayerEntity target) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 70, 0, false, false));
    }

    private void vanish(ServerWorld world) {
        world.spawnParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + 1.2, this.getZ(), 30, 0.4, 0.8, 0.4, 0.03);
        world.playSound(null, this.getBlockPos(), ModSounds.STALKER_TELEPORT, SoundCategory.HOSTILE, 0.6f, 0.6f);
        this.discard();
    }

    @Override
    public void checkDespawn() {
        if (this.watchTicks > 20 * 60 * 5) {
            this.discard();
            return;
        }
        super.checkDespawn();
    }

    @Override
    protected void playHurtSound(DamageSource source) {}

    @Override
    protected float getSoundVolume() { return 0.5f; }
}
