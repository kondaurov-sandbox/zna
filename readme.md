# Описание

В src содержится решение двух задач:
- Rest сервер с использованием spring
- ThreadSafeCache, потокобезопасный класс кеш

У ThreadSaveCache есть один юнит тест (см src/test)

А Rest имеет два endpoint: 

`GET /`

`POST /recipe`
В теле ожидается json с чеком

Нет тестов, не стал их делать т.к. протестировал вручную

# Запуск

Можно собрать fat jar: 

`maven package` 

Запустить 

`java -jar ./target/demo-0.0.1-SNAPSHOT.jar`

## Что можно улучшить
- Дописать тест кейсы на cache
- Написать один, два тест кейса на rest эндпоинт /recipe
