<div align="center">

<a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.demolab.com?font=Manrope&weight=700&size=38&pause=1000&color=EB2544&background=FFFFFF00&center=true&vCenter=true&width=700&lines=Example+Addon;Your+own+addon+for+React" alt="Example Addon Typing SVG"/></a>

<p>Шаблон своего аддона для React</p>

<p>
  <a href="https://github.com/react-minecraft/example-addon/blob/main/LICENSE">
    <img src="https://img.shields.io/github/license/react-minecraft/example-addon?style=flat&logo=opensourceinitiative&logoColor=white" alt="License">
  </a>
</p>

<p>
  <a href="https://react-ac.space">Сайт</a>
  &nbsp;•&nbsp;
  <a href="https://t.me/react_ac">Канал</a>
  &nbsp;•&nbsp;
  <a href="https://t.me/react_ac_support">Поддержка</a>
</p>

</div>

---

## Что это?

- Минимальный аддон для плагина [React](https://github.com/react-minecraft/plugin): пишет персоналу в чат, когда
  игрок проваливает проверку.
- Это шаблон: клонируете и меняете `FailListener` под себя.

---

## Аддон или RCP?

| Аддон                                           | RCP                                                      |
|-------------------------------------------------|----------------------------------------------------------|
| `plugins/React/addons/`, файл `react-addon.yml` | `plugins/React/.rcp/providers/`, файл `rcp-metadata.yml` |
| Видит весь плагин (`react-common`)              | Видит только стабильный `react-api`                      |
| Реагирует на события: флаги, история, игроки    | Даёт плагину модели и результаты проверок                |
| Может сломаться при обновлении плагина          | Совместимость гарантирует `api-version`                  |

Нужна своя модель или проверка - смотрите [example-rcp](https://github.com/react-minecraft/example-rcp).

---

## Что делает пример

- на `onEnable` копирует `config.yml` в `plugins/React/addons/example-addon/`;
- слушает `PlayerCheckFailEvent`: React шлёт его, когда игрок проваливает проверку, в том числе по результату RCP;
- отправляет строку из конфига всем, у кого есть право `react.example.notify`;
- снимает слушатель на `onDisable`.

```yaml
permission: react.example.notify
# Плейсхолдеры: {player}, {check}, {violations}
message: '<red>[React] <white>{player} <gray>failed <white>{check} <gray>(<white>{violations}<gray>)'
```

Сообщение понимает и MiniMessage, и `&`-коды.

---

## Сборка & Установка

1. `./gradlew build`
2. Возьмите `build/libs/example-addon-1.0.0.jar`
3. Положите его в `plugins/React/addons/`
4. Перезапустите сервер

---

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

---

## Лицензия

Пример распространяется под MIT — см. [LICENSE](LICENSE).
