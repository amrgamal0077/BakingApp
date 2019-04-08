package com.example.amrgamal.bakingapp.APIData;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AmrGamal on 19/03/2019.
 */

public class Steps implements Parcelable {

    public String shortDescription;
    public String description;
    public String videoURL;
    public String thumbnailURL;

    public Steps(String shortDisc, String disc, String video,String thumbnailURL) {
    shortDescription=shortDisc;
    description=disc;
    videoURL=video;
    this.thumbnailURL=thumbnailURL;
    }

    protected Steps(Parcel in) {
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };
}
