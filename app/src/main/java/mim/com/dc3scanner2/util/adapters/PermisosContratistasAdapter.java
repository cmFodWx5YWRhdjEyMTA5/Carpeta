package mim.com.dc3scanner2.util.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.interfaces.ListenerClick;
import mim.com.dc3scanner2.util.models.PermisoTrabajo;

public class PermisosContratistasAdapter extends RecyclerView.Adapter<PermisosContratistasAdapter.ViewHolder> {
    private final List<PermisoTrabajo> mDataset;
    private final ListenerClick listener;
    private Transition mListener;

    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        LinearLayout layout;
        TextView nombreCompañia;
        TextView descripcion;
        TextView area;
        TextView sub;
        Button cerrarBtn;

        public ViewHolder(View v) {
            super(v);
            layout = (LinearLayout) v;
            nombreCompañia = layout.findViewById(R.id.nombre_compañia_list_item_permiso_contra);
            descripcion = layout.findViewById(R.id.descripcion_permiso_contra);
            descripcion.setEnabled(false);
            area = layout.findViewById(R.id.area_permiso_contra);
            area.setEnabled(false);
            sub = layout.findViewById(R.id.subarea_permiso_contra);
            sub.setEnabled(false);
            cerrarBtn = layout.findViewById(R.id.cerrar_permiso_btn_contra);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PermisosContratistasAdapter(List<PermisoTrabajo> myDataset, ListenerClick listener, Transition mListener) {
        this.mDataset = myDataset;
        this.listener = listener;
        this.mListener=mListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PermisosContratistasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.permiso_list_item_contratistas, parent, false);
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
        PermisoTrabajo permiso = mDataset.get(position);
        holder.nombreCompañia.setText(permiso.getTrabajadorIdtrabajador().getEmpresaIdempresa().getNombre());
        holder.descripcion.setText(permiso.getDescripcion());

        holder.sub.setText(permiso.getSubareaIdsubarea().getNombre());
        holder.area.setText(permiso.getAreaIdarea().getNombre());
        holder.cerrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mListener.close(position);
            }
        });
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

    public interface Transition{
        public void close(int pos);
    }
}
