package net.voidstalker.item;

import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.voidstalker.registry.ModEntities;

import java.util.List;

/**
 * On use, senses the nearest Void horror mob within range and gives the
 * player a rough direction and distance -- an unsettling hint rather than a
 * precise locator.
 */
public class HorrorDetectorItem extends Item {

    private static final double RANGE = 48.0;

    public HorrorDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (world instanceof ServerWorld serverWorld) {
            Box box = player.getBoundingBox().expand(RANGE);
            List<HostileEntity> found = serverWorld.getEntitiesByClass(HostileEntity.class, box, e ->
                    e.getType() == ModEntities.STALKER || e.getType() == ModEntities.VOIDLING
                            || e.getType() == ModEntities.VOID_BRUTE || e.getType() == ModEntities.VOID_WATCHER);

            HostileEntity nearest = null;
            double bestDist = Double.MAX_VALUE;
            for (HostileEntity entity : found) {
                double d = entity.squaredDistanceTo(player);
                if (d < bestDist) {
                    bestDist = d;
                    nearest = entity;
                }
            }

            if (nearest != null) {
                double dx = nearest.getX() - player.getX();
                double dz = nearest.getZ() - player.getZ();
                String direction = describeDirection(dx, dz);
                int dist = (int) Math.sqrt(bestDist);
                player.sendMessage(Text.translatable("item.voidstalker.horror_detector.hint", direction, dist), true);
            } else {
                player.sendMessage(Text.translatable("item.voidstalker.horror_detector.nothing"), true);
            }
        }

        return TypedActionResult.success(stack, world.isClient);
    }

    private String describeDirection(double dx, double dz) {
        double angle = Math.toDegrees(Math.atan2(dx, -dz));
        if (angle < 0) angle += 360;
        String[] dirs = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        int index = (int) Math.round(angle / 45.0) % 8;
        return dirs[index];
    }
}
