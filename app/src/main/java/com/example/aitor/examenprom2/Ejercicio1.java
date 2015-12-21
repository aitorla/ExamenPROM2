package com.example.aitor.examenprom2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.List;

public class Ejercicio1 extends AppCompatActivity {

    private TextView temperatura, hora;
    private List<Pronostico> pronosticos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        temperatura = (TextView) findViewById(R.id.txtTemperatura);
        hora =(TextView) findViewById(R.id.txtHora);

        /// Llamamos a la AsyncTask obtenemos el tiempo de Vitoria
        CargarTiempoXmlTask tarea = new CargarTiempoXmlTask();
        tarea.execute("http://xml.tutiempo.net/xml/3768.xml?lan=es");

    }


    //Tarea Asíncrona para cargar un XML en segundo plano
    private class CargarTiempoXmlTask extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... params) {

            ElTiempoParseDom domparser =
                    new ElTiempoParseDom(params[0]);

           pronosticos = domparser.parse();

            return true;
        }


        protected void onPostExecute(Boolean result) {

            // Visualizamos las temperaturas

            temperatura.setText(pronosticos.get(0).getTemperatura()+"");
            hora.setText(pronosticos.get(0).getHora()+"");

/*
            txtTemperaturas.setText("");
            txtTemperaturas.setText("Próximos días: ");
            for (int i = 2; i < temperaturas.size(); i++) {
                txtTemperaturas.setText(
                        txtTemperaturas.getText().toString() +
                                System.getProperty("line.separator") +
                                temperaturas.get(i).getMaxima() + " - " +
                                temperaturas.get(i).getMinima()     );
            }
*/

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ejercicio1, menu);
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
