package com.bignerdranch.android.cryptoapp.api

import com.bignerdranch.android.cryptoapp.pojo.CoinInfoListOfData
import com.bignerdranch.android.cryptoapp.pojo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Тут хранятся все методы по работе с сетью
 */
interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "a3216030cb740ea6bdfe9418df01dbf8c8aeff2dd7886939b72de4df59f84148",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY

    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "a3216030cb740ea6bdfe9418df01dbf8c8aeff2dd7886939b72de4df59f84148",
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String
    ): Single<CoinPriceInfoRawData>

    companion object{
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val CURRENCY = "USD"
    }
}