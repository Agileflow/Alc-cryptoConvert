package com.alc.agileflow.cryptoexchange.ui.createcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.alc.agileflow.cryptoexchange.R;
import com.alc.agileflow.cryptoexchange.ui.CryptoCardsActivity;

public class CreateExchangeCardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String SELECTED = "exchangecurrencies";
    private Spinner fromSpinner, toSpinner;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exchange_card);

        addListenerOnSpinnersItemSelection();
        addListenerOnCreateButton();
    }

    private void addListenerOnSpinnersItemSelection() {
        fromSpinner = (Spinner) findViewById(R.id.from_symbol_select);
        toSpinner = (Spinner) findViewById(R.id.to_symbol_select);
        fromSpinner.setOnItemSelectedListener(this);
        toSpinner.setOnItemSelectedListener(this);
    }

    private void addListenerOnCreateButton() {
        btnCreate = (Button) findViewById(R.id.btn_create);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(String.valueOf(fromSpinner.getSelectedItem()).isEmpty()) && !(String.valueOf(toSpinner.getSelectedItem()).isEmpty())) {
                    String selection[] = {String.valueOf(fromSpinner.getSelectedItem()), String.valueOf(toSpinner.getSelectedItem())};

                    Intent cardIntent = new Intent(getBaseContext(), CryptoCardsActivity.class);
                    cardIntent.putExtra(SELECTED, selection);
                    startActivity(cardIntent);
                }else {
                    Toast.makeText(getBaseContext(),
                            "Select both choice currencies",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(!(String.valueOf(fromSpinner.getSelectedItem()).isEmpty()) && !(String.valueOf(toSpinner.getSelectedItem()).isEmpty())){
            btnCreate.setTextColor(getResources().getColor(R.color.holo_green));
        }else {
            btnCreate.setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // TODO Auto-generated method stub
    }
}
