package net.voidstalker.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.EnumSet;

public class VoidWatcherEntity extends HostileEntity {
    public VoidWatcherEntity(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);
        this.setNoGravity(true);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    public static DefaultAttributeContainer.Builder createWatcherAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 18.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.1)
                .add(EntityAttributes.FLYING_SPEED, 0.1)
                .add(EntityAttributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 24.0f));
        this.goalSelector.add(2, new LookAroundGoal(this));
        this.goalSelector.add(3, new StareDreadGoal(this));
    }

    private static class StareDreadGoal extends Goal {
        private final VoidWatcherEntity watcher;
        private int cooldown;

        StareDreadGoal(VoidWatcherEntity watcher) {
            this.watcher = watcher;
            this.setControls(EnumSet.noneOf(Goal.Control.class));
        }

        @Override
        public boolean canStart() {
            return watcher.getTarget() != null || watcher.getWorld().getClosestPlayer(watcher, 10.0) != null;
        }

        @Override
        public void tick() {
            if (cooldown-- > 0) return;
            PlayerEntity player = watcher.getWorld().getClosestPlayer(watcher, 6.0);
            if (player != null) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
                cooldown = 100;
            }
        }
    }
}
