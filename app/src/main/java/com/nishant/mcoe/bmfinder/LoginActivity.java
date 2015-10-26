package com.nishant.mcoe.bmfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by nishasin on 10/7/2015.
 */
public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.nishant.mcoe.bmfinder.R.layout.activity_login);
        editTextUsername = (EditText)findViewById(R.id.Username);
        editTextPassword = (EditText) findViewById(R.id.Password);
        buttonLogin = (Button) findViewById(R.id.btnLogin);
    }
    public void homePage(View v){
        String strUserName = editTextUsername.getText().toString();
        String strPassWord = editTextPassword.getText().toString();
        if((strUserName.equals("admin"))&&(strPassWord.equals("admin"))){
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show();
        }
    }
}
