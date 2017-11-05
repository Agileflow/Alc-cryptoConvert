package com.alc.agileflow.cryptoexchange.service;

import android.util.Log;

import com.alc.agileflow.cryptoexchange.remote.CryptoCompareDataAPI;
import com.alc.agileflow.cryptoexchange.remote.models.ResponseData;
import com.alc.agileflow.cryptoexchange.utils.Parameter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell pc on 11/2/2017.
 */

public class CryptoCompareService {

    public interface onCryptoExchangeRatesLoadedListener {
        void onCryptoExchangeRatesLoaded(List<ResponseData.ExchangeDisplay> cryptosRate);

        void onCryptoExchangeRatesLoadFailure(String err);
    }

    public static void getExchangeRate(String from, String to, final onCryptoExchangeRatesLoadedListener exchangeRatesLoadedListener) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Parameter.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CryptoCompareDataAPI api = retrofit.create(CryptoCompareDataAPI.class);

        Call<ResponseData> call = api.getExchangeRate(from,to);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                exchangeRatesLoadedListener.onCryptoExchangeRatesLoaded(response.body().getCryptosRate());
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                exchangeRatesLoadedListener.onCryptoExchangeRatesLoadFailure(t.getMessage());
            }
        });
    }
}