package com.example.amrgamal.bakingapp.APIData;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AmrGamal on 19/03/2019.
 */

public class Ingredients implements Parcelable {

    private String measure;
    private int  quantity;
    private String ingredients;


    public Ingredients(String measure, int quantity, String ingredient) {
        this.measure=measure;
        this.quantity=quantity;
        this.ingredients=ingredient;
    }

    protected Ingredients(Parcel in) {
        measure = in.readString();
        quantity = in.readInt();
        ingredients = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    public int getQuantity() {
        return quantity;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(measure);
        dest.writeInt(quantity);
        dest.writeString(ingredients);
    }

}
