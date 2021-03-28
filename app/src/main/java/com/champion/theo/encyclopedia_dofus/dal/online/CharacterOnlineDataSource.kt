package com.champion.theo.encyclopedia_dofus.dal.online

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.champion.theo.encyclopedia_dofus.dal.online.responses.CharacterResponseItem
import com.champion.theo.encyclopedia_dofus.dal.online.responses.MonsterResponseItem
import com.champion.theo.encyclopedia_dofus.dal.online.services.RetrofitApiService
import com.champion.theo.encyclopedia_dofus.dal.utils.toCharacter
import com.champion.theo.encyclopedia_dofus.dal.utils.toMonster
import com.champion.theo.encyclopedia_dofus.models.Character
import com.champion.theo.encyclopedia_dofus.models.Monster
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterOnlineDataSource {
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

    fun getCharacters(): LiveData<List<Character>> {
        val _data = MutableLiveData<List<Character>> ()

        val characterList = service.getCharacters().execute().body() ?: ArrayList<CharacterResponseItem>()

        val characters = characterList.map {
            it.toCharacter()
        }

        _data.postValue(characters)
        return _data
    }
    companion object {
        private const val apiUrl = "https://fr.dofus.dofapi.fr"
    }
}