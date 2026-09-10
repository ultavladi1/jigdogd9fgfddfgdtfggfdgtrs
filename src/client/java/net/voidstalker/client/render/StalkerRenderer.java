package net.voidstalker.client.render;

import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;
import net.voidstalker.entity.custom.StalkerEntity;

/**
 * Renders the Stalker with vanilla's zombie/humanoid model (which works for
 * any MobEntity, not specifically zombies) with our own texture and a
 * taller, thinner scale. A genuinely bespoke "tall, faceless" skeleton needs
 * real modeled geometry (e.g. made in Blockbench) -- see README.
 */
public class StalkerRenderer extends MobEntityRenderer<StalkerEntity, ZombieEntityModel<StalkerEntity>> {

    private static final Identifier TEXTURE = Identifier.of("voidstalker", "textures/entity/stalker.png");

    public StalkerRenderer(EntityRendererFactory.Context context) {
        super(context, new ZombieEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE)), 0.5f);
    }

    @Override
    public Identifier getTexture(StalkerEntity entity) {
        return TEXTURE;
    }
}
