package net.voidstalker.client.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import net.voidstalker.entity.custom.StalkerEntity;

/** Renders the Stalker with a vanilla humanoid model and custom texture. */
public class StalkerRenderer extends MobEntityRenderer<StalkerEntity, LivingEntityRenderState, BipedEntityModel<LivingEntityRenderState>> {

    private static final Identifier TEXTURE = Identifier.of("voidstalker", "textures/entity/stalker.png");

    public StalkerRenderer(EntityRendererFactory.Context context) {
        super(context, new BipedEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE)), 0.5f);
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
