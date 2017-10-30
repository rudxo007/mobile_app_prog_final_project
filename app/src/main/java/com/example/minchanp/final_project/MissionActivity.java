package com.example.minchanp.final_project;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static android.widget.AdapterView.*;

public class MissionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    static final int get_String = 1;
    SimpleCursorAdapter adapt;
    Cursor c;
    ListView view;
    int cash;
    String nick;
    String finalpoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        loadDB();
        view.setOnItemClickListener(this);

        Intent i = getIntent();
        cash = i.getIntExtra("cash", 0);
        nick = i.getStringExtra("name");

    }
   /* @Override
    public void onResume()
    {
        super.onResume();

        loadDB();

    } */

    public void loadDB()
    {
         view = null;
        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        view =(ListView)findViewById(R.id.listView);
        db.execSQL("CREATE TABLE IF NOT EXISTS mission " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT);");

     c = db.rawQuery("SELECT * FROM mission;", null);

     adapt = new SimpleCursorAdapter(
                this, R.layout.missionlist,
                c, new String[]{"title", "content"},
                new int[]{R.id.textView1, R.id.textView2}
        );
        view.setAdapter(adapt);
        db.close();

    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
// TODO Auto-generated method stub
        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Intent intent = new Intent(this, AddMission.class);
        c.moveToPosition(position);
        intent.putExtra("ParamID", c.getInt(0));
        Cursor c = db.rawQuery("SELECT * FROM LoginSystem2 WHERE nick = '" + nick + "'", null);
        c.moveToFirst();
        {
            if (c.moveToFirst()) {

                nick = c.getString(c.getColumnIndex("nick"));
                cash  = c.getInt(c.getColumnIndex("point"));

                String p = String.valueOf(cash);

            }
        }
        intent.putExtra("point", cash);
        intent.putExtra("nick", nick);
        startActivityForResult(intent, get_String);
    }


    public void MissionAdd(View v)
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
                    Intent i = new Intent(MissionActivity.this, AddMission.class);
                    startActivityForResult(i, get_String);
                } else {
                    Toast.makeText(MissionActivity.this, "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                }
            }

        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == get_String) {
            if (resultCode == RESULT_OK) {

                SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

                 c = db.rawQuery("SELECT * FROM mission;", null);
                //startManagingCursor(c);
                adapt.changeCursor(c);
                db.close();

                //finalpoint = data.getStringExtra("finalpoint");


            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mission, menu);
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
