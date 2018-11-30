package com.fragments.eva.a07fragmentsasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by Eva on 23/11/2018.
 */

public class DownloadImage extends AsyncTask<ImageView, Void, Bitmap> {
    ImageView image;

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {

        image=imageViews[0];

        Bitmap bitmap = null;
        try {
            URL url[] = new URL[6];  //Tenemos unas cuantas URL y se mostrará una al azar.
            url[0]= new URL("http://25.media.tumblr.com/tumblr_m7j9qiQnJm1ra2xmeo1_500.png");


            int num=0;
            HttpURLConnection urlConnection =  (HttpURLConnection)url[num].openConnection();
            Log.d("Connexio", "Inici openConnection a la URL: " + num);

            int status = urlConnection.getResponseCode();



            if(status==200){
                //Recuperem contingut de la URL
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                //Convertim el inputStream en imatge bitmap
                bitmap = BitmapFactory.decodeStream(in);
                Log.d("Connexio", "Imatge descarregada num: " + num);

                //Hacemos que sea un poco más lento

                new Thread().sleep(1000*new Random().nextInt(5)); //Espera entre 0 y 5 segundos

            }
            else {
                Log.d("Connexio", "Error de connexió. Estat que retorna la connexió: " + status );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        image.setImageBitmap(bitmap);
        Log.d("Connexio", "Imatge al lloc " + image.getId());




    }
}
