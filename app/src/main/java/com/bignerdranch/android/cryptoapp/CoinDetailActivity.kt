package com.bignerdranch.android.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.cryptoapp.databinding.ActivityCoinDetailBinding
import com.bignerdranch.android.cryptoapp.viewModel.CoinViewModel
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinDetailBinding

    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this).get(CoinViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)

        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                binding.tvPrice.text = it.price.toString()
                binding.tvMinPrice.text = it.lowday.toString()
                binding.tvMaxPrice.text = it.highday.toString()
                binding.tvLastMarket.text = it.lastmarket
                binding.tvLastUpdate.text = it.getFormattedTime()
                binding.tvFromSymbol.text = it.fromsymbol
                binding.tvToSymbol.text = it.tosymbol
                Picasso.get().load(it.getFullImageUrl()).into(binding.ivLogoCoin)


            })
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }


    }
}