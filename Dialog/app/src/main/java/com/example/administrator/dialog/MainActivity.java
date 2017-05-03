package com.example.administrator.dialog;

import android.content.DialogInterface;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog.Builder;
import android.app.AlertDialog;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Builder dialog=new AlertDialog.Builder(MainActivity.this);

        dialog.setTitle("欢迎");
        dialog.setMessage("欢迎使用");
        dialog.setPositiveButton("取消",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface a0,int b1){

            }
        });
        dialog.setNegativeButton("确定",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface a0, int b1){

            }
        });
        dialog.create();
        dialog.show();



    }
}
