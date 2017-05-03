package com.example.administrator.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Listview extends AppCompatActivity {
    String[] weekStrings =new String[] {"星期日" ,"星期一","星期二" ,"星期三","星期四" ,"星期五","星期六" };
    ArrayAdapter<String> adapter;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,weekStrings);
        this.setListAdapter(adapter);
        this.getListView().setOnItemSelectedListener(
                new OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2, long arg3)
                    {
                        Listview.this.setTitle(((TextView)arg1).getText());

                    }
                    @Override
                    public  void onNothingSelected(AdapterView<?> arg0){
                        Listview.this.setTitle("NothingSelected");
                    }
                }
        );
    }
}
