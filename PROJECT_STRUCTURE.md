# 📋 Полная структура проекта BlockLiftGun

## 🎯 Что было создано

Полностью готовый **NeoForge мод для Minecraft 1.21.1** с пушкой, поднимающей блоки.

---

## 📁 Основные папки

```
BlockLiftGun/
├── 🔨 Система сборки
│   ├── build.gradle                    Gradle конфигурация
│   ├── settings.gradle                 Настройки проекта
│   └── gradle.properties               Версии и свойства
│
├── 📚 Исходный код
│   └── src/main/java/com/blockliftgun/
│       ├── BlockLiftGun.java           👈 ГЛАВНЫЙ КЛАСС
│       └── item/
│           ├── ModItems.java           👈 РЕГИСТРАЦИЯ ПРЕДМЕТОВ
│           ├── BlockLiftGunItem.java    👈 БАЗОВАЯ ПУШКА
│           └── BlockLiftGunItemAdvanced.java  👈 УЛУЧШЕННАЯ ПУШКА
│
├── 🎮 Ресурсы
│   └── src/main/resources/
│       ├── META-INF/mods.toml          👈 КОНФИГ МОДА
│       └── assets/blockliftgun/
│           ├── lang/
│           │   ├── en_us.json          Английский язык
│           │   └── ru_ru.json          Русский язык
│           └── models/item/
│               └── block_lift_gun.json Модель предмета
│
└── 📖 Документация
    ├── README.md                       🌟 НАЧНИТЕ ОТСЮДА
    ├── QUICK_START.md                  ⚡ Быстрый старт (5 мин)
    ├── DEVELOPMENT.md                  📚 Полный гайд разработчика
    ├── EXAMPLES.md                     💡 Примеры расширения
    ├── API_REFERENCE.md                🔌 API справочник
    ├── PROJECT_STRUCTURE.md            📋 Этот файл
    └── .gitignore                      Git конфиг
```

---

## 📄 Описание каждого файла

### 🔨 Сборка и конфигурация

#### `build.gradle`
**Что это:** Основной файл Gradle, описывает зависимости и задачи сборки

**Содержит:**
- Плагины (Gradle, NeoForge)
- Версии (Minecraft 1.21.1, NeoForge 21.1.47)
- Зависимости от NeoForge

**Когда редактировать:**
- Добавить новую библиотеку
- Обновить версию Minecraft
- Изменить версию Java

#### `settings.gradle`
**Что это:** Настройки проекта Gradle

**Содержит:**
- Репозитории плагинов
- Название проекта

**Обычно не редактируется**

#### `gradle.properties`
**Что это:** Глобальные свойства и переменные

**Содержит:**
- Версии (neoforge_version, minecraft_version)
- Java версия
- Лицензия проекта

**Когда редактировать:**
- Обновить версию NeoForge
- Изменить Java версию

#### `.gitignore`
**Что это:** Файлы/папки, которые Git игнорирует

**Что исключает:**
- `.gradle/`, `build/` (скомпилированные файлы)
- `.idea/`, `.vscode/` (IDE файлы)
- `*.class`, `*.jar` (скомпилированные классы)

---

### 💻 Java исходный код

#### `src/main/java/com/blockliftgun/BlockLiftGun.java`
**Что это:** Главный класс мода (точка входа)

**Назначение:**
- Регистрирует мод с аннотацией `@Mod("blockliftgun")`
- Инициализирует обработчики событий
- Загружает предметы мода
- Выводит логи инициализации

**Структура:**
```java
@Mod("blockliftgun")
public class BlockLiftGun {
    - static String MOD_ID = "blockliftgun"
    - static Logger LOGGER
    - constructor(ModContainer, IEventBus)
    - commonSetup()
    - clientSetup()
}
```

**Когда редактировать:**
- Добавить новые обработчики событий
- Добавить регистрацию новых типов (блоки, сущности)
- Добавить конфиги

---

#### `src/main/java/com/blockliftgun/item/ModItems.java`
**Что это:** Реестр всех предметов мода

**Назначение:**
- Регистрирует предметы через `DeferredRegister`
- Создает `RegistryObject` для каждого предмета
- Делает предметы доступными через `ModItems.BLOCK_LIFT_GUN.get()`

