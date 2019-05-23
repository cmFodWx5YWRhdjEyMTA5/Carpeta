package mim.com.dc3scanner2.scannerActivityFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.models.Certificaciones;
import mim.com.dc3scanner2.util.models.MapaCertificaciones;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CertificationInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CertificationInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CertificationInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private MapaCertificaciones certificacion;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextView nombreCurso;
    private TextView duracion;
    private TextView fecha;
    private TextView area;
    private TextView capacitador;
    private TextView registro;

    public CertificationInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CertificationInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static CertificationInfoFragment newInstance(MapaCertificaciones param1, String param2) {
        CertificationInfoFragment fragment = new CertificationInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            certificacion = getArguments().getParcelable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_certification_info, container, false);

        nombreCurso = view.findViewById(R.id.nombre_curso_info);
        nombreCurso.setText(certificacion.getCertificaciones().getNombre());

        duracion = view.findViewById(R.id.duracion_info);
        if (certificacion.getDuracion() != null) {
            duracion.setText(certificacion.getDuracion().toString());
        }

        fecha = view.findViewById(R.id.fecha_info);
        if (certificacion.getFechaEjecucion() != null) {
            int dayX = Integer.parseInt((String) DateFormat.format("dd", certificacion.getFechaEjecucion())); // 20
            int mX = Integer.parseInt((String) DateFormat.format("MM", certificacion.getFechaEjecucion())); // 06
            int yearX = Integer.parseInt(((String) DateFormat.format("yyyy", certificacion.getFechaEjecucion()))); // 2013
            fecha.setText(dayX + "/" + mX + "/" + (yearX + 1));
        }
        area = view.findViewById(R.id.area_tematica_info);
        area.setText(certificacion.getAreaTematica());

        capacitador = view.findViewById(R.id.capacitador_info);
        capacitador.setText(certificacion.getCapacitador());

        registro = view.findViewById(R.id.registro_info);
        registro.setText(certificacion.getRegistro());
        return view;
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
    }
}
