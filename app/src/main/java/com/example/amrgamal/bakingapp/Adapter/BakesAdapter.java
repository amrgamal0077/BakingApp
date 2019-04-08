package com.example.amrgamal.bakingapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amrgamal.bakingapp.APIData.Bakes;
import com.example.amrgamal.bakingapp.R;

import java.util.ArrayList;

/**
 * Created by AmrGamal on 23/03/2019.
 */

public class BakesAdapter extends RecyclerView.Adapter<BakesAdapter.recyclerVH> {

    private final ArrayList<Bakes> bakes;
private final Context context;
private final itemclick itemclick;
public BakesAdapter(ArrayList<Bakes> name, Context context, itemclick itemclick) {
        this.bakes = name;
        this.context = context;
        this.itemclick=itemclick;
        }
@NonNull
@Override
public BakesAdapter.recyclerVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bake_item, viewGroup, false);
        return new BakesAdapter.recyclerVH(view);        }

@Override
public void onBindViewHolder(@NonNull BakesAdapter.recyclerVH holder,final int position) {
        TextView name=holder.itemView.findViewById(R.id.name) ;
        TextView servings=holder.itemView.findViewById(R.id.servings) ;
        name.setText(bakes.get(position).name);
        servings.setText(String.valueOf( bakes.get(position).servings));
        }

@Override
public int getItemCount() {
        return bakes.size();
        }
public interface  itemclick{
    void onItemClicked(int position);
}
class recyclerVH extends RecyclerView.ViewHolder implements View.OnClickListener {
    public recyclerVH(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemclick.onItemClicked(getAdapterPosition());
    }
}
}

