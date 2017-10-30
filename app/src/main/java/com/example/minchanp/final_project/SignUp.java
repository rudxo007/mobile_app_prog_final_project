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
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    int x = 0;
    String a = null;
    String b = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    public void SignUpButton(View v) {
        SQLiteDatabase db = openOrCreateDatabase(
                "text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        EditText txt = null;

        txt = (EditText) findViewById(R.id.editText1);
        String id = txt.getText().toString();

        txt = (EditText) findViewById(R.id.editText2);
        String pass = txt.getText().toString();

        txt = (EditText) findViewById(R.id.editText3);
        String nick = txt.getText().toString();

        int point= 500;
        int date = 0;

        String sql = "INSERT INTO LoginSystem2 (id,pass,nick,point,date) VALUES ('" + id + "','" + pass + "','" + nick + "',"+ point +","+ date +");";



                if (x !=2) {
                    Toast.makeText(SignUp.this, "중복확인을 먼저 해주세요.", Toast.LENGTH_SHORT).show();
                }


                if (x == 2) {
                    db.execSQL(sql);

                    Toast.makeText(SignUp.this, "가입이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUp.this, Login.class);
                    i.putExtra("date",date);
                    i.putExtra("point",date);
                    startActivity(i);
                }

            }



    public void CheckidButton(View v) {
        SQLiteDatabase db = openOrCreateDatabase(
                "text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        EditText txt = null;
        txt = (EditText) findViewById(R.id.editText1);
        String id = txt.getText().toString();

        Cursor c = db.rawQuery("SELECT * FROM LoginSystem2 WHERE id = '" + id + "'", null);

        c.moveToFirst();

                        if(id.equals(""))
                             {
                                Toast.makeText(SignUp.this,"아이디를 입력하세요",Toast.LENGTH_SHORT).show();
                                 x=0;
                            }
                        else if (c.moveToFirst()) {

                             a = c.getString(c.getColumnIndex("id"));
                            Toast.makeText(SignUp.this, "중복된 아이디가 이미 가입되어 있습니다.", Toast.LENGTH_SHORT).show();
                            x = 0;
                        }


                        else {
                            Toast.makeText(SignUp.this, "중복된 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                            x = 1;

                        txt.setFocusable(false);

                        }
    }

    public void ChecknickButton(View v) {
        SQLiteDatabase db = openOrCreateDatabase(
                "text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        EditText txt = null;
        txt = (EditText) findViewById(R.id.editText3);
        String nick = txt.getText().toString();

        Cursor c = db.rawQuery("SELECT * FROM LoginSystem2 WHERE nick = '" + nick + "'", null);

        c.moveToFirst();


        if(x==0)
        {
            Toast.makeText(SignUp.this, "아이디의 중복확인을 해주세요.", Toast.LENGTH_SHORT).show();
            x=0;
        }
       else if (c.moveToFirst()) {

            b = c.getString(c.getColumnIndex("nick"));
            Toast.makeText(SignUp.this, "중복된 닉네임이 이미 가입되어 있습니다.", Toast.LENGTH_SHORT).show();
            x = 1;
        }

        else if(x==1 && nick.equals("")==true){
            Toast.makeText(SignUp.this,"닉네임을 입력하세요",Toast.LENGTH_SHORT).show();
            x = 1;
        }

        else {
            Toast.makeText(SignUp.this, "중복된 닉네임이 없습니다.", Toast.LENGTH_SHORT).show();
            x = 2;

            txt.setFocusable(false);

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
