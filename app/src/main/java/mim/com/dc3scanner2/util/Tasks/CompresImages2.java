package mim.com.dc3scanner2.util.Tasks;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import mim.com.dc3scanner2.util.ImageFileFilter;
import mim.com.dc3scanner2.util.interfaces.CompresConsumer;
import mim.com.dc3scanner2.util.models.Fotos;


/**
 * Created by marcoisaac on 5/20/2016.
 */
public class CompresImages2 extends AsyncTask<Fotos, Void, Boolean> {


    private int codigo;


    private CompresConsumer consumer;
    private Context context;

    public CompresImages2(CompresConsumer consumer, int codigo, Context context) {
        this.consumer = consumer;
        this.codigo = codigo;
        this.context = context;


    }


    private void showUnsupportedExceptionDialog() {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("dispositivo no compatible")
                .setMessage("dispositivo no compatible")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    @Override
    protected Boolean doInBackground(Fotos... params) {

        for (Fotos foto : params) {

            String ruta = foto.getArchivo();
            File rep = new File(ruta);
            if (!new ImageFileFilter(rep).accept(rep)) {
                //proccessVideo(ruta);
                continue;
            }
            if (getFileSizeInMB(foto.getArchivo()) > 1) {
                Bitmap bit = BitmapFactory.decodeFile(ruta);
                if (bit != null) {
                    try {

                        OutputStream stream = new FileOutputStream(rep);
                        bit.compress(Bitmap.CompressFormat.JPEG, 30, stream);
                        stream.flush();
                        stream.close();
                        Log.d("ASYNC_TASK", rep.getName());

                    } catch (IOException e) {
                        return false;
                    }
                }
            }
        }
        return true;
    }



    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (consumer != null) {
            consumer.compresResultProfilePic(aBoolean, codigo);
        }
    }

    public float getFileSizeInMB(String fileName) {
        float ret = getFileSizeInBytes(fileName);
        ret = ret / (float) (1024 * 1024);
        return ret;
    }

    public long getFileSizeInBytes(String fileName) {
        long ret = 0;
        File f = new File(fileName);
        if (f.isFile()) {
            return f.length();
        } else if (f.isDirectory()) {
            File[] contents = f.listFiles();
            for (int i = 0; i < contents.length; i++) {
                if (contents[i].isFile()) {
                    ret += contents[i].length();
                } else if (contents[i].isDirectory()) {
                    ret += getFileSizeInBytes(contents[i].getPath());
                }
            }
        }
        return ret;
    }
}
