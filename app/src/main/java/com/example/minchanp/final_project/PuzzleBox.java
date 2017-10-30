package com.example.minchanp.final_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class PuzzleBox extends AppCompatActivity {

    TextView txt1,txt2;
    int point;
    String nick;
    int n;
    int x = 0;

    ArrayList<Integer> ranNumber = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_box);
        txt1 = (TextView)findViewById(R.id.textView30);

        Intent intent = getIntent();
        point = intent.getIntExtra("point",0);
        nick = intent.getStringExtra("nick");

        String cash = String.valueOf(point);

        txt1.setText(cash);



        for(int i=0; i <25;i++){

            ranNumber.add(i+1);

        }
        Collections.shuffle(ranNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_puzzle_box, menu);
        return true;
    }

    public void drawButton(View v)
    {
        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        txt2 = (TextView)findViewById(R.id.textView26);
        txt1 = (TextView)findViewById(R.id.textView30);




        String number = String.valueOf(ranNumber);

        if(point < 300)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("포인트가 부족합니다.");
            dlg.show();
        }
        if(point >=300) {
            point -=300;
            if (x < 25) {
                String num = String.valueOf(ranNumber.get(x).toString());
                String number1 = String.valueOf(ranNumber);

                n = Integer.parseInt(num);

                txt2.setText(num);
                //txt1.setText(num);
            }
            x++;
            if (n == 7) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setIcon(R.drawable.p3);
                dlg.setTitle("Lucky Seven!! 살아있네~. ");
                dlg.setMessage("2000p를 획득하셨습니다.");
                dlg.show();

                point += 2000;

                for(int i=0; i <20;i++){


                    ranNumber.add(i + 1);
                    ranNumber.remove(i + 1);


                }
                Collections.shuffle(ranNumber);
                x=0;

            }





        }
        String cash = String.valueOf(point);
        txt1.setText(cash);
        String sql = "UPDATE LoginSystem2 SET point="+point+ " WHERE nick='"+ nick +"';";
        db.execSQL(sql);

        Intent i = new Intent();
        String c = String.valueOf(point);
        i.putExtra("INPUT_TEXT", c);
        setResult(RESULT_OK, i);


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