**Структура:**
```java
public class ModItems {
    - static DeferredRegister<Item> ITEMS
    - static RegistryObject<Item> BLOCK_LIFT_GUN
}
```

**Когда редактировать:**
- Добавить новый предмет
- Изменить свойства пушки
- Использовать другую реализацию (Advanced вместо базовой)

---

#### `src/main/java/com/blockliftgun/item/BlockLiftGunItem.java`
**Что это:** Основная реализация пушки

**Назначение:**
- Обрабатывает ПКМ для выстрела
- Поднимает блоки в воздух
- Создает визуальные эффекты

**Ключевые методы:**
- `use()` - главный обработчик ПКМ
- `liftBlocksAround()` - поднимает несколько блоков
- `liftBlock()` - поднимает один блок
- `spawnLiftParticles()` - частицы

**Параметры:**
```java
LIFT_FORCE = 1.2f      // Сила подъема
LIFT_RANGE = 20        // Дальность
COOLDOWN = 15          // Перезарядка
```

**Когда редактировать:**
- Изменить поведение пушки
- Добавить новые эффекты
- Изменить силу/дальность/перезарядку

---

#### `src/main/java/com/blockliftgun/item/BlockLiftGunItemAdvanced.java`
**Что это:** Расширенная версия пушки

**Отличия:**
- Звуковые эффекты (взрывы)
- Отдача для игрока
- Лучше частицы (16 вместо 8)
- Дополнительные дымовые частицы
- Больше блоков поднимается (3x3 вместо 2x2)

**Когда использовать:**
- Когда хотите более зрелищный эффект
- В `ModItems.java` замените:
  ```java
  // С: () -> new BlockLiftGunItem(...)
  // На: () -> new BlockLiftGunItemAdvanced(...)
  ```

---

### 🎮 Ресурсы и конфиги

#### `src/main/resources/META-INF/mods.toml`
**Что это:** Главный конфиг мода

**Содержит:**
- ID мода (`blockliftgun`)
- Версия мода (`1.0.0`)
- Название в лаунчере
- Описание
- Зависимости (NeoForge, Minecraft)

**Структура:**
```toml
modLoader = "javafxmod"
loaderVersion = "[21,)"
license = "MIT"

[[mods]]
modId = "blockliftgun"
version = "1.0.0"
displayName = "Block Lift Gun"
description = "..."

[[dependencies.blockliftgun]]
    modId = "neoforge"
    versionRange = "[21,)"
```

**Когда редактировать:**
- Обновить версию мода
- Изменить описание
- Добавить зависимости
- Изменить лицензию

---

#### `src/main/resources/assets/blockliftgun/lang/en_us.json`
**Что это:** Английские переводы

**Содержит:**
```json
{
  "item.blockliftgun.block_lift_gun": "Block Lift Gun",
  "itemGroup.blockliftgun": "Block Lift Gun"
}
```

**Ключи:**
- `item.<modid>.<itemid>` - название предмета
- `itemGroup.<modid>` - название группы в креативе

---

#### `src/main/resources/assets/blockliftgun/lang/ru_ru.json`
**Что это:** Русские переводы

**Содержит:**
```json
{
  "item.blockliftgun.block_lift_gun": "Пушка поднимающая блоки",
  "itemGroup.blockliftgun": "Block Lift Gun"
}
```

**Когда добавить поддержку языка:**
1. Создать файл `src/main/resources/assets/blockliftgun/lang/<lang_code>.json`
2. Использовать правильный код языка:
   - `en_us.json` - Английский (США)
   - `ru_ru.json` - Русский
   - `de_de.json` - Немецкий
   - `fr_fr.json` - Французский
   - `zh_cn.json` - Китайский (упрощённый)

---

#### `src/main/resources/assets/blockliftgun/models/item/block_lift_gun.json`
**Что это:** 3D модель предмета

**Содержит:**
```json
{
  "parent": "item/handheld",
  "textures": {
    "layer0": "blockliftgun:item/block_lift_gun"
  }
}
```

**Парент типы:**
- `item/handheld` - держится в руке (пистолет, меч)
- `item/generated` - обычный предмет (зелье, хлеб)
- `item/handheld_rod` - в виде стержня (удочка, посох)

