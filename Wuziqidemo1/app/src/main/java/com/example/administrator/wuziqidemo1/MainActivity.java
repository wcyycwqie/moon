package com.example.administrator.wuziqidemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WuziqiPanel wuziqiPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wuziqiPanel = (WuziqiPanel) findViewById(R.id.id_wuziqi);
        fuziqigamestart();

    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            wuziqiPanel.restart();
            fuziqigamestart();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void fuziqigamestart() {
        String text ="白棋先手";
//      String text ="黑棋先手";
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }


}
