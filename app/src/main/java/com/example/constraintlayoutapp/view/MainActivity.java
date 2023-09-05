package com.example.constraintlayoutapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.SpringAnimation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.constraintlayoutapp.R;
import com.example.constraintlayoutapp.model.CelsiusStrategy;
import com.example.constraintlayoutapp.model.ConversorTemperatura;
import com.example.constraintlayoutapp.model.FahrenheitStrategy;
import com.example.constraintlayoutapp.model.KelvinStrategy;

public class MainActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Button celsiusButton;
    private Button fahrenheitButton;
    private Button kelvinButton;
    private TextView saidaTextView;
    private TextView msgSaidaTextView;
    private ConversorTemperatura conversorStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findById();
        setClick();
    }

    private void findById() {
        inputEditText = findViewById(R.id.edittext_temperatura);
        celsiusButton = findViewById(R.id.btn_celsius);
        fahrenheitButton = findViewById(R.id.btn_fahrenheit);
        kelvinButton = findViewById(R.id.btn_Kelvin);
        saidaTextView = findViewById(R.id.textview_resultado);
        msgSaidaTextView = findViewById(R.id.textview_resultado2);
    }

    private void setClick() {
        celsiusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processo(CelsiusStrategy.getInstance(), "°C");
            }
        });
        fahrenheitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processo(FahrenheitStrategy.getInstance(), "°F");
            }
        });
        kelvinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processo(KelvinStrategy.getInstance(), "°K");
            }
        });
    }
    private double lerTemperatura() throws NumberFormatException{
        double retorno;
        try {
            retorno = Double.parseDouble(inputEditText.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(this, "Erro na leitura de dados.",
                    Toast.LENGTH_SHORT).show();
            throw  new NumberFormatException();
        }
        return retorno;
    }

    private void processo(ConversorTemperatura strategy, String escala){
        conversorStrategy = strategy;
        double value;
        try {
            value = lerTemperatura();
            saidaTextView.setText(String.format("%.2f %s", conversorStrategy.converter(value), escala));
            if (escala.equals("°C")) {
                msgSaidaTextView.setText(getString(R.string.msgFtoC));
                if (escala.equals("°F")) {
                    msgSaidaTextView.setText(getString(R.string.msgCtoF));
                } else
                    msgSaidaTextView.setText(getString(R.string.msgCtoK));
                }
        }catch (NumberFormatException e){
            value = 0;
        }
    }
}











