package com.alc.agileflow.cryptoexchange.ui.exchangerconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.alc.agileflow.cryptoexchange.R;
import com.alc.agileflow.cryptoexchange.remote.models.CurrencyExchange;
import com.alc.agileflow.cryptoexchange.ui.CryptoCardsActivityFragment;
import com.alc.agileflow.cryptoexchange.utils.Currencies;
import com.alc.agileflow.cryptoexchange.utils.Helper;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExchangeRateConvertActivity extends AppCompatActivity {

    @BindView(R.id.tv_crypto_sym)
    TextView cryptoSym;

    @BindView(R.id.tv_world_rate)
    TextView toWorldRate;

    @BindView(R.id.convert_value_field)
    EditText convertValue;

    @BindView(R.id.tv_conversion_result)
    TextView convertResult;

    @BindView(R.id.btn_convert_to)
    Button btnConvertTo;

    @BindView(R.id.btn_convert_from)
    Button btnConvertFrom;

    private String from, to;

    private CurrencyExchange currencyExchange;

    private DecimalFormat format = new DecimalFormat("#,###.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_rate_convert);

        ButterKnife.bind(this,getCurrentFocus());

//        convertValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                convertValue.setText("");
//            }
//        });

        Intent intent = getIntent();

        if(intent.hasExtra(CryptoCardsActivityFragment.CURRENCY_EXCHANGE)){
            currencyExchange = (CurrencyExchange) intent.getSerializableExtra(CryptoCardsActivityFragment.CURRENCY_EXCHANGE);

            this.from = currencyExchange.getFromSymbol();
            this.to = currencyExchange.getToSymbol();

            if(from.equalsIgnoreCase("Ƀ")){
                this.cryptoSym.setBackgroundResource(R.color.holo_orange_light);
            }else if(from.equalsIgnoreCase("Ξ")){
                this.cryptoSym.setBackgroundResource(R.color.colorEthereum);
            }

            cryptoSym.setText(from);
            toWorldRate.setText(currencyExchange.getPrice());
        }
    }

    @OnClick(R.id.btn_convert_from)
    public void convertCryptoToWorld(){
        Helper.showToast(getApplicationContext(),"btn_convert_from clicked");
        if(currencyExchange != null){
            double value = Double.parseDouble(convertValue.getText().toString());

            double rate = Helper.convertPrice(convertValue.getText().toString());

            double result = value * rate;

            convertResult.setText(to + " " + format.format(String.valueOf(result)));
        }
    }

    @OnClick(R.id.btn_convert_to)
    public void convertWorldToCrypto(){
        Helper.showToast(getApplicationContext(),"btn_convert_to clicked");
        if(currencyExchange != null){
            double value = Double.parseDouble(convertValue.getText().toString());

            double rate = Helper.convertPrice(convertValue.getText().toString());

            double result = value / rate;

            convertResult.setText(from + " " + format.format(String.valueOf(result)));
        }
    }
}
