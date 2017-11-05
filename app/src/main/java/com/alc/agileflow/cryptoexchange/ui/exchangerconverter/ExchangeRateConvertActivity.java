package com.alc.agileflow.cryptoexchange.ui.exchangerconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.alc.agileflow.cryptoexchange.R;
import com.alc.agileflow.cryptoexchange.remote.models.CurrencyExchange;
import com.alc.agileflow.cryptoexchange.ui.CryptoCardsActivityFragment;
import com.alc.agileflow.cryptoexchange.utils.Helper;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

public class ExchangeRateConvertActivity extends AppCompatActivity {

    @BindView(R.id.tv_crypto_sym)
    TextView cryptoSym;

    @BindView(R.id.tv_world_rate)
    TextView toWorldRate;

    @BindView(R.id.to_currency_select)
    Spinner currencySelect;

    @BindView(R.id.convert_value_field)
    EditText convertValue;

    @BindView(R.id.tv_conversion_result)
    TextView convertResult;

    @BindView(R.id.btn_convert_to)
    Button btnConvertTo;

    @BindView(R.id.btn_convert_from)
    Button getBtnConvertFrom;

    private CurrencyExchange currencyExchange;

    private DecimalFormat format = new DecimalFormat("#,###.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_rate_convert);

        Intent intent = getIntent();

        if(intent.hasExtra(CryptoCardsActivityFragment.CURRENCY_EXCHANGE)){
            currencyExchange = (CurrencyExchange) intent.getSerializableExtra(CryptoCardsActivityFragment.CURRENCY_EXCHANGE);
        }
    }

    @OnClick(R.id.btn_convert_from)
    public void convertCryptoToWorld(){
        if(currencyExchange != null){
            double value = Double.parseDouble(convertValue.getText().toString());
            double rate = Helper.convertPrice(convertValue.getText().toString());

            double result = value * rate;

            convertResult.setText(format.format(String.valueOf(result)));
        }
    }

    @OnClick(R.id.btn_convert_from)
    public void convertWorldToCrypto(){
        if(currencyExchange != null){
            double value = Double.parseDouble(convertValue.getText().toString());

            double rate = Helper.convertPrice(convertValue.getText().toString());

            double result = value / rate;

            convertResult.setText(format.format(String.valueOf(result)));
        }
    }
}
