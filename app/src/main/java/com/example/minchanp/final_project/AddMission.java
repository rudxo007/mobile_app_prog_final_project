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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AddMission extends AppCompatActivity {

    int mId;
   int point;
    String nick;

    EditText title, content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mission);

        title = (EditText)findViewById(R.id.editText1);
        content = (EditText)findViewById(R.id.editText2);
        Intent i = getIntent();
        mId = i.getIntExtra("ParamID", -1);
        point = i.getIntExtra("point",0);
        nick = i.getStringExtra("nick");

        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor cursor = db.rawQuery("SELECT * FROM mission WHERE _id='" + mId + "'", null);
        if (cursor.moveToNext()) {

            title.setText(cursor.getString(1));
            content.setText(cursor.getString(2));

        }
        db.close();

        Button del = (Button)findViewById(R.id.button11);

        if(mId == -1) {
            del.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_mission, menu);
        return true;
    }

    public void MissionCreateButton(View v)
    {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("관리자용입니다. 비밀번호를 입력해주세요.");

        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String srt = input.getEditableText().toString();

                SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

                EditText txt = null;

                txt=(EditText)findViewById(R.id.editText1);
                String title = txt.getText().toString();

                txt=(EditText)findViewById(R.id.editText2);
                String content = txt.getText().toString();

                if (srt.equals("1111")) {
                    if (mId != -1) {
                        String sql = "UPDATE mission SET title='"+title+"', content ='"+content+"' WHERE _id='"+mId+"';";
                        db.execSQL(sql);

                    }
                    else {
                        String sql = "INSERT INTO mission (title,content) VALUES('" + title + "','" + content + "');";
                        db.execSQL(sql);
                    }
                    db.close();
                    setResult(RESULT_OK);
                    finish();

                } else {
                    Toast.makeText(AddMission.this, "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                }
            }

        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
    public void MissionDelButton(View v)
    {


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("관리자용입니다. 비밀번호를 입력해주세요.");

        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String srt = input.getEditableText().toString();


                if (srt.equals("1111")) {
                    if (mId != -1) {
                        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                        db.execSQL("DELETE FROM mission WHERE _id='" + mId + "';");
                        db.close();
                    }
                    setResult(RESULT_OK);
                    finish();

                } else {
                    Toast.makeText(AddMission.this, "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                }
            }

        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();



    }

    public void GetPoint(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("포인트 비밀번호를 입력해주세요.");

        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String srt = input.getEditableText().toString();
                SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

                if (srt.equals("1111")) {

                   {
                       point +=3000;

                        Toast.makeText(AddMission.this,"포인트획득!",Toast.LENGTH_SHORT).show();

                    }
                    String sql = "UPDATE LoginSystem2 SET point="+point+ " WHERE nick='"+ nick +"';";

                    db.execSQL(sql);

                    Intent i = new Intent();
                    String c = String.valueOf(point);
                    i.putExtra("finalpoint", c);

                    setResult(RESULT_OK);
                    finish();

                } else {
                    Toast.makeText(AddMission.this, "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                }
            }

        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
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
