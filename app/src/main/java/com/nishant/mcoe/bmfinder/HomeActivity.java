package com.nishant.mcoe.bmfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by nishasin on 10/7/2015.
 */
public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void About(View v){
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }
    public void BookSearch(View v){
        Intent intent = new Intent(this,BookSearchActivity.class);
        startActivity(intent);
    }
    public void MovieSearch(View v){
        Intent intent = new Intent(this,MovieSearchActivity.class);
        startActivity(intent);
    }
    public void Logout(View v){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }

}
