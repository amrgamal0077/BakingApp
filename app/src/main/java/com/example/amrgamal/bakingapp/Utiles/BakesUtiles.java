package com.example.amrgamal.bakingapp.Utiles;

import android.os.Parcel;
import android.util.Log;

import com.example.amrgamal.bakingapp.APIData.Bakes;
import com.example.amrgamal.bakingapp.APIData.Ingredients;
import com.example.amrgamal.bakingapp.APIData.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by AmrGamal on 19/03/2019.
 */

public class BakesUtiles {
        private static URL createUrl(String Url)
        {
            URL url=null;
            try {
                url = new URL(Url);
            } catch (MalformedURLException e) {
                Log.e(BakesUtiles.class.getName(), "Problem building the URL ", e);
            }
            return url;
        }
        private static ArrayList<Bakes> extraxtMovies(String json){
            ArrayList<Bakes>BakesArray = new ArrayList<>();

            try {

                JSONArray result=new JSONArray(json);
                for (int i=0;i<result.length();i++)
                {
                    ArrayList<Ingredients>ingredients= new ArrayList<>();
                    ArrayList<Steps>steps= new ArrayList<>();
                    JSONObject recipeObject  = result.optJSONObject(i);
                    String name = recipeObject.optString("name","no");
                    int serving = recipeObject.optInt("servings",-1);
                    JSONArray ing = recipeObject.optJSONArray("ingredients");
                    for (int j=0;j<ing.length();j++){
                        JSONObject ingArray = ing.optJSONObject(j);
                        int quantity = ingArray.optInt("quantity",-1);
                        String measure = ingArray.optString("measure","no");
                        String ingredient = ingArray.optString("ingredient","no");
                        ingredients.add(new Ingredients(measure,quantity,ingredient));
                    }
                    JSONArray stepArray = recipeObject.optJSONArray("steps");
                    for (int j=0;j<stepArray.length();j++){
                        JSONObject stepObject = stepArray.optJSONObject(j);
                        String shortDisc = stepObject.optString("shortDescription","no");
                        String Disc = stepObject.optString("description","no");
                        String video = stepObject.optString("videoURL","no");
                        String Image = stepObject.optString("thumbnailURL","no");
                        steps.add(new Steps(shortDisc,Disc,video,Image));
                    }
                    BakesArray.add(new Bakes(name,ingredients,steps,serving
                            )
                    );
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return BakesArray;

        }
        private static String makeHttpRequest(URL url) throws IOException {
            String JsonResponse ="";
            if (url==null)
                return null;
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream=null;
            try {
                httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode()==200)
                {
                    inputStream=httpURLConnection.getInputStream();
                    JsonResponse=readFromStream(inputStream);
                }
                else {
                    Log.e(BakesUtiles.class.getName(), "Error response code: " +httpURLConnection.getResponseCode());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {
                    // Closing the input stream could throw an IOException, which is why
                    // the makeHttpRequest(URL url) method signature specifies than an IOException
                    // could be thrown.
                    inputStream.close();
                }
            }

            return JsonResponse;
        }
        private static String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
        public static ArrayList<Bakes> fetchdata(String Url)
        {

            URL url =createUrl(Url);
            String jsonData=null;
            try {
                jsonData=makeHttpRequest(url);
            } catch (IOException e) {
               // Log.e(MoviesUtils.class.getName(), "Problem making the HTTP request.", e);
            }

            return extraxtMovies(jsonData);
        }
    }


