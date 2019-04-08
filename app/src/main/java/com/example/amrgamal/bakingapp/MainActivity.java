package com.example.amrgamal.bakingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.LoaderManager;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.amrgamal.bakingapp.APIData.Bakes;
import com.example.amrgamal.bakingapp.Adapter.BakesAdapter;
import com.example.amrgamal.bakingapp.UI.DetailsActivity;
import com.example.amrgamal.bakingapp.Utiles.SimpleIdlingResource;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Bakes>>,BakesAdapter.itemclick {

    private final String url="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private ArrayList<Bakes> bakes=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(0,null,this);

    }


    @Override
    public android.content.Loader<ArrayList<Bakes>> onCreateLoader(int id, Bundle args) {
        return new BakeLoader(url,this);
    }

    @Override
    public void onLoadFinished(android.content.Loader<ArrayList<Bakes>> loader, ArrayList<Bakes> data) {
        bakes=data;

          if(findViewById(R.id.main_gridView) != null){
              // tablet mode
              RecyclerView recyclerView = findViewById(R.id.main_gridView);
              BakesAdapter bakesAdapter= new BakesAdapter(data,this,this);
              recyclerView.setLayoutManager(new GridLayoutManager(this,3));
              recyclerView.setAdapter(bakesAdapter);

          }
          else{
        RecyclerView recyclerView=findViewById(R.id.linear);
        BakesAdapter bakesAdapter= new BakesAdapter(data,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bakesAdapter);}

    }

    @Override
    public void onLoaderReset(android.content.Loader<ArrayList<Bakes>> loader) {

    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        Bakes bake = bakes.get(position);
        intent.putParcelableArrayListExtra("bake",bake);
        startActivity(intent);
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
