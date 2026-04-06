package com.example.valyutakursi.data

import com.example.valyutakursi.model.CurrencyRate
import retrofit2.http.GET

interface ApiService {
    @GET("uz/arkhiv-kursov-valyut/json/")
    suspend fun getCurrency(): List<CurrencyRate>
}
