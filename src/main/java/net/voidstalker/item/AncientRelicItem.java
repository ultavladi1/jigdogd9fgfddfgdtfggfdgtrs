package net.voidstalker.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class AncientRelicItem extends Item {
    private static final int COOLDOWN_TICKS = 20 * 60 * 3;

    public AncientRelicItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (player.getItemCooldownManager().isCoolingDown(stack)) {
            return ActionResult.FAIL;
        }
        if (!world.isClient) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20 * 30, 0));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 10, 1));
            world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 0.6f, 1.4f);
            player.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
        }
        return ActionResult.SUCCESS;
    }
}
