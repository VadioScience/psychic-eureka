# API Reference - Block Lift Gun Mod

## 📦 Структура проекта

```
BlockLiftGun/
│
├── 📄 Gradle конфиги
│   ├── build.gradle                 # Основные зависимости и задачи
│   ├── settings.gradle              # Настройки Gradle
│   └── gradle.properties            # Переменные (версии, Java версия)
│
├── 📁 src/main/java/com/blockliftgun/
│   ├── BlockLiftGun.java            # @Mod класс (точка входа)
│   └── 📁 item/
│       ├── ModItems.java            # Регистрация всех предметов
│       ├── BlockLiftGunItem.java     # Базовая версия пушки
│       └── BlockLiftGunItemAdvanced.java  # Продвинутая версия с эффектами
│
├── 📁 src/main/resources/
│   ├── 📁 META-INF/
│   │   └── mods.toml               # Конфиг мода (версия, автор, описание)
│   │
│   └── 📁 assets/blockliftgun/
│       ├── 📁 lang/
│       │   ├── en_us.json          # Английские названия
│       │   └── ru_ru.json          # Русские названия
│       │
│       ├── 📁 models/item/
│       │   └── block_lift_gun.json # 3D модель предмета
│       │
│       └── 📁 textures/item/       # (Добавить потом)
│           └── block_lift_gun.png  # Текстура пушки
│
├── 📖 Документация
│   ├── README.md                    # Основной гайд
│   ├── QUICK_START.md              # Быстрый старт (5 минут)
│   ├── DEVELOPMENT.md              # Полный гайд разработчика
│   ├── EXAMPLES.md                 # Примеры расширения
│   └── API_REFERENCE.md            # Этот файл
│
└── 📝 Служебные файлы
    └── .gitignore                  # Исключения для Git
```

---

## 🔧 Классы и методы

### BlockLiftGun.java

Главный класс мода с аннотацией `@Mod("blockliftgun")`

**Конструктор:**
```java
public BlockLiftGun(ModContainer container, IEventBus modEventBus)
```
- Регистрирует обработчики событий
- Инициализирует предметы мода

**Методы:**
```java
private void commonSetup(FMLCommonSetupEvent event)   // Серверные операции
private void clientSetup(FMLClientSetupEvent event)   // Клиентские операции
```

---

### ModItems.java

Реестр предметов с использованием `DeferredRegister`

**Статические переменные:**
```java
public static final DeferredRegister<Item> ITEMS  // Регистратор предметов

public static final RegistryObject<Item> BLOCK_LIFT_GUN  // Сама пушка
```

**Использование:**
```java
// Получить экземпляр пушки
Item gun = ModItems.BLOCK_LIFT_GUN.get();

// Создать ItemStack с пушкой
ItemStack gunStack = new ItemStack(ModItems.BLOCK_LIFT_GUN.get());
```

---

### BlockLiftGunItem.java

Основная логика пушки

**Константы:**
```java
private static final float LIFT_FORCE = 1.2f;    // Сила подъема
private static final int LIFT_RANGE = 20;        // Дальность в блоках  
private static final int COOLDOWN = 15;          // Перезарядка в тиках
```

**Главный метод:**
```java
public InteractionResultHolder<ItemStack> use(
    Level level, 
    Player player, 
    InteractionHand hand
)
```
Вызывается при ПКМ:
1. Проверяет перезарядку
2. Отправляет луч raycast от игрока
3. Вызывает `liftBlocksAround()` если попал в блок

**Вспомогательные методы:**

```java
private void liftBlocksAround(Level level, BlockPos centerPos, Player player)
```
Поднимает все блоки в радиусе вокруг центра
- Радиус: 2 блока
- Исключает: воздух, коренную породу

```java
private void liftBlock(Level level, BlockPos pos)
```
Поднимает один блок:
1. Ищет сущности в позиции
2. Применяет им вертикальную скорость
3. Превращает блок в ItemEntity
4. Удаляет блок из мира

```java
private void spawnLiftParticles(Level level, BlockPos pos)
```
Создает визуальный эффект - частицы огня

---

### BlockLiftGunItemAdvanced.java

Расширенная версия с дополнительными эффектами

**Отличия от базовой:**
- Больше частиц (16 вместо 8)
- Дымовые частицы дополнительно
- Звуковые эффекты взрыва
- Отдача для игрока
- Исключение обсидиана

**Дополнительные методы:**
```java
private void spawnExplosionParticles(Level level, BlockPos pos)
```
Создает эффект взрыва с огненными и дымовыми частицами

---

## 📝 JSON структуры

### mods.toml

```toml
modLoader = "javafxmod"                 # Тип загрузчика
loaderVersion = "[21,)"                 # Версия Java 21+
license = "MIT"                         # Лицензия

[[mods]]
modId = "blockliftgun"                  # Уникальный ID мода
namespace = "blockliftgun"              # Пространство имен для ассетов
version = "1.0.0"                       # Версия мода
displayName = "Block Lift Gun"          # Название в лаунчере
description = "..."                     # Описание

[[dependencies.blockliftgun]]
    modId = "neoforge"                  # Зависит от NeoForge
    mandatory = true                    # Обязательна
    versionRange = "[21,)"              # Версия 21+
```

### block_lift_gun.json (модель)

```json
{
  "parent": "item/handheld",            # Тип держания
  "textures": {
    "layer0": "blockliftgun:item/block_lift_gun"  # Путь к текстуре
  }
}
```

### Файлы языков

```json
{
  "item.blockliftgun.block_lift_gun": "Block Lift Gun",
  "itemGroup.blockliftgun": "Block Lift Gun"
}
```

---

## 🎮 Использование в коде

