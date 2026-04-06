package com.example.valyutakursi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valyutakursi.model.CurrencyRate
import com.example.valyutakursiapp.R

class CurrencyAdapter (
    private val items: List<CurrencyRate>,
    private val onClick: (CurrencyRate) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imgFlag: ImageView = view.findViewById(R.id.image)
        val tvName: TextView = view.findViewById(R.id.ccyTv)
        val tvCcyNmUZ: TextView = view.findViewById(R.id.CcyNmUZTv)
        val tvRate: TextView = view.findViewById(R.id.rateTv)
        val tvDiff: TextView = view.findViewById(R.id.diffTv)
    }

    fun getFlagUrl(ccy: String): String {
        return when (ccy) {
            "USD" -> "https://flagsapi.com/US/flat/64.png"
            "EUR" -> "https://flagsapi.com/EU/flat/64.png"
            "RUB" -> "https://flagsapi.com/RU/flat/64.png"
            "GBP" -> "https://flagsapi.com/GB/flat/64.png"
            "JPY" -> "https://flagsapi.com/JP/flat/64.png"
            "AZN" -> "https://flagsapi.com/AZ/shiny/64.png"
            "BDT" -> "https://flagsapi.com/BD/shiny/64.png"
            "BGN" -> "https://flagsapi.com/AZ/shiny/64.png"
            "BHD" -> "https://flagsapi.com/BG/shiny/64.png"
            "BND" -> "https://flagsapi.com/BH/shiny/64.png"
            "BRL" -> "https://flagsapi.com/BN/shiny/64.png"
            "BYN" -> "https://flagsapi.com/BR/shiny/64.png"
            "CAD" -> "https://flagsapi.com/AZ/shiny/64.png"
            "CHF" -> "https://flagsapi.com/BY/shiny/64.png"
            "CNY" -> "https://flagsapi.com/CA/shiny/64.png"
            "CUP" -> "https://flagsapi.com/CC/shiny/64.png"
            "CZK" -> "https://flagsapi.com/CD/shiny/64.png"
            "DKK" -> "https://flagsapi.com/CF/shiny/64.png"
            "EGP" -> "https://flagsapi.com/CG/shiny/64.png"
            "AFN" -> "https://flagsapi.com/CH/shiny/64.png"
            "ARS" -> "https://flagsapi.com/CI/shiny/64.png"
            "GEL" -> "https://flagsapi.com/CK/shiny/64.png"
            "HKD" -> "https://flagsapi.com/CL/shiny/64.png"
            "HUF" -> "https://flagsapi.com/CM/shiny/64.png"
            "IDR" -> "https://flagsapi.com/CN/shiny/64.png"
            "ILS" -> "https://flagsapi.com/CO/shiny/64.png"
            "INR" -> "https://flagsapi.com/CR/shiny/64.png"
            "IQD" -> "https://flagsapi.com/CW/shiny/64.png"
            "IRR" -> "https://flagsapi.com/CU/shiny/64.png"
            "ISK" -> "https://flagsapi.com/CV/shiny/64.png"
            "JOD" -> "https://flagsapi.com/CX/shiny/64.png"
            "AUD" -> "https://flagsapi.com/CY/shiny/64.png"
            "KGS" -> "https://flagsapi.com/CZ/shiny/64.png"
            "KHR" -> "https://flagsapi.com/DE/shiny/64.png"
            "KRW" -> "https://flagsapi.com/DJ/shiny/64.png"
            "KWD" -> "https://flagsapi.com/DK/shiny/64.png"
            "KZT" -> "https://flagsapi.com/DM/shiny/64.png"
            "LAK" -> "https://flagsapi.com/DO/shiny/64.png"
            "LBP" -> "https://flagsapi.com/DZ/shiny/64.png"
            "LYD" -> "https://flagsapi.com/EC/shiny/64.png"
            "MAD" -> "https://flagsapi.com/EE/shiny/64.png"
            "MDL" -> "https://flagsapi.com/EG/shiny/64.png"
            "MMK" -> "https://flagsapi.com/EH/shiny/64.png"
            "MNT" -> "https://flagsapi.com/ER/shiny/64.png"
            "MXN" -> "https://flagsapi.com/AZ/shiny/64.png"
            "MYR" -> "https://flagsapi.com/EG/shiny/64.png"
            "NOK" -> "https://flagsapi.com/AZ/shiny/64.png"
            "NZD" -> "https://flagsapi.com/EG/shiny/64.png"
            "OMR" -> "https://flagsapi.com/EC/shiny/64.png"
            "PHP" -> "https://flagsapi.com/EC/shiny/64.png"
            "PKR" -> "https://flagsapi.com/AZ/shiny/64.png"
            "PLN" -> "https://flagsapi.com/AZ/shiny/64.png"
            "QAR" -> "https://flagsapi.com/EC/shiny/64.png"
            "RON" -> "https://flagsapi.com/AZ/shiny/64.png"
            "RSD" -> "https://flagsapi.com/AZ/shiny/64.png"
            "AMD" -> "https://flagsapi.com/EC/shiny/64.png"
            "SAR" -> "https://flagsapi.com/EG/shiny/64.png"
            "SDG" -> "https://flagsapi.com/AZ/shiny/64.png"
            "SEK" -> "https://flagsapi.com/AZ/shiny/64.png"
            "SGD" -> "https://flagsapi.com/EG/shiny/64.png"
            "SYP" -> "https://flagsapi.com/AZ/shiny/64.png"
            "THB" -> "https://flagsapi.com/AZ/shiny/64.png"
            "TJS" -> "https://flagsapi.com/EG/shiny/64.png"
            "TMT" -> "https://flagsapi.com/GA/shiny/64.png"
            "TND" -> "https://flagsapi.com/GG/shiny/64.png"
            "TRY" -> "https://flagsapi.com/GB/shiny/64.png"
            "UAH" -> "https://flagsapi.com/GA/shiny/64.png"
            "AED" -> "https://flagsapi.com/FR/shiny/64.png"
            "UYU" -> "https://flagsapi.com/FO/shiny/64.png"
            "VES" -> "https://flagsapi.com/FM/shiny/64.png"
            "VND" -> "https://flagsapi.com/FK/shiny/64.png"
            "XDR" -> "https://flagsapi.com/FJ/shiny/64.png"
            "YER" -> "https://flagsapi.com/FI/shiny/64.png"
            else -> "https://flagsapi.com/UN/flat/64.png"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.tvName.text = "${item.Ccy} - ${item.CcyNm_UZ}"
        holder.tvRate.text = item.Rate

        Glide.with(holder.itemView.context)
            .load(getFlagUrl(item.Ccy))
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imgFlag)

        holder.tvName.text = "${item.Ccy}"
        holder.tvCcyNmUZ.text = "${item.CcyNm_UZ}"
        holder.tvRate.text = "${item.Rate} UZS"
        holder.tvDiff.text = "${item.Diff}%"
        holder.itemView.setOnClickListener { onClick(item) }
    }
}
