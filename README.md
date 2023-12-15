# Ссылка на release и debug версии apk подписанные debug ключом
https://1drv.ms/f/s!AteblQhF6znLgrsLdUZPaQNrkV4bSw?e=7ApYGJ

Для проверки, на экране ввода ключа, нажмите далее при пустом поле ввода, чтобы получить ключ по умолчанию, на котором уже имеются заполненные списки товаров.

# Описание работы приложения 
При отсутствии сети, или недоступности сервера, к которому обращается приложение, 
доступен вход при помощи ключа по умолчанию, 
все изменения, внесённые в этот период в локальную базу данных,
будут утеряны, при успешной попытке соединения.

## Интерфейс

### Запуск
При первом запусе открывается окно авторизации,
требующее API ключ, здесь же можно запросить новый ключ с сервера,
или показать ключ по умолчанию, нажав далее 
(стрелка на краю поля ввода),
при пустом поле ввода, 
если введён  верный ключ, нажатие далее откроет экран со всеми списками покупок созданных по этому ключу.

### Экран списков покупок
Заголовок экрана содежит ключ, по которому мы вошли, кнопку создания нового  списка, открывающая диалог с вводом имени списка,
кнопку запрашивающую изменения с сервера, и кнопку выхода на экран авторизации

Каждый элемент списка содежит своё имя, id, и дату создания,
нажатие на элемент списка открывает экран отображения данного списка,
при нажатии и удеживании на элементе, он выделяется,
попутно включая режим выделения,
режим сохраняется до тех пор, пока выделен хотя бы 1 элемент,
в этом режиме элемент может быть выделен обычным нажатием,
а заголовок приобретает новый вид, место предыдущих кнопок занимают кнопка удаления выделенных списков,
и кнопка выделения всех списков.

### Экран списка товаров
Этот список обновляется автоматически каждые 5 секунд, и если в список будут внесены изменения с другого устройства,
то они отобразятся и на нашем.

Заголовок экрана содержит имя списка, а так же кнопку добавления нового товара, 
открывающая диалог с вводом названия товара и его количества,
и кнопку показа/сокрытия зачёркнутых товаров.

Элемент списка содержит название товара, его количество и id,
а так же кнопку вычёркивающую/восстанавливающую товар.

В режиме показа зачёркнутых товаров, они отображаются полупрозрачными.

При длительном удерживании на элементе, он выделяется и включает режим выделения,
теперь для выделения достаточно обычного нажатия,
в заголовке экрана появляются кнопки выделения всех элементов, и кнопки удаления
и вычёркивания/восстановления выделенных.

Если во время просмотра списка произошла ошибка соединения, 
то в заголовке, вне режима выделения, появляется кнопка, запускающая цикл синхронизации заново.

## Реализация

Приложение состоит из 5 модулей, 3 feature модуля, data модуль, и само приложение.

Все модули, помимо основного, не зависят от  других модулей,
контроль навигации между фичами осуществлён в основном модуле,
он же связывает feature модули с data модулем.

Экраны получают данные из базы данных SQLite, реализованную с помощью Room,
а сама база данных, при помощи Retrofit, запрашивает все необходимое с сервера,
если есть связь с ним,
иначе, предоставляет последние запрошенные данные, либо данные по умолчанию,
которые поставляются в инициализирующей базе данных из ресурсов data модуля.

Если изменения были внесены в оффлайн режиме, то при подключении, все они перетираются.

# Unisafe_test
Тестовое задание из вакансии на джуна в компании UniSafe

# Указания к выполнению:
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


