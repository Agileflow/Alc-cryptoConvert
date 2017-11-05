package com.alc.agileflow.cryptoexchange.model;

/**
 * Created by dell pc on 11/2/2017.
 */

public class CurrencyExchange {

    private String fromSymbol;
    private String toSymbol;
    private String market;
    private double price;
    private String updated;

    public CurrencyExchange(String fsym, String tsym, double price, String market, String updated){
        this.fromSymbol = fsym;
        this.toSymbol = tsym;
        this.price = price;
        this.market = market;
        this.updated = updated;
    }

    public String getFromSymbol(){ return this.fromSymbol; }

    public String getToSymbol(){ return this.toSymbol; }

    public double getPrice(){ return this.price; }

    public String getUpdated(){ return this.updated; }

    public String getMarket(){ return this.market; }
}
