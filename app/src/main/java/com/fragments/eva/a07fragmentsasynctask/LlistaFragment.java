package com.fragments.eva.a07fragmentsasynctask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LlistaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LlistaFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;
    private static Context context;

    private static OnFragmentInteractionListener listenerStatic;
    private static LinearLayout layout;

    public LlistaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_llista, container, false);

        ImageView imatge = view.findViewById(R.id.imatge);
        //TextView textOnEscriura = view.findViewById(R.id.textOnEscriura);
        layout = view.findViewById(R.id.layout);
        Button botoMostrarDetalls = view.findViewById(R.id.MostrarDetalls);  //Recupero botó que crida al segon fragment

        botoMostrarDetalls.setOnClickListener(this);


        /* Cridar al AsyncTask per descarregar la llista de restaurants (inicialment imatge)*/
        //new DownloadImage().execute(imatge);
        //new DownloadText(getActivity().getApplicationContext()).execute(layout);

        new DownloadText(getActivity().getApplicationContext()).execute(layout);

        context= getActivity().getApplicationContext();


        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View v) {
        if (mListener != null) {

            mListener.onFragmentInteraction(v);
        }
    }


    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            listenerStatic = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        onButtonPressed(view);  //No necessitem passar parametres
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(View view);
    }

    public static void mostraJSON(String string) {
        //Recupero el JSOn del AsyncTask i mostro els TextView

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

        // AQUI LLEGIM ELS POKEMON
        try {
            JSONObject obj= new JSONObject(string);
            JSONArray array = obj.getJSONArray("result");
            for (int i=0; i<array.length(); i++) {
                String nom = array.getJSONObject(i).getString("pokemon_id");
                String lat = array.getJSONObject(i).getString("latitude");
                String lon = array.getJSONObject(i).getString("longitude");


                LinearLayout horiz = new LinearLayout(context);
                horiz.setOrientation(LinearLayout.HORIZONTAL);




                TextView textView = new TextView(context);
                textView.setText(nom);
                textView.setTextSize(22);



                TextView textView2 = new TextView(context);
                textView2.setText(lat);

                TextView textView3 = new TextView(context);
                textView3.setText(lon);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listenerStatic.onFragmentInteraction(view);

                    }
                });


                horiz.addView(textView);
                horiz.addView(textView2);
                horiz.addView(textView3);

                layout.addView(horiz);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
