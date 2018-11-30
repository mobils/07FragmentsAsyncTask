package com.fragments.eva.a07fragmentsasynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Eva on 23/11/2018.
 */

public class DownloadText extends AsyncTask<LinearLayout, Void, String> {
    LinearLayout layout;
    TextView textOnEscriura;
    Context context;

    public DownloadText(Context miContext) {
        context=miContext;
    }

    @Override
    protected String doInBackground(LinearLayout... layouts) {

        layout=layouts[0];   //Es pot enviar una llista però si només hi ha 1 agafem el primer.
        //Recupera un text del servidor i li retorna al postExecute
        String result="";

        try {

            URL url = new URL("http://project.phpeducem.dx.am/getAllRestaurants.php");

            HttpURLConnection urlConnection =  (HttpURLConnection)url.openConnection();
            urlConnection.setInstanceFollowRedirects(true);  //test per evitar error connexio
            Log.d("Connexio", "Inici openConnection a la URL: http://project.phpeducem.dx.am/getAllRestaurants.php");

            int status = urlConnection.getResponseCode();
            if (status==200) {

                //Recuperem contingut de la URL
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedInputStream bis = new BufferedInputStream(in);
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                int num = bis.read();
                while(num != -1) {
                    buf.write((byte) num);
                    num = bis.read();
                }
                result= buf.toString("UTF-8");
            }
            else {
                Log.d("Connexio", "Error de connexió. Estat que retorna la connexió: " + status );

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);

        //textOnEscriura.setText(string);
        Log.d("Connexio", "Text escrit a: " + textOnEscriura.getId());




        /*Ara llegim el JSON */
        /* Una alternativa seria retornar el JSON al Fragment i que sigui aquest qui crea els TextView,
         així sí es podrien fer clicables i que tinguin un OnClickListener
          */
        JSONArray json = null;

        try {
            json = new JSONArray(string);
            for (int i=0; i<json.length(); i++) {
                String nombre = json.getJSONObject(i).getString("nombre");
                //textOnEscriura.setText(textOnEscriura.getText() + ", " + nombre);

                TextView textView = new TextView(context);
                textView.setText(nombre);



                layout.addView(textView);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }








    }
}
