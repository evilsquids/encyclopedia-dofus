package com.champion.theo.encyclopedia_dofus.dal.online.services

import com.champion.theo.encyclopedia_dofus.dal.online.responses.MonsterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    @GET("monsters")
    fun getMonsters(@Query("type") type: String): Call<MonsterResponse>
}
