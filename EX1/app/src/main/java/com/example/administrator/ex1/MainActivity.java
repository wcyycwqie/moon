package com.example.administrator.ex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioButton r1 = null;
    RadioButton r2 = null;
    RadioButton r3 = null;
    RadioButton r4 = null;
    RadioGroup radioGroup = null;
    RadioButton currentRadioButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        r1 = (RadioButton) findViewById(R.id.a);
        r2 = (RadioButton) findViewById(R.id.b);
        r3 = (RadioButton) findViewById(R.id.c);
        r4 = (RadioButton) findViewById(R.id.d);
        r1.setClickable(true);

        Button botton1 =(Button)findViewById(R.id.sure);
        Button botton2 =(Button)findViewById(R.id.cancel);
        botton1.setOnClickListener(new botton1());
        botton2.setOnClickListener(new botton2());


    }
    class botton1 implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (currentRadioButton.getText().equals("in")) {
                setTitle("你选择的答案是：是正确的！");
            } else {
                setTitle("你选择的答案是:是错误的！");
            }
        }
    }

    class botton2 implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            radioGroup.clearCheck();
            setTitle("");
        }

    }

}
