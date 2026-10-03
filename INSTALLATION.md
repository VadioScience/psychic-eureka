# 💾 Установка и развертывание мода

## 📥 Скачивание проекта

### Вариант 1: Git (рекомендуется)
```bash
git clone <URL проекта>
cd BlockLiftGun
```

### Вариант 2: Скачивание ZIP
1. Откройте репозиторий
2. Нажмите Code → Download ZIP
3. Распакуйте архив
4. Откройте папку BlockLiftGun в терминале

### Вариант 3: Скопировать файлы напрямую
Все файлы из проекта должны сохраниться в папку `BlockLiftGun`

---

## 🔧 Подготовка окружения

### Шаг 1: Установите Java 21+

**Windows:**
```bash
# Проверьте версию
java -version

# Должно быть 21 или выше
# Если нет, скачайте отсюда:
# https://www.oracle.com/java/technologies/downloads/
```

**Linux/Mac:**
```bash
# Ubuntu/Debian
sudo apt-get install openjdk-21-jdk

# macOS (Homebrew)
brew install openjdk@21

# Проверка
java -version
```

### Шаг 2: Откройте IDE

**IntelliJ IDEA (рекомендуется):**
1. File → Open
2. Выберите папку BlockLiftGun
3. Выберите "Open as Project"
4. Нажмите "Trust Project"
5. Подождите загрузки Gradle

**VS Code:**
1. File → Open Folder
2. Выберите BlockLiftGun
3. Установите расширение "Extension Pack for Java"
4. Откройте `build.gradle`

**Eclipse:**
1. File → Import
2. Gradle Project
3. Выберите BlockLiftGun
4. Finish

---

## ⚡ Быстрый запуск (3 способа)

### Способ A: Через IDE (самый простой)

**IntelliJ IDEA:**
1. Откройте проект
2. На правой стороне найдите "Gradle"
3. Раскройте: BlockLiftGun → Tasks → run
4. Двойнажмите "runClient"

Minecraft запустится! 🎮

**VS Code/Eclipse:**
Откройте терминал в IDE и выполните:
```bash
./gradlew runClient
```

### Способ B: Через терминал

```bash
# Перейдите в папку проекта
cd BlockLiftGun

# Сгенерируйте исходники (первый раз)
./gradlew genSources

# Запустите мод
./gradlew runClient
```

Minecraft запустится через 1-2 минуты

### Способ C: Собрать JAR и установить

```bash
# Скомпилируйте проект
./gradlew build

# JAR файл создан в:
# build/libs/blockliftgun-1.0.0.jar
```

**Установка в Minecraft:**

1. **Найдите папку .minecraft:**
   - Windows: `%APPDATA%\.minecraft`
   - Linux: `~/.minecraft`
   - Mac: `~/Library/Application Support/minecraft`

2. **Создайте папку `mods` (если её нет)**

3. **Скопируйте JAR:**
   ```
   Скопируйте: build/libs/blockliftgun-1.0.0.jar
   В папку: .minecraft/mods/
   ```

4. **Проверьте профиль:**
   - Запустите Minecraft Launcher
   - Убедитесь, что выбран профиль с **NeoForge 1.21.1**
   - Если профиля нет, создайте новый

5. **Запустите игру:**
   - Нажмите Play
   - Создайте новый мир
   - Введите команду:
     ```
     /give @s blockliftgun:block_lift_gun
     ```
   - Пушка в вашей руке! 🔫

---

## 📋 Требования

| Компонент | Версия | Где получить |
|-----------|--------|-------------|
| Java | 21+ | https://www.oracle.com/java/technologies/downloads/ |
| Gradle | 8.2.1 | Встроен в проект (не нужно устанавливать) |
| Minecraft | 1.21.1 | https://minecraft.net |
| NeoForge | 21.1.47 | https://neoforged.net |
| Git | Latest | https://git-scm.com (опционально) |

---

## ✅ Проверка установки

### Проверьте Java:
```bash
java -version
# Должно выдать версию 21 или выше
```

