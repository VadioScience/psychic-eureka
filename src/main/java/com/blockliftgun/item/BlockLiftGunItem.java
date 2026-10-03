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
import net.minecraft.nbt.Tag;

public class BlockLiftGunItem extends Item {
    private static final float LIFT_FORCE = 1.2f;
    private static final int LIFT_RANGE = 20;
    private static final int COOLDOWN = 15;

    public BlockLiftGunItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide && !player.getCooldowns().isOnCooldown(this)) {
            // Проводим луч от игрока
            HitResult hitResult = player.pick(LIFT_RANGE, 0.0F, false);

            if (hitResult instanceof BlockHitResult blockHit) {
                BlockPos blockPos = blockHit.getBlockPos();
                
                // Не поднимаем нижний слой и коренные блоки
                if (blockPos.getY() > level.getMinBuildHeight() && 
                    level.getBlockState(blockPos).getDestroySpeed(level, blockPos) >= 0) {
                    
                    liftBlocksAround(level, blockPos, player);
                }
            }

            player.getCooldowns().addCooldown(this, COOLDOWN);
        }

        return InteractionResultHolder.success(itemStack);
    }

    private void liftBlocksAround(Level level, BlockPos centerPos, Player player) {
        // Поднимаем блоки в радиусе вокруг поражаемого блока
        int radius = 2;
        
        for (int x = -radius; x <= radius; x++) {
            for (int y = 0; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos targetPos = centerPos.offset(x, y, z);
                    
                    if (!level.getBlockState(targetPos).isAir() && 
                        level.getBlockState(targetPos) != Blocks.BEDROCK.defaultBlockState()) {
                        
                        liftBlock(level, targetPos);
                    }
                }
            }
        }

        // Визуальный эффект - энергия вверх
        spawnLiftParticles(level, centerPos);
    }

    private void liftBlock(Level level, BlockPos pos) {
        // Ищем все сущности (основы для блоков, что поднялись) в этой позиции
        AABB area = new AABB(pos);
        
        for (Entity entity : level.getEntities(null, area)) {
            // Поднимаем уже существующие сущности
            entity.setDeltaMovement(entity.getDeltaMovement().x, LIFT_FORCE, entity.getDeltaMovement().z);
        }
        
        // Конвертируем блок в ItemEntity и поднимаем его
        if (level.getBlockState(pos).getMaterial().isReplaceable()) {
            return;
        }

        ItemEntity itemEntity = new ItemEntity(
            level,
            pos.getX() + 0.5,
            pos.getY() + 0.5,
            pos.getZ() + 0.5,
            new ItemStack(level.getBlockState(pos).getBlock())
        );

        itemEntity.setDeltaMovement(0, LIFT_FORCE, 0);
        level.addFreshEntity(itemEntity);
        
        // Удаляем блок
        level.destroyBlock(pos, false);
    }

    private void spawnLiftParticles(Level level, BlockPos pos) {
        for (int i = 0; i < 8; i++) {
            double xOffset = (Math.random() - 0.5) * 2;
            double zOffset = (Math.random() - 0.5) * 2;
            double yOffset = Math.random() * 0.5;

            level.addParticle(
                net.minecraft.core.particles.ParticleTypes.FLAME,
                pos.getX() + 0.5 + xOffset,
                pos.getY() + 0.5 + yOffset,
                pos.getZ() + 0.5 + zOffset,
                xOffset * 0.2,
                0.2,
                zOffset * 0.2
            );
        }
    }
}
