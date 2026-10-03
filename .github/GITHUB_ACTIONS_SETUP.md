# 🚀 GitHub Actions Setup для BlockLiftGun Mod

## 📋 Что это такое?

GitHub Actions - это CI/CD система, которая автоматически собирает и публикует ваш мод при каждом push или создании release.

## 🎯 Возможности

### Workflow: `build.yml`
Запускается на каждый push/pull request:
- ✅ Автоматическая сборка мода
- ✅ Проверка кода
- ✅ Загрузка артефактов (JAR файлы)
- ✅ Создание Release при тегировании

### Workflow: `publish.yml`
Запускается при создании Release:
- ✅ Автоматическая сборка
- ✅ Публикация на GitHub Releases
- ✅ Поддержка CurseForge (с токеном)
- ✅ Поддержка Modrinth (с токеном)

---

## 🔧 Быстрая настройка

### Шаг 1: Загрузите мод на GitHub

```bash
cd BlockLiftGun

# Инициализируем Git
git init
git add .
git commit -m "Initial commit: BlockLiftGun Mod"

# Добавляем remote
git remote add origin https://github.com/ВАШ_НИК/BlockLiftGun.git

# Отправляем на GitHub
git branch -M main
git push -u origin main
```

### Шаг 2: Проверьте Workflows

1. Перейдите на GitHub → ваш репозиторий
2. Нажмите вкладку "Actions"
3. Должны быть 2 workflow: `Build Mod` и `Publish Release`

### Шаг 3: Готово!

GitHub Actions теперь будет автоматически:
- Собирать мод при каждом push
- Создавать Release при тегировании

---

## 📊 Как это работает

### Автоматическая сборка (build.yml)

```
Вы пушите код → GitHub Actions запускается → 
Устанавливает Java 21 → 
Собирает мод → 
Загружает JAR файлы как artifact
```

**Когда запускается:**
- На каждый push в main/master/develop
- На каждый pull request
- Вручную через "Run workflow"

**Результат:**
- JAR файлы в секции "Artifacts" (хранятся 30 дней)

### Публикация (publish.yml)

```
Вы создаёте Release → GitHub Actions запускается →
Собирает мод →
Загружает на GitHub Releases →
(Опционально) Публикует на CurseForge/Modrinth
```

**Когда запускается:**
- При создании нового Release
- Вручную через "Run workflow"

---

## 🏷️ Создание Release

### Способ 1: Через GitHub Web

1. Перейдите на GitHub
2. Нажмите "Releases" → "Create a new release"
3. Укажите тег: `v1.0.0`
4. Название: `BlockLiftGun 1.0.0`
5. Опишите изменения
6. Нажмите "Publish release"

GitHub Actions автоматически:
- Соберёт мод
- Загрузит JAR на Release
- Создаст Download ссылку

### Способ 2: Через Git команды

```bash
# Тегируем версию
git tag v1.0.0

# Отправляем тег
git push origin v1.0.0

# GitHub Actions автоматически создаст Release
```

---

## 📦 Скачивание собранного мода

### Из Artifacts (временно, 30 дней)

1. GitHub → Actions
2. Выберите последний успешный build
3. Скачайте "build-artifacts"

### Из Releases (постоянно)

1. GitHub → Releases
2. Найдите нужную версию
3. Скачайте `blockliftgun-1.0.0.jar`

---

## 🔐 Настройка публикации на CurseForge/Modrinth

### Шаг 1: Получите API токены

**CurseForge:**
1. Перейдите на https://console.curseforge.com/
2. Создайте новый API ключ
3. Скопируйте его

**Modrinth:**
1. Перейдите на https://modrinth.com/settings/account
2. Создайте API токен
3. Скопируйте его

### Шаг 2: Добавьте токены в GitHub Secrets

1. GitHub → Settings → Secrets and variables → Actions
2. Нажмите "New repository secret"
3. Добавьте:
   - Имя: `CURSEFORGE_TOKEN`
   - Значение: Ваш CurseForge API ключ
4. Повторите для `MODRINTH_TOKEN`

