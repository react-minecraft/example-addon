<div align="center">

<a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.demolab.com?font=Manrope&weight=700&size=38&pause=1000&color=EB2544&background=FFFFFF00&center=true&vCenter=true&width=700&lines=Example+Addon;Your+own+addon+for+React" alt="Example Addon"/></a>

<p>Шаблон аддона на событиях React</p>

<p>
  <a href="https://react-ac.space"><img src="https://img.shields.io/badge/Website-EB2544?style=plastic&logo=googlechrome&logoColor=white" alt="Website"/></a>
  <a href="https://t.me/react_ac"><img src="https://img.shields.io/badge/Telegram-26A5E4?style=plastic&logo=telegram&logoColor=white" alt="Telegram"/></a>
  <a href="https://t.me/react_ac_support"><img src="https://img.shields.io/badge/Support-555555?style=plastic&logo=telegram&logoColor=white" alt="Support"/></a>
</p>

<p>
  <a href="https://github.com/react-minecraft/example-addon/blob/master/LICENSE"><img src="https://img.shields.io/github/license/react-minecraft/example-addon?style=plastic&logo=opensourceinitiative&logoColor=white" alt="License"/></a>
</p>

</div>

## Что делает пример

`ExampleAddon` копирует `config.yml` и регистрирует `FailListener` через `environment().listeners()`. Слушатель получает `CheckFlagEvent` перед увеличением уровня нарушения и отправляет уведомление игрокам с правом `react.example.notify`. Уведомления используют планировщик окружения и работают с MiniMessage и `&`-кодами.

```yaml
permission: react.example.notify
message: '<red>[React] <white><player> <gray>failed <white><check> <gray>(<white><violations><gray>)'
```

`<player>`, `<check>` и `<violations>` подставляются для каждого уведомления. Настройки лежат в `plugins/React/addons/example-addon/config.yml`.

## Сборка и установка

Используйте JDK 21. Запустите `./gradlew build` или `.\gradlew.bat build` на Windows. Положите `build/libs/example-addon-1.0.0.jar` в `plugins/React/addons/` и выполните `/react reboot addons` или перезапустите сервер.

Пример использует React API 2. Версия зависимости задаётся ключом `reactVersion` в `gradle.properties`. Сборка берёт `react-api` и `react-common` с JitPack.

Если нужна собственная модель или логика анализа, смотрите [example-rcp](https://github.com/react-minecraft/example-rcp).


## Лицензия

[MIT](LICENSE).
