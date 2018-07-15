package com.example.root.test;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 22/3/18.
 */

public class RegistrationActivity extends AppCompatActivity {
    Button reg_bn;
    EditText Name, Email, Password, Con_pass;
    ImageView iv;
    String name, email, pass, con_pass;
    AlertDialog.Builder builder;


    String reg_url = "http://192.168.1.102/webapp/register.php";               //wifi
    //String reg_url = "http://mainakshil.000webhostapp.com/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.registration);

        iv = (ImageView) findViewById(R.id.iv);
        Name = (EditText) findViewById(R.id.name);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.pass);
        Con_pass = (EditText) findViewById(R.id.con_pass);

        builder = new AlertDialog.Builder(RegistrationActivity.this);

        Toast.makeText(getApplicationContext(),UserType.usertype,Toast.LENGTH_LONG).show();

        reg_bn = (Button)findViewById(R.id.register);

        reg_bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                name = Name.getText().toString();
                email = Email.getText().toString();
                pass = Password.getText().toString();
                con_pass = Con_pass.getText().toString();

                if (name.equals("") && email.equals("") && pass.equals("") && con_pass.equals("")) {
                    Name.setError("Invalid Name");
                    Email.setError("Invalid Email");
                    Password.setError("Invalid Password");
                    Con_pass.setError("Invalid Confirm Password");
                }if (name.equals("")) {
                    Name.setError("Invalid Name");
                } else if (email.equals("")) {
                    Email.setError("Invalid Email");
                } else if (pass.equals("")) {
                    Password.setError("Invalid Password");
                } else if (con_pass.equals("")) {
                    Con_pass.setError("Invalid Confirm Password");
                } else if (!ValidName(name)) {
                    Name.setError("Invalid Name");
                } else if (!ValidEmail(email)) {
                    Email.setError("Invalid Email");
                } else if (!ValidPass(pass)) {
                    Password.setError("Invalid Password");
                } else if (!ValidPass(con_pass)) {
                    Con_pass.setError("Invalid Confirm Password");
                }else if (!pass.equals(con_pass)){
                    builder.setTitle("Something went wrong....");
                    builder.setMessage("Passwords are not matching");
                    displayAlert("input_error");

                }else{

                    //
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        Log.i("ret:", response.toString());
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");
                                        String message =jsonObject.getString("message");
                                        builder.setTitle("Server Response.");
                                        builder.setMessage(message);
                                        displayAlert(code);
                                        if (code.equals("reg_failed")) {
                                            builder.setTitle("Registration Error...");
                                            displayAlert(jsonObject.getString("message"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("name", name);
                            params.put("email", email);
                            params.put("password", pass);
                            params.put("usertype", UserType.usertype);
                            return params;
                        }
                    };
                    MySingleton.getInstance(RegistrationActivity.this).addToRequestque(stringRequest);

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UserType.usertype="";
    }

    public void displayAlert(final String code)
    {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals("input_error"))
                {

                Password.setText("");
                Con_pass.setText("");

                }
                else if(code.equals("reg_success")){
                    finish();
                }
                else if(code.equals("reg_failed")){

                    Name.setText("");
                    Email.setText("");
                    Password.setText("");
                    Con_pass.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    public boolean ValidPass(String pass) {
        if (pass.equals("")) {
            return false;
        } else return true;
    }

    public boolean ValidName(String name) {
        if (name.equals("^[a-zA-Z\\s]*$"))
            return false;
        else return true;
    }

    public boolean ValidEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
