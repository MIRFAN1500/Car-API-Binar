package com.example.networkingwithretrofitchapter5.network

import com.example.networkingwithretrofitchapter5.model.GetAllCarResponseItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
private const val BASE_URL = "https://rent-cars-api.herokuapp.com/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface CarsApiService{
    //GET DIGUNAKAN UNTUK MEMANGGIL SEMUA DATA YANG TERDAPAT PADA SERVER
    @GET("admin/car")
    fun AllCar():Call<List<GetAllCarResponseItem>>
}
object CarsApi{
    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val instance: CarsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(CarsApiService::class.java)
    }

}