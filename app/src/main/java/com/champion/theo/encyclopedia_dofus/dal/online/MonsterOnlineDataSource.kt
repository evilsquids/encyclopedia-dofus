package com.champion.theo.encyclopedia_dofus.dal.online

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.champion.theo.encyclopedia_dofus.dal.online.responses.MonsterResponseItem
import com.champion.theo.encyclopedia_dofus.dal.online.services.RetrofitApiService
import com.champion.theo.encyclopedia_dofus.dal.utils.toMonster
import com.champion.theo.encyclopedia_dofus.models.Monster
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MonsterOnlineDataSource {
    private val service: RetrofitApiService

    init {
        val retrofit = buildClient()
        // Init the api service with retrofit
        service = retrofit.create(RetrofitApiService::class.java)
    }

    private fun buildClient(): Retrofit {
        val httpClient = OkHttpClient.Builder().apply {
            addLogInterceptor(this)
            addApiInterceptor(this)
        }.build()
        return Retrofit
                .Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
    }

    private fun addLogInterceptor(builder: OkHttpClient.Builder) {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addNetworkInterceptor(httpLoggingInterceptor)
    }

    private fun addApiInterceptor(builder: OkHttpClient.Builder) {
        builder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                        //.addQueryParameter("apiKey", apiKey)
                        .build()

                val requestBuilder = original.newBuilder()
                        .url(url)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })
    }

    fun getMonsters(type: String): LiveData<List<Monster>> {
        val _data = MutableLiveData<List<Monster>> ()

        val monsterList = service.getMonsters(type).execute().body() ?: ArrayList<MonsterResponseItem>()

        // TODO Convertir la liste des articles du modèle du web service vers le modèle métier ArcicleItem --> Article

        val articles = monsterList.map {
            it.toMonster()
        }

        _data.postValue(articles)
        return _data
    }
    companion object {
        private const val apiUrl = "https://api.dofus.championtheo.fr/api/monsters"
    }
}