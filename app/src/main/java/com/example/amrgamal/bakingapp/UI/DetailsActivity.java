package com.example.amrgamal.bakingapp.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.amrgamal.bakingapp.APIData.Bakes;
import com.example.amrgamal.bakingapp.APIData.Steps;
import com.example.amrgamal.bakingapp.R;
import com.example.amrgamal.bakingapp.Utiles.SimpleIdlingResource;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    int position ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bakes bakes ;
        Intent intent=getIntent();
        bakes  = intent.getParcelableExtra("bake");

        Toast.makeText(this, String.valueOf(bakes.ingredients.size()) , Toast.LENGTH_SHORT).show();

        Details_fragment details_fragment = new Details_fragment();
        Details_fragment.fetch(bakes.steps,bakes.ingredients);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container,details_fragment).commit();


    }
    public void fire(ArrayList<Steps> stepsItems , int mposition)
    {
        if (findViewById(R.id.steps)  == null)
        {
            Intent intent = new Intent(this,StepsVideoActivity.class);
            intent.putParcelableArrayListExtra("array",stepsItems);
            intent.putExtra("position",mposition);
            startActivity(intent);
        }
        else {
            VideoFragment videoFragment = new VideoFragment();
            videoFragment.showData(stepsItems,mposition);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.steps,videoFragment).commit();

        }


    }
    @Nullable
    private SimpleIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
}
