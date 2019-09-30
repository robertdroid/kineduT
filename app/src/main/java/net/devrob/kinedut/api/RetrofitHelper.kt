package net.devrob.kinedut.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import net.devrob.kinedut.api.response.ActivitiesResponse
import net.devrob.kinedut.api.response.ArticleReponse
import net.devrob.kinedut.commons.Const
import net.devrob.kinedut.commons.Const.BASE_URL
import net.devrob.kinedut.commons.DataSession
import net.devrob.kinedut.models.RequestResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private const val CONNECTION_TIMEOUT:Long = 10000
private const val DATA_RETRIEVAL_TIMEOUT:Long = 30000

object RetrofitHelper {
    private val client = OkHttpClient.Builder()
        .callTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(DATA_RETRIEVAL_TIMEOUT, TimeUnit.MILLISECONDS)
        .build()

    private val gsonBuilder = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
        .build()

    private val restClient = retrofit.create(RestClient::class.java)

    suspend fun getActivities(skillId: Int): RequestResponse<ActivitiesResponse> {
        val call = restClient.getActivities(
            String.format(Const.TOKEN_VALUE, DataSession.sessionToken),
            skillId,
            DataSession.sessionBabyId)
        return performRequest(call)
    }

    suspend fun getArticles(skillId: Int): RequestResponse<ArticleReponse> {
        val call = restClient.getArticles(
            String.format(Const.TOKEN_VALUE, DataSession.sessionToken),
            skillId,
            DataSession.sessionBabyId)
        return performRequest(call)
    }
    
    private suspend fun <T>performRequest(call: Call<T>) = suspendCoroutine<RequestResponse<T>> { continuation ->
        val result = RequestResponse<T>()
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                result.statusCode = response.code()
                result.objeto = response.body()
                continuation.resume(result)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                result.throwable = t
                result.isError = true
                continuation.resume(result)
            }
        })
    }
}