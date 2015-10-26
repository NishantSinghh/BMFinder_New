package com.nishant.mcoe.bmfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Nishant on 10/17/2015.
 */
public class DetailBookActivity extends AppCompatActivity {
    private static final String IMAGE_BASE_URL = "http://covers.openlibrary.org/b/id/"; // 13
    String mImageURL; // 13
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imageView = (ImageView) findViewById(R.id.img_cover);
        TextView textView = (TextView) findViewById(R.id.AuthorNameDetails);
        String coverID = this.getIntent().getExtras().getString("coverID");
        String strAuthor = this.getIntent().getExtras().getString("Author");
        if(coverID.length()>0){
            mImageURL = IMAGE_BASE_URL + coverID + "-L.jpg";
            Picasso.with(this).load(mImageURL).placeholder(R.drawable.img_books_loading).into(imageView);
        }
        textView.setText(strAuthor);
    }

}
