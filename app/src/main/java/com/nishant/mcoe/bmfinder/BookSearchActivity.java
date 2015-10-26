package com.nishant.mcoe.bmfinder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Nishant on 10/14/2015.
 */
public class BookSearchActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    EditText mEditSearch;
    Button mbtnSearchBook;
    ListView mlstBook;
    JsonBookAdapter mJsonAdapter;
    ProgressDialog mDialog;
    ArrayList mArrayList = new ArrayList();
    private static final String QUERY_URL="http://openlibrary.org/search.json?q=";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booksearch);
        mEditSearch = (EditText) findViewById(R.id.txtBookSearch);
        mbtnSearchBook = (Button) findViewById(R.id.btnButtonSearch);
        mlstBook = (ListView) findViewById(R.id.mainbooklist);
        mbtnSearchBook.setOnClickListener(this);
        mJsonAdapter = new JsonBookAdapter(this,getLayoutInflater());
        mlstBook.setAdapter(mJsonAdapter);
        mlstBook.setOnItemClickListener(this);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Searching for books");
        mDialog.setCancelable(false);

    }

    @Override
    public void onClick(View view) {
        mDialog.show();
        queryBook(mEditSearch.getText().toString());
        mEditSearch.setText("");
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }
    private void queryBook(String SearchString){
        String strUrl="";
        try{
            strUrl= URLEncoder.encode(SearchString,"UTF-8");

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            Toast.makeText(this,"Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(QUERY_URL+strUrl, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject jsonObject){
                mDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Success !", Toast.LENGTH_LONG).show();
                Log.d("BMF Finder", jsonObject.toString());
                mJsonAdapter.updateData(jsonObject.optJSONArray("docs"));

            }
            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error){
                mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error : " + statusCode + " " + throwable.getMessage(),Toast.LENGTH_LONG ).show();
                Log.e("BMFinder",statusCode+ " " + throwable.getMessage() );
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JSONObject jsonObject = (JSONObject) mJsonAdapter.getItem(position);
        String coverID = jsonObject.optString("cover_i","");
        String strAuthor = jsonObject.optJSONArray("author_name").optString(0);
        Intent detailIntent = new Intent(this, DetailBookActivity.class);
        detailIntent.putExtra("coverID",coverID);
        detailIntent.putExtra("Author", strAuthor);
        startActivity(detailIntent);
    }
}
