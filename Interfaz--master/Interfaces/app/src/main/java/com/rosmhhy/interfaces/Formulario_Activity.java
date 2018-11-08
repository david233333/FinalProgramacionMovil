package com.rosmhhy.interfaces;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Formulario_Activity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bfecha)
    Button bfecha;
    @BindView(R.id.bhora)
    Button bhora;
    @BindView(R.id.efecha)
    EditText efecha;
    @BindView(R.id.ehora)
    EditText ehora;

    private int dia,mes,ano,hora,minutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_);
        ButterKnife.bind(this);


        bfecha.setOnClickListener(this);
        bhora.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==bfecha){
            final Calendar c = Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    efecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
            ,dia,mes,ano);
            datePickerDialog.show();
        }
        if (v==bhora){
            final Calendar c = Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        ehora.setText(hourOfDay+":"+minute);

                }
            },hora,minutos,false);
            timePickerDialog.show();

        }

    }
}
