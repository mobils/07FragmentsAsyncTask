package com.fragments.eva.a07fragmentsasynctask;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LlistaFragment.OnFragmentInteractionListener, DetallsFragment.OnFragmentInteractionListener {

    public Button boto;
    public float dpWidth=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boto = (Button) findViewById(R.id.botoIniciaFragment);
        boto.setOnClickListener(this);

          /* Per mesurar l'ample de la pantalla necessitem la variable dpWidth*/
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);
        float density = getResources().getDisplayMetrics().density;
        dpWidth = outMetrics.widthPixels / density;

    }

    @Override
    public void onClick(View view) {
        /*Inflo el fragment Llista amb el primer botó */

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.contenidorFragment) == null) {   //Per no carregar el fragment més d'un cop
            LlistaFragment fragment = new LlistaFragment();

            fm.beginTransaction().replace(R.id.contenidorFragment, fragment).commit();
        }

    }

    @Override
    public void onFragmentInteraction(View v) {
        //Llega aquí cuando alguien clica algún TextView fragment, li hauria de passar les dades en un Bundle per mostrar-les
        // o llamar al activity si un movil o inflar el segundo fragment si es tablet

        if (dpWidth>600) {  //Si es tablet inflo fragment de detalls
            FragmentManager fm = getFragmentManager();
            //if (fm.findFragmentById(R.id.contenidorFragmentDetalls) == null) {   //Aquí sí carregarem el fragment més d'un cop
                DetallsFragment fragment = new DetallsFragment();

                if (v instanceof TextView) {

                    Bundle bundle = new Bundle();
                    bundle.putString("nom", ((TextView) v).getText().toString());


                    fragment.setArguments(bundle);
                }

                fm.beginTransaction().replace(R.id.contenidorFragmentDetalls, fragment).commit();
            //}
        }
        else {
            //TODO intent crida activity amb fragment detalls

            Intent i = new Intent(this,DetallsActivity.class);
            startActivity(i);
        }


    }

    @Override
    public void onFragmentInteractionDetalls(Uri uri) {

    }
}
