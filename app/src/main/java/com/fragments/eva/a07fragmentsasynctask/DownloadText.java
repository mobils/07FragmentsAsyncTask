package com.fragments.eva.a07fragmentsasynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Eva on 23/11/2018.
 */

public class DownloadText extends AsyncTask<LinearLayout, Void, String> {
    LinearLayout layout;
    TextView textOnEscriura;
    Context context;

    //public OnDataFetchedListener listener =null;

    public DownloadText(Context miContext) {
        context=miContext;


    }

    @Override
    protected String doInBackground(LinearLayout... layouts) {

        layout=layouts[0];   //Es pot enviar una llista però si només hi ha 1 agafem el primer.
        //Recupera un text del servidor i li retorna al postExecute
        String result="";

        try {
/*
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
*/
            Random random = new Random();

            Thread.sleep((random.nextInt(3)+1)*1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //return result;
        return "{'result':[{'spawn_point_id':'48761cbc80b','encounter_id':'8774076881359073581','pokemon_id':'VOLTORB','latitude':51.531039698408804,'longitude':-0.033453745476703,'expiration_timestamp_ms':'1474705165772'},{'spawn_point_id':'48761cbb0db','encounter_id':'5024433270417738282','pokemon_id':'VENUSAUR','latitude':51.42923201185921,'longitude':-0.088043570107511,'expiration_timestamp_ms':'1474704786572'},{'spawn_point_id':'48761ccb0db','encounter_id':'5128166349700273373','pokemon_id':'PIKACHU','latitude':51.53923201185921,'longitude':-0.4788043570107511,'expiration_timestamp_ms':'1474704786572'},{'spawn_point_id':'48761ccb0db','encounter_id':'5128166349700273374','pokemon_id':'PIPLUP','latitude':51.2623201185921,'longitude':-0.5703570107511,'expiration_timestamp_ms':'1474704786572'},{'spawn_point_id':'48761ccb0db','encounter_id':'5128166349700273375','pokemon_id':'CHARMANDER','latitude':50.9223201185921,'longitude':-0.173570107511,'expiration_timestamp_ms':'1474704786572'},{'spawn_point_id':'48761ccb0db','encounter_id':'5128166349700273376','pokemon_id':'CHICORITA','latitude':51.162320118592,'longitude':-0.210357010751,'expiration_timestamp_ms':'1474704786572'},{'spawn_point_id':'48761ccb0db','encounter_id':'5128166349700273377','pokemon_id':'BLAZIKEN','latitude':52.123201185921,'longitude':-0.09570107511,'expiration_timestamp_ms':'1474704786572'},\n" +
                "{'spawn_point_id':'48761ccb0db','encounter_id':'5128166349700273378','pokemon_id':'BULBASAUR','latitude':51.736000,'longitude':0.4759311,'expiration_timestamp_ms':'1474704786572'},\n" +
                "{'spawn_point_id':'48761ccb0db','encounter_id':'5128166349700273379','pokemon_id':'PIDGEOT','latitude':51.460466,'longitude':0.224619,'expiration_timestamp_ms':'1474704786572'},\n" +
                "{'spawn_point_id':'48761ccb0db','encounter_id':'5128166349700273310','pokemon_id':'CHESPIN','latitude':52.187827,'longitude':0.118511,'expiration_timestamp_ms':'1474704786572'}]}\n" +
                "\n" +
                "\n";
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);


        //TODO Vull tornar el string amb el JSON al Fragment. Ara podria passar tot el codi al mètode mostraJSON

        LlistaFragment.mostraJSON(string);

        //Ara envio las dades al Fragment amb interface:   NO FUNCIONA
        //listener.updateText(string);


        //textOnEscriura.setText(string);
        //Log.d("Connexio", "Text escrit a: " + textOnEscriura.getId());




        /*Ara llegim el JSON */
        /* Una alternativa seria retornar el JSON al Fragment i que sigui aquest qui crea els TextView,
         així sí es podrien fer clicables i que tinguin un OnClickListener
          */
        /*JSONArray json = null;

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
        }*/


    }
/*
    public interface OnDataFetchedListener
    {
        void updateText(String data);
    }*/
}
