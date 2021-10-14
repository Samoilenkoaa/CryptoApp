package com.bignerdranch.android.cryptoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.cryptoapp.R
import com.bignerdranch.android.cryptoapp.databinding.ItemCoinInfoBinding
import com.bignerdranch.android.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter() : RecyclerView.Adapter<CoinViewHolder>() {


    var coinInfoList = listOf<CoinPriceInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCoinClickListener: OnCoinClickListener? = null

    // 1) ПРИВЯЗКА КАРТОЧКИ К АДАПТЕРУ
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinViewHolder(view)
    }
    /// ВЫЗОВ НАПОЛНЕНИЯ КАРТОЧКИ
    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinInfoList[position]
        holder.bind(coin)

        holder.itemView.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)

        }
    }
    // ЗА ПЕРЕДАЧУ СПИСКА ФИЛЬМОВ (РАЗМЕР СПИСКА КАРТОЧЕК)
    override fun getItemCount() = coinInfoList.size
}

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: ItemCoinInfoBinding = ItemCoinInfoBinding.bind(itemView)


    //метод отвечает за расстоновку полей(элементов) в карточке
    fun bind(coin: CoinPriceInfo) {

        binding.tvSymbols.text = coin.fromsymbol + "/" + coin.tosymbol
        binding.tvPrice.text = coin.price.toString()
        binding.tvLastUpdate.text = "Время последнего обновления: " + coin.getFormattedTime()

        //установка картинки через Picasso
        Picasso.get().load(coin.getFullImageUrl()).into(binding.ivLogoCoin)

    }
}