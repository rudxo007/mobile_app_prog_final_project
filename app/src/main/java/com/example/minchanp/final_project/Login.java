package com.example.minchanp.final_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    static final int NickName = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadDB();

    }

    @Override
    public void onResume() {
        super.onResume();
        loadDB();
    }


    public void SignUp(View v)
    {

        Intent i = new Intent(Login.this,SignUp.class);
        startActivity(i);
    }

    public void login(View v)
    {
        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        EditText txt1 = null;
        txt1 = (EditText)findViewById(R.id.editText1);
        String id = txt1.getText().toString();

        txt1 = (EditText)findViewById(R.id.editText2);
        String pass=txt1.getText().toString();




        Cursor c = db.rawQuery("SELECT * FROM LoginSystem2 WHERE id = '" + id + "' AND pass ='" + pass + "'", null);
        c.moveToFirst();
        {
            if (c.moveToFirst()) {

                id =  c.getString(c.getColumnIndex("id"));
                pass = c.getString(c.getColumnIndex("pass"));
                String nick= c.getString(c.getColumnIndex("nick"));
                int date = c.getInt(c.getColumnIndex("date"));
                int point =c.getInt(c.getColumnIndex("point"));

                Intent i = new Intent(Login.this, MainActivity.class);

                i.putExtra("id",id);
                i.putExtra("nick", nick);
                i.putExtra("date", date);
                i.putExtra("point",point);

                startActivity(i);


            }else if (id.equals("")==true && pass.equals("")==true) {
                Toast.makeText(Login.this, "아이디,비밀번호를 입력 하세요.", Toast.LENGTH_SHORT).show();

            } else if (id.equals("")==true) {
                Toast.makeText(Login.this, "아이디를 입력 하세요.", Toast.LENGTH_SHORT).show();

            } else if (pass.equals("")==true) {
                Toast.makeText(Login.this, "비밀번호를 입력 하세요.", Toast.LENGTH_SHORT).show();

            }  else {
                Toast.makeText(Login.this, "아이디, 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void loadDB()
    {
        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS LoginSystem2" + "(id TEXT , pass TEXT, nick TEXT, point INTEGER, date INTEGER);");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
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
