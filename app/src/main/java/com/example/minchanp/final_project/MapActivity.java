package com.example.minchanp.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    public void m1(View v)
    {
        Intent i = new Intent(MapActivity.this, M1.class);
        startActivity(i);
    }

    public void m2(View v)
    {
        Intent i = new Intent(MapActivity.this, M2.class);
        startActivity(i);
    }
    public void m3(View v)
    {
        Intent i = new Intent(MapActivity.this, M3.class);
        startActivity(i);
    }
    public void m4(View v)
    {
        Intent i = new Intent(MapActivity.this, M4.class);
        startActivity(i);
    }
    public void m5(View v)
    {
        Intent i = new Intent(MapActivity.this, M5.class);
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
