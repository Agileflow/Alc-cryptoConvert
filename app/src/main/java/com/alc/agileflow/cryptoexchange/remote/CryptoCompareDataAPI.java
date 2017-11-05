package com.alc.agileflow.cryptoexchange.remote;


import com.alc.agileflow.cryptoexchange.remote.models.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dell pc on 11/2/2017.
 */

public interface CryptoCompareDataAPI {

    @GET("/data/pricemultifull")
    Call<ResponseData> getExchangeRate(@Query("fsyms") String from, @Query("tsyms") String to);
}
