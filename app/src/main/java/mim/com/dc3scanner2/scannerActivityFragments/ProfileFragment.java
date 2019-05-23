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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.activities.ScannerActivity;
import mim.com.dc3scanner2.util.interfaces.ProfileLink;
import mim.com.dc3scanner2.util.models.Trabajador;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements ProfileLink {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Trabajador trabajador = null;

    private TextView nombreHeader;
    private TextView puestoHeader;
    private TextView areaHeader;

    private TextView nombreProfile;
    private TextView seguroProfile;
    private TextView edadProfile;
    private TextView telProfile;

    private CircleImageView profileImageView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(Trabajador param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
            // trabajador = getArguments().getParcelable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);

    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.showFloatingButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nombreHeader = view.findViewById(R.id.nameHeaderProfile);
        puestoHeader = view.findViewById(R.id.puestoHeaderProfile);
        areaHeader = view.findViewById(R.id.areaHeaderProfile);

        nombreProfile = view.findViewById(R.id.nameProfile);
        seguroProfile = view.findViewById(R.id.seguroProfile);
        edadProfile = view.findViewById(R.id.edadProfile);
        telProfile = view.findViewById(R.id.telProfile);

        profileImageView = view.findViewById(R.id.profile);


        trabajador = mListener.getTrabajador();
        if (trabajador == null) {
            nombreHeader.setText(R.string.empty);
            puestoHeader.setText(R.string.empty);
            areaHeader.setText(R.string.empty);
            nombreProfile.setText(R.string.empty);
            seguroProfile.setText(R.string.empty);
            edadProfile.setText(R.string.empty);
            telProfile.setText(R.string.empty);
        } else {
            sync();
        }

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

    @Override
    public void sync() {
        ScannerActivity activity = (ScannerActivity) getActivity();
        if (activity != null) {
            trabajador = activity.getTrabajador();
        }
        if (trabajador == null) {
            nombreHeader.setText(R.string.empty);
            puestoHeader.setText(R.string.empty);
            areaHeader.setText(R.string.empty);
            nombreProfile.setText(R.string.empty);
            seguroProfile.setText(R.string.empty);
            edadProfile.setText(R.string.empty);
            telProfile.setText(R.string.empty);
        } else {
            nombreHeader.setText(trabajador.getNombreCompleto());
            puestoHeader.setText(trabajador.getPuestoIdpuesto().getNombrePuesto());
            areaHeader.setText(trabajador.getAreaIdarea().getNombre());
            nombreProfile.setText(trabajador.getEmpresaIdempresa().getNombre());
            seguroProfile.setText(trabajador.getNss());
            edadProfile.setText(String.valueOf(trabajador.getEdad()));
            telProfile.setText(trabajador.getEstatus());

            if (trabajador.getImagen() != null) {
                Picasso.get().load("https://node37201-dc3-2018.jl.serv.net.mx/dc3BackEnd2/webresources/com.mim.entities.trabajador/api/" + trabajador.getIdtrabajador()).fit().
                        into(profileImageView);
            } else {
                if (Picasso.get() != null) {
                    Picasso.get().load(R.drawable.man).fit().
                            into(profileImageView);
                }
            }
        }
    }

    @Override
    public void updateProfilePic() {
        Picasso.get().load("https://node37201-dc3-2018.jl.serv.net.mx/dc3BackEnd2/webresources/com.mim.entities.trabajador/api/" + trabajador.getIdtrabajador()).fit().
                into(profileImageView);
    }

    @Override
    public Trabajador shareProfile() {
        return trabajador;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.profile_menu, menu);
    }

    @Override
    public void onStop() {
        super.onStop();
        mListener.hideFloatingButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.crear_permiso:
                if (trabajador != null) {
                    mListener.createPermiso(trabajador);
                } else {
                    Toast.makeText(getContext(), "escanea codigo", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sanciones_profile:
                if (trabajador != null) {
                    mListener.showSanciones(trabajador.getIdtrabajador());
                } else {
                    Toast.makeText(getContext(), "escanea codigo", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.subir_foto:
                if (trabajador != null) {
                    mListener.launchProfilePictureCaptureFragment(trabajador);
                } else {
                    Toast.makeText(getContext(), "escanea codigo", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
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

        void createPermiso(Trabajador trabajador);

        Trabajador getTrabajador();

        void showSanciones(Integer idtrabajador);

        void launchProfilePictureCaptureFragment(Trabajador trabajador);

        void showFloatingButton();

        void hideFloatingButton();
    }
}
