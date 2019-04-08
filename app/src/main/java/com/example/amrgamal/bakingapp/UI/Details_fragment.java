package com.example.amrgamal.bakingapp.UI;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amrgamal.bakingapp.APIData.Bakes;
import com.example.amrgamal.bakingapp.APIData.Ingredients;
import com.example.amrgamal.bakingapp.APIData.Steps;
import com.example.amrgamal.bakingapp.Adapter.VideoAdapter;
import com.example.amrgamal.bakingapp.R;
import com.example.amrgamal.bakingapp.Utiles.AppWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AmrGamal on 26/03/2019.
 */

public class Details_fragment extends Fragment  implements VideoAdapter.VideoClickListener{

    public static ArrayList<Steps>steps= new ArrayList<>();
    public static ArrayList<Ingredients> ingredients= new ArrayList<>();

    @BindView(R.id.ingredients)
    TextView ingredient;

    @BindView(R.id.fab_widget)
    FloatingActionButton add;

    public Details_fragment(){

    }

    public static void fetch(ArrayList <Steps> stepsArrayList, ArrayList<Ingredients> ingredientsArrayList)
    {
        steps=stepsArrayList;
        ingredients=ingredientsArrayList;
        String s ="";
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bake_details,container,false);
        ButterKnife.bind(this,view);
        if (savedInstanceState!=null)
        {
            ingredients=savedInstanceState.getParcelableArrayList("ing");
            steps=savedInstanceState.getParcelableArrayList("steps");
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToWidget();
                Toast.makeText(getContext(),"Added to widget ", Toast.LENGTH_LONG).show();

            }
        });

        for (int i=0;i<ingredients.size();i++)
        {
            ingredient.append("\t *  " +ingredients.get(i).getIngredients() + "  "+
                    String.valueOf(ingredients.get(i).getQuantity()) + "  " +
                    ingredients.get(i).getMeasure()+"\n");
        }
        RecyclerView recyclerView = view.findViewById(R.id.videos_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VideoAdapter adapter = new VideoAdapter( getContext(),steps, this);
        recyclerView.setAdapter(adapter);


        return view;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("ing",ingredients);
        outState.putParcelableArrayList("steps",  steps);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onItemClick(int position) {
        DetailsActivity detailsActivity= (DetailsActivity) getActivity();
        detailsActivity.fire(steps,position);
    }
    public void addToWidget(){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getContext(), AppWidget.class));
        AppWidget.updateWidget(getContext(),appWidgetManager,appWidgetIds,String.valueOf(ingredient.getText()));
    }
}
