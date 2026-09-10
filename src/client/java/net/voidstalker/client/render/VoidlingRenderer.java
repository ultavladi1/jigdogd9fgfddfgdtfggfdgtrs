package net.voidstalker.client.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import net.voidstalker.entity.custom.VoidlingEntity;

public class VoidlingRenderer extends MobEntityRenderer<VoidlingEntity, LivingEntityRenderState, BipedEntityModel<LivingEntityRenderState>> {
    private static final Identifier TEXTURE = Identifier.of("voidstalker", "textures/entity/voidling.png");

    public VoidlingRenderer(EntityRendererFactory.Context context) {
        super(context, new BipedEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE)), 0.25f);
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
