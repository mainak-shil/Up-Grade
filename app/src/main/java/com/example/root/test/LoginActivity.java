package com.example.root.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {
    TextView register_text;
    Button login_bn;
    EditText Email, Pass;
    String email, password;
    AlertDialog.Builder builder;

//    String login_url = "http://192.168.43.142/webapp/login.php";              //MobileHostspot
    String login_url = "http://192.168.1.102/webapp/login.php";               //wifi
    //String login_url = "http://mainakshil.000webhostapp.com/login.php";      //webhost

    //SharedPreferences s ;
//    public static String usertype=UserType.usertype;
   // public static final String typeT= "T";
//    public String type = getIntent().getStringExtra("string");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //String d = s.getString(type,"");
        Email = (EditText) findViewById(R.id.email);
        Pass = (EditText) findViewById(R.id.pass);
        login_bn=findViewById(R.id.login_btn);
        register_text = (TextView) findViewById(R.id.tv_register);

        builder = new AlertDialog.Builder(LoginActivity.this);

        Toast.makeText(getApplicationContext(),UserType.usertype,Toast.LENGTH_LONG).show();

        register_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
//                intent.putExtra("type",type);
                startActivity(intent);

            }
        });

        login_bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                email = Email.getText().toString();
                password = Pass.getText().toString();

                if (email.equals("") && password.equals("")) {
                    builder.setTitle("Something went wrong...");
                    displayAlert("Enter a valid email and password...");
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        Log.i("ret:",response.toString());
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");

                                        if (code.equals("login_failed")) {
                                            builder.setTitle("Login Error...");
                                            displayAlert(jsonObject.getString("message"));
                                        } else {
                                            Intent intent = new Intent(LoginActivity.this, TabActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("name", jsonObject.getString("name"));
                                            bundle.putString("email", jsonObject.getString("email"));
                                            intent.putExtras(bundle);
                                            startActivity(intent);
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
                            params.put("email", email);
                            params.put("password", password);
                            params.put("usertype",UserType.usertype);
                            return params;
                        }
                    };
                    MySingleton.getInstance(LoginActivity.this).addToRequestque(stringRequest);

                }
                }
            });

            }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UserType.usertype="";
    }

    public void displayAlert(String message)
    {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Email.setText("");
                Pass.setText("");

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
