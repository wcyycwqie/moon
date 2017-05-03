package com.example.administrator.handlerdemo1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button start = null;
    private Button end = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new StartButtonListener());
        end = (Button) findViewById(R.id.end);
        end.setOnClickListener(new EndButtonListener());

    }

    Handler handler = new Handler();

    class StartButtonListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            //2、调用Handler的post方法，将要执行的线程对象添加到队列当中，同样下面也有停止按钮
            handler.post(updateThread);
        }
    }

    Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            System.out.println("UpdateThread");
            Log.v("tag", "UpdateThread");
            //在run方法内部，执行postDelayed或者是post方法
            handler.postDelayed(updateThread, 2000);
        }
    };
        class EndButtonListener implements Button.OnClickListener {

            @Override
            public void onClick(View v) {
                handler.removeCallbacks(updateThread);
            }
        }

}






