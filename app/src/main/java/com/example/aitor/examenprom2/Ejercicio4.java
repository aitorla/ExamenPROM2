package com.example.aitor.examenprom2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Ejercicio4 extends AppCompatActivity {

    private Button bt;
    private EditText elementoatomico;
    private String resultado;
    private String numeroatomico, simbolo, pesoatomico, puntodeebullicion, densidad;
    private TextView tvNumeroatomico, tvSimbolo, tvPesoatomico, tvPuntodeebullicion, tvDensidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio4);

        bt = (Button) findViewById(R.id.bt);
        tvNumeroatomico=(TextView) findViewById(R.id.numeroatomico);
        tvSimbolo=(TextView) findViewById(R.id.simbolo);
        tvPesoatomico=(TextView) findViewById(R.id.pesoatomico);
        tvPuntodeebullicion=(TextView) findViewById(R.id.puntodeebullicion);
        tvDensidad=(TextView) findViewById(R.id.densidad);

        elementoatomico = (EditText) findViewById(R.id.elementoatomico);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccionip = elementoatomico.getText().toString();
                AsyncPost task = new AsyncPost();
                task.execute(direccionip);
            }
        });

    }

    private class AsyncPost extends AsyncTask<String,Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                HttpURLConnection conn;
                URL url = new URL("http://www.webservicex.net/periodictable.asmx/GetAtomicNumber ");

                String param="ElementName=" + URLEncoder.encode(params[0],"UTF-8");//+
                conn=(HttpURLConnection)url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setFixedLengthStreamingMode(param.getBytes().length);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(param);
                out.close();
                String result = "";
                numeroatomico="";
                simbolo="";
                pesoatomico="";
                puntodeebullicion="";
                densidad="";

                resultado="";
                //start listening to the stream
                Scanner inStream = new Scanner(conn.getInputStream());
                //process the stream and store it in StringBuilder
                while(inStream.hasNextLine()) {
                    result =(inStream.nextLine());
                    resultado+=result;
                    if (result.indexOf("&lt;AtomicNumber&gt;") > 0)
                        numeroatomico=result.replace("&lt;AtomicNumber&gt","").replace("&lt;/AtomicNumber&gt","").replace(";","");
                    if (result.indexOf("&lt;Symbol&gt;") > 0)
                        simbolo=result.replace("&lt;Symbol&gt","").replace("&lt;/Symbol&gt","").replace(";","");
                    if (result.indexOf("&lt;AtomicWeight&gt;") > 0)
                        pesoatomico=result.replace("&lt;AtomicWeight&gt","").replace("&lt;/AtomicWeight&gt","").replace(";","");
                    if (result.indexOf("&lt;BoilingPoint&gt;") > 0)
                        puntodeebullicion=result.replace("&lt;BoilingPoint&gt","").replace("&lt;/BoilingPoint&gt","").replace(";","");
                    if (result.indexOf("&lt;Density&gt;") > 0)
                        densidad=result.replace("&lt;Density&gt","").replace("&lt;/Density&gt","").replace(";","");
                }
            } catch (MalformedURLException e) {
                Log.e("A", "exception: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e("A", "exception: " + e.getMessage());
            } catch (IOException e) {
                Log.e("A", "exception: " + e.getMessage());
            } catch (Exception e) {
                Log.e("A", "exception: " + e.getMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute (Void result){
            Toast.makeText(Ejercicio4.this, "Obtengo Resultado", Toast.LENGTH_LONG).show();
            tvNumeroatomico.setText(numeroatomico);
            tvSimbolo.setText(simbolo);
            tvPesoatomico.setText(pesoatomico);
            tvPuntodeebullicion.setText(puntodeebullicion);
            tvDensidad.setText(densidad);

        }
    }





        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ejercicio4, menu);
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
