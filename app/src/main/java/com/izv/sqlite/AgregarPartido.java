package com.izv.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AgregarPartido extends Activity {

    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_agregar_partido);
        et1 = (EditText) findViewById(R.id.etContrincante);
        et2 = (EditText) findViewById(R.id.etValoracion);
    }

    public void agregar(View view){
        Intent i = getIntent();
        String contricante;
        int valoracion;
        contricante = et1.getText().toString();
        valoracion= Integer.valueOf(et2.getText().toString());
        Partido p = new Partido(0,contricante,valoracion);
        i.putExtra("partido",p);
        setResult(RESULT_OK,i);
        finish();
    }
}
