package com.alc.agileflow.cryptoexchange.remote.models;

import com.alc.agileflow.cryptoexchange.utils.Currencies;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell pc on 11/2/2017.
 */

public class ResponseData {

    @SerializedName("DISPLAY")
    private
    ExchangeDisplay ratesDisplay;


    public class ExchangeDisplay implements Serializable{

        @SerializedName(Currencies.BITCOIN)
        private
        BindExchange btcRate;

        @SerializedName(Currencies.ETHEREUM)
        private
        BindExchange ethRate;

        public BindExchange getBTCRate(){ return this.btcRate; }

        public BindExchange getETHRate(){ return this.ethRate; }

        public class BindExchange {

            @SerializedName(Currencies.NAIRA)
            private
            CurrencyExchange naira;

            @SerializedName(Currencies.US_DOLLAR)
            private
            CurrencyExchange usd;

            @SerializedName(Currencies.BRITISH_POUND)
            private
            CurrencyExchange gbp;

            @SerializedName(Currencies.EURO)
            private
            CurrencyExchange euro;

            @SerializedName(Currencies.CANADIAN_DOLLA)
            private
            CurrencyExchange cad;

            @SerializedName(Currencies.AUSTRALIAN_DOLLAR)
            private
            CurrencyExchange aud;

            @SerializedName(Currencies.CHINESE_RENMINBI)
            private
            CurrencyExchange cny;

            @SerializedName(Currencies.CZECH_KORUNA)
            private
            CurrencyExchange czk;

            @SerializedName(Currencies.HONG_KONG_DOLLAR)
            private
            CurrencyExchange hkd;

            @SerializedName(Currencies.INDIAN_RUPEE)
            private
            CurrencyExchange inr;

            @SerializedName(Currencies.ISREALI_NEW_SHEKEL)
            private
            CurrencyExchange isl;

            @SerializedName(Currencies.JAPANESE_YEN)
            private
            CurrencyExchange jpy;

            @SerializedName(Currencies.MALAYSIAN_RINGIT)
            private
            CurrencyExchange myr;

            @SerializedName(Currencies.NEW_ZEALAND_DOLLAR)
            private
            CurrencyExchange nzd;

            @SerializedName(Currencies.RUSSIAN_RUBLE)
            private
            CurrencyExchange rub;

            @SerializedName(Currencies.SINGAPORE_DOLLAR)
            private
            CurrencyExchange sgd;

            @SerializedName(Currencies.SOUTH_AFRICAN_RAND)
            private
            CurrencyExchange zar;

            @SerializedName(Currencies.SWISS_FRANC)
            private
            CurrencyExchange chf;

            @SerializedName(Currencies.TURKISH_LIRA)
            private
            CurrencyExchange lira;

            @SerializedName(Currencies.UAE_DIHAM)
            private
            CurrencyExchange aed;

            @SerializedName(Currencies.BITCOIN)
            private
            CurrencyExchange btc;

            @SerializedName(Currencies.ETHEREUM)
            private
            CurrencyExchange eth;

            public Map<String, CurrencyExchange> getCurrencyRate(){

                Map currencyRate = new HashMap<String, BindExchange>();

                if(this.naira != null)
                    currencyRate.put(Currencies.NAIRA, this.naira);
                if(this.usd != null)
                    currencyRate.put(Currencies.US_DOLLAR, this.usd);
                if(this.gbp != null)
                    currencyRate.put(Currencies.BRITISH_POUND, this.gbp);
                if(this.euro != null)
                    currencyRate.put(Currencies.EURO, this.euro);
                if(this.cad != null)
                    currencyRate.put(Currencies.CANADIAN_DOLLA, this.cad);
                if(this.aud != null)
                    currencyRate.put(Currencies.AUSTRALIAN_DOLLAR, this.aud);
                if(this.cny != null)
                    currencyRate.put(Currencies.CHINESE_RENMINBI, this.cny);
                if(this.czk != null)
                    currencyRate.put(Currencies.CZECH_KORUNA, this.czk);
                if(this.hkd != null)
                    currencyRate.put(Currencies.HONG_KONG_DOLLAR, this.hkd);
                if(this.inr != null)
                    currencyRate.put(Currencies.INDIAN_RUPEE, this.inr);
                if(this.isl != null)
                    currencyRate.put(Currencies.ISREALI_NEW_SHEKEL, this.isl);
                if(this.jpy != null)
                    currencyRate.put(Currencies.JAPANESE_YEN, this.jpy);
                if(this.myr != null)
                    currencyRate.put(Currencies.MALAYSIAN_RINGIT, this.myr);
                if(this.nzd != null)
                    currencyRate.put(Currencies.NEW_ZEALAND_DOLLAR, this.nzd);
                if(this.rub != null)
                    currencyRate.put(Currencies.RUSSIAN_RUBLE, this.rub);
                if(this.sgd != null)
                    currencyRate.put(Currencies.SINGAPORE_DOLLAR, this.sgd);
                if(this.zar != null)
                    currencyRate.put(Currencies.SOUTH_AFRICAN_RAND, this.zar);
                if(this.chf != null)
                    currencyRate.put(Currencies.SWISS_FRANC, this.chf);
                if(this.lira != null)
                    currencyRate.put(Currencies.TURKISH_LIRA, this.lira);
                if(this.aed != null)
                    currencyRate.put(Currencies.UAE_DIHAM, this.aed);
                if(this.btc != null)
                    currencyRate.put(Currencies.BITCOIN, this.btc);
                if(this.eth != null)
                    currencyRate.put(Currencies.ETHEREUM, this.eth);

                return currencyRate;
            }
        }
    }

    public ExchangeDisplay getCryptosRate(){ return this.ratesDisplay; }
}