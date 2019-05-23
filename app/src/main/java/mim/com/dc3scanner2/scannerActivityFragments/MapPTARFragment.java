package mim.com.dc3scanner2.scannerActivityFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.OnSwipeTouchListener;
import mim.com.dc3scanner2.util.Posicion;
import mim.com.dc3scanner2.util.custom.MapView4;
import mim.com.dc3scanner2.util.custom.MapView4PTAR;
import mim.com.dc3scanner2.util.custom.MapView4PTARImprovedMap;
import mim.com.dc3scanner2.util.interfaces.CanvasCommand;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapPTARFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapPTARFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapPTARFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private CanvasCommand canvas;
    private ImageView imageView;

    public MapPTARFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapPTARFragment newInstance(String param1, String param2) {
        MapPTARFragment fragment = new MapPTARFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.fragment_map_ptar, container, false);

        canvas = new MapView4PTARImprovedMap(this.getContext(), R.raw.ptar_nuevo);
        ((View) canvas).setOnTouchListener(new OnSwipeTouchListener(this.getContext()) {
            //Context ctx = MapFragment.this.getContext();

            public void onSwipeTop() {
                canvas.moveBottom();

                //Toast.makeText(ctx, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                canvas.moveLeft();
                //Toast.makeText(ctx, "right", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeLeft() {
                canvas.moveRight();
                //Toast.makeText(ctx, "left", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeBottom() {
                canvas.moveTop();
                //Toast.makeText(ctx, "bottom", Toast.LENGTH_SHORT).show();
            }
        });

        mListener.linkCanvas(canvas);
        view.addView((View) canvas);
        ((View) canvas).setVisibility(View.GONE);

        imageView = view.findViewById(R.id.imaview_map_ptar);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("imageview dimensions: ", imageView.getWidth() + " x " + imageView.getHeight());
                Log.d("touch event: ", " x: " + motionEvent.getX() + " y: " + motionEvent.getY());
                //Toast.makeText(MapPTARFragment.this.getContext(), "x: " + motionEvent.getX() + " y: " + motionEvent.getY(), Toast.LENGTH_LONG).show();
                imageView.setVisibility(View.GONE);
                ((View) canvas).setVisibility(View.VISIBLE);
                canvas.updatePosition(motionEvent.getX(),motionEvent.getY());
                return false;
            }
        });
        Picasso.get().load(R.raw.ptar_nuevo_mini).fit().
                into(imageView);
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
    public void onResume() {
        super.onResume();
        mListener.showBotoncitos();
    }

    @Override
    public void onStop() {
        super.onStop();
        mListener.hideBotoncitos();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_map, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.full:
                imageView.setVisibility(View.VISIBLE);
                ((View) canvas).setVisibility(View.GONE);
                break;
            case R.id.marker:
                List<Integer> posList = canvas.markPos();
                if (posList != null) {
                    Gson gson = new Gson();
                    Posicion pos = new Posicion(posList.get(0), posList.get(1));
                    //Toast.makeText(getContext(), "posicion guardada. " + " X: " + pos.getxPos() + " Y: " + pos.getyPos(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(), "posicion guardada. ", Toast.LENGTH_LONG).show();
                    mListener.saveMarker(gson.toJson(pos));
                    mListener.showPermisoForm();
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

        void linkCanvas(CanvasCommand canvas);

        void saveMarker(String json);

        void hideBotoncitos();

        void showBotoncitos();

        void showPermisoForm();
    }
}
