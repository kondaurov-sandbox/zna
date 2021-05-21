# Описание

В src содержится решение двух задач:
- Rest сервер с использованием spring
- ThreadSafeCache, потокобезопасный класс кеш

У ThreadSaveCache есть один юнит тест.
Может его не достаточно, и стоит еще дописать кейсов.

А Rest имеет два endpoint: 

`GET /`

`POST /recipe`
В теле ожидается json с чеком

# Запуск

Можно собрать fat jar: 

`maven package` 

Запустить 

`java -jar ./target/demo-0.0.1-SNAPSHOT.jar`