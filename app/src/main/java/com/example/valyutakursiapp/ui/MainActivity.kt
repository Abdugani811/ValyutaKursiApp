package com.example.valyutakursi.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valyutakursi.model.CurrencyRate
import com.example.valyutakursiapp.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var date: TextView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // API dan ma’lumot olish
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cbu.uz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(CurrencyApi::class.java)
        api.getCurrency().enqueue(object : Callback<List<CurrencyRate>> {
            override fun onResponse(
                call: Call<List<CurrencyRate>>,
                response: Response<List<CurrencyRate>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val list = response.body()!!

                    val apiDate = list[0].Date
                    binding.date.text = apiDate

                    val adapter = CurrencyAdapter(list) { currency ->
                        val intent = Intent(this@MainActivity, ConvertActivity::class.java)
                        intent.putExtra("currency", currency.Ccy)
                        intent.putExtra("rate", currency.Rate.toString())
                        startActivity(intent)
                    }
                    binding.recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "Ma'lumot topilmadi", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<CurrencyRate>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Xatolik: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}

// Retrofit API interfeysi
interface CurrencyApi {
    @GET("uz/arkhiv-kursov-valyut/json/")
    fun getCurrency(): Call<List<CurrencyRate>>
}