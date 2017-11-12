package com.alc.agileflow.cryptoexchange.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.alc.agileflow.cryptoexchange.R;
import com.alc.agileflow.cryptoexchange.remote.models.CurrencyExchange;
import com.alc.agileflow.cryptoexchange.remote.models.ResponseData;
import com.alc.agileflow.cryptoexchange.service.CryptoCompareService;
import com.alc.agileflow.cryptoexchange.ui.createcard.CreateExchangeCardActivity;
import com.alc.agileflow.cryptoexchange.ui.exchangerconverter.ExchangeRateConvertActivity;
import com.alc.agileflow.cryptoexchange.utils.Currencies;
import com.alc.agileflow.cryptoexchange.utils.Network;
import com.alc.agileflow.cryptoexchange.utils.Helper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class CryptoCardsActivityFragment extends Fragment implements CrytoCardsContract.View{

    private static final int SPAN_COUNT = 2;

    public static final String CURRENCY_EXCHANGE = "currencyexchange";

    public static Map<String, Map<String, CurrencyExchange>> rates = new HashMap<>();

    @BindView(R.id.rv_exchange_cards)
    RecyclerView exchangeCardsRecyclerView;

    FrameLayout noCard;

    CurrencyExchangeAdapter currencyExchangeAdapter;

    CrytoCardsContract.UserActionListener actionListener;

    private int index = 0;

    // Mandatory empty constructor for the fragment manager to instantiate the fragment
    public CryptoCardsActivityFragment() {
    }

    public static CryptoCardsActivityFragment getInstance(){
        return new CryptoCardsActivityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //String from = Currencies.BITCOIN + "," + Currencies.ETHEREUM;

        //this.rates = new HashMap<>();

        currencyExchangeAdapter = new CurrencyExchangeAdapter(exchangeItemClickedListener);

        actionListener = new CryptoCardsPresenter(this);

        //new GetRatesTask().execute(from, Currencies.toCurrencies);
        getExchangeRates();
    }


//    private void getRates(String from, String to){
//        if(checkNetworkConnection()){
//            CryptoCompareService.getExchangeRate(from,to,listener);
//        }
//        else
//            showErrorMessage( "Check Internet Connectivity!");
//    }
//
//    class GetRatesTask extends AsyncTask{
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            getRates(objects[0].toString(), objects[1].toString());
//            return null;
//        }
//    }

    private void getExchangeRates() {
        String from = Currencies.BITCOIN + "," + Currencies.ETHEREUM;

        actionListener.getExchangeData(from, Currencies.toCurrencies);
    }

    private CurrencyExchange getCurrencyExchange(String from, String to){
        if(!this.rates.isEmpty())
            return this.rates.get(from).get(to);
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        noCard = getActivity().findViewById(R.id.no_exchange_card);

        View root = inflater.inflate(R.layout.fragment_crypto_cards, container, false);
        ButterKnife.bind(this,root);

        setupRecyclerView();


        if(savedInstanceState != null){

            // check if card(s) was saved before hiding message
            hideNoCard();

        }

        Intent intent = getActivity().getIntent();
        if(intent.hasExtra(CreateExchangeCardActivity.SELECTED)) {
            String selected[] = intent.getStringArrayExtra(CreateExchangeCardActivity.SELECTED);

            currencyExchangeAdapter.addCurrencyExchange(selected[0], updateAdapterData(selected));
            this.index++;
        }
        return root;
    }

    public Map<Integer, CurrencyExchange> updateAdapterData(String[] selected){

        Map<Integer, CurrencyExchange> temp = currencyExchangeAdapter.getAdapterData();
        temp.put(this.index,getCurrencyExchange(selected[0],selected[1]));

        return temp;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        SharedPreferences settings = getContext().getSharedPreferences(CURRENCY_EXCHANGE,0);
        SharedPreferences.Editor editor = settings.edit();


        // Commit the edits!
        editor.commit();

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private void setupRecyclerView() {
        exchangeCardsRecyclerView.setAdapter(currencyExchangeAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(SPAN_COUNT,StaggeredGridLayoutManager.VERTICAL);
        exchangeCardsRecyclerView.setLayoutManager(layoutManager);
        exchangeCardsRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void hideNoCard() {
        noCard.setVisibility(View.GONE);
    }

    @Override
    public boolean checkNetworkConnection() {
        return Network.isNetworkConnected(getContext());
    }

    @Override
    public void showErrorMessage(String err) {
        Helper.showToast(getContext(), err);
    }

    @Override
    public void setExchangeRateData(String from, Map<String, CurrencyExchange> currencyExchanges) {
        if(this.rates.size() < 2)
            this.rates.put(from, currencyExchanges);
    }

    @Override
    public void openExchangeConverter(CurrencyExchange currencyExchange) {
        Intent converterIntent = new Intent(getContext(), ExchangeRateConvertActivity.class);
        converterIntent.putExtra(CURRENCY_EXCHANGE, currencyExchange);
        startActivity(converterIntent);
    }

    CurrencyExchangeAdapter.OnExchangeItemClickedListener exchangeItemClickedListener = new CurrencyExchangeAdapter.OnExchangeItemClickedListener(){

        @Override
        public void onExchangeItemClicked(CurrencyExchange currencyExchange) {
            openExchangeConverter(currencyExchange);
        }
    };

    private CryptoCompareService.OnCryptoExchangeRatesLoadedListener listener = new CryptoCompareService.OnCryptoExchangeRatesLoadedListener() {
        @Override
        public void onCryptoExchangeRatesLoaded(ResponseData.ExchangeDisplay cryptosRate) {
            if (cryptosRate.getBTCRate().getCurrencyRate() != null)
                setExchangeRateData(Currencies.BITCOIN, cryptosRate.getBTCRate().getCurrencyRate());
            if (cryptosRate.getETHRate().getCurrencyRate() != null)
                setExchangeRateData(Currencies.ETHEREUM, cryptosRate.getETHRate().getCurrencyRate());
        }

        @Override
        public void onCryptoExchangeRatesLoadFailure(String err) {
            showErrorMessage(err);
        }
    };

    static class CurrencyExchangeAdapter extends RecyclerView.Adapter<CurrencyExchangeAdapter.ViewHolder> {
        private Map<Integer, CurrencyExchange> currencyExchanges;
        private OnExchangeItemClickedListener exchangeItemClickedListener;
        private String coinType;

        CurrencyExchangeAdapter(OnExchangeItemClickedListener exchangeItemClickedListener) {
            this.currencyExchanges = new HashMap<>();
            this.exchangeItemClickedListener = exchangeItemClickedListener;
        }

        @Override
        public CurrencyExchangeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View exchangeItemView = inflater.inflate(R.layout.exchange_card,parent,false);

            return new ViewHolder(exchangeItemView,exchangeItemClickedListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            CurrencyExchange currencyExchange = currencyExchanges.get(position);

            if(coinType.equalsIgnoreCase(Currencies.BITCOIN))
                holder.fromSym.setBackgroundResource(R.color.holo_orange_light);
            else if(coinType.equalsIgnoreCase(Currencies.ETHEREUM))
                holder.fromSym.setBackgroundResource(R.color.colorEthereum);

            holder.fromSym.setText(currencyExchange.getFromSymbol());
            holder.rateTextView.setText(currencyExchange.getPrice());
            holder.marketTextView.setText(currencyExchange.getMarket());
            holder.updatedTextView.setText(currencyExchange.getUpdated());
        }

        @Override
        public int getItemCount() {
            return currencyExchanges.size();
        }

        public CurrencyExchange getItem(int position){
            return currencyExchanges.get(position);
        }

        public void addCurrencyExchange(String from, Map<Integer, CurrencyExchange> currencyExchange){
            this.coinType = from;
            this.currencyExchanges = currencyExchanges;
            notifyDataSetChanged();
        }

        public Map<Integer,CurrencyExchange> getAdapterData(){ return this.currencyExchanges; }


        class ViewHolder extends RecyclerView.ViewHolder {
            private OnExchangeItemClickedListener itemClickedListener;

            @BindView(R.id.tv_from_symbol)
            TextView fromSym;

            @BindView(R.id.tv_to_world_rate)
            TextView rateTextView;

            @BindView(R.id.tv_market)
            TextView marketTextView;

            @BindView(R.id.tv_updated)
            TextView updatedTextView;


            public ViewHolder(View itemView, OnExchangeItemClickedListener itemClickedListener) {
                super(itemView);

                ButterKnife.bind(this,itemView);
                this.itemClickedListener = itemClickedListener;

                itemView.setOnClickListener(cardClickListener);
            }

            View.OnClickListener cardClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    CurrencyExchange currencyExchange= getItem(position);
                    itemClickedListener.onExchangeItemClicked(currencyExchange);
                }
            };
        }

        public interface OnExchangeItemClickedListener{
            void onExchangeItemClicked(CurrencyExchange currencyExchange);
        }
    }
}
