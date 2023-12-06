package ru.unisafe.directaccessinterface

import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

const val URL = "https://cyberprot.ru/shopping/v2/"


const val CREATE_TEST_KEY = "CreateTestKey"

const val AUTHENTICATION = "Authentication"

const val CREATE_SHOPPING_LIST = "CreateShoppingList"

const val REMOVE_SHOPPING_LIST = "RemoveShoppingList"

const val GET_ALL_SHOPPING_LISTS = "GetAllMyShopLists"

const val GET_SHOPPING_LIST = "GetShoppingList"

const val ADD_ITEM_TO_SHOPPING_LIST = "AddToShoppingList"

const val REMOVE_ITEM_FROM_LIST = "RemoveFromList"

const val CROSS_ITEM_OFF = "CrossItOff"

data class Result(
    val success: Boolean
)

interface AuthAPI {

    @POST(CREATE_TEST_KEY)
    suspend fun createTestKey(): ResponseBody

    @POST(AUTHENTICATION)
    suspend fun authentication(@Query("key") key: String): Result

}

interface ShoppingListAPI {

    @POST(CREATE_SHOPPING_LIST)
    suspend fun createShoppingList(
        @Query("key") key: String,
        @Query("name") name: String
    ): ResponseBody

    @POST(REMOVE_SHOPPING_LIST)
    suspend fun removeShoppingList(@Query("list_id") listId: Int): ResponseBody

    @GET(GET_ALL_SHOPPING_LISTS)
    suspend fun getAllShoppingLists(@Query("key") key: String): ResponseBody

    @GET(GET_SHOPPING_LIST)
    suspend fun getShoppingList(@Query("list_id") listId: Int): ResponseBody

}

interface ItemApi {

    @POST(ADD_ITEM_TO_SHOPPING_LIST)
    suspend fun addItemToShoppingList(
        @Query("id") listId: Int,
        @Query("value") itemName: String,
        @Query("n") quantityOfItem: Int
    ): ResponseBody

    @POST(REMOVE_ITEM_FROM_LIST)
    suspend fun removeItemFromList(
        @Query("list_id") listId: Int,
        @Query("item_id") itemId: Int
    ): ResponseBody

    @POST(CROSS_ITEM_OFF)
    suspend fun crossItemOff(@Query("id") itemId: Int): ResponseBody

}

fun main() = runBlocking {

    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    val moshi = Moshi.Builder().build()

    val moshiConverterFactory = MoshiConverterFactory.create(moshi)

    val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(client)
        .addConverterFactory(moshiConverterFactory)
        .build()

    val authAPI = retrofit.create(AuthAPI::class.java)
    val shoppingListAPI = retrofit.create(ShoppingListAPI::class.java)
    val itemAPI = retrofit.create(ItemApi::class.java)

    val test = itemAPI.removeItemFromList(4, 1207).string()

    println(test)
}
// https://cyberprot.ru/shopping/v2/Authentication?key=92EGHS
// https://cyberprot.ru/shopping/v2/GetShoppingList?list_id=4