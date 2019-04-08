package com.example.amrgamal.bakingapp.Utiles;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.amrgamal.bakingapp.R;

/**
 * Created by AmrGamal on 06/04/2019.
 */

public class AppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context,String text ,AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.appwidget_text, text);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }



    public static void updateWidget(Context context, AppWidgetManager appWidgetManager,
                                    int[] appWidgetIds , String text){

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, text , appWidgetManager, appWidgetId);
        }
    }
}
