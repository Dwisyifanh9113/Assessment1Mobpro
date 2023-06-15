package org.d3if3135.mobpro1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3135.mobpro1.model.NamaOrang
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "Dwisyifanh9113/Assessment1Mobpro/static_api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NamaOrangApiService {
    @GET("static_api.json")
    suspend fun getNamaOrang(): List<NamaOrang>
}

object NamaOrangApi {
    val service: NamaOrangApiService by lazy {
        retrofit.create(NamaOrangApiService::class.java)
    }

    enum class ApiStatus { LOADING, SUCCESS, FAILED }

    fun getNamaOrangUrl(gambar: String): String {
        return "$BASE_URL$gambar.jpeg"
    }
}