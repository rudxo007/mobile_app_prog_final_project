package project.final_mobile;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class LuckyDice extends AppCompatActivity {

    TextView txt;
    int point;
    String nick;

    int x;
    int y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_dice);

        txt = (TextView)findViewById(R.id.textView24);

        Intent i = getIntent();
        point = i.getIntExtra("point",0);
        nick = i.getStringExtra("nick");

        String cash = String.valueOf(point);

        txt.setText(cash);
    }

    public void drawButton(View v)
    {
        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        txt = (TextView)findViewById(R.id.textView24);

        point -=100;


        ImageView v1 =(ImageView)findViewById(R.id.imageView1);
        ImageView v2 =(ImageView)findViewById(R.id.imageView2);

        Random dice1 = new Random();
        Random dice2 = new Random();

        int resultdice1 = dice1.nextInt(6)+1;
        int resultdice2 = dice1.nextInt(6)+1;

        if(point<0) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setIcon(R.drawable.dice);
            dlg.setTitle("포인트가 부족합니다.");
            dlg.show();
        }

        if(point>=100 &&resultdice1 == 1)
        {
            v1.setImageResource(R.drawable.d1);
            x = 1;
        }
        else if(point>=100 &&resultdice1 == 2)
        {
            v1.setImageResource(R.drawable.d2);
            x = 2;
        }

        else if(point>=100 &&resultdice1 == 3)
        {
            v1.setImageResource(R.drawable.d3);
            x = 3;
        }


        else if(point>=100 &&resultdice1 == 4)
        {
            v1.setImageResource(R.drawable.d4);
            x = 4;
        }

        else if(point>=100 &&resultdice1 == 5)
        {
            v1.setImageResource(R.drawable.d5);
            x = 5;
        }
        else if(point>=100 &&resultdice1 == 6)

        {
            v1.setImageResource(R.drawable.d6);
            x = 6;
        }

        if(point>=100 &&resultdice2 == 1)
        {
            v2.setImageResource(R.drawable.d1);
            y = 1;
        }
        else if(point>=100 &&resultdice2 == 2)
        {
            v2.setImageResource(R.drawable.d2);
            y = 2;
        }

        else if(point>=100 &&resultdice2 == 3)
        {
            v2.setImageResource(R.drawable.d3);
            y = 3;
        }


        else if(point>=100 &&resultdice2 == 4)
        {
            v2.setImageResource(R.drawable.d4);
            y = 4;
        }

        else if(point>=100 &&resultdice2 == 5)
        {
            v2.setImageResource(R.drawable.d5);
            y = 5;
        }
        else if(point>=100 &&resultdice2 == 6)
        {
            v2.setImageResource(R.drawable.d6);
            y = 6;
        }

        if(point >=0) {

            if(x+y == 7)
            {
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setIcon(R.drawable.dice);
                dlg.setTitle("Lucky Seven!!.");
                dlg.setMessage("200p 를 획득 하셨습니다.");
                dlg.show();

                point +=200;
                /*String sqli = "UPDATE LoginSystem2 SET point="+point+ " WHERE nick='"+ nick +"';";
                db.execSQL(sqli);*/
            }
            if(x+y==12)
            {
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setIcon(R.drawable.d10);
                dlg.setTitle("이 맛이지 Jackpot!");
                dlg.setMessage("2000p 를 획득 하셨습니다.");
                dlg.show();

                point +=2000;
            }

            String cash = String.valueOf(point);
            txt.setText(cash);

            String sql = "UPDATE LoginSystem2 SET point="+point+ " WHERE nick='"+ nick +"';";
            db.execSQL(sql);

            Intent i = new Intent();
            String c = String.valueOf(point);
            i.putExtra("INPUT_TEXT", c);
            setResult(RESULT_OK, i);



        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lucky_dice, menu);
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
