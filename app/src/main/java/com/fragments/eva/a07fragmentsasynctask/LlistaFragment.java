package com.fragments.eva.a07fragmentsasynctask;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LlistaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LlistaFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

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
        LinearLayout layout = view.findViewById(R.id.layout);
        Button botoMostrarDetalls = view.findViewById(R.id.MostrarDetalls);  //Recupero botó que crida al segon fragment

        botoMostrarDetalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed();  //No necessitem passar parametres
            }
        });


        /* Cridar al AsyncTask per descarregar la llista de restaurants (inicialment imatge)*/
        new DownloadImage().execute(imatge);
        //new DownloadText(getActivity().getApplicationContext()).execute(layout);

        new DownloadText(getActivity().getApplicationContext()).execute(layout);


        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
        void onFragmentInteraction();
    }
}
