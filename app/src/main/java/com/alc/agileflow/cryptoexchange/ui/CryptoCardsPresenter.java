package com.alc.agileflow.cryptoexchange.ui;

import com.alc.agileflow.cryptoexchange.remote.models.CurrencyExchange;
import com.alc.agileflow.cryptoexchange.remote.models.ResponseData;
import com.alc.agileflow.cryptoexchange.service.CryptoCompareService;
import com.alc.agileflow.cryptoexchange.utils.Currencies;

/**
 * Created by dell pc on 11/4/2017.
 */

public class CryptoCardsPresenter implements CrytoCardsContract.UserActionListener{

    private CrytoCardsContract.View cardsView;

    CryptoCardsPresenter(CrytoCardsContract.View cardsView){
        this.cardsView = cardsView;
    }

    @Override
    public void getExchangeData(String from, String to) {
        if(cardsView.checkNetworkConnection())
            CryptoCompareService.getExchangeRate(from,to,loadedListener);
        else
            cardsView.showErrorMessage("Check Internet Connectivity!");
    }

    @Override
    public void showExchangeConverter(CurrencyExchange currencyExchange) {
        cardsView.openExchangeConverter(currencyExchange);
    }

    private CryptoCompareService.OnCryptoExchangeRatesLoadedListener loadedListener = new CryptoCompareService.OnCryptoExchangeRatesLoadedListener() {
        @Override
        public void onCryptoExchangeRatesLoaded(ResponseData.ExchangeDisplay cryptosRate) {
            if (cryptosRate.getBTCRate().getCurrencyRate() != null)
                cardsView.setExchangeRateData(Currencies.BITCOIN, cryptosRate.getBTCRate().getCurrencyRate());
            if(cryptosRate.getETHRate().getCurrencyRate() != null)
                cardsView.setExchangeRateData(Currencies.ETHEREUM, cryptosRate.getETHRate().getCurrencyRate());
        }

        @Override
        public void onCryptoExchangeRatesLoadFailure(String err) {
            cardsView.showErrorMessage(err);
        }
    };
}
