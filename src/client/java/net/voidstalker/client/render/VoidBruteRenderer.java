package net.voidstalker.client.render;

import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;
import net.voidstalker.entity.custom.VoidBruteEntity;

public class VoidBruteRenderer extends MobEntityRenderer<VoidBruteEntity, ZombieEntityModel<VoidBruteEntity>> {
    private static final Identifier TEXTURE = Identifier.of("voidstalker", "textures/entity/void_brute.png");

    public VoidBruteRenderer(EntityRendererFactory.Context context) {
        super(context, new ZombieEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE)), 0.6f);
    }

    @Override
    public Identifier getTexture(VoidBruteEntity entity) {
        return TEXTURE;
    }
}
