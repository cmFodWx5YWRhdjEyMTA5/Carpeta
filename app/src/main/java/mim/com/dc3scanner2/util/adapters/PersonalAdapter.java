package mim.com.dc3scanner2.util.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.interfaces.ListenerClick;
import mim.com.dc3scanner2.util.models.Empresa;
import mim.com.dc3scanner2.util.models.Trabajador;

public class PersonalAdapter extends RecyclerView.Adapter<PersonalAdapter.ViewHolder> {
    private ListenerClick listener;
    private List<Trabajador> mDataset;
    private List<Trabajador> copyList;

    public void resetList() {
        mDataset.clear();
        mDataset.addAll(copyList);
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View layout;
        public TextView nombres;
        public TextView apellidos;
        public TextView puesto;

        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            nombres = v.findViewById(R.id.nombre_lista_personal);
            apellidos = v.findViewById(R.id.apellidos_lista_personal);
            puesto = v.findViewById(R.id.ocupacion_lista_personal);
            imageView = v.findViewById(R.id.imageView3);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PersonalAdapter(List<Trabajador> myDataset, ListenerClick listener) {
        mDataset = myDataset;
        this.listener = listener;
        copyList = new ArrayList<>(myDataset);
    }

    public void filter(String text) {
        mDataset.clear();
        if (text.isEmpty()) {
            mDataset.addAll(copyList);
        } else {
            text = text.toLowerCase();
            for (Trabajador item : copyList) {
                if (item.getNombreCompleto().toLowerCase().contains(text)) {
                    mDataset.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PersonalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.personal_list_item, parent, false);

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
        String[] arreglo = mDataset.get(position).getNombreCompleto().split(" ");
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

        holder.puesto.setText(mDataset.get(position).getPuestoIdpuesto().getNombrePuesto());

        if (mDataset.get(position).getImagen() != null) {
            Picasso.get().load("https://node37201-dc3-2018.jl.serv.net.mx/dc3BackEnd2/webresources/com.mim.entities.trabajador/api/" + mDataset.get(position).getIdtrabajador()).fit().
                    into(holder.imageView);
        } else {
            // Picasso.get().load("https://node37201-dc3-2018.jl.serv.net.mx/dc3BackEnd2/webresources/com.mim.entities.trabajador/api/281").fit().
            //       into(holder.imageView);
            holder.imageView.setImageResource(R.drawable.default_for_people);
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
