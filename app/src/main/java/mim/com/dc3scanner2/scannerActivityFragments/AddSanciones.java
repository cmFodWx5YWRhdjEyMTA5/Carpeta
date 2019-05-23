package mim.com.dc3scanner2.scannerActivityFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.models.Sanciones;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddSanciones.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddSanciones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSanciones extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button btnAdd;
    private EditText motivo;
    private EditText incumplimiento;
    private EditText sancionField;

    public AddSanciones() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddSanciones.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSanciones newInstance(String param1, String param2) {
        AddSanciones fragment = new AddSanciones();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_sanciones, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {

        motivo = view.findViewById(R.id.motivo_add);
        incumplimiento = view.findViewById(R.id.incumplimiento_add);
        sancionField = view.findViewById(R.id.sancion_add);

        btnAdd = view.findViewById(R.id.btn_add_sancion);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    Sanciones sancion = new Sanciones();
                    sancion.setMotivo(motivo.getText().toString());
                    sancion.setIncumplimiento(incumplimiento.getText().toString());
                    sancion.setSancion(sancionField.getText().toString());
                    mListener.uploadSancion(sancion);
                } else {
                    if (getContext() != null) {
                        Toast.makeText(getContext(), "llena todos los campos...", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }

    private boolean validateForm() {
        if (motivo.getText().toString().isEmpty()) {
            return false;
        }

        if (incumplimiento.getText().toString().isEmpty()) {
            return false;
        }

        if (sancionField.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
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
        void onFragmentInteraction(Uri uri);

        void uploadSancion(Sanciones sancion);
    }
}
