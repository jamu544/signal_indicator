package android.com.jumpco.io.signalindicator.adapters;

import android.com.jumpco.io.signalindicator.R;
import android.com.jumpco.io.signalindicator.databinding.ListItemBinding;
import android.com.jumpco.io.signalindicator.model.WifiModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WifiRecyclerViewAdapter extends RecyclerView.Adapter<WifiRecyclerViewAdapter.ViewHolder>{

    private ArrayList<WifiModel> list;
    private Context context;
    public WifiRecyclerViewAdapter(Context context,ArrayList<WifiModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WifiModel pojo = list.get(position);
        holder.nameTextView.setText(pojo.name);
        holder.wifiStrengthTextView.setText(String.valueOf(pojo.wifiStrength)+" dBm");
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void notifyDataStateChanged() {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView wifiStrengthTextView;

        private ListItemBinding listItemBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTxtView);
            wifiStrengthTextView = (TextView) itemView.findViewById(R.id.wifiTxtView);

        }
    }
}
