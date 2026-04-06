package com.example.valyutakursi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.valyutakursi.model.CurrencyRate
import com.example.valyutakursi.data.RetrofitInstance
import com.example.valyutakursiapp.R
import kotlinx.coroutines.launch

class ConvertActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var etAmount: EditText
    private lateinit var otherEt: EditText
    private lateinit var btnConvert: Button
    private lateinit var tvResult: TextView

    private var currencyList: List<CurrencyRate> = listOf()

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        spinner = findViewById(R.id.spinner)
        etAmount = findViewById(R.id.etAmount)
        otherEt = findViewById(R.id.otherEt)
        btnConvert = findViewById(R.id.btnConvert)
        tvResult = findViewById(R.id.tvResult)

        etAmount.addTextChangedListener {
            convert()
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                convert()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        etAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convert()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        loadCurrencies()
    }

    private fun loadCurrencies() {
        lifecycleScope.launch {
            try {
                currencyList = RetrofitInstance.api.getCurrency()

                val names = currencyList.map { it.Ccy }
                val adapter = ArrayAdapter(
                    this@ConvertActivity,
                    android.R.layout.simple_spinner_item,
                    names
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter

            } catch (e: Exception) {
                Toast.makeText(this@ConvertActivity, "Xatolik: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        btnConvert.setOnClickListener {
            convert()
        }
    }

    private fun convert() {
        val amount = etAmount.text.toString().toDoubleOrNull()

        if (amount == null) {
            Toast.makeText(this, "Miqdor kiriting!", Toast.LENGTH_SHORT).show()
            return
        }

        val position = spinner.selectedItemPosition
        if (position >= 0 && position < currencyList.size) {
            val selectedCurrency = currencyList[position]
            val rate = selectedCurrency.Rate.toDoubleOrNull() ?: 0.0

            // UZS → boshqa valyuta
            val result = amount / rate

            otherEt.setText("%.2f".format(result))
            tvResult.text = "1 ${selectedCurrency.Ccy} = ${selectedCurrency.Rate} UZS"
        }
    }
}
