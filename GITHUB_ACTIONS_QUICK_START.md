# 🚀 GitHub Actions - Быстрый Старт

## 📦 Что вы получили

ZIP архив **BlockLiftGun-complete.zip** с:
- ✅ Готовым мод-проектом
- ✅ GitHub Actions workflows (2 штуки)
- ✅ GitHub templates для Issues/PR
- ✅ Полной документацией на русском
- ✅ Всем необходимым для публикации на GitHub

## 🚀 Быстрая установка (5 шагов)

### Шаг 1: Скачайте и распакуйте
```bash
# Распакуйте BlockLiftGun-complete.zip
unzip BlockLiftGun-complete.zip
cd BlockLiftGun
```

### Шаг 2: Создайте репозиторий на GitHub
1. Перейдите на https://github.com/new
2. Назовите: **BlockLiftGun**
3. Выберите Public
4. Создайте репозиторий

### Шаг 3: Отправьте код на GitHub
```bash
git init
git add .
git commit -m "Initial commit: BlockLiftGun Mod with GitHub Actions"
git branch -M main
git remote add origin https://github.com/ВАШ_НИК/BlockLiftGun.git
git push -u origin main
```

### Шаг 4: Проверьте Actions
1. Откройте https://github.com/ВАШ_НИК/BlockLiftGun
2. Перейдите на вкладку **Actions**
3. Должны быть 2 workflow:
   - ✅ **Build Mod** - собирает при push
   - ✅ **Publish Release** - публикует релизы

4. Смотрите, как проходит первая сборка (зелёная галочка = успех)

### Шаг 5: Создайте первый Release
```bash
# Способ 1: Через Git
git tag v1.0.0
git push origin v1.0.0

# Способ 2: Через GitHub Web UI
GitHub → Releases → Create a new release
```

**Готово! 🎉** GitHub Actions теперь автоматически:
- Собирает мод при каждом push
- Публикует JAR на Releases

---

## 📋 Что находится в ZIP

```
BlockLiftGun-complete.zip (63 KB)
├── 📁 .github/
│   ├── workflows/
│   │   ├── build.yml              ← Автосборка при push
│   │   └── publish.yml            ← Публикация Release
│   ├── ISSUE_TEMPLATE/
│   │   ├── bug_report.md          Шаблон для ошибок
│   │   └── feature_request.md     Шаблон для идей
│   ├── pull_request_template.md   Шаблон для PR
│   ├── GITHUB_SETUP.md            Настройка репозитория
│   └── GITHUB_ACTIONS_SETUP.md    Полная документация
│
├── 📁 src/
│   └── main/
│       ├── java/...               Java исходный код
│       └── resources/...          Ресурсы и конфиги
│
├── 📁 .github/workflows/
│   ├── build.yml                  ✨ Автосборка
│   └── publish.yml                ✨ Публикация
│
├── 📖 Документация (на русском!)
│   ├── START_HERE.md              Начните отсюда
│   ├── QUICK_START.md             За 5 минут
│   ├── INSTALLATION.md            Установка
│   ├── DEVELOPMENT.md             Разработка
│   ├── API_REFERENCE.md           API справочник
│   ├── EXAMPLES.md                Примеры
│   ├── CONTRIBUTING.md            Для контрибьюторов
│   ├── CHANGELOG.md               История версий
│   ├── GITHUB_ACTIONS_SETUP.md    GitHub Actions
│   ├── GITHUB_SETUP.md            Настройка GitHub
│   └── ... и ещё 10+ файлов
│
├── 🔧 Конфигурация
│   ├── build.gradle               Gradle конфиг
│   ├── settings.gradle            Настройки
│   ├── gradle.properties          Версии
│   └── .gitignore                 Git конфиг
│
└── 📄 Лицензии
    ├── LICENSE                    MIT лицензия
    └── CHANGELOG.md               История обновлений
```

**Итого:** 60 файлов, готовых к использованию

---

## 🎯 Workflows Объяснение

### 1️⃣ Build Workflow (`build.yml`)

**Когда запускается:**
- На каждый push (main, master, develop)
- На каждый pull request
- Вручную через "Run workflow"

**Что делает:**
1. Устанавливает Java 21
2. Генерирует исходники: `./gradlew genSources`
3. Собирает мод: `./gradlew build`
4. Загружает JAR как artifact (хранится 30 дней)

**Результат:**
- ✅ Зелёная галочка = мод успешно собран
- ❌ Красный крест = ошибка компиляции

### 2️⃣ Publish Workflow (`publish.yml`)

**Когда запускается:**
- При создании нового Release
- Вручную через "Run workflow"

**Что делает:**
1. Собирает мод
2. Загружает JAR на GitHub Release
3. (Опционально) Публикует на CurseForge
4. (Опционально) Публикует на Modrinth

**Результат:**
- JAR доступен для скачивания на Release странице
- Автоматическая публикация на платформы

---

## 📥 Как скачать собранный мод

### Вариант 1: Из Artifacts (временно, 30 дней)
1. GitHub → Actions
2. Выберите последний успешный "Build Mod"
3. Скачайте "build-artifacts"
4. Распакуйте JAR