### Проверьте Gradle:
```bash
cd BlockLiftGun
./gradlew --version
# Должно выдать Gradle 8.2.1
```

### Проверьте проект:
```bash
cd BlockLiftGun
./gradlew tasks
# Должен вывести список задач
```

---

## 🎮 Первый запуск

После запуска Minecraft с модом:

```
1. Создайте новый мир
2. Откройте чат (T клавиша)
3. Введите: /give @s blockliftgun:block_lift_gun
4. Нажмите Enter
5. Пушка появится в вашей руке!
6. Посмотрите на блок
7. Нажмите ПКМ (правая кнопка мыши)
8. Блоки летят вверх! 🚀
```

---

## 🔄 Обновление кода и перезапуск

### Во время разработки:

```bash
# Редактируйте файлы в: src/main/java/...

# Способ 1: Перезагрузка (Ctrl+F5 в IDE)
# или просто закройте Minecraft и запустите снова

# Способ 2: Через терминал
./gradlew runClient --rerun-tasks
```

### После компиляции в JAR:

```bash
# Скомпилируйте
./gradlew build

# Удалите старый JAR из mods папки
# Скопируйте новый JAR из build/libs/

# Перезапустите Minecraft
```

---

## ❌ Решение проблем

### "Gradle sync failed"
```bash
./gradlew clean
./gradlew genSources
./gradlew build
```

### "Java version mismatch"
Убедитесь что Java 21+:
```bash
java -version
# Если меньше 21, установите Java 21 с oracle.com
```

### "Мод не появляется в игре"
1. Убедитесь что профиль в лаунчере установлен на NeoForge 1.21.1
2. Проверьте что JAR находится в папке `mods`
3. Проверьте версию в `mods.toml`

### "Cannot find BlockLiftGunItem symbol"
Нажмите Ctrl+Shift+O (или Cmd+Shift+O на Mac) в IDE для автоимпорта

### "Failed to load native library"
Это нормально - это ошибка LWJGL (графической библиотеки), но мод работает

---

## 📦 Размеры файлов

| Компонент | Размер |
|-----------|--------|
| Исходный код | 20 KB |
| Ресурсы | 5 KB |
| Полный проект | 180 KB |
| JAR файл (скомпилированный) | ~100 KB |

---

## 🚀 Что дальше?

Когда мод работает:

1. **Читайте документацию:**
   - START_HERE.md - быстрый обзор
   - QUICK_START.md - за 5 минут
   - DEVELOPMENT.md - полный гайд

2. **Модифицируйте мод:**
   - Откройте BlockLiftGunItem.java
   - Меняйте параметры (LIFT_FORCE, COOLDOWN)
   - Перезапустите runClient

3. **Добавляйте функции:**
   - Рецепты крафта (см. EXAMPLES.md)
   - Конфиги (см. EXAMPLES.md)
   - Звуковые эффекты (см. EXAMPLES.md)
   - Текстуры (см. DEVELOPMENT.md)

4. **Публикуйте мод:**
   - На CurseForge: https://www.curseforge.com/
   - На Modrinth: https://modrinth.com/
   - На GitHub: https://github.com/

---

## 📞 Нужна помощь?

### Быстрые ответы в файлах:
- 🔍 Ошибки → DEVELOPMENT.md (таблица ошибок)
- 🎯 Что менять → API_REFERENCE.md
- 💡 Примеры → EXAMPLES.md
- 📋 Структура → PROJECT_STRUCTURE.md

### Не поняли что-то?
1. Перечитайте START_HERE.md
2. Смотрите комментарии в коде
3. Гуглите "neoforge [ваша ошибка]"
4. Смотрите видео на YouTube о разработке NeoForge модов

---

## ✨ Готово!

Поздравляем! У вас установлен и работает **BlockLiftGun мод** для Minecraft 1.21.1!

Следующий шаг:
```bash
./gradlew runClient
# или запустите через IDE
```

**Версия:** 1.0.0  
**Статус:** ✅ Готово к использованию  
**Лицензия:** MIT

---

Удачи в разработке! 🚀✨

