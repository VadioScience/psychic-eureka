package com.blockliftgun.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

/**
 * Улучшенная версия пушки со звуковыми эффектами
 * Используйте эту версию вместо BlockLiftGunItem для большего антуража
 */
public class BlockLiftGunItemAdvanced extends Item {
    private static final float LIFT_FORCE = 1.5f;
    private static final int LIFT_RANGE = 25;
    private static final int COOLDOWN = 12;
    private static final float EXPLOSION_POWER = 2.0f;

    public BlockLiftGunItemAdvanced(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide && !player.getCooldowns().isOnCooldown(this)) {
            HitResult hitResult = player.pick(LIFT_RANGE, 0.0F, false);

            if (hitResult instanceof BlockHitResult blockHit) {
                BlockPos blockPos = blockHit.getBlockPos();
                
                if (blockPos.getY() > level.getMinBuildHeight() && 
                    level.getBlockState(blockPos).getDestroySpeed(level, blockPos) >= 0) {
                    
                    // Воспроизводим звук выстрела
                    level.playSound(null, blockPos, SoundEvents.GENERIC_EXPLODE, 
                        SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4f);
                    
                    liftBlocksAround(level, blockPos, player);
                    
                    // Эффект отдачи для игрока
                    player.knockback(0.5f, Math.sin(player.getYRot() * 3.14159f / 180.0f), 
                        -Math.cos(player.getYRot() * 3.14159f / 180.0f));
                }
            }

            player.getCooldowns().addCooldown(this, COOLDOWN);
        }

        return InteractionResultHolder.success(itemStack);
    }

    private void liftBlocksAround(Level level, BlockPos centerPos, Player player) {
        int radius = 3; // Немного больше радиус
        int blocksLifted = 0;
        
        for (int x = -radius; x <= radius; x++) {
            for (int y = 0; y <= radius + 1; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos targetPos = centerPos.offset(x, y, z);
                    
                    if (!level.getBlockState(targetPos).isAir() && 
                        level.getBlockState(targetPos) != Blocks.BEDROCK.defaultBlockState() &&
                        level.getBlockState(targetPos) != Blocks.OBSIDIAN.defaultBlockState()) {
                        
                        liftBlock(level, targetPos);
                        blocksLifted++;
                    }
                }
            }
        }

        // Эффект взрыва в центре
        spawnExplosionParticles(level, centerPos);
        
        // Если поднято много блоков, добавляем эффект
        if (blocksLifted > 5) {
            level.playSound(null, centerPos, SoundEvents.GENERIC_EXPLODE, 
                SoundSource.BLOCKS, 0.5f, 0.5f);
        }
    }

    private void liftBlock(Level level, BlockPos pos) {
        AABB area = new AABB(pos);
        
        for (Entity entity : level.getEntities(null, area)) {
            if (!(entity instanceof Player)) {
                entity.setDeltaMovement(entity.getDeltaMovement().x, LIFT_FORCE, 
                    entity.getDeltaMovement().z);
            }
        }
        
        if (level.getBlockState(pos).getMaterial().isReplaceable()) {
            return;
        }

        ItemEntity itemEntity = new ItemEntity(
            level,
            pos.getX() + 0.5,
            pos.getY() + 1.2,
            pos.getZ() + 0.5,
            new ItemStack(level.getBlockState(pos).getBlock())
        );

        double randomX = (Math.random() - 0.5) * 0.5;
        double randomZ = (Math.random() - 0.5) * 0.5;
        itemEntity.setDeltaMovement(randomX, LIFT_FORCE, randomZ);
        level.addFreshEntity(itemEntity);
        
        level.destroyBlock(pos, false);
    }

    private void spawnExplosionParticles(Level level, BlockPos pos) {
        // Множество частиц для эффектного взрыва
        for (int i = 0; i < 16; i++) {
            double angle = Math.random() * 2 * Math.PI;
            double radius = Math.random() * 2;
            double height = Math.random();
            
            double xOffset = Math.cos(angle) * radius;
            double zOffset = Math.sin(angle) * radius;

            level.addParticle(
                net.minecraft.core.particles.ParticleTypes.FLAME,
                pos.getX() + 0.5 + xOffset,
                pos.getY() + 1.0 + height,
                pos.getZ() + 0.5 + zOffset,
                xOffset * 0.3,
                0.3 + Math.random() * 0.2,
                zOffset * 0.3
            );
            
            // Дымовые частицы
            level.addParticle(
                net.minecraft.core.particles.ParticleTypes.SMOKE,
                pos.getX() + 0.5 + xOffset,
                pos.getY() + 0.5 + height,
                pos.getZ() + 0.5 + zOffset,
                xOffset * 0.2,
                0.1,
                zOffset * 0.2
            );
        }
    }
}
