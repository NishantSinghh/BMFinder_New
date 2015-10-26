package com.nishant.mcoe.bmfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by nishasin on 10/8/2015.
 */
public class RegisterActivity extends AppCompatActivity {
    Button btnSubmit;
    EditText txtUserName, txtFirstName, txtLastName, txtPassword;
    CheckBox chkAgree;
    RadioButton rdbMale, rdbFemale;
    Spinner spCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnSubmit = (Button) findViewById(R.id.btnRstSubmit);
        txtFirstName = (EditText) findViewById(R.id.txtRstFirstName);
        txtLastName = (EditText) findViewById(R.id.txtRstLastName);
        txtPassword = (EditText) findViewById(R.id.txtRstPwd);
        txtUserName = (EditText) findViewById(R.id.txtRstUsrName);
        rdbMale = (RadioButton) findViewById(R.id.rdMale);
        rdbFemale = (RadioButton) findViewById(R.id.rdFemale);
        chkAgree = (CheckBox) findViewById(R.id.chkAgree);
        spCountry = (Spinner) findViewById(R.id.spnCountry);
    }
    public void SaveData(View v) {
        String strFileName = txtUserName.getText().toString();
        String strFUsrName = "UserName = " + strFileName;
        txtUserName.setText("");
        String strFirstName = txtFirstName.getText().toString();
        String strFFirstName = "FirstName = " + strFirstName;
        txtFirstName.setText("");
        String strLastName = txtLastName.getText().toString();
        String strFLastName = "LastName = " + strLastName;
        txtLastName.setText("");
        String strPassword = txtPassword.getText().toString();
        String strFPassword = "Password = " + strPassword;
        txtPassword.setText("");
        String strGender = "";
        if (rdbMale.isChecked()) {
            strGender = "Male";
            rdbMale.setChecked(false);
        } else if (rdbFemale.isChecked()) {
            strGender = "Female";
            rdbFemale.setChecked(false);
        }
        String strFGender = "Gender = " + strGender;
        String strListValue = spCountry.getSelectedItem().toString();
        String strFCountry = "Country = " + strListValue;
        String strData = strFFirstName + "," + strFLastName + "," + strFUsrName + "," + strFPassword + "," + strFGender + "," + strFCountry;
        String temp = "";
        String tempShow = "";
        int c;
        boolean strStatus = fileExist(strFileName);
        try {
            if(strStatus){
                Toast.makeText(this,"Error : UserName already exist please select other username",Toast.LENGTH_LONG).show();
            }
            else{
                FileOutputStream fos = openFileOutput(strFileName, MODE_PRIVATE);
                fos.write(strData.getBytes());
                fos.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(strFileName)));
            String strLine;
            String strUserName1 = "";
            while ((strLine = br.readLine()) != null) {
                temp = strLine;


            }
            String[] strTempShow = temp.split(",");
            for (String str1 : strTempShow) {
                if (str1.contains("UserName")) {
                    int intIndex = str1.indexOf("=");
                    int intLength = str1.length();
                    strUserName1 = str1.substring(intIndex + 1, intLength);
                }
            }
            Log.d("BMF finder", strUserName1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean fileExist(String FileName){
        File file = getBaseContext().getFileStreamPath(FileName);
        return file.exists();
    }



}
