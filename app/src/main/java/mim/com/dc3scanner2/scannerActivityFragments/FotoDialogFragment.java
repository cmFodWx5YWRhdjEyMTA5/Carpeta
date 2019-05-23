package mim.com.dc3scanner2.scannerActivityFragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import mim.com.dc3scanner2.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FotoDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FotoDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public interface DialogConsumer {
        public void consumeDialog(String title, String descripcion);

        public void updateModel(String title, String descripcion, int posicion);
    }

    DialogConsumer consumer;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String POSICION = "ADSDCX";

    // TODO: Rename and change types of parameters
    private String mParam1 = null;
    private String mParam2 = null;
    private int pos;

    public FotoDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FotoDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FotoDialogFragment newInstance(String param1, String param2, int position) {
        FotoDialogFragment fragment = new FotoDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putInt(POSICION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            pos = getArguments().getInt(POSICION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.fragment_foto_detail, container, false);

        setUpDialogDimension(view);
        controlSetUp(view);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        consumer = (DialogConsumer) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        consumer = null;
    }

    private void controlSetUp(View view) {
        final EditText titleField = (EditText) view.findViewById(R.id.titleDialog);
        final EditText descriptionField = (EditText) view.findViewById(R.id.descriptionDialog);

        if (!(mParam1 == null && mParam2 == null)) {
            titleField.setText(mParam1);
            descriptionField.setText(mParam2);
        }

        Button aceptar = (Button) view.findViewById(R.id.aceptar_btn);
        aceptar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleField.getText().toString().length() > 0 && descriptionField.getText().toString().length() > 0) {
                    if (consumer != null) {
                        if (mParam1 == null && mParam2 == null) {
                            consumer.consumeDialog(titleField.getText().toString(), descriptionField.getText().toString());
                        } else {
                            //editando objeto
                            consumer.updateModel(titleField.getText().toString(), descriptionField.getText().toString(), pos);
                        }
                    }
                    FotoDialogFragment.this.dismiss();
                }
            }
        });
        Button cancelBtn = (Button) view.findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                FotoDialogFragment.this.dismiss();
            }
        });
    }

    private void setUpDialogDimension(View view) {
        DisplayMetrics metrics = new DisplayMetrics();

        final WindowManager windowManager = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);

        windowManager.getDefaultDisplay().getMetrics(metrics);

        int width = (int) (metrics.widthPixels * 0.80);

        FrameLayout framito = (FrameLayout) view.findViewById(R.id.frammito);
        framito.setMinimumWidth(width);

        LinearLayout layout = (LinearLayout) framito.getChildAt(0);
        int size = layout.getChildCount();

        for (int i = 0; i < size; i++) {
            View child = layout.getChildAt(i);
            child.setMinimumWidth(width);
        }
    }


}
