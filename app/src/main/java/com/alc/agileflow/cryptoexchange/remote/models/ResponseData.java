package com.alc.agileflow.cryptoexchange.remote.models;

import com.alc.agileflow.cryptoexchange.utils.Currencies;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell pc on 11/2/2017.
 */

public class ResponseData {
    
    @SerializedName("DISPLAY")
    private
    List<ExchangeDisplay> ratesDisplay;


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
            
            private Map currencyRate;
            
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
            
            
            BindExchange(){
                
                currencyRate = new HashMap<String, BindExchange>();
                
                currencyRate.put(Currencies.NAIRA, this.naira);
                currencyRate.put(Currencies.US_DOLLAR, this.usd);
                currencyRate.put(Currencies.BRITISH_POUND, this.gbp);
                currencyRate.put(Currencies.EURO, this.euro);
                currencyRate.put(Currencies.CANADIAN_DOLLA, this.cad);
                currencyRate.put(Currencies.AUSTRALIAN_DOLLAR, this.aud);
                currencyRate.put(Currencies.CHINESE_RENMINBI, this.cny);
                currencyRate.put(Currencies.CZECH_KORUNA, this.czk);
                currencyRate.put(Currencies.HONG_KONG_DOLLAR, this.hkd);
                currencyRate.put(Currencies.INDIAN_RUPEE, this.inr);
                currencyRate.put(Currencies.ISREALI_NEW_SHEKEL, this.isl);
                currencyRate.put(Currencies.JAPANESE_YEN, this.jpy);
                currencyRate.put(Currencies.MALAYSIAN_RINGIT, this.myr);
                currencyRate.put(Currencies.NEW_ZEALAND_DOLLAR, this.nzd);
                currencyRate.put(Currencies.RUSSIAN_RUBLE, this.rub);
                currencyRate.put(Currencies.SINGAPORE_DOLLAR, this.sgd);
                currencyRate.put(Currencies.SOUTH_AFRICAN_RAND, this.zar);
                currencyRate.put(Currencies.SWISS_FRANC, this.chf);
                currencyRate.put(Currencies.TURKISH_LIRA, this.lira);
                currencyRate.put(Currencies.UAE_DIHAM, this.aed);
            }
            
            public Map<String, CurrencyExchange> getcurrencyRate(){ return this.currencyRate; }
        }
    }

    public List<ExchangeDisplay> getCryptosRate(){ return this.ratesDisplay; }
}
