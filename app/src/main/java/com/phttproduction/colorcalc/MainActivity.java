package com.phttproduction.colorcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText etWidth, etHeight, etExpenditure, etLayerQuantity, etVolume;
    private RadioGroup radioGroup2;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWidth = findViewById(R.id.width);
        etHeight = findViewById(R.id.height);
        etExpenditure = findViewById(R.id.expenditure);
        etLayerQuantity = findViewById(R.id.layerQuantity);
        etVolume = findViewById(R.id.volume);
        radioGroup2 = findViewById(R.id.radioGroup2);
        btnCalculate = findViewById(R.id.button);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем значения из EditText'ов
                String widthString = etWidth.getText().toString().trim();
                String heightString = etHeight.getText().toString().trim();
                String expenditureString = etExpenditure.getText().toString().trim();
                String layerQuantityString = etLayerQuantity.getText().toString().trim();
                String volumeString = etVolume.getText().toString().trim();

                // Проверяем, что все значения введены корректно и являются числами
                if (TextUtils.isEmpty(widthString) || TextUtils.isEmpty(heightString) ||
                        TextUtils.isEmpty(expenditureString) || TextUtils.isEmpty(layerQuantityString) ||
                        TextUtils.isEmpty(volumeString)) {
                    Toast.makeText(MainActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                double width, height, expenditure, layerQuantity, volume;
                try {
                    width = Double.parseDouble(widthString);
                    height = Double.parseDouble(heightString);
                    expenditure = Double.parseDouble(expenditureString);
                    layerQuantity = Double.parseDouble(layerQuantityString);
                    volume = Double.parseDouble(volumeString);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Введите числовые значения", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Получаем выбранную кнопку из radioGroup2 и определяем ее значение запаса
                double reserve = 1.0;
                int checkedRadioButtonId = radioGroup2.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.radioButton) {
                    reserve = 1.1;
                } else if (checkedRadioButtonId == R.id.radioButton2) {
                    reserve = 1.15;
                }

                // Рассчитываем необходимый объем краски с учетом выбранного запаса
                double paintVolume;
                if (reserve == 1.0) {
                    paintVolume = (width * height * layerQuantity) / expenditure;
                } else {
                    paintVolume = (width * height * layerQuantity * reserve) / expenditure;
                }

                // Выводим результат во всплывающем уведомлении
                String resultString = String.format(Locale.getDefault(), "Необходимый объем краски: %.2f л", paintVolume);
                Toast.makeText(MainActivity.this, resultString, Toast.LENGTH_SHORT).show();
            }
        });
    }

}