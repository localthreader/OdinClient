package net.threader.odinclient.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.chunk.BlockBufferBuilderStorage;
import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.client.render.chunk.ChunkRendererRegion;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import net.threader.odinclient.OdinClient;
import net.threader.odinclient.feature.AbstractFeature;
import net.threader.odinclient.feature.hacks.XRayFeature;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

@Mixin(BlockRenderManager.class)
public abstract class BlockRenderManagerMixin {

    @Inject(method = "renderBlock", at = @At("HEAD"), cancellable = true)
    private void render(BlockState state, BlockPos pos,
                        BlockRenderView world, MatrixStack matrix,
                        VertexConsumer vertexConsumer, boolean cull,
                        Random random, CallbackInfoReturnable<Boolean> cir) {

        if(!AbstractFeature.instance(XRayFeature.class)
                .getVisibleBlocks().contains(Registry.BLOCK.getId(state.getBlock()))
                && AbstractFeature.instance(XRayFeature.class).isActivated()) {
            cir.cancel();
        }

    }

}