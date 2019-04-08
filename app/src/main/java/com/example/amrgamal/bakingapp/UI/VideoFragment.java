package com.example.amrgamal.bakingapp.UI;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amrgamal.bakingapp.APIData.Steps;
import com.example.amrgamal.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by AmrGamal on 01/04/2019.
 */

public class VideoFragment extends Fragment {

    private ArrayList<Steps> arrayList;
    private int position ;
    private static Long time = Long.valueOf(0);
    private SimpleExoPlayer mExoPlayer;
    @BindView(R.id.exoplayer)
    SimpleExoPlayerView mPlayerView;
    @BindView(R.id.no_video)
    ImageView no_video;
    public VideoFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.videofragment,container,false);
        ButterKnife.bind(this,view);


        if(savedInstanceState != null){
            arrayList = savedInstanceState.getParcelableArrayList("mySteps");
            position = savedInstanceState.getInt("pos");
            time = savedInstanceState.getLong("time");
            SharedPreferences.Editor editor = getContext().getSharedPreferences("shared", MODE_PRIVATE).edit();
            editor.putLong("time", time);
            editor.apply();
        }

        if(arrayList != null){
            TextView textView = view.findViewById(R.id.text);
            textView.setText(String.valueOf(arrayList.get(position).description));

            if(mExoPlayer != null){
                mExoPlayer.setPlayWhenReady(false);
                mExoPlayer.release();
                mExoPlayer.stop();
                mExoPlayer = null;
            }
            if (arrayList.get(position).videoURL.equals(""))
            {
                mPlayerView.setVisibility(View.GONE);
                no_video.setVisibility(View.VISIBLE);
                if (!arrayList.get(position).thumbnailURL.equals("")) {
                    Picasso.with(getContext()).load(arrayList.get(position).thumbnailURL).into(no_video);
                }

            }
            else {
                mPlayerView.setVisibility(View.VISIBLE);
                no_video.setVisibility(View.GONE);


                int orientation = getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    //  landscape mode
                    ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams
                            (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
                    textView.setVisibility(View.GONE);
                    mPlayerView.setLayoutParams(params);

                }
            }
        }

        return view;
    }

    public void showData(ArrayList<Steps> stepsItems , int mposition){

        arrayList = stepsItems ;
        position = mposition;
    }

    private void initializePlayer(Uri mediaUri , Long time) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this.getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);


            String userAgent = Util.getUserAgent(this.getContext(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    this.getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.seekTo(time);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        time = getContext().getSharedPreferences("shared", MODE_PRIVATE).getLong("time",Long.valueOf(0));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        if (mExoPlayer != null) {
            time = mExoPlayer.getCurrentPosition();
            mExoPlayer.setPlayWhenReady(false);
            mExoPlayer.release();
            mExoPlayer = null;
        }
        super.onPause();
    }


    @Override
    public void onStart() {
        initializePlayer(Uri.parse(arrayList.get(position).videoURL),time);
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("mySteps",arrayList);
        outState.putInt("pos",position);
        outState.putLong("time",time);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        SharedPreferences.Editor sharedPreferences = getContext().getSharedPreferences("shared", MODE_PRIVATE).edit();
        sharedPreferences.putLong("time",Long.valueOf(0));
        sharedPreferences.apply();
        super.onStop();
    }}
