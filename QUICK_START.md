# 🚀 Быстрый старт - 5 минут

## Шаг 1: Подготовка (1 мин)
```bash
cd BlockLiftGun
./gradlew genSources
```

## Шаг 2: Открытие в IDE (1 мин)
- **IntelliJ IDEA**: Откройте `build.gradle` → Откройте как проект
- **Eclipse**: `./gradlew eclipse` → File → Import → Gradle Project
- **VS Code**: Откройте папку напрямую

## Шаг 3: Запуск (1 мин)
В терминале IDE:
```bash
./gradlew runClient
```

Minecraft запустится с вашим модом в dev-окружении

## Шаг 4: Тестирование (2 мин)
В игре используйте:
```
/give @s blockliftgun:block_lift_gun
```

Нажмите ПКМ на блок - вуаля! 🎉

---

## Структура файлов

```
BlockLiftGun/
├── src/main/java/com/blockliftgun/
│   ├── BlockLiftGun.java           👈 Главное
│   └── item/
│       ├── ModItems.java           👈 Регистрация
│       └── BlockLiftGunItem.java    👈 Логика пушки
└── src/main/resources/
    └── assets/blockliftgun/
        ├── lang/en_us.json         👈 Переводы
        └── models/item/            👈 Текстуры
```

---

## Основные изменения

### Изменить силу подъема
**Файл:** `BlockLiftGunItem.java`
```java
private static final float LIFT_FORCE = 1.2f;  // Измените число
```

### Изменить радиус
**Файл:** `BlockLiftGunItem.java`
```java
int radius = 2;  // Измените число
```

### Изменить перезарядку
**Файл:** `BlockLiftGunItem.java`
```java
private static final int COOLDOWN = 15;  // В тиках (20 = 1 сек)
```

---

## Компиляция и установка

```bash
# Собрать JAR
./gradlew build

# JAR находится здесь:
# build/libs/blockliftgun-1.0.0.jar

# Установить в Minecraft
# 1. Создайте папку mods в профиле NeoForge 1.21.1
# 2. Скопируйте JAR в mods/
# 3. Запустите Minecraft
```

---

## Частые вопросы

**Q: Мод не появляется в игре?**  
A: Проверьте версию NeoForge (нужна 1.21.1) и что JAR в папке `mods`

**Q: Как добавить звуковой эффект?**  
A: В `BlockLiftGunItem.java` найдите `spawnLiftParticles()` и добавьте:
```java
level.playSound(null, centerPos, SoundEvents.GENERIC_EXPLODE, 
    SoundSource.BLOCKS, 1.0f, 1.0f);
```

**Q: Как добавить текстуру?**  
A: Создайте PNG 16x16 и положите в:  
`src/main/resources/assets/blockliftgun/textures/item/block_lift_gun.png`

**Q: Как сделать пушку крафтиться?**  
A: Смотрите `EXAMPLES.md` → "Добавление рецепта крафта"

---

## Полезные команды

```bash
# Очистить старые файлы
./gradlew clean

# Переделать всё с нуля
./gradlew clean genSources

# Просто собрать без запуска
./gradlew build

# Запустить дебаг сервер
./gradlew runServer
```

---

## Следующие шаги

1. 📖 Читайте `DEVELOPMENT.md` для полного гайда
2. 📚 Смотрите `EXAMPLES.md` для идей расширения
3. 🎨 Создайте текстуру пушки
4. 🔊 Добавьте звуковые эффекты
5. ⚙️ Добавьте конфиги
6. 🚀 Публикуйте на CurseForge/Modrinth!

---

**Пример улучшенной версии уже есть в коде!**  
Файл: `BlockLiftGunItemAdvanced.java`

Чтобы использовать её, в `ModItems.java` замените:
```java
// С:
() -> new BlockLiftGunItem(...)

// На:
() -> new BlockLiftGunItemAdvanced(...)
```

---

Happy modding! 🎮✨
