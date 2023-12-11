package ru.unisafe.data.auth

import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.http.Query
import ru.unisafe.data.network.AUTHENTICATION
import ru.unisafe.data.network.CREATE_TEST_KEY

interface AuthAPI {


    /**
     * Параметров не принимает
     *
     * https://cyberprot.ru/shopping/v2/CreateTestKey
     *
     * Возвращает 6-ти значный ключ, например: 92EGHS
     *
     * Content-Type: text/plain
     */
    @POST(CREATE_TEST_KEY)
    suspend fun createTestKey(): ResponseBody

    /**
     *
     * Принимает ключ в параметрах
     *
     * https://cyberprot.ru/shopping/v2/Authentication?key=92EGHS
     *
     * Если был передан верный ключ, возвращает json:
     *
     *      {"success":true}
     *
     * https://cyberprot.ru/shopping/v2/Authentication?key=j439F%23
     *
     * При передаче ложного ключа возвращает ошибку
     *
     * 406 Not Acceptable,
     *
     * которая, тем не менее, никак не связана с реальной ошибкой 406,
     * а лишь сигнализирует о неверном ключе
     * и json с описанием ошибки, например:
     *
     *      {
     *          "success":false,
     *          "error":"92EGSZ key do not exist!"
     *      }
     *
     * Content-Type: application/json
     * @exception retrofit2.HttpException
     */
    @POST(AUTHENTICATION)
    suspend fun authentication(@Query("key") key: String)



}