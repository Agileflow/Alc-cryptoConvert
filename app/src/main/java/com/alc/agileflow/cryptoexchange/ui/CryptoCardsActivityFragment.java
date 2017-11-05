package com.alc.agileflow.cryptoexchange.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

    @BindView(R.id.rv_exchange_cards)
    RecyclerView exchangeCardsRecyclerView;

    FrameLayout noCard;

    CurrencyExchangeAdapter currencyExchangeAdapter;

    CrytoCardsContract.UserActionListener actionListener;

    // Mandatory empty constructor for the fragment manager to instantiate the fragment
    public CryptoCardsActivityFragment() {
    }

    public static CryptoCardsActivityFragment getInstance(){
        return new CryptoCardsActivityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currencyExchangeAdapter = new CurrencyExchangeAdapter(new HashMap<String, CurrencyExchange>(),exchangeItemClickedListener, Currencies.BITCOIN);
        actionListener = new CryptoCardsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        noCard = getActivity().findViewById(R.id.no_exchange_card);

        if(savedInstanceState != null){

            // check if card(s) was saved before hiding message
            //hideNoCard();
        }
        View root = inflater.inflate(R.layout.fragment_crypto_cards, container, false);
        ButterKnife.bind(this,root);

        setupRecyclerView();

        Intent intent = getActivity().getIntent();
        if(intent.hasExtra(CreateExchangeCardActivity.SELECTED)) {
            String selected[] = intent.getStringArrayExtra(CreateExchangeCardActivity.SELECTED);

            actionListener.getExchangeCard(Currencies.BITCOIN + "," + Currencies.ETHEREUM, selected[1]+"," + Currencies.CANADIAN_DOLLA);
        }


        return root;
    }

    private void setupRecyclerView() {
        exchangeCardsRecyclerView.setAdapter(currencyExchangeAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(SPAN_COUNT,StaggeredGridLayoutManager.VERTICAL);
        exchangeCardsRecyclerView.setLayoutManager(layoutManager);
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
        Log.i("CARDS --->>>>>>> ", err);
        Helper.showToast(getContext(),err);
    }

    @Override
    public void setExchangeCards(Map<String, CurrencyExchange> currencyExchanges) {
        currencyExchangeAdapter.replaceData(currencyExchanges);
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
            actionListener.showExchangeConverter(currencyExchange);
        }
    };

    static class CurrencyExchangeAdapter extends RecyclerView.Adapter<CurrencyExchangeAdapter.ViewHolder> {
        private Map<String, CurrencyExchange> currencyExchanges;
        private OnExchangeItemClickedListener exchangeItemClickedListener;
        private String coinType;

        CurrencyExchangeAdapter(Map<String, CurrencyExchange> currencyExchanges, OnExchangeItemClickedListener exchangeItemClickedListener, String coinType) {
            this.currencyExchanges = currencyExchanges;
            this.exchangeItemClickedListener = exchangeItemClickedListener;
            this.coinType = coinType;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View exchangeItemView = inflater.inflate(R.layout.exchange_card,parent,false);

            return new ViewHolder(exchangeItemView,exchangeItemClickedListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            CurrencyExchange currencyExchange = currencyExchanges.get(position);

            if(coinType.equalsIgnoreCase(Currencies.BITCOIN)){
                holder.fromSym.setBackgroundResource(R.color.holo_orange_light);
            }else if(coinType.equalsIgnoreCase(Currencies.ETHEREUM)){
                holder.fromSym.setBackgroundResource(R.color.colorEthereum);
            }

            holder.fromSym.setText(currencyExchange.getFromSymbol());
            holder.toSym.setText(currencyExchange.getToSymbol());
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

        public void replaceData(Map<String, CurrencyExchange> currencyExchanges){
            this.currencyExchanges = currencyExchanges;
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private OnExchangeItemClickedListener itemClickedListener;

            @BindView(R.id.tv_from_symbol)
            TextView fromSym;

            @BindView(R.id.tv_to_symbol)
            TextView toSym;

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
