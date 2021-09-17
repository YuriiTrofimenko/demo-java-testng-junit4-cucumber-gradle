# Demo TestNg Parallel + Allure Report / JUnit4 + Cucumber Testing (rozetka.com.ua)
Проект примеров параллельного тестирования веб-приложения rozetka.com.ua на основе каркаса TestNg с отчетами Allure или JUnit4 + Cucumber - на выбор при помощи параметров
## Предварительные требования
- OS macOS Big Sur / Linux Ubuntu 20.04 / Другие современные настольные OS (не проверено)
- JDK 11
- Gradle 7.1
- браузеры Chrome, Firefox
## Скачивание и запуск
- скачать:

**git clone** https://github.com/YuriiTrofimenko/demo-java-testng-junit4-cucumber-gradle.git

- драйверы скачиваются по адресу https://www.selenium.dev/downloads/ и добавляются в каталог _src/main/resources_ (версии драйверов должны соответствовать версиям браузеров), при запуске на OS Windows 10 необходимо изменить имя драйвера в файле src/main/resources/config.properties

- запустить тесты на основе JUnit4 + Cucumber:

**./gradlew clean test --info**

- запустить тесты на основе TestNg + Allure Report, затем отобразить отчет в браузере:

**./gradlew clean test -Ptestng --info**
**./gradlew allureServe**

## Показаны техники:
- BDD-именование методов тест-кейсов
- TestNg DataProvider с многопоточным выполнением
- JUnit4 с человеко-понятной записью шагов теста при помощи Cucumber
- выбор варианта запуска тестов при помощи параметров командной строки
- вывод отчетов Allure на веб-страницу с подробными сведениями о каждом событии теста