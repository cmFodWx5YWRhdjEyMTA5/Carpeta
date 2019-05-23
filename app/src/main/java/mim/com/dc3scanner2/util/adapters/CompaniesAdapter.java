package mim.com.dc3scanner2.util.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mim.com.dc3scanner2.R;
import mim.com.dc3scanner2.util.interfaces.ListenerClick;
import mim.com.dc3scanner2.util.models.Empresa;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.ViewHolder> {
    private final List<Empresa> mDataset;
    private final ListenerClick listener;
    private List<Empresa> copyList;

    public void resetList(List<Empresa> list) {
        mDataset.clear();
        mDataset.addAll(copyList);
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        LinearLayout layout;
        TextView nombreCompañia;
        TextView cantidad;
        TextView servicios;

        public ViewHolder(View v) {
            super(v);
            layout = (LinearLayout) v;
            nombreCompañia = layout.findViewById(R.id.nombre_compañia_list_item);
            cantidad = layout.findViewById(R.id.cantidad);
            servicios = layout.findViewById(R.id.servicios_list_item);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CompaniesAdapter(List<Empresa> myDataset, ListenerClick listener) {
        this.mDataset = myDataset;
        this.listener = listener;
        if (myDataset != null) {
            copyList = new ArrayList<>();
            copyList.addAll(myDataset);
        }
    }

    public void filter(String text) {
        mDataset.clear();
        if (text.isEmpty()) {
            mDataset.addAll(copyList);
        } else {
            text = text.toLowerCase();
            for (Empresa item : copyList) {
                if (item.getNombre().toLowerCase().contains(text)) {
                    mDataset.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CompaniesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_list_item, parent, false);
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
        Empresa empresa = mDataset.get(position);
        if (empresa != null) {
            holder.servicios.setText(empresa.getServicios());
            holder.cantidad.setText(String.valueOf(empresa.getCantidad()));
            holder.nombreCompañia.setText(empresa.getNombre());
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
