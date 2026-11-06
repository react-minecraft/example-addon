# Example Addon

Минимальный аддон для плагина [React](https://github.com/react-minecraft/plugin). Он пишет персоналу в чат, когда
игрок проваливает проверку. Это шаблон: склонируйте репозиторий и поменяйте `FailListener` под себя.

## Чем аддон отличается от RCP

| Аддон                                            | RCP                                                       |
|--------------------------------------------------|-----------------------------------------------------------|
| `plugins/React/addons/`, файл `react-addon.yml`  | `plugins/React/.rcp/providers/`, файл `rcp-metadata.yml`  |
| Видит весь плагин (`react-common`)               | Видит только стабильный `react-api`                       |
| Реагирует на события: флаги, история, игроки     | Даёт плагину модели и результаты проверок                 |
| Может сломаться при обновлении плагина           | Совместимость гарантирует `api-version`                   |

Если нужна своя модель или проверка, смотрите [example-rcp](https://github.com/react-minecraft/example-rcp).

## Что делает пример

- на `onEnable` копирует `config.yml` в `plugins/React/addons/example-addon/`;
- слушает `PlayerCheckFailEvent`: React шлёт его, когда игрок проваливает проверку, в том числе по результату RCP;
- отправляет строку из конфига всем, у кого есть право `react.example.notify`;
- снимает слушатель на `onDisable`.

```yaml
permission: react.example.notify
# Плейсхолдеры: %player%, %check%, %violations%
message: '&c[React] &f%player% &7failed &f%check% &7(&f%violations%&7)'
```

## Сборка и установка

```bash
./gradlew build
```

1. Возьмите `build/libs/example-addon-1.0.0.jar`.
2. Положите его в `plugins/React/addons/`.
3. Перезапустите сервер.

## Зависимость

Версия плагина задаётся в `gradle.properties` в ключе `reactVersion`: коммит или ветка плагина на JitPack.
Если рядом лежит чекаут плагина (`../react-vector`), сборка берёт классы из него.

```kotlin
repositories {
    maven("https://jitpack.io/")
}

dependencies {
    compileOnly("com.github.react-minecraft.plugin:react-api:VERSION")
    compileOnly("com.github.react-minecraft.plugin:react-common:VERSION")
}
```
