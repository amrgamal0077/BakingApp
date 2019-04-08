package com.example.amrgamal.bakingapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amrgamal.bakingapp.APIData.Steps;
import com.example.amrgamal.bakingapp.R;

import java.util.List;

/**
 * Created by AmrGamal on 26/03/2019.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoVH> {


    List<Steps> steps;
    Context context;

    private VideoClickListener videoClickListener;

    public interface VideoClickListener {
        public void onItemClick(int position);
    }

    public VideoAdapter(Context context,List<Steps> steps,VideoClickListener videoClickListener){
        this.context = context;
        this.steps = steps;
        this.videoClickListener = videoClickListener;
    }

    @NonNull
    @Override
    public VideoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        return new VideoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoVH holder, int position) {
        holder.videoName.setText(steps.get(position).shortDescription);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class VideoVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView videoName;
        public VideoVH(View itemView) {
            super(itemView);
            videoName = itemView.findViewById(R.id.video_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            videoClickListener.onItemClick(getAdapterPosition());
        }
    }
}
