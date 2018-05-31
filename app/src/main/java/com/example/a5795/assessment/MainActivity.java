package com.example.a5795.assessment;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    LinearLayout menuLinerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuLinerLayout=(LinearLayout)findViewById(R.id.menuFrame);


    }

    public void menubuttonOnclick(View view) {
        menuLinerLayout.setVisibility(View.VISIBLE);
    }

    public void closemenubuttonOnclick(View view) {
        menuLinerLayout.setVisibility(View.GONE);
    }
}
