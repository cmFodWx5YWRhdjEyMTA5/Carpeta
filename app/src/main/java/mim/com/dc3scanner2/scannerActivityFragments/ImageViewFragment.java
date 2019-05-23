package mim.com.dc3scanner2.scannerActivityFragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.ImageFileFilter;


public class ImageViewFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
     private String mParam1;
    private String mParam2;

    //private File mParam1;


    public ImageViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageViewFragment newInstance(String param1, String param2) {
        ImageViewFragment fragment = new ImageViewFragment();
        //fragment.mParam1 = param1;
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
        final View view = inflater.inflate(R.layout.fragment_image_view, container, false);
        Toast.makeText(getContext(), "imagen: " + mParam1, Toast.LENGTH_LONG).show();

        final ImageView image = (ImageView) view.findViewById(R.id.img_fragment);
        final VideoView video = (VideoView) view.findViewById(R.id.video_view);
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });
        final File fil = new File(mParam1);//

        if (new ImageFileFilter(fil).accept(fil)) {
            image.setVisibility(View.VISIBLE);
            video.setVisibility(View.GONE);
            isImage(view, image, fil);
        } else {
            view.findViewById(R.id.rotate_btn).setVisibility(View.GONE);
            image.setVisibility(View.GONE);
            video.setVisibility(View.VISIBLE);
            video.setVideoPath(fil.getPath());
            video.start();
        }

        return view;
    }

    private void isImage(View view, final ImageView image, final File fil) {
        Picasso.get().load(fil).
                into(image);

        final Button btn = (Button) view.findViewById(R.id.rotate_btn);
        btn.setVisibility(View.GONE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Context context = ImageViewFragment.this.getContext();

                if (context == null) {
                    return;
                }

                Toast.makeText(context, "rotando... ", Toast.LENGTH_SHORT).show();
                btn.setEnabled(false);
                new AsyncTask<Void, Void, Boolean>() {

                    @Override
                    protected Boolean doInBackground(Void... params) {
                        Bitmap bmp = BitmapFactory.decodeFile(fil.getPath());

                        Matrix matrix = new Matrix();
                        matrix.postRotate(90);
                        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);

                        FileOutputStream fOut;
                        try {

                            fOut = new FileOutputStream(fil);
                            bmp.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                            fOut.flush();
                            fOut.close();
                            Log.d("hola", "sadsadasdqw dasdasd as");


                            return true;

                        } catch (FileNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                            return false;
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            return false;
                        }

                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);
                        btn.setEnabled(true);
                        if (aBoolean) {
                            Picasso picasso = Picasso.get();
                            picasso.invalidate(fil);
                            picasso.load(fil).into(image);
                        }
                    }
                }.execute();


            }
        });
    }

}
