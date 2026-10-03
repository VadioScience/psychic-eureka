# ⚙️ Настройка GitHub репозитория

После создания репозитория выполните эту настройку.

## 📝 Основная информация

### 1. Description (Описание)
```
BlockLiftGun - NeoForge мод для Minecraft 1.21.1 с пушкой, поднимающей блоки в воздух
```

### 2. Website (Сайт) - Опционально
```
https://github.com/ВАШ_НИК/BlockLiftGun
```

### 3. Topics (Темы)
Добавьте эти темы для лучшей индексации:
```
minecraft
neoforge
mod
blockliftgun
minecraft-mod
1.21.1
java
gradle
```

**Как добавить:**
Settings → About → Topics → Add topics

---

## 🎯 Настройка репозитория

### Settings → General

#### Repository name
```
BlockLiftGun
```

#### Description
```
NeoForge мод для Minecraft 1.21.1 - поднимает блоки в воздух с помощью пушки
```

#### Visibility
- ✅ Public (если хотите чтобы все видели)
- 🔒 Private (если приватный проект)

#### Default branch
```
main
```

#### Discussions
- ✅ Включить (для вопросов и обсуждений)

### Settings → Access → Collaborators
Добавьте соавторов если нужны:
1. Нажмите "Add people"
2. Выберите роль (Maintainer, Developer, etc.)

### Settings → Code security

#### Secret scanning
- ✅ Включить (для поиска API ключей)

#### Dependabot
- ✅ Включить (для обновления зависимостей)

---

## 🔐 Secrets для GitHub Actions

### 1. GITHUB_TOKEN
Создаётся автоматически - не нужно ничего делать

### 2. CURSEFORGE_TOKEN (Опционально)
Если хотите публиковать на CurseForge:

1. Перейдите Settings → Secrets and variables → Actions
2. Нажмите "New repository secret"
3. Имя: `CURSEFORGE_TOKEN`
4. Значение: Ваш API ключ с CurseForge

### 3. MODRINTH_TOKEN (Опционально)
Если хотите публиковать на Modrinth:

1. Перейдите Settings → Secrets and variables → Actions
2. Нажмите "New repository secret"
3. Имя: `MODRINTH_TOKEN`
4. Значение: Ваш API токен с Modrinth

### 4. DISCORD_WEBHOOK (Опционально)
Для уведомлений в Discord:

1. Создайте вебхук в Discord сервере
2. Скопируйте URL
3. Добавьте как Secret: `DISCORD_WEBHOOK`

---

## 🛡️ Branch Protection

### Защита main branch

Settings → Branches → Add rule

#### Branch name pattern
```
main
```

#### Protection rules

- ✅ Require a pull request before merging
  - ✅ Require approval reviews: 1
  - ✅ Dismiss stale pull request approvals
  
- ✅ Require status checks to pass before merging
  - ✅ Require branches to be up to date
  - ✅ Require Build Mod to pass
  
- ✅ Require code reviews before merging
  - ✅ Request changes from code reviewers

- ✅ Restrict who can push to matching branches
  - Только Maintainers

---

## 📋 README Badge

Добавьте в README.md вверху:

```markdown
# BlockLiftGun Mod

[![Build Mod](https://github.com/ВАШ_НИК/BlockLiftGun/workflows/Build%20Mod/badge.svg)](https://github.com/ВАШ_НИК/BlockLiftGun/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Minecraft 1.21.1](https://img.shields.io/badge/Minecraft-1.21.1-brightgreen.svg)](https://minecraft.net)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.47-blue.svg)](https://neoforged.net)
```

---

## 📊 GitHub Pages (Опционально)

Для сайта мода:

1. Settings → Pages
2. Source: Deploy from a branch
3. Branch: main
4. Folder: / (root)
5. Save

Затем создайте `docs/index.html` для сайта

---

## 🔄 Настройка Releases

### Автоматическое создание

Ваши workflow уже настроены на автоматическое создание Release при тегировании.

### Как создать Release:

```bash
# Способ 1: Через Git
git tag v1.0.0
git push origin v1.0.0

# Способ 2: Через GitHub Web
GitHub → Releases → Create a new release
```

---

## 📊 Metrics & Insights

### Активировать в Settings:

- ✅ Insights (статистика активности)
- ✅ Discussions (вопросы и ответы)
- ✅ Projects (доски задач)
- ✅ Security (сканирование уязвимостей)

---

## 🎨 Кастомизация

### GitHub Profile (По желанию)

1. Добавьте profile picture
2. Добавьте bio
3. Добавьте социальные сети

### Organization (если хотите)

1. Settings → Create organization
2. Перенесите репозиторий в организацию
3. Пригласите членов

---

## 📝 Wiki (Опционально)

Включить в Settings:

1. Settings → Features
2. ✅ Wikis
3. Создайте Wiki страницы для документации

---

## 🤖 Automations (Опционально)

### Dependabot
Автоматические обновления зависимостей:

Settings → Code security and analysis → Dependabot

- ✅ Dependabot alerts
- ✅ Dependabot security updates
- ✅ Dependabot version updates

### GitHub Copilot
Если есть Copilot:

Settings → Copilot → Allow Copilot Suggestions

---

## 🔍 Проверка настройки

Откройте GitHub репозиторий и проверьте:

- ✅ Название правильное
- ✅ Описание заполнено
- ✅ Topics добавлены (7-10 штук)
- ✅ README видно на главной
- ✅ Actions вкладка показывает workflow
- ✅ Релизы создаются автоматически
- ✅ Badge в README работают

---

## 📚 Что дальше?

1. Добавьте .gitignore (уже есть в проекте)
2. Добавьте LICENSE (MIT файл)
3. Добавьте CHANGELOG.md
4. Настройте Issues templates (уже есть в .github/)
5. Настройте Pull Request template (уже есть в .github/)

---

## 🎯 Финальный Checklist

- [ ] Репозиторий создан на GitHub
- [ ] Код загружен (git push)
- [ ] Description заполнено
- [ ] Topics добавлены
- [ ] Branch protection настроена
- [ ] Secrets добавлены (если нужны)
- [ ] GitHub Actions работает
- [ ] Первый Release создан
- [ ] Badge добавлены в README
- [ ] Contributing гайд на месте

---

**Версия:** 1.0.0  
**Обновлено:** October 2026

Готово к запуску! 🚀✨

