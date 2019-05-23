package mim.com.dc3scanner2.scannerActivityFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.interfaces.FragmentCommand;
import mim.com.dc3scanner2.util.models.Area;
import mim.com.dc3scanner2.util.models.Fotos;
import mim.com.dc3scanner2.util.models.PermisoTrabajo;
import mim.com.dc3scanner2.util.models.Subarea;
import mim.com.dc3scanner2.util.models.TipoActividad;
import mim.com.dc3scanner2.util.models.Trabajador;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterPermisionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterPermisionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterPermisionFragment extends Fragment implements FragmentCommand {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Trabajador mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Button btn;
    private Button btn2;
    private EditText descripcion;


    private AlertDialog.Builder builderSingle;
    private ArrayAdapter<Area> arrayAdapter;
    private ArrayAdapter<Subarea> arrayAdapter2;
    private Button btn3;
    private Button riesgoBtn;
    private Button directoFotos;
    private Button directoMapa;


    private String actividad = null;
    private Area departamento = null;
    private Subarea subarea = null;
    private String riesgoTrabajo = null;

    private Integer mapaSelected;

    public RegisterPermisionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterPermision.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterPermisionFragment newInstance(Trabajador param1, String param2) {
        RegisterPermisionFragment fragment = new RegisterPermisionFragment();
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
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        btn = view.findViewById(R.id.tipo_actividad_boton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.closeKeyboard();
                launchDialog();
            }
        });
        if (actividad != null) {
            btn.setText(actividad);
        }

        btn2 = view.findViewById(R.id.departamento_boton);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialogDepartamento();
            }
        });

        btn3 = view.findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialogSubArea();
            }
        });

        if (departamento != null) {
            btn2.setText(departamento.getNombre());
        }

        if (subarea != null) {
            btn3.setText(subarea.getNombre());
        }

        descripcion = view.findViewById(R.id.descripcion_permiso);

        riesgoBtn = view.findViewById(R.id.btn3Riesgo);
        riesgoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialogRiesgo();
            }
        });

        if (riesgoTrabajo != null) {
            riesgoBtn.setText(riesgoTrabajo);
        }

        directoFotos = view.findViewById(R.id.direct_fotos);
        directoFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.photo();
            }
        });
        directoFotos.setTextColor(Color.WHITE);
        directoFotos.setBackgroundColor(Color.rgb(57, 183, 205));


        directoMapa = view.findViewById(R.id.direct_Mapa);
        directoMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMapSelector();
            }
        });
        directoMapa.setTextColor(Color.WHITE);
        directoMapa.setBackgroundColor(Color.rgb(57, 183, 205));
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.hideFloatingButtons();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_permission, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.photo_register_permission:
                mListener.photo();
                break;
            case R.id.enviar_permiso:
                if (riesgoTrabajo == null) {
                    Toast.makeText(getContext(), "selecciona riesgo trabajo...", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (departamento == null) {
                    Toast.makeText(getContext(), "selecciona departamento...", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (subarea == null) {
                    Toast.makeText(getContext(), "selecciona subarea...", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (actividad == null) {
                    Toast.makeText(getContext(), "selecciona actividad...", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (descripcion.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "escribe descripcion....", Toast.LENGTH_SHORT).show();
                    return true;
                }

                PermisoTrabajo permiso = new PermisoTrabajo();
                permiso.setRiesgo(riesgoTrabajo);
                permiso.setAreaIdarea(departamento);
                permiso.setSubareaIdsubarea(subarea);
                permiso.setDescripcion(descripcion.getText().toString());
                permiso.setTipoActividadIdtipoActividad(new TipoActividad(actividad));
                mListener.uploadPermisoData(permiso);
                break;
            case R.id.map_plant:
                launchMapSelector();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void launchDialog() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
        //builderSingle.setIcon(R.drawable.);
        builderSingle.setTitle("Escoge Tipo Actividad");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Fuego");
        arrayAdapter.add("Lineas con fluidos");
        arrayAdapter.add("electricos");
        arrayAdapter.add("Espacio confinado");
        arrayAdapter.add("Altura");
        arrayAdapter.add("Otro");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn.setText(arrayAdapter.getItem(which));
                actividad = arrayAdapter.getItem(which);

            }
        });
        builderSingle.show();
    }

    public void launchMapSelector() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
        //builderSingle.setIcon(R.drawable.);
        builderSingle.setTitle("Escoge Lugar");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Planta");
        arrayAdapter.add("Bodega Externa");
        arrayAdapter.add("PTAR");


        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mapaSelected = which;
                mListener.setSelectedMap(mapaSelected);
                switch (mapaSelected) {
                    case 0:
                        mListener.launchMap();
                        break;
                    case 1:
                        mListener.launchMapBodega();
                        break;
                    case 2:
                        mListener.launchMapPTAR();
                        break;
                }

            }
        });
        builderSingle.show();
    }

    public void launchDialogDepartamento() {

        builderSingle = new AlertDialog.Builder(getContext());
        //builderSingle.setIcon(R.drawable.);
        builderSingle.setTitle("Escoge Area");
        arrayAdapter = new ArrayAdapter<Area>(getContext(), android.R.layout.select_dialog_singlechoice);


        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn2.setText(arrayAdapter.getItem(which).getNombre());
                departamento = arrayAdapter.getItem(which);
               /* String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(getContext());
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();*/
            }
        });
        mListener.findAllAreas();

    }

    private void launchDialogRiesgo() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
        //builderSingle.setIcon(R.drawable.);
        builderSingle.setTitle("Escoge Tipo Actividad");

        final ArrayAdapter<String> arrayAdapterRiesgo = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
        arrayAdapterRiesgo.add("Alto");
        arrayAdapterRiesgo.add("Medio");
        arrayAdapterRiesgo.add("Bajo");


        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapterRiesgo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                riesgoBtn.setText(arrayAdapterRiesgo.getItem(which));
                riesgoTrabajo = arrayAdapterRiesgo.getItem(which);
               /* String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(getContext());
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();*/
            }
        });
        builderSingle.show();
    }

    public void launchDialogSubArea() {
        if (departamento == null) {
            return;
        }
        builderSingle = new AlertDialog.Builder(getContext());
        //builderSingle.setIcon(R.drawable.);
        builderSingle.setTitle("Escoge Area");
        arrayAdapter2 = new ArrayAdapter<Subarea>(getContext(), android.R.layout.select_dialog_singlechoice);


        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn3.setText(arrayAdapter2.getItem(which).getNombre());
                subarea = arrayAdapter2.getItem(which);
               /* String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(getContext());
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();*/
            }
        });
        mListener.findSubAreas(departamento.getIdarea());

    }

    @Override
    public void executeCommand(int resultCode) {

    }

    @Override
    public List<Fotos> getUpdatedList() {
        return null;
    }

    @Override
    public void executeDialog(List<Area> list) {
        for (int i = 0; i < list.size(); i++) {
            arrayAdapter.add(list.get(i));
        }
        builderSingle.show();
    }

    @Override
    public void executeDialogSubArea(List<Subarea> list) {
        for (int i = 0; i < list.size(); i++) {
            arrayAdapter2.add(list.get(i));
        }
        builderSingle.show();
    }

    @Override
    public void updateRoute(String ruta) {

    }

    @Override
    public void syncAdapter() {

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

        void photo();

        void uploadPermisoData(PermisoTrabajo permiso);

        void findAllAreas();

        void findSubAreas(int id);

        void launchMap();

        void launchMapPTAR();

        void launchMapBodega();

        void hideFloatingButtons();

        void setSelectedMap(int mapa);

        void closeKeyboard();
    }
}
