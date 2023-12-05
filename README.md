# Unisafe_test
Тестовое задание из вакансии на джуна в компании UniSafe

# Указания к выполнению:

## 1 Условия

Привет! Для того чтобы тебя пригласили на собеседование, необходимо решить задачу. Она не
является сложной, но может занять определенное время. Человек, обладающий необходимыми
навыками (или способный их приобрести), сможет справиться с ней за несколько часов. Именно
такого человека мы и ищем! Однако, время на выполнение не ограничено. Можно подходить
к решению творчески, и любые положительные отклонения от необходимого минимума будут
учтены. Запрещено использовать помощь других людей, но можно находить готовые фрагменты
кода. По завершению, необходимо предоставить открытый код и собранный APK. Удачи!

### 1.1 При успешном выполнении

При успешном выполнении задачи, ты
будешь приглашен на очное собеседование.

### 1.2 Задание

Необходимо написать пример приложения списка покупок на Kotlin. Используя выбранные
запросы, необходимо создавать, редактировать и загружать списки покупок. Приложение
должно работать на нескольких устройствах. Представь, что ты вычеркиваешь товары, которые
покупаешь прямо в магазине, а твой друг добавляет новые, находясь дома. Следовательно,
нужно эффективно обновлять список. Как сконструировать сами списки и как оно будет
выглядеть, решать тебе. В приложении обязательно должна быть возможность "вычеркивать"
элементы списка покупок.

### 1.3 Запросы

Необходимо выполнять HTTPS POST-запросы на наш сервер. Параметры запроса передаются
в заголовке. Тип запросов GET также поддерживается, чтобы было проще понять принцип их
работы (к примеру, в поисковой строке браузера).
Если у тебя есть дополнительные вопросы или требуется дополнительная информация, пожалуйста,
свяжись с нами.


```
Адрес запросов: https://cyberprot.ru/shopping/v1/
Методы:
Создать тестовый ключ - CreateTestKey?
Аутентификация по ключу - Authentication?
Создать список покупок - CreateShoppingList?
Удалить список покупок - RemoveShoppingList?
Добавить товар в список - AddToShoppingList?
Вычеркнуть товар из списка - CrossItOff?
Получить перечень списков - GetAllMyShopLists?
Загрузить конкретный список - GetShoppingList?
```
1.3.1 Запрос с ключами

Создать тестовый ключ - CreateTestKey? без параметров, возвращает 6-ти значный
ключ.Аутентификация по ключу - Authentication?добавляется параметрkey=#XXXXXX#.
Пример запросов: https://cyberprot.ru/shopping/v1/CreateTestKey? (ответ "92EGHS") и
https://cyberprot.ru/shopping/v1/Authentication?key=92EGHS
Ответ: json cтрока вида{"success":true}

1.3.2 Создать/удалить список и добавить/убрать элемент

Создать список покупок - CreateShoppingList? параметрыkeyиname,тестовый ключ и
название списка, соответственно. Пример запроса:https://cyberprot.ru/shopping/v1/
CreateShoppingList?key=92EGHS&name=Shopping%20with%20bestie. Ответ сервера: json строка
вида{success":true,"list_id":4}. list_id - id списка, созданный на сервере, по этому id
можно взаимодествовать со списком.

Удалить список покупок - RemoveShoppingList? параметр list_id - это id списка который
мы хотим удалить или восстановить. Пример ответа: {"success":true,"new_value":true}.
new_value= true - список удален, false - список восстановлен.

Добавить товар в список - AddToShoppingList? и Вычеркнуть товар из списка -
CrossItOff?- в обоих случаях должен присутствовать параметрidномер списка в котором мы
зачеркиваем элемент или добавляем новый. В случае с добавлением элемента нужно передать
в параметрах названиеnameи значение количества таких элементовvalue. Примеры запроса:


https://cyberprot.ru/shopping/v1/AddToShoppingList?id=4&value=tools&n=
Ответ сервера:{"success":true,"item_id":8}, гдеitem_id- id предмета внутри списка покупок.
https://cyberprot.ru/shopping/v1/RemoveShoppingList?list_id=
Ответ сервера:{"success":true,"new_value":false}

1.3.3 Загрузить списки покупок и их списки элементов

Получить перечень списков - GetAllMyShopLists?, параметрkey- ключ пользователя.
Пример запроса:https://cyberprot.ru/shopping/v1/GetAllMyShopLists?key=92EGHS, ответ:
{"shop_list":[{"created":"2023-08-28 06:35:44","name":"Test1","id":2},
{"created":"2023-08-28 07:08:53","name":"My favorite list","id":3},
{"created":"2023-08-28 07:09:39","name":"Shopping with bestie","id":4}],"success":true}

Загрузить конкретный список - GetShoppingList? в параметре передается id нужного
списка.
Пример запроса:https://cyberprot.ru/shopping/v1/GetShoppingList?list_id=
Пример ответа сервера:
{"success":true,"item_list":[{"created":"2","name":"steel armor","id":3},
{"created":"1","name":"huge hammer","id":4},{"created":"1","name":"sharp spear","id":5},
{"created":"17","name":"bear traps","id":6},{"created":"40","name":"barrels of
explosives","id":7},{"created":"5","name":"thieves tools","id":8}]}

Если нашел ошибку, необходимо добавить новый запрос на сервер или изменить те, что есть,
можешь написать на почту:
galaev@team.usafe.ru

### 1.4 API v

Добавился новый методRemoveFromList с параметрами list_id иitem_id и теперь метод
GetShoppingListвозвращает внутриjson - boolпеременнуюis_crossed. Т.е. нужно будет
обращаться к ...shopping/v2/....

