package com.example.amrgamal.bakingapp.APIData;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by AmrGamal on 19/03/2019.
 */

public class Bakes extends ArrayList<Parcelable> implements Parcelable {
    public String name;
    public ArrayList<Ingredients> ingredients;
    public ArrayList<Steps> steps;
    public int servings;

    public Bakes(){

    }


    public Bakes(String name, ArrayList<Ingredients> ingredients, ArrayList<Steps> steps, int serving) {
   this.name=name;
   this.ingredients=ingredients;
   this.steps=steps;
   this.servings=serving;
    }


    protected Bakes(Parcel in) {
        name = in.readString();
        ingredients = in.createTypedArrayList(Ingredients.CREATOR);
        steps = in.createTypedArrayList(Steps.CREATOR);
        servings = in.readInt();
    }

    public static final Creator<Bakes> CREATOR = new Creator<Bakes>() {
        @Override
        public Bakes createFromParcel(Parcel in) {
            return new Bakes(in);
        }

        @Override
        public Bakes[] newArray(int size) {
            return new Bakes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
        dest.writeInt(servings);
    }
}