### Шаг 3: Раскомментируйте в publish.yml

```yaml
# В файле .github/workflows/publish.yml
# Найдите строки с "Раскомментируйте"
# Удалите # в начале строк
```

### Шаг 4: Готово!

Теперь при создании Release мод будет:
- Автоматически загружен на CurseForge
- Автоматически загружен на Modrinth
- Добавлен на GitHub Releases

---

## 📊 Мониторинг сборки

### Проверить статус

1. GitHub → Actions
2. Смотрите список workflow runs
3. Зелёная галочка ✅ = успешно
4. Красный крест ❌ = ошибка

### Читать логи

1. Нажмите на неудачную сборку
2. Нажмите "build" job
3. Смотрите Output → Build with Gradle

---

## 🐛 Частые проблемы

### "Gradle sync failed"
Обычно решается само при следующем push

### "Java version not found"
Проверьте что в build.gradle указана Java 21

### "Artifact не создан"
Проверьте в логах раздел "Build with Gradle"

### "Permission denied: ./gradlew"
Это баг GitHub Actions, исправляется строкой:
```yaml
- name: Make gradlew executable
  run: chmod +x gradlew
```

---

## 🎯 Рекомендуемый workflow

### День 1: Разработка
```
git commit -am "Added new feature"
git push
→ GitHub Actions собирает мод автоматически
```

### День 30: Публикация версии
```
git tag v1.0.0
git push origin v1.0.0
→ GitHub Actions создаёт Release
→ JAR автоматически загружен
```

### День 31: Обновление на CurseForge/Modrinth
```
Если токены добавлены:
→ GitHub Actions публикует сам
```

---

## 📝 Файлы конфигурации

### `.github/workflows/build.yml`
- Основной workflow для сборки
- Запускается на push/PR
- Загружает artifacts

### `.github/workflows/publish.yml`
- Workflow для публикации Release
- Запускается при создании Release
- Публикует на платформы

---

## 🚀 Продвинутые возможности

### Кастомный Java Version

В `build.yml` измените:
```yaml
java-version: '21'  # Измените на нужную версию
```

### Кастомные branches

В `build.yml` измените:
```yaml
branches: [ main, master, develop ]  # Добавьте свои branches
```

### Различные OS

```yaml
runs-on: ${{ matrix.os }}
strategy:
  matrix:
    os: [ubuntu-latest, windows-latest, macos-latest]
```

### Уведомления в Discord

```yaml
- name: Discord notification
  if: failure()
  uses: sarisia/actions-status-discord@v1
  with:
    webhook_url: ${{ secrets.DISCORD_WEBHOOK }}
```

---

## 📚 Полезные ссылки

- 📖 GitHub Actions Docs: https://docs.github.com/en/actions
- 🔧 Gradle on GitHub Actions: https://github.com/gradle/gradle-build-action
- 📦 Release Action: https://github.com/softprops/action-gh-release
- 🎮 MC Publish: https://github.com/Kir-Antipov/mc-publish

---

## ✅ Checklist

- [ ] Код загружен на GitHub
- [ ] GitHub Actions workflow создана
- [ ] Первый build прошёл успешно
- [ ] Артефакты загружаются
- [ ] Release создана и тестирована
- [ ] Токены CurseForge/Modrinth добавлены (опционально)
- [ ] Публикация на платформы работает (опционально)

---

## 💡 Советы

1. **Тестируйте локально перед push:**
   ```bash
   ./gradlew build
   ```

2. **Используйте semantic versioning:**
   - v1.0.0 - major release
   - v1.0.1 - patch release
   - v1.1.0 - minor release

3. **Добавляйте CHANGELOG.md:**
   Опишите что изменилось в каждой версии

4. **Мониторьте Actions:**
   Получайте уведомления об ошибках

5. **Используйте draft releases:**
   Проверьте перед финальной публикацией

---

**Версия:** 1.0.0  
**Статус:** ✅ Готово к использованию  
**Обновлено:** October 2026

Удачи с CI/CD! 🚀✨

