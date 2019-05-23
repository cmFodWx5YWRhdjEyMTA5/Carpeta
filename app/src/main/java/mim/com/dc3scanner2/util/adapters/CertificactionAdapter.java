package mim.com.dc3scanner2.util.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.Date;
import java.util.List;


import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.custom.Led;
import mim.com.dc3scanner2.util.interfaces.ListenerClick;
import mim.com.dc3scanner2.util.models.MapaCertificaciones;

public class CertificactionAdapter extends RecyclerView.Adapter<CertificactionAdapter.ViewHolder> {
    private ListenerClick listener;
    private List<MapaCertificaciones> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout layout;

        public ViewHolder(LinearLayout v) {
            super(v);
            layout = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CertificactionAdapter(List<MapaCertificaciones> myDataset, ListenerClick listener) {
        mDataset = myDataset;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CertificactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.certification_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // holder.mTextView.setText(mDataset.);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(position);
            }
        });
        TextView view = holder.layout.findViewById(R.id.nombreCertificacionList);
        MapaCertificaciones certificacion = mDataset.get(position);
        view.setText(certificacion.getCertificaciones().getNombre());
        Led led = holder.layout.findViewById(R.id.estatusList);

        if (certificacion.getFechaEjecucion() != null) {
            int dayX = Integer.parseInt((String) DateFormat.format("dd", certificacion.getFechaEjecucion())); // 20
            int mX = Integer.parseInt((String) DateFormat.format("MM", certificacion.getFechaEjecucion())); // 06
            int yearX = Integer.parseInt(((String) DateFormat.format("yyyy", certificacion.getFechaEjecucion()))); // 2013

            int dayCurrent = Integer.parseInt((String) DateFormat.format("dd", new Date())); // 20
            int mCurrent = Integer.parseInt((String) DateFormat.format("MM", new Date())); // 06
            int yearCurrent = Integer.parseInt(((String) DateFormat.format("yyyy", new Date()))); // 2013

            int res = mX - mCurrent;
            int resDay = dayX - dayCurrent;


            switch (yearCurrent - yearX) {
                case 1:
                    if ((res <= 3 && res > 0)) {
                        led.setColor(Color.YELLOW);
                    } else if (res < 0) {
                        //vencidos
                        led.setColor(Color.RED);
                    } else if (res == 0) {
                        //verifica dia
                        if (resDay < 0) {
                            led.setColor(Color.RED);

                        } else {

                            led.setColor(Color.YELLOW);
                        }
                    } else {
                        led.setColor(Color.GREEN);
                    }
                    break;
                case 0:
                    led.setColor(Color.GREEN);
                    break;
                default:
                    //vencidos
                    if ((yearCurrent - yearX) > 0) {
                        led.setColor(Color.RED);
                    }
                    break;
            }

            if (certificacion.getTipo() != null) {
                if (certificacion.getTipo().equals("Operacion")) {
                    led.setColor(Color.GREEN);
                }
            }
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        } else {
            return 0;
        }
    }
}
