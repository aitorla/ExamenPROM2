package com.example.aitor.examenprom2;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio2 extends AppCompatActivity {

    private Spinner spinner;
    MediaPlayer sonido;

    //// Lo hacemos con un array list y añadimos con add en lugar de utilizar Array!!!!
    ArrayList<String> sonidos =new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        spinner = (Spinner)findViewById(R.id.spinner);

        //// Añadimos las provincias del fichero provincias.txt
        try
        {
            InputStream fraw = getResources().openRawResource(R.raw.sonidos);

            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
            String texto = brin.readLine();
            int i =0;
            while (texto != null) {
                sonidos.add(texto);
                texto = brin.readLine();
            }
            fraw.close();
        }
        catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }
        ////////////

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sonidos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                String sonidoactual = sonidos.get(position);
                int soundId = getResources().getIdentifier(sonidoactual, "raw", getPackageName());
                sonido = MediaPlayer.create(Ejercicio2.this, soundId);
                sonido.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ejercicio2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