### Получить пушку

```java
// В командах
@Override
public int run(CommandContext<CommandSourceStack> ctx) {
    Player player = ctx.getSource().getPlayerOrException();
    ItemStack gun = new ItemStack(ModItems.BLOCK_LIFT_GUN.get());
    player.addItem(gun);
    return 1;
}

// В рецептах крафта
"result": {
    "item": "blockliftgun:block_lift_gun",
    "count": 1
}
```

### Проверить если рука содержит пушку

```java
if (player.getItemInHand(Hand.MAIN_HAND).getItem() instanceof BlockLiftGunItem) {
    // Это пушка!
}
```

### Дать эффект при выстреле

```java
// Добавить эффект всем близким игрокам
for (Player nearby : level.players()) {
    nearby.addEffect(new MobEffectInstance(MobEffects.JUMP, 100, 1));
}
```

---

## 🔌 События и Хуки

### FML Events

```java
@SubscribeEvent
public static void onPlayerClick(PlayerInteractEvent.RightClickItem event) {
    if (event.getItemStack().getItem() instanceof BlockLiftGunItem) {
        // Обработка события
    }
}
```

### World Events

```java
@SubscribeEvent
public static void onBlockPlace(BlockEvent.Break event) {
    BlockPos pos = event.getPos();
    // Реагировать на разрушение блока
}
```

---

## 🎨 Майнкрафт Классы

### Часто используемые классы

| Класс | Использование |
|-------|---------------|
| `Level` | Мир (блоки, сущности, частицы) |
| `Player` | Игрок |
| `ItemStack` | Предмет в руке |
| `BlockPos` | Координаты блока (x, y, z) |
| `Entity` | Сущность (игрок, моб, предмет) |
| `ItemEntity` | Предмет на земле |
| `HitResult` / `BlockHitResult` | Результат лучекаста |
| `ParticleTypes` | Типы частиц |
| `SoundEvents` | Типы звуков |

---

## 📊 Параметры и их влияние

| Параметр | Значение | Эффект |
|----------|----------|--------|
| `LIFT_FORCE` | 0.5 | Медленное поднятие |
| `LIFT_FORCE` | 1.2 | Нормальное поднятие |
| `LIFT_FORCE` | 2.0 | Быстрое поднятие |
| `LIFT_FORCE` | 3.0+ | Очень быстро (может быть нестабильно) |
| `LIFT_RANGE` | 10 | Малая дальность |
| `LIFT_RANGE` | 20 | Нормальная дальность |
| `LIFT_RANGE` | 50+ | Очень большая дальность |
| `COOLDOWN` | 0 | Без перезарядки (спам) |
| `COOLDOWN` | 15 | Умеренная перезарядка |
| `COOLDOWN` | 60+ | Долгая перезарядка |
| Радиус поднятия | 1 | Только сам блок |
| Радиус поднятия | 2 | 3x3 в ширину |
| Радиус поднятия | 3 | 5x5 в ширину |

---

## 🚀 Расширение функциональности

### Добавить свойства NBT

```java
CompoundTag tag = stack.getOrCreateTag();
tag.putInt("customInt", 42);
tag.putDouble("customDouble", 3.14);
tag.putString("customString", "Текст");

// Получить значение
int value = stack.getOrCreateTag().getInt("customInt");
```

### Добавить счетчик использований

```java
public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack stack = player.getItemInHand(hand);
    CompoundTag tag = stack.getOrCreateTag();
    
    int uses = tag.getInt("Uses");
    tag.putInt("Uses", uses + 1);
    
    if (uses > 100) {
        stack.shrink(1);  // Сломать после 100 использований
    }
    
    return InteractionResultHolder.success(stack);
}
```

### Добавить энергию/ману

```java
private static final int MAX_ENERGY = 1000;

public void storeEnergy(ItemStack stack, int energy) {
    CompoundTag tag = stack.getOrCreateTag();
    int current = tag.getInt("Energy");
    tag.putInt("Energy", Math.min(current + energy, MAX_ENERGY));
}

public boolean consumeEnergy(ItemStack stack, int amount) {
    CompoundTag tag = stack.getOrCreateTag();
    int current = tag.getInt("Energy");
    if (current >= amount) {
        tag.putInt("Energy", current - amount);
        return true;
    }
    return false;
}
```

---

## 🐛 Отладка

### Включить DetailedLogging

```java
LOGGER.info("Информационное сообщение");
LOGGER.warn("Предупреждение");
LOGGER.error("Ошибка");
LOGGER.debug("Отладка");
```

### Вывести информацию в чат

```java
player.displayClientMessage(
    Component.literal("§6Текст жёлтого цвета"),
    false  // false = основной чат, true = action bar
);
```

### Скрины и breakpoint

В IntelliJ IDEA:
1. Кликните на строку слева - появится красная точка
2. Запустите `./gradlew runClient`
3. Код остановится на breakpoint
4. Используйте Debug панель для просмотра переменных

---

## 📚 Рекомендуемые источники

- 🔗 [NeoForge Documentation](https://docs.neoforged.net/)
- 🔗 [Minecraft Wiki](https://minecraft.wiki/)
- 🔗 [Forge Community Discord](https://discord.gg/forge)
- 🔗 [McJty Tutorials](https://www.youtube.com/@McJty)

---

## 📞 Поддержка

Если возникнут вопросы:
1. Проверьте `DEVELOPMENT.md`
2. Смотрите `EXAMPLES.md`
3. Читайте комментарии в коде
4. Гуглите ошибку + "neoforge 1.21"

---

**Версия:** 1.0.0  
**Minecraft:** 1.21.1  
**NeoForge:** 21.1.47  
**Java:** 21+

