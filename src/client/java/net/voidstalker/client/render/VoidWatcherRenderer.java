package net.voidstalker.client.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import net.voidstalker.entity.custom.VoidWatcherEntity;

/** Placeholder floating-humanoid look until a bespoke model is made. */
public class VoidWatcherRenderer extends MobEntityRenderer<VoidWatcherEntity, LivingEntityRenderState, BipedEntityModel<LivingEntityRenderState>> {
    private static final Identifier TEXTURE = Identifier.of("voidstalker", "textures/entity/void_watcher.png");

    public VoidWatcherRenderer(EntityRendererFactory.Context context) {
        super(context, new BipedEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE)), 0.4f);
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
