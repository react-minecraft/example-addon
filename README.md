<div align="center">

<a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.demolab.com?font=Manrope&weight=700&size=38&pause=1000&color=EB2544&background=FFFFFF00&center=true&vCenter=true&width=700&lines=Example+Addon;Your+own+addon+for+React" alt="Example Addon Typing SVG"/></a>

<p>Шаблон своего аддона для React</p>

<p>
  <a href="https://github.com/react-minecraft/example-addon/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/react-minecraft/example-addon?style=plastic&logo=opensourceinitiative&logoColor=white" alt="License">
  </a>
</p>

<p>
  <a href="https://react-ac.space"><img src="https://img.shields.io/badge/Website-EB2544?style=plastic&logo=googlechrome&logoColor=white" alt="Website"/></a>
  <a href="https://t.me/react_ac"><img src="https://img.shields.io/badge/Telegram-26A5E4?style=plastic&logo=telegram&logoColor=white" alt="Telegram"/></a>
  <a href="https://t.me/react_ac_support"><img src="https://img.shields.io/badge/Support-555555?style=plastic&logo=telegram&logoColor=white" alt="Support"/></a>
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
| Может сломаться при обновлении плагина          | При загрузке проверяется `api-version`                  |

Нужна своя модель или проверка - смотрите [example-rcp](https://github.com/react-minecraft/example-rcp).

---

## Что делает пример

- на `onEnable` копирует `config.yml` в `plugins/React/addons/example-addon/`;
- слушает `CheckFlagEvent` на шине событий React: он приходит перед каждым ростом VL, в том числе по результату RCP;
- отправляет строку из конфига всем, у кого есть право `react.example.notify`;
- слушатель регистрируется через `environment().listeners()`, React сам снимает его при выключении аддона.

```yaml
permission: react.example.notify
# Плейсхолдеры: <player>, <check>, <violations>
message: '<red>[React] <white><player> <gray>failed <white><check> <gray>(<white><violations><gray>)'
```

Сообщение понимает и MiniMessage, и `&`-коды.

---

## Сборка & Установка

Для сборки нужен JDK 21. На сервере нужен React с API 2.

1. `./gradlew build`
2. Возьмите `build/libs/example-addon-1.0.0.jar`
3. Положите его в `plugins/React/addons/`
4. Перезапустите сервер или выполните `/react reboot addons`

---

## Зависимость

> [!IMPORTANT]
> Версия плагина задаётся в `gradle.properties` в ключе `reactVersion`: коммит или ветка плагина на JitPack. `react-api` и `react-common` берутся с JitPack.

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
