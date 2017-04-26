package com.example.administrator.intentdemo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.widget.Button;


public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button=(Button)findViewById(R.id.Button01);
        button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View b){
                Intent I=new Intent(Intent.ACTION_DIAL,Uri.parse("tel://114"));
                startActivity(I);

            }
        }
        );

    }
}
