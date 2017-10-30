package com.example.minchanp.final_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CashActivity extends AppCompatActivity {

    TextView txt;
    int cash;
    String nick;
    static final int get_String = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        txt = (TextView) findViewById(R.id.textView16);

        Intent i = getIntent();
        cash = i.getIntExtra("cash", 0);
        nick = i.getStringExtra("name");

        //String p = String.valueOf(cash);
        //txt.setText(p);


        Cursor c = db.rawQuery("SELECT * FROM LoginSystem2 WHERE nick = '" + nick + "'", null);
        c.moveToFirst();
        {
            if (c.moveToFirst()) {

                nick = c.getString(c.getColumnIndex("nick"));
                cash  = c.getInt(c.getColumnIndex("point"));

                String p = String.valueOf(cash);


                txt.setText(p);
            }
        }
    }

  /* public void onRestart()
    {
        super.onRestart();
        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        String sql = "UPDATE LoginSystem2 SET point="+ point +";";

        String p = String.valueOf(point);
        db.execSQL(sql);

        txt.setText(p);
    } */

    public void RefreshButton(View v)
    {
        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Cursor c = db.rawQuery("SELECT * FROM LoginSystem2 WHERE nick = '" + nick + "'", null);
        c.moveToFirst();
        {
            if (c.moveToFirst()) {

                nick = c.getString(c.getColumnIndex("nick"));
                cash  = c.getInt(c.getColumnIndex("point"));

                String p = String.valueOf(cash);


                txt.setText(p);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cash, menu);
        return true;
    }

    public void LuckyButton(View v)
    {
        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        txt = (TextView)findViewById(R.id.textView16);
        //String point = txt.getText().toString();
        Intent i = new Intent(CashActivity.this, LuckyDice.class);


        Cursor c = db.rawQuery("SELECT * FROM LoginSystem2 WHERE nick = '" + nick + "'", null);
        c.moveToFirst();
        {
            if (c.moveToFirst()) {

                nick = c.getString(c.getColumnIndex("nick"));
                cash  = c.getInt(c.getColumnIndex("point"));

                String p = String.valueOf(cash);


                txt.setText(p);
            }
        }
        i.putExtra("point",cash);
        i.putExtra("nick", nick);


        startActivityForResult(i, get_String);
    }

    public void CouponButton(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("문화 상품권 교환에 5000포인트가 필요합니다. 교환하시겠습니까?");


        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);


                if (cash < 5000) {
                    Toast.makeText(CashActivity.this, "포인트가 부족합니다..", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(CashActivity.this);
                    dlg.setIcon(R.drawable.p1);
                    dlg.setTitle("문화 상품권 교환!");
                    dlg.setMessage("4377-1966-0855-270877");
                    dlg.show();

                    cash -= 5000;
                }
                String p = String.valueOf(cash);
                txt.setText(p);

                String sql = "UPDATE LoginSystem2 SET point=" + cash + " WHERE nick='" + nick + "';";
                db.execSQL(sql);
            }

        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();


        }


    public void PuzzleBoxButton(View v)
    {
        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor c = db.rawQuery("SELECT * FROM LoginSystem2 WHERE nick = '" + nick + "'", null);
        c.moveToFirst();
        {
            if (c.moveToFirst()) {

                nick = c.getString(c.getColumnIndex("nick"));
                cash  = c.getInt(c.getColumnIndex("point"));

                String p = String.valueOf(cash);


                txt.setText(p);
            }
        }
        Intent i = new Intent(CashActivity.this,PuzzleBox.class);
        i.putExtra("point",cash);
        i.putExtra("nick", nick);

        startActivityForResult(i, get_String);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == get_String) {
            if (resultCode == RESULT_OK) {

                txt.setText(data.getStringExtra("INPUT_TEXT"));

                }
            }
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
