package ru.unisafe.retrofit.shopping_lists

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.unisafe.retrofit.CREATE_SHOPPING_LIST
import ru.unisafe.retrofit.GET_ALL_SHOPPING_LISTS
import ru.unisafe.retrofit.REMOVE_SHOPPING_LIST
import ru.unisafe.retrofit.shopping_lists.entities.CreateShoppingListResponse
import ru.unisafe.retrofit.shopping_lists.entities.GetAllShoppingListsResponse

interface ShoppingListsAPI {

    /**
     * Принимает в параметрах ключ и название списка, название может быть пустым
     *
     * https://cyberprot.ru/shopping/v2/CreateShoppingList?key=92EGHS&name=test
     *
     * При указании верного ключа, создаёт список, и возвращает его id в теле json, например:
     *
     *      {
     *          "success":true,
     *          "list_id":850
     *      }
     *
     * Новый список иногда может быть совсем даже не новым,
     * после создания, следует проверить, пустой ли он
     *
     * https://cyberprot.ru/shopping/v2/CreateShoppingList?key=j439F%23&name=TTT
     *
     * При указании ложного ключа, возвращает ошибку 406 Not Acceptable,
     *
     * а так же тело запроса с сообщением об ошибке:
     *
     *      {
     *          "success":false,
     *          "error":"j439F# key do not exist!"
     *      }
     *
     * Content-Type: application/json
     * @exception retrofit2.HttpException
     */
    @POST(CREATE_SHOPPING_LIST)
    suspend fun createShoppingList(
        @Query("key") key: String,
        @Query("name") name: String
    ): CreateShoppingListResponse

    /**
     * Принимает id списка в аргументах
     *
     * https://cyberprot.ru/shopping/v2/RemoveShoppingList?list_id=852
     *
     * При передаче id существующего списка, скрывает его, и возвращает json:
     *
     *      {
     *          "success":true,
     *          "new_value":true
     *      }
     *
     * https://cyberprot.ru/shopping/v2/RemoveShoppingList?list_id=852
     *
     * При передаче id уже скрытого списка, восстанавливает его и возвращает json:
     *
     *      {
     *          "success":true,
     *          "new_value":false
     *      }
     *
     * https://cyberprot.ru/shopping/v2/RemoveShoppingList?list_id=5433
     *
     * При передаче id несуществующего списка, всегда возвращает json:
     *
     *      {
     *          "success":true,
     *          "new_value":false
     *      }
     *
     * Content-Type: application/json
     */
    @POST(REMOVE_SHOPPING_LIST)
    suspend fun removeShoppingList(@Query("list_id") listId: Int): ResponseBody

    /**
     * Принимает в аргументах ключ, по которому ищутся списки
     *
     * https://cyberprot.ru/shopping/v2/GetAllMyShopLists?key=92EGHS
     *
     * При передаче правильного ключа возвращает json с массивом списков:
     *
     *      {
     *          "shop_list":[
     *              {
     *                  "created":"2023-08-28 06:35:44",
     *                  "name":"Test1",
     *                  "id":2
     *              },
     *              ...
     *              {
     *                  "created":"2023-12-11 13:59:40",
     *                  "name":"",
     *                  "id":851
     *              }
     *          ],
     *          "success":true
     *      }
     *
     * https://cyberprot.ru/shopping/v2/GetAllMyShopLists?key=j439F%23
     *
     * При передаче ложного ключа возвращает пустой массив json:
     *
     *      {
     *          "shop_list":[],
     *          "success":true
     *      }
     *
     * Content-Type: application/json
     */
    @GET(GET_ALL_SHOPPING_LISTS)
    suspend fun getAllShoppingLists(@Query("key") key: String): GetAllShoppingListsResponse

}