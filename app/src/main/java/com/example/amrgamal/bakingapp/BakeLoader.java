package com.example.amrgamal.bakingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.amrgamal.bakingapp.APIData.Bakes;
import com.example.amrgamal.bakingapp.Utiles.BakesUtiles;

import java.util.ArrayList;

/**
 * Created by AmrGamal on 23/03/2019.
 */

public class BakeLoader extends AsyncTaskLoader<ArrayList<Bakes>> {

    private final String url;

    public BakeLoader(String Url, Context context) {
        super(context);
        url = Url;

    }

    @Override
    public ArrayList<Bakes> loadInBackground() {
        if (url == null)
            return null;
        return BakesUtiles.fetchdata(url);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
