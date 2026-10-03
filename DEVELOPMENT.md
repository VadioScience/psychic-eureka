# Руководство по разработке Block Lift Gun Mod

## Быстрый старт

### 1. Подготовка окружения

```bash
# Скачайте проект
git clone <url>
cd BlockLiftGun

# Сгенерируйте исходники
./gradlew genSources

# Если используете IntelliJ IDEA:
./gradlew idea

# Если используете Eclipse:
./gradlew eclipse
```

### 2. Запуск в развивающей среде

```bash
# Для запуска development сервера с модом:
./gradlew runClient
```

### 3. Компиляция и получение JAR

```bash
./gradlew build
```

JAR файл будет: `build/libs/blockliftgun-1.0.0.jar`

---

## Структура кода

### BlockLiftGun.java
Главный класс мода - точка входа. Регистрирует:
- Обработчики событий
- Предметы
- Логирование

### ModItems.java
Регистрирует все предметы мода используя `DeferredRegister`

### BlockLiftGunItem.java
Основная логика пушки:
- `use()` - обработчик ПКМ
- `liftBlock()` - поднимает один блок
- `liftBlocksAround()` - поднимает несколько блоков в радиусе
- `spawnLiftParticles()` - визуальные эффекты

### BlockLiftGunItemAdvanced.java
Улучшенная версия с:
- Звуковыми эффектами
- Отдачей для игрока
- Лучшими частицами

---

## Как использовать Advanced версию

В файле `ModItems.java` замените:

```java
// С этого:
public static final RegistryObject<Item> BLOCK_LIFT_GUN = ITEMS.register("block_lift_gun",
        () -> new BlockLiftGunItem(new Item.Properties().stacksTo(1)));

// На это:
public static final RegistryObject<Item> BLOCK_LIFT_GUN = ITEMS.register("block_lift_gun",
        () -> new BlockLiftGunItemAdvanced(new Item.Properties().stacksTo(1)));
```

---

## Кастомизация

### Изменение силы подъема

В `BlockLiftGunItem.java`:
```java
private static final float LIFT_FORCE = 1.2f; // Увеличьте значение для выше полета
```

### Изменение радиуса

```java
private static final int LIFT_RANGE = 20; // Максимальное расстояние до блока
private static final int LIFT_RANGE = 3;  // В liftBlocksAround() - радиус вокруг центра
```

### Изменение перезарядки

```java
private static final int COOLDOWN = 15; // Время в тиках (20 тиков = 1 сек)
```

### Добавление звука

```java
level.playSound(null, blockPos, SoundEvents.GENERIC_EXPLODE, 
    SoundSource.BLOCKS, 1.0f, 1.0f);
```

Доступные звуки:
- `SoundEvents.GENERIC_EXPLODE` - взрыв
- `SoundEvents.LIGHTNING_BOLT_THUNDER` - гром
- `SoundEvents.WITHER_SHOOT` - выстрел
- `SoundEvents.FIREWORK_ROCKET_BLAST` - взрыв фейерверка

---

## Добавление текстуры

1. Создайте текстуру 16x16 или 32x32 пикселей
2. Сохраните как `block_lift_gun.png`
3. Поместите в: `src/main/resources/assets/blockliftgun/textures/item/`

Простой способ создать текстуру:
- Используйте любой редактор (Paint, Photoshop, GIMP)
- Нарисуйте пушку/пистолет
- Экспортируйте как PNG 16x16

---

## Отладка

### Включить логирование

В `BlockLiftGun.java`:
```java
LOGGER.info("Что-то произошло!");
LOGGER.debug("Отладочная информация");
LOGGER.warn("Предупреждение");
LOGGER.error("Ошибка");
```

### Проверка консоли

При запуске `./gradlew runClient` все логи будут в консоли IDE

### Основные ошибки

| Ошибка | Решение |
|--------|---------|
| `ClassNotFoundException` | Переустановите: `./gradlew clean genSources` |
| `Mod не появляется` | Проверьте mods.toml и модид в коде |
| `Текстура не грузится` | Проверьте путь: `assets/blockliftgun/textures/item/` |

---

## Публикация мода

1. Скомпилируйте: `./gradlew build`
2. Возьмите JAR из `build/libs/`
3. Загрузите на:
   - [CurseForge](https://www.curseforge.com/)
   - [Modrinth](https://modrinth.com/)

---

## Дополнительные ресурсы

- [NeoForge Docs](https://docs.neoforged.net/)
- [Minecraft Wiki](https://minecraft.wiki/)
- [Forge Community Discord](https://discord.gg/forge)

---

Happy modding! 🚀
