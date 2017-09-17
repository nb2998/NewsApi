package com.apps.nishtha.inshortsnewsapi;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.apps.nishtha.inshortsnewsapi.PoJo.NewsDetails;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by nishtha on 17/9/17.
 */

public class FetchNews extends AsyncTask<String,Void,ArrayList<NewsDetails>> {
    public static final String TAG="TAG";
    Context context;
    NewsAdapter newsAdapter;
    ArrayList<NewsDetails> newsArrayList;
    Handler handler=new Handler(Looper.getMainLooper());;

    public FetchNews(Context context, NewsAdapter newsAdapter, ArrayList<NewsDetails> newsArrayList) {
        this.context = context;
        this.newsAdapter = newsAdapter;
        this.newsArrayList = newsArrayList;
    }


//    ProgressDialog progressDialog=new ProgressDialog(context);
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<NewsDetails> doInBackground(String... strings) {
        OkHttpClient okHttpClient=new OkHttpClient();
        final ArrayList<NewsDetails> retVal=new ArrayList<>();
        Request request=new Request.Builder().url(strings[0]).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                Gson gson=new Gson();
                NewsDetails[] newsDetails=gson.fromJson(result, new GenericArrayType() {
                    @Override
                    public Type getGenericComponentType() {
                        return NewsDetails.class;
                    }
                });
                for(int i=0;i<newsDetails.length;i++){
                    newsArrayList.add(newsDetails[i]);
                }


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        newsAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        return retVal;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
//        progressDialog.setMessage("Please wait..");
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                progressDialog.show();
//            }
//        });

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<NewsDetails> newsDetailses) {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                progressDialog.hide();
//            }
//        });
        super.onPostExecute(newsDetailses);
    }

    @Override
    protected void onCancelled(ArrayList<NewsDetails> newsDetailses) {

        super.onCancelled(newsDetailses);
    }
}

//Inshorts Outlinks
//        [Problem Statement]
//        Develop a pseudo Mobile application(Android) which should list and open best outlinked news articles from the app.
//        Your application should fetch all news article details from the provided API and list them on the home screen. These details include Headlines and URLs among other variables. When user clicks on the listed headline, a browser window should load and display the article.
//        [Minimum Requirement]
//        — Use of Web API to fetch news details.
//        ● API Parameters: Provided below
//        — Visually interactive design to list​ details.
//        — Implement paging ​to display the results properly.
//        — Zip all your Source code, Screenshots & detailed deployment instructions and upload.
//        [Plus Point]
//        — Implement a browser window in your application to display news articles.
//        — We expect a beautiful, robust, feature rich application UI and production level application code.
//        — Implement a feature to favorite the news articles which users like.
//        — Custom design, font and icons to make app more user ­friendly.
//        — You may add portfolio ​activity comprising awesome work you have done in Android.
//        — Use your imagination and add features which would make things easier for end users.
//        — Beautify; Comment; Documented code; handle Input Exceptions, Unicode and Null values.
//        [Advanced]
//        — Some links are expired and do not contain data. Make sure you handle them gracefully.
//        — Implement a feature to filter and list articles based on Publisher and Category.
//        — Implement a feature to sort and list articles based on old-to-new and new-to-old.
//        — Implement an "Offline Mode" which enables browsing articles and full stories offline too.
//        [Guide]
//        — Inshorts Outlinks API : http://starlord.hackerearth.com/newsjson
//        — Android JSON Parsing:
//        https://www.tutorialspoint.com/android/android_json_parser.htm
//        You are expected to develop a Native Android Application.
//        You are expected to code for API Level 19 and higher.
//        — IMPORTANT: ​Archive source, screenshots and documentation in ZIP file and upload it.
//        — For icons and subtle texture:
//        http://glyphsearch.com/
//        http://subtlepatterns.com/thumbnail­view/
//        http://codebeautify.org/jsonviewer
//        http://www.flaticon.com/categories
//        [API Params]
//        ID : the numeric ID of the article
//        TITLE : the headline of the article
//        URL : the URL of the article
//        CATEGORY : the category of the news item; one of: -- b : business -- t : science and technology -- e: entertainment -- m : health
//        HOSTNAME : hostname where the article was posted
//        TIMESTAMP : approximate timestamp of the article's publication, given in Unix time (seconds since midnight on Jan 1, 1970)