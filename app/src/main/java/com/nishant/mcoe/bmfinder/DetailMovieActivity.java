package com.nishant.mcoe.bmfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Nishant on 10/18/2015.
 */
public class DetailMovieActivity extends AppCompatActivity {
    TextView tvMovieTitle, tvMovieRelease, tvIMDBRating,tvDirector, tvWriter, tvActors, tvGenre, tvCountry, tvRuntime, tvLanguage;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);
        tvMovieTitle = (TextView)findViewById(R.id.tvMovieTitle);
        tvMovieRelease = (TextView)findViewById(R.id.tvReleased);
        tvIMDBRating = (TextView)findViewById(R.id.tvIMDBRating);
        tvDirector = (TextView)findViewById(R.id.tvDirector);
        tvWriter = (TextView)findViewById(R.id.tvWriter);
        tvActors = (TextView)findViewById(R.id.tvActors);
        tvGenre = (TextView)findViewById(R.id.tvGenre);
        tvCountry = (TextView)findViewById(R.id.tvCountry);
        tvRuntime = (TextView)findViewById(R.id.tvRuntime);
        tvLanguage = (TextView)findViewById(R.id.tvLanguage);
        Bundle extras = this.getIntent().getExtras();
        String strTitle = extras.getString("Title");
        String strRelease = extras.getString("Released");
        String strIMDBRating = extras.getString("imdbRating");
        String strDirector = extras.getString("Director");
        String strWriter = extras.getString("Writer");
        String strActors = extras.getString("Actors");
        String strGenre = extras.getString("Genre");
        String strCountry = extras.getString("Country");
        String strRuntime = extras.getString("Runtime");
        String strLanguage = extras.getString("Language");
        tvMovieTitle.setText(strTitle);
        tvMovieRelease.setText("Released : " + strRelease);
        tvIMDBRating.setText("IMDB Rating : " +strIMDBRating);
        tvDirector.setText("Director : "+strDirector);
        tvWriter.setText("Writer : "+strWriter);
        tvActors.setText("Actors : " +strActors);
        tvGenre.setText("Genre : "+strGenre);
        tvCountry.setText("Country : "+strCountry);
        tvRuntime.setText("Runtime : " +strRuntime);
        tvLanguage.setText("Language : " + strLanguage);

    }
}
