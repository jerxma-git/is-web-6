# TODO-List
Проект представляет из себя примитивный todo-list доступный при авторизации на платформе

## Views:
- `/dashboard` - Сам todo-list, view находится в [/resources/templates/dashboard.html](src/main/resources/templates/dashboard.html)
- `/login` - стандартный логин Spring'а
- `/profile` - профиль пользователя
- `/statistics` - страничка статистики выполнения задач
- `/registration` - страница регистрации (реализована по минимуму функционала)
- `/chat` - чат для обмена сообщениями по WebSocket

Вспомогательные страницы (в конечном результате их не будет)
- `/show-users` - выводит информацию о всех пользователях


## Лаб1
Проект залит на Render: [https://jerxma-is-web-6.onrender.com](https://jerxma-is-web-6.onrender.com)

## Лаб2
Используется шаблонизатор Thymeleaf: выделены partials ("fragments") в [/fragments/base.html](src/main/resources/templates/fragments/base.html)
в футере добавляется информация о времени отрисовки страницы (логика реализована в [Interceptor'е](/src/main/java/com/mysite/todolist/interceptor/TimingInterceptor.java) и в скрипте [footer.js](/src/main/resources/static/js/footer.js))

## Лаб3
Подключен Postgres на Render, реализованы 2 сущности Task и User в [/model](/src/main/java/com/mysite/todolist/model))

## Лаб4
Подключена генерация API по URL определенному в переменной окружения API_DOCS_URL 
Контроллеры страниц реализованы в [/controller](/src/main/java/com/mysite/todolist/controller).

## Лаб5
Реализованы контроллеры, сервисы, модели, репозитории Spring'а

## Лаб6
Используется Spring Security, основная конфигурация лежит в [SecurityConfig.java](/src/main/java/com/mysite/todolist/security/SecurityConfig.java)
Аутентификация и авторизация реализованы через пользовательские сессии на сервере.

## Лаб7
Реализован чат по WebSocket каналу в `/chat`
Конфигурация WebSocket'a лежит в 