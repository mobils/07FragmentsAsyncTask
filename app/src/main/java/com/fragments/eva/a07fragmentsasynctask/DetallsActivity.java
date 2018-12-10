package com.fragments.eva.a07fragmentsasynctask;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetallsActivity extends AppCompatActivity implements DetallsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalls);

        //TODO Inflar fragment detalls


    }


    @Override
    public void onFragmentInteractionDetalls(Uri uri) {

    }
}
