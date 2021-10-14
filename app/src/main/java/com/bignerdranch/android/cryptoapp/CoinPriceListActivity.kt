package com.bignerdranch.android.cryptoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.cryptoapp.adapters.CoinInfoAdapter
import com.bignerdranch.android.cryptoapp.adapters.OnCoinClickListener
import com.bignerdranch.android.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.bignerdranch.android.cryptoapp.pojo.CoinPriceInfo
import com.bignerdranch.android.cryptoapp.viewModel.CoinViewModel


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinPriceListBinding

    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this).get(CoinViewModel::class.java)
    }

    var adapter = CoinInfoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter.onCoinClickListener = object : OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromsymbol)
                startActivity(intent)
            }
        }


        binding.rvCoinPriceList.adapter = adapter
        viewModel.priceList.observe(this, Observer { list ->
            adapter.coinInfoList = list
        })
    }
}
