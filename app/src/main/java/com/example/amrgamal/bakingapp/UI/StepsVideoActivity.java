package com.example.amrgamal.bakingapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.amrgamal.bakingapp.APIData.Steps;
import com.example.amrgamal.bakingapp.R;

import java.util.ArrayList;

public class StepsVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_video);
        Intent intent = getIntent();
        ArrayList<Steps> arrayList = intent.getParcelableArrayListExtra("array");
        int position = intent.getIntExtra("position",-1);

        VideoFragment stepsfragment = new VideoFragment();
        stepsfragment.showData(arrayList,position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.steps_videos, stepsfragment)
                .commit();
    }

}
