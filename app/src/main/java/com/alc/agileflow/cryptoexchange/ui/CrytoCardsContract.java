package com.alc.agileflow.cryptoexchange.ui;

import com.alc.agileflow.cryptoexchange.remote.models.CurrencyExchange;
import com.alc.agileflow.cryptoexchange.utils.Currencies;

import java.util.List;
import java.util.Map;

/**
 * Created by dell pc on 11/4/2017.
 */

public class CrytoCardsContract {
    interface View{
        void hideNoCard();

        boolean checkNetworkConnection();

        void showErrorMessage(String err);

        void setExchangeCards(Map<String, CurrencyExchange> currencyExchanges);

        void openExchangeConverter(CurrencyExchange currencyExchange);
    }

    interface UserActionListener{
        void getExchangeCard(String from, String to);

        void showExchangeConverter(CurrencyExchange currencyExchange);
    }
}

