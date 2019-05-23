package mim.com.dc3scanner2.util.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.interfaces.ListenerClick;
import mim.com.dc3scanner2.util.models.MapaCertificaciones;
import mim.com.dc3scanner2.util.models.Trabajador;

public class PersonalCriticAdapter extends RecyclerView.Adapter<PersonalCriticAdapter.ViewHolder> {
    private ListenerClick listener;
    private List<Trabajador> mDataset;
    private int criticosValor;
    private int vencidosValor;

    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View layout;
        public TextView nombres;
        public TextView apellidos;
        public TextView puesto;

        public TextView vencidos;
        public TextView criticos;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            nombres = v.findViewById(R.id.nombre_lista_personal_critic);
            apellidos = v.findViewById(R.id.apellidos_lista_personal_critic);
            puesto = v.findViewById(R.id.ocupacion_lista_personal_critic);
            imageView = v.findViewById(R.id.imageView3_critic);
            vencidos = v.findViewById(R.id.vencidos_view);
            criticos = v.findViewById(R.id.criticos_view);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PersonalCriticAdapter(List<Trabajador> myDataset, ListenerClick listener) {
        mDataset = myDataset;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PersonalCriticAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_critic_item, parent, false);

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
        Trabajador worker = mDataset.get(position);
        clasifyCertificates(worker);

        holder.vencidos.setText(String.valueOf(vencidosValor));
        holder.criticos.setText(String.valueOf(criticosValor));
        String[] arreglo = worker.getNombreCompleto().split(" ");
        if (arreglo.length == 4) {
            holder.nombres.setText(arreglo[0] + " " + arreglo[1]);
            holder.apellidos.setText(arreglo[2] + " " + arreglo[3]);
        } else {
            holder.nombres.setText(arreglo[0]);
            String apellidos = "";
            for (int i = 1; i <= (arreglo.length - 1); i++) {
                apellidos = apellidos + arreglo[i] + " ";
            }
            holder.apellidos.setText(apellidos);
        }

        holder.puesto.setText(worker.getPuestoIdpuesto().getNombrePuesto());

        if (worker.getImagen() != null) {
            Picasso.get().load("https://node37201-dc3-2018.jl.serv.net.mx/dc3BackEnd2/webresources/com.mim.entities.trabajador/api/" + worker.getIdtrabajador()).fit().
                    into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.default_for_people);
        }
    }

    private void clasifyCertificates(Trabajador cv) {

        criticosValor = 0;
        vencidosValor = 0;

        //if(cv.getMapaCertificacionesList() != null) {
            List<MapaCertificaciones> certificacionesList = cv.getMapaCertificacionesList();

            for (MapaCertificaciones mp : certificacionesList) {

                int dayX = Integer.parseInt((String) DateFormat.format("dd", mp.getFechaEjecucion())); // 20
                int mX = Integer.parseInt((String) DateFormat.format("MM", mp.getFechaEjecucion())); // 06
                int yearX = Integer.parseInt(((String) DateFormat.format("yyyy", mp.getFechaEjecucion()))); // 2013

                int dayCurrent = Integer.parseInt((String) DateFormat.format("dd", new Date())); // 20
                int mCurrent = Integer.parseInt((String) DateFormat.format("MM", new Date())); // 06
                int yearCurrent = Integer.parseInt(((String) DateFormat.format("yyyy", new Date()))); // 2013

                int res = mCurrent - mX;
                int resDay = dayCurrent - dayX;


                switch (yearCurrent - yearX) {
                    case 1:
                        if ((res <= -2 && res < 0)) {
                            //criticos
                            criticosValor++;
                        } else if (res > 0) {
                            //vencidos
                            vencidosValor++;
                        } else if (res == 0) {
                            //verifica dia
                            if (resDay < 0) {
                                criticosValor++;
                            } else {
                                vencidosValor++;
                            }
                        }
                        break;
                    case 0:
                        break;
                    default:
                        //vencidos
                        if ((yearCurrent - yearX) > 0) {
                            vencidosValor++;
                        }
                        break;
                }
            }
        //}

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
