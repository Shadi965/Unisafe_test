package ru.unisafe.data.retrofit.products

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.unisafe.data.retrofit.ADD_ITEM_TO_SHOPPING_LIST
import ru.unisafe.data.retrofit.CROSS_ITEM_OFF
import ru.unisafe.data.retrofit.GET_SHOPPING_LIST
import ru.unisafe.data.retrofit.REMOVE_ITEM_FROM_LIST

interface ProductsAPI {

    /**
     * Принимает id списка
     *
     * https://cyberprot.ru/shopping/v2/GetShoppingList?list_id=4
     *
     * При указании id существующего списка вернёт массив json:
     *
     *      {
     *          "success":true,
     *          "item_list":[
     *              {
     *                  "created":"2",
     *                  "name":"steel armor",
     *                  "is_crossed":true,
     *                  "id":3
     *              },
     *              ...
     *              {
     *                  "created":"12",
     *                  "name":"test",
     *                  "is_crossed":false,
     *                  "id":1212
     *              }
     *          ]
     *      }
     *
     * https://cyberprot.ru/shopping/v2/GetShoppingList?list_id=5232
     *
     * При указании несуществующего списка вернёт пустой массив json:
     *
     *      {
     *          "success":true,
     *          "item_list":[]
     *      }
     *
     * Content-Type: application/json
     */
    @GET(GET_SHOPPING_LIST)
    suspend fun getShoppingList(@Query("list_id") listId: Int): ResponseBody

    /**
     * Принимает id списка, имя товара, и количество товара
     *
     * https://cyberprot.ru/shopping/v2/AddToShoppingList?id=4&value=test&n=4
     *
     * При передаче id существующего списка, добавит в него товар, и вернёт json:
     *
     *      {
     *          "success":true,
     *          "item_id":1234
     *      }
     *
     * https://cyberprot.ru/shopping/v2/AddToShoppingList?id=9239&value=test&n=4
     *
     * При передаче id несуществующего списка, создаст его, и добавит товар в этот список
     * список при это не будет привязан ни к какому ключу, и при выполнении команды создания списка,
     * может быть привязан к ключу как новый.
     * Вернёт json:
     *
     *      {
     *          "success":true,
     *          "item_id":1235
     *      }
     *
     *
     * Content-Type: application/json
     */
    @POST(ADD_ITEM_TO_SHOPPING_LIST)
    suspend fun addItemToShoppingList(
        @Query("id") listId: Int,
        @Query("value") itemName: String,
        @Query("n") quantityOfItem: Int
    ): ResponseBody

    /**
     * Принимает id списка и id товара
     * При чём id списка ни на что не влияет, и может быть указано любое значение INT
     *
     * https://cyberprot.ru/shopping/v2/RemoveFromList?list_id=4&item_id=1212
     *
     * При указании id существующего товара, удалит товар и вернёт json:
     *
     *      {"success":true}
     *
     * ВАЖНО! Независимо от того какой id списка указан, данный довар будет удалён из всех списков!
     *
     * https://cyberprot.ru/shopping/v2/RemoveFromList?list_id=651&item_id=1212
     *
     * https://cyberprot.ru/shopping/v2/RemoveFromList?list_id=91203&item_id=92345
     *
     * При указании уже удалённого или несуществующего товара, ничего не сделает, и вернёт то же самое:
     *
     *      {"success":true}
     *
     * Так как возвращаемый результат одинаков в при любом исходе, следует вручную
     * проверить список на отсутствие данного продукта в нём
     *
     * Content-Type: application/json
     */
    @POST(REMOVE_ITEM_FROM_LIST)
    suspend fun removeItemFromList(
        @Query("list_id") listId: Int,
        @Query("item_id") itemId: Int
    ): ResponseBody

    /**
     * Принимает id продукта
     *
     * https://cyberprot.ru/shopping/v2/CrossItOff?id=1204
     * 
     * При указании id существующего продукта, зачеркнёт его и вернёт json:
     *
     *      {
     *          "success":true,
     *          "rows_affected":1
     *      }
     *
     * https://cyberprot.ru/shopping/v2/CrossItOff?id=1204
     *
     * При указании id уже зачёркнутого продукта, откатит зачёркивание и вернёт такой же json:
     *
     *      {
     *          "success":true,
     *          "rows_affected":1
     *      }
     *
     * https://cyberprot.ru/shopping/v2/CrossItOff?id=5
     *
     * https://cyberprot.ru/shopping/v2/CrossItOff?id=8432
     *
     * При указании id уже удалённого или несуществующего продукта,
     * не сделает ничего, вернёт json:
     *
     *      {
     *          "success":true,
     *          "rows_affected":0
     *      }
     *
     * Content-Type: application/json
     */
    @POST(CROSS_ITEM_OFF)
    suspend fun crossItemOff(@Query("id") itemId: Int): ResponseBody

}