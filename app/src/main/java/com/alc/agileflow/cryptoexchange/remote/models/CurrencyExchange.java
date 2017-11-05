package com.alc.agileflow.cryptoexchange.remote.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dell pc on 11/2/2017.
 */

public class CurrencyExchange implements Serializable{

    @SerializedName("FROMSYMBOL")
    private
    String fromSymbol;

    @SerializedName("TOSYMBOL")
    private
    String toSymbol;

    @SerializedName("PRICE")
    private
    String price;

    @SerializedName("LASTUPDATE")
    private
    String updated;

    @SerializedName("MARKET")
    private
    String market;

    public String getFromSymbol(){ return this.fromSymbol; }

    public String getToSymbol(){ return this.toSymbol; }

    public String  getPrice(){ return this.price; }

    public String getUpdated(){ return this.updated; }

    public String getMarket(){ return this.market; }

    @Override
    public String toString(){
        return "From: " + this.fromSymbol + "\n" +
                "To: " + this.toSymbol + "\n" +
                "Price: " + this.price + "\n" +
                "Lastupdate: " + this.updated + "\n" +
                "Market: " + this.market;
    }

}