**Когда редактировать:**
- Изменить тип держания
- Добавить несколько слоев (текстур)

---

### 📖 Документация

#### `README.md` ⭐
**Начните отсюда!**

Содержит:
- Что это и возможности
- Требования и установка
- Инструкции использования
- Техническое описание
- Возможные улучшения

#### `QUICK_START.md` ⚡
**За 5 минут от нуля до запуска**

Содержит:
- 4 пошаговых шага
- Структура файлов
- Основные изменения
- Частые вопросы

#### `DEVELOPMENT.md` 📚
**Полный гайд для разработчиков**

Содержит:
- Подробная подготовка
- Запуск в dev окружении
- Структура кода
- Кастомизация параметров
- Добавление текстур и звуков
- Таблица ошибок и решений

#### `EXAMPLES.md` 💡
**Примеры расширения функциональности**

Содержит примеры:
- Добавление рецепта крафта
- Создание группы в креативе
- Множественные режимы стрельбы
- Конфиг-файлы
- Использование конфигов в коде
- Частицы и звуки
- Тестирование командами

#### `API_REFERENCE.md` 🔌
**Полный справочник API**

Содержит:
- Детальное описание каждого класса
- Методы и параметры
- JSON структуры
- Использование в коде
- События и хуки
- Часто используемые классы Minecraft
- Таблица параметров и их эффектов
- Расширение функциональности
- Отладка и breakpoints

#### `PROJECT_STRUCTURE.md` 📋
**Этот файл - описание всей структуры**

---

## 🚀 С чего начать

### Вариант 1: Быстрый старт (5 минут)
1. Читайте `QUICK_START.md`
2. Запустите `./gradlew runClient`
3. Тестируйте в игре

### Вариант 2: Полное понимание
1. Читайте `README.md`
2. Читайте `DEVELOPMENT.md`
3. Изучите `API_REFERENCE.md`
4. Смотрите примеры в `EXAMPLES.md`

### Вариант 3: Кодирование
1. Откройте `BlockLiftGun.java`
2. Откройте `BlockLiftGunItem.java`
3. Читайте комментарии в коде
4. Пробуйте менять параметры

---

## 🔧 Типичные задачи

### Изменить силу подъема
→ Файл: `BlockLiftGunItem.java` строка `LIFT_FORCE`

### Добавить звуковой эффект
→ Файл: `BlockLiftGunItem.java` в методе `liftBlocksAround()`

### Добавить текстуру
→ Создать: `src/main/resources/assets/blockliftgun/textures/item/block_lift_gun.png`

### Добавить рецепт крафта
→ Читайте: `EXAMPLES.md` → "Добавление рецепта крафта"

### Добавить конфиг
→ Читайте: `EXAMPLES.md` → "Использование конфиг-файлов"

### Добавить новый предмет
→ Читайте: `DEVELOPMENT.md` → "Добавление текстуры"

---

## 📊 Быстрая справка параметров

| Файл | Параметр | Значение | Для чего |
|------|----------|----------|----------|
| BlockLiftGunItem.java | LIFT_FORCE | 1.2 | Сила подъема |
| BlockLiftGunItem.java | LIFT_RANGE | 20 | Дальность до блока |
| BlockLiftGunItem.java | COOLDOWN | 15 | Перезарядка в тиках |
| BlockLiftGunItem.java | radius | 2 | Радиус поднятия вокруг центра |
| BlockLiftGunItemAdvanced.java | LIFT_FORCE | 1.5 | Сила подъема (Advanced) |
| BlockLiftGunItemAdvanced.java | radius | 3 | Больший радиус (Advanced) |

---

## 📦 Версии компонентов

| Компонент | Версия |
|-----------|--------|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.47 |
| Java | 21+ |
| Gradle | 8.2.1 (встроен) |

---

## 🎯 Итого

✅ Полностью готовый мод для Minecraft 1.21.1  
✅ Два варианта пушки (базовый и продвинутый)  
✅ Полная документация на русском  
✅ Примеры расширения функциональности  
✅ API справочник для разработчиков  
✅ Готово к компиляции и установке  

🚀 **Готовы ли вы начать?** Смотрите `QUICK_START.md` или `README.md`

