package com.bignerdranch.android.cryptoapp.adapters

import com.bignerdranch.android.cryptoapp.pojo.CoinPriceInfo

interface OnCoinClickListener {
    fun onCoinClick(coinPriceInfo: CoinPriceInfo)
}