### Вариант 2: Из Releases (постоянно)
1. GitHub → Releases
2. Найдите нужную версию (например v1.0.0)
3. Скачайте blockliftgun-1.0.0.jar

### Вариант 3: Через GitHub API
```bash
# Скачать latest release
curl -L https://api.github.com/repos/ВАШ_НИК/BlockLiftGun/releases/latest \
  -o release.json | grep browser_download_url
```

---

## 🔐 Публикация на CurseForge и Modrinth

### Если хотите автоматическую публикацию:

#### 1. Получите API токены

**CurseForge:**
- Перейдите на https://console.curseforge.com/
- Создайте API ключ
- Скопируйте его

**Modrinth:**
- Перейдите на https://modrinth.com/settings/account
- Создайте API токен
- Скопируйте его

#### 2. Добавьте токены в GitHub

1. GitHub → Settings → Secrets and variables → Actions
2. Нажмите "New repository secret"
3. Добавьте:
   - Имя: `CURSEFORGE_TOKEN`, Значение: ваш ключ
   - Имя: `MODRINTH_TOKEN`, Значение: ваш токен

#### 3. Раскомментируйте в publish.yml

Откройте `.github/workflows/publish.yml`:
- Найдите строки с `# Раскомментируйте`
- Удалите `#` в начале

#### 4. Готово!

При создании Release мод будет автоматически:
- Загружен на GitHub Releases
- Опубликован на CurseForge
- Опубликован на Modrinth

---

## 📊 Примеры использования

### Разработка (день в день)
```bash
# Вы меняете код
git commit -am "Добавил новую функцию"
git push
→ GitHub Actions автоматически собирает
→ Через 2-3 минуты JAR готов
→ Скачайте из Actions → Artifacts
```

### Выпуск версии (раз в месяц)
```bash
# Готово выпустить версию?
git tag v1.1.0
git push origin v1.1.0
→ GitHub Actions собирает
→ Создаёт Release на GitHub
→ JAR автоматически загружен
→ Если токены добавлены - публикует на CurseForge/Modrinth
```

---

## 🐛 Решение проблем

### "Build Failed" (Красный крест)
1. Нажмите на неудачную сборку
2. Смотрите логи в разделе "Build with Gradle"
3. Обычно ошибка компиляции

### "Artifacts не загружены"
Проверьте логи build.gradle - обычно ошибка там

### "Релиз не создан"
Убедитесь что вы:
1. Создали tag: `git tag v1.0.0`
2. Пушнули tag: `git push origin v1.0.0`
3. Ждите 2-3 минуты пока Actions сработает

### "Java version mismatch"
В build.yml указана Java 21 - это правильно
Если нужна другая версия - измените в файле

---

## 📈 Мониторинг

### Что смотреть:

1. **Actions вкладка** - статус всех сборок
2. **Releases** - доступные для скачивания версии
3. **Issues** - багов и идеи от сообщества
4. **Insights** - статистика активности

### Как получить уведомления:

1. GitHub → Watching → Configure notifications
2. Выберите тип событий
3. Получайте email уведомления о событиях

---

## ✅ Финальный Checklist

- [ ] ZIP распакован
- [ ] Репозиторий создан на GitHub
- [ ] Код загружен (git push)
- [ ] Actions запустилась и собрала мод
- [ ] Первый Release создан
- [ ] JAR скачан и установлен в Minecraft
- [ ] Мод работает в игре
- [ ] Документация прочитана

---

## 🎯 Возможные действия

### Автоматическое
- ✅ Сборка при push
- ✅ Создание Release при tag
- ✅ Загрузка JAR на GitHub
- ✅ Публикация на CurseForge (с токеном)
- ✅ Публикация на Modrinth (с токеном)

### Ручное
- 👤 Создание Issue
- 👤 Pull Request с улучшениями
- 👤 Запуск workflow через UI
- 👤 Управление Releases

---

## 📚 Дополнительно

### Если нужна помощь:
- 📖 GITHUB_ACTIONS_SETUP.md - полная документация
- 🔧 GITHUB_SETUP.md - настройка репозитория
- 💡 EXAMPLES.md - примеры расширения

### Если хотите улучшить:
- ✏️ CONTRIBUTING.md - как контрибьютить
- 🐛 Используйте Issue templates для баг-репортов
- 💡 Используйте Feature Request для идей

---

## 🚀 Что дальше?

1. ✅ Распакуйте ZIP
2. ✅ Создайте репозиторий на GitHub
3. ✅ Пушьте код: `git push`
4. ✅ Смотрите Actions собирает
5. ✅ Создавайте Releases для версий
6. ✅ Наслаждайтесь автоматизацией! 🎉

---

**Версия:** 1.0.0  
**Архив:** BlockLiftGun-complete.zip (63 KB)  
**Готово к использованию:** ✅ ДА  

Удачи! 🚀✨

P.S. Если что-то непонятно - читайте GITHUB_ACTIONS_SETUP.md в проекте!

