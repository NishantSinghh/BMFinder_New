package com.nishant.mcoe.bmfinder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Nishant on 10/18/2015.
 */
public class MovieSearchActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEditSearch;
    Button mbtnSearchMovie;
    ProgressDialog mDialog;
    public static JSONObject jsonObject = new JSONObject();
    ArrayList mArrayList = new ArrayList();
    private static final String QUERY_URL="http://www.omdbapi.com/?t=";
    TextView tvMovieTitle, tvMovieRelease;
    ImageView imgPoster;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviesearch);
        mEditSearch = (EditText) findViewById(R.id.txtMovieSearch);
        mbtnSearchMovie = (Button) findViewById(R.id.btnMovieSearch);
        mbtnSearchMovie.setOnClickListener(this);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Searching for movie..");
        mDialog.setCancelable(false);
        tvMovieTitle = (TextView) findViewById(R.id.MovieTitle);
        tvMovieRelease = (TextView) findViewById(R.id.MovieYear);
        imgPoster = (ImageView) findViewById(R.id.MovieImage);
    }

    @Override
    public void onClick(View view) {
        mDialog.show();
        queryMovie(mEditSearch.getText().toString());
        mEditSearch.setText("");
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    private void queryMovie(String SearchString){
        String strURL="";
        try{
            strURL = URLEncoder.encode(SearchString, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            Toast.makeText(this, "Error : " + e.getMessage(),Toast.LENGTH_LONG).show();
        }
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(QUERY_URL + strURL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                MovieSearchActivity.jsonObject =jsonObject;
                mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Success !", Toast.LENGTH_LONG).show();
                Log.d("BMF Finder", jsonObject.toString());
                updateData(jsonObject);
            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error : " + statusCode + " " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("BMFinder", statusCode + " " + throwable.getMessage());
            }
        });

    }
    public void updateData(JSONObject jsonObject){
        tvMovieTitle = (TextView) findViewById(R.id.MovieTitle);
        tvMovieRelease = (TextView) findViewById(R.id.MovieYear);
        imgPoster = (ImageView) findViewById(R.id.MovieImage);

        if(jsonObject.has("Poster")){
            String strImgPath = jsonObject.optString("Poster");
            Picasso.with(this).load(strImgPath).into(imgPoster);
        }else{
            imgPoster.setImageResource(R.drawable.loading);
        }
        if(jsonObject.has("Title")){
            String strTitle = jsonObject.optString("Title");
            tvMovieTitle.setText("Movie Title : " + strTitle);
        }
        if(jsonObject.has("Released")){
            String strReleased = jsonObject.optString("Released");
            tvMovieRelease.setText("Movie Released : "+ strReleased);
        }

    }

    public void MovieDetails(View v){
        String strTitle = jsonObject.optString("Title");
        String strRelease = jsonObject.optString("Released");
        String strIMDBRating = jsonObject.optString("imdbRating");
        String strDirector = jsonObject.optString("Director");
        String strWriter = jsonObject.optString("Writer");
        String strActors = jsonObject.optString("Actors");
        String strGenre = jsonObject.optString("Genre");
        String strCountry =jsonObject.optString("Country");
        String strRuntime = jsonObject.optString("Runtime");
        String strLanguage = jsonObject.optString("Language");
        Bundle extras = new Bundle();
        extras.putString("Title", strTitle);
        extras.putString("Released", strRelease);
        extras.putString("imdbRating", strIMDBRating);
        extras.putString("Director", strDirector);
        extras.putString("Writer", strWriter);
        extras.putString("Actors", strActors);
        extras.putString("Genre", strGenre);
        extras.putString("Country", strCountry);
        extras.putString("Runtime", strRuntime);
        extras.putString("Language", strLanguage);

        Intent intent = new Intent(this,DetailMovieActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
