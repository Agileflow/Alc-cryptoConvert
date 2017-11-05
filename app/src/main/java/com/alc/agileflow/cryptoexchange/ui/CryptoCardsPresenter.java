package com.alc.agileflow.cryptoexchange.ui;

import com.alc.agileflow.cryptoexchange.remote.models.CurrencyExchange;
import com.alc.agileflow.cryptoexchange.remote.models.ResponseData;
import com.alc.agileflow.cryptoexchange.service.CryptoCompareService;
import com.alc.agileflow.cryptoexchange.utils.Currencies;

import java.util.List;

/**
 * Created by dell pc on 11/4/2017.
 */

public class CryptoCardsPresenter implements CrytoCardsContract.UserActionListener{

    private CrytoCardsContract.View cardsView;
    private String from;

    CryptoCardsPresenter(CrytoCardsContract.View cardsView){
        this.cardsView = cardsView;
    }

    @Override
    public void getExchangeCard(String from, String to) {
        this.from = from;
        if(cardsView.checkNetworkConnection())
            CryptoCompareService.getExchangeRate(from,to,loadedListener);
        else
            cardsView.showErrorMessage("Check internet connection!");
    }

    @Override
    public void showExchangeConverter(CurrencyExchange currencyExchange) {
        cardsView.openExchangeConverter(currencyExchange);
    }

    private CryptoCompareService.onCryptoExchangeRatesLoadedListener loadedListener = new CryptoCompareService.onCryptoExchangeRatesLoadedListener() {
        @Override
        public void onCryptoExchangeRatesLoaded(List<ResponseData.ExchangeDisplay> cryptosRate) {
            if (from.equalsIgnoreCase(Currencies.BITCOIN))
                cardsView.setExchangeCards(cryptosRate.get(0).getBTCRate().getcurrencyRate());
            else if(from.equalsIgnoreCase(Currencies.ETHEREUM))
                cardsView.setExchangeCards(cryptosRate.get(0).getETHRate().getcurrencyRate());
        }

        @Override
        public void onCryptoExchangeRatesLoadFailure(String err) {
            cardsView.showErrorMessage(err);
        }
    };
}
