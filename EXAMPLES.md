# Примеры и расширения для Block Lift Gun

## Добавление рецепта крафта

Создайте файл: `src/main/resources/data/blockliftgun/recipes/block_lift_gun.json`

```json
{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    " I ",
    "IDI",
    "IRI"
  ],
  "key": {
    "I": {
      "item": "minecraft:iron_ingot"
    },
    "D": {
      "item": "minecraft:diamond"
    },
    "R": {
      "item": "minecraft:redstone_block"
    }
  },
  "result": {
    "item": "blockliftgun:block_lift_gun",
    "count": 1
  }
}
```

Этот рецепт требует:
- 5 железных слитков
- 1 алмаза
- 1 блока редстоуна

---

## Добавление группы предметов (Creative Tab)

Создайте новый класс `ModCreativeTab.java`:

```java
package com.blockliftgun.item;

import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryObject;
import net.minecraft.core.registries.Registries;
import com.blockliftgun.BlockLiftGun;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlockLiftGun.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BLOCKLIFTGUN_TAB = 
        CREATIVE_TABS.register("blockliftgun", () ->
            CreativeModeTab.builder()
                .title(net.minecraft.network.chat.Component.literal("Block Lift Gun"))
                .icon(() -> new net.minecraft.world.item.ItemStack(ModItems.BLOCK_LIFT_GUN.get()))
                .displayItems((enabledFeatures, output) -> {
                    output.accept(ModItems.BLOCK_LIFT_GUN.get());
                })
                .build()
    );
}
```

Потом в `BlockLiftGun.java` добавьте:

```java
ModCreativeTab.CREATIVE_TABS.register(modEventBus);
```

---

## Версия с режимами стрельбы

Класс с несколькими режимами (Shift + ПКМ для смены):

```java
package com.blockliftgun.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class BlockLiftGunMultiMode extends BlockLiftGunItem {
    
    enum Mode {
        LIFT("Поднятие", 1.2f, 2),
        BLAST("Взрыв", 2.0f, 4),
        PULL("Притяжение", 0.8f, 1);
        
        String name;
        float force;
        int radius;
        
        Mode(String name, float force, int radius) {
            this.name = name;
            this.force = force;
            this.radius = radius;
        }
    }
    
    public BlockLiftGunMultiMode(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        
        // Shift + ПКМ = смена режима
        if (player.isShiftKeyDown()) {
            if (!level.isClientSide) {
                Mode currentMode = getCurrentMode(itemStack);
                Mode nextMode = Mode.values()[(currentMode.ordinal() + 1) % Mode.values().length];
                setMode(itemStack, nextMode);
                
                player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal("§6Режим: " + nextMode.name),
                    true
                );
            }
            return InteractionResultHolder.success(itemStack);
        }
        
        return super.use(level, player, hand);
    }
    
    private Mode getCurrentMode(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return Mode.values()[tag.getInt("Mode") % Mode.values().length];
    }
    
    private void setMode(ItemStack stack, Mode mode) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("Mode", mode.ordinal());
    }
}
```

---

## Использование конфиг-файлов

Создайте `BlockLiftGunConfig.java`:

```java
package com.blockliftgun.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class BlockLiftGunConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    
    public static final ModConfigSpec.DoubleValue LIFT_FORCE = BUILDER
        .comment("Сила подъема блоков")
        .defineInRange("liftForce", 1.2d, 0.1d, 5.0d);
    
    public static final ModConfigSpec.IntValue LIFT_RANGE = BUILDER
        .comment("Дальность действия в блоках")
        .defineInRange("liftRange", 20, 5, 50);
    
    public static final ModConfigSpec.IntValue COOLDOWN = BUILDER
        .comment("Перезарядка в тиках (20 = 1 сек)")
        .defineInRange("cooldown", 15, 0, 100);
    
    public static final ModConfigSpec CONFIG = BUILDER.build();
}
```

В `BlockLiftGun.java`:

```java
modEventBus.addListener((FMLCommonSetupEvent event) -> {
    FMLConfig.register(ModConfigSpec.Builder.build(), BlockLiftGunConfig.class);
});
```

---

## Использование конфиг значений в коде

В `BlockLiftGunItem.java`:

```java
// Вместо:
private static final float LIFT_FORCE = 1.2f;

// Используйте:
private float getLiftForce() {
    return BlockLiftGunConfig.LIFT_FORCE.get().floatValue();
}

// Потом в коде:
itemEntity.setDeltaMovement(0, getLiftForce(), 0);
```

---

## Добавление частиц в мир

```java
// Базовые частицы
level.addParticle(ParticleTypes.FLAME, x, y, z, vx, vy, vz);

// Доступные типы частиц:
ParticleTypes.SMOKE
ParticleTypes.FLAME
ParticleTypes.EXPLOSION
ParticleTypes.LARGE_EXPLOSION
ParticleTypes.SPARK
ParticleTypes.DUST
ParticleTypes.CLOUD
ParticleTypes.PORTAL
ParticleTypes.ENCHANT
```

---

## Добавление звуковых эффектов

```java
// Воспроизведение звука всем рядом
level.playSound(null, blockPos, SoundEvents.GENERIC_EXPLODE, 
    SoundSource.BLOCKS, volume, pitch);

// Доступные SoundSource:
SoundSource.MASTER
SoundSource.MUSIC
SoundSource.RECORD
SoundSource.WEATHER
SoundSource.BLOCK
SoundSource.HOSTILE
SoundSource.NEUTRAL
SoundSource.PLAYER
SoundSource.AMBIENT
SoundSource.VOICE

// Популярные звуки:
SoundEvents.FIREWORK_ROCKET_BLAST
SoundEvents.WITHER_SHOOT
SoundEvents.LIGHTNING_BOLT_THUNDER
SoundEvents.GENERIC_EXPLODE
SoundEvents.ITEM_PICKUP
```

---

## Тестирование команды

Для тестирования в игре используйте команды:

```minecraft
# Выдать пушку
/give @s blockliftgun:block_lift_gun

# Выдать несколько копий
/give @s blockliftgun:block_lift_gun 64

# Выдать только в один слот
/give @s blockliftgun:block_lift_gun{display:{Name:'{"text":"Моя пушка"}'}}

# Установить дальность (если добавите NBT хранение)
/give @s blockliftgun:block_lift_gun{Range:50}
```

---

## Совет по оптимизации

Не создавайте слишком много блоков за раз, так как это может вызвать лаги:

```java
// Плохо - 343 блока сразу
int radius = 7;

// Хорошо - максимум 27 блоков
int radius = 3;

// Оптимально - 1 блок + соседи
int radius = 1;
```

---

Удачи с модом! 🎮✨
