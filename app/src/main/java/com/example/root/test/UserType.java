package com.example.root.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by root on 17/4/18.
 */


public class UserType extends AppCompatActivity {

    Button teacher, student;
    public static String usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.usertype);

        teacher=(Button) findViewById(R.id.teacher);
        student=(Button) findViewById(R.id.student);


        teacher.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        usertype = "T";
                         Intent intent = new Intent(UserType.this,LoginActivity.class);
                        startActivity(intent);
                    }


                });

        student.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                usertype = "S";
                Intent intent = new Intent(UserType.this,LoginActivity.class);
                startActivity(intent);

            }


        });
            }
            }


