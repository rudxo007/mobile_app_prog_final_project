package com.example.minchanp.final_project;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends TabActivity {


    final static String img1="홈";
    final static String img2="지도";
    final static String img3="미션";
    final static String img4="Cash";
    final static String img5="내 정보";
    String nick;
    int cash;


    public static TabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = openOrCreateDatabase("text.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Intent i = getIntent();
        int date = i.getIntExtra("date", 0);
        int point = i.getIntExtra("point", 0);

            ++date;
        if(date == 1)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setIcon(R.drawable.o4);
            dlg.setTitle("환영 합니다.");
            dlg.setMessage("Cash & Treasure에 처음 오신걸 환영합니다."
                    + "처음 오신 분들께 이벤트 포인트 500p를 드립니다.");
            dlg.show();

            String sql = "UPDATE LoginSystem2 SET date="+ date +";";
            db.execSQL(sql);

            db.close();


        }


        tabHost = getTabHost();
        tabHost.setup();


        setupTab(new TextView(this), img1);
        setupTab(new TextView(this), img2);
        setupTab(new TextView(this), img3);
        setupTab(new TextView(this), img4);
        //setupTab(new TextView(this), img5);

        tabHost.getTabWidget().setDividerDrawable(null);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                ImageView img;
                TextView txt;

                switch(tabId){
                    case img1:
                        img = (ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.o1);
                        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.parseColor("#3871a6"));

                        img = (ImageView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n2);
                        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n3);
                        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n4);
                        tabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                      /*  img = (ImageView) tabHost.getTabWidget().getChildAt(4).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(4).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n5);
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        tabHost.getTabWidget().getChildAt(4).setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE); */

                        break;
                    case img2:
                        img = (ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n1);
                        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.o2);
                        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#000000"));
                                img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.RED);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n3);
                        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n4);
                        tabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                    /*    img = (ImageView) tabHost.getTabWidget().getChildAt(4).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(4).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n5);
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        tabHost.getTabWidget().getChildAt(4).setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE); */
                        break;
                    case img3:
                        img = (ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n1);
                        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.textView3);
                        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        img.setImageResource(R.drawable.n2);
                        txt.setTextColor(Color.WHITE);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.o3);
                        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.parseColor("#764e82"));

                        img = (ImageView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.textView3);
                        tabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        img.setImageResource(R.drawable.n4);
                        txt.setTextColor(Color.WHITE);

                       /* img = (ImageView) tabHost.getTabWidget().getChildAt(4).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(4).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n5);
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        tabHost.getTabWidget().getChildAt(4).setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);*/
                        break;
                    case img4:
                        img = (ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.imageView);
                        img.setImageResource(R.drawable.n1);
                        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#000000"));
                        txt =(TextView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.textView3);
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n2);
                        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);
                        img = (ImageView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n3);
                        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.o4);
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        tabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.parseColor("#f3753a"));

                      /*  img = (ImageView) tabHost.getTabWidget().getChildAt(4).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(4).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n5);
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        tabHost.getTabWidget().getChildAt(4).setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE); */
                        break;

                    case img5:
                        img = (ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.imageView);
                        img.setImageResource(R.drawable.n1);
                        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#000000"));
                        txt =(TextView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.textView3);
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n2);
                        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);
                        img = (ImageView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n3);
                        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#000000"));
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);


                        img = (ImageView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.n4);
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        tabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.WHITE);

                        img = (ImageView) tabHost.getTabWidget().getChildAt(4).findViewById(R.id.imageView);
                        txt =(TextView) tabHost.getTabWidget().getChildAt(4).findViewById(R.id.textView3);
                        img.setImageResource(R.drawable.o5);
                        img.setBackgroundColor(Color.parseColor("#000000"));
                        tabHost.getTabWidget().getChildAt(4).setBackgroundColor(Color.parseColor("#000000"));
                        txt.setTextColor(Color.parseColor("#8ec96d"));

                        break;
                }

            }
        });



    }


    private static View createTabView(final Context context,final String text)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.background,null);
        ImageView img;
        TextView txt;
        TextView tv = (TextView) view.findViewById(R.id.textView3);

        if(text.equals(img1))
        {
            img = (ImageView)view.findViewById(R.id.imageView);
            img.setImageResource(R.drawable.o1);
            view.setBackgroundColor(Color.parseColor("#000000"));
            img.setBackgroundColor(Color.parseColor("#000000"));
            tv.setTextColor(Color.parseColor("#3871a6"));
        }

        else if(text.equals(img2))
        {
            img =(ImageView)view.findViewById(R.id.imageView);
            img.setImageResource(R.drawable.n2);
            view.setBackgroundColor(Color.parseColor("#000000"));
            img.setBackgroundColor(Color.parseColor("#000000"));
            tv.setTextColor(Color.WHITE);
        }

        else if(text.equals(img3))
        {
            img =(ImageView)view.findViewById(R.id.imageView);
            img.setImageResource(R.drawable.n3);
            view.setBackgroundColor(Color.parseColor("#000000"));
            img.setBackgroundColor(Color.parseColor("#000000"));
            tv.setTextColor(Color.WHITE);
        }

        else if(text.equals(img4))
        {
            img =(ImageView)view.findViewById(R.id.imageView);
            img.setImageResource(R.drawable.n4);
            view.setBackgroundColor(Color.parseColor("#000000"));
            img.setBackgroundColor(Color.parseColor("#000000"));
            tv.setTextColor(Color.WHITE);
        }
        else if(text.equals(img5))
        {

            img =(ImageView)view.findViewById(R.id.imageView);
            img.setImageResource(R.drawable.n5);
            view.setBackgroundColor(Color.parseColor("#000000"));
            img.setBackgroundColor(Color.parseColor("#000000"));
            tv.setTextColor(Color.WHITE);
        }

        tv.setText(text);

        return view;
    }

    private void setupTab(final View view, final String tag)
    {

        View tabview = createTabView(tabHost.getContext(), tag);
        TabHost.TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabview);

        Intent i = getIntent();
        nick = i.getStringExtra("nick");
        cash = i.getIntExtra("point",0);

        if(tag.equals(img1))
            setContent.setContent(new Intent(this,HomeActivity.class).putExtra("name", nick));
        else if(tag.equals(img2))
            setContent.setContent(new Intent(this,MapActivity.class));
        else if(tag.equals(img3))
            setContent.setContent(new Intent(this, MissionActivity.class).putExtra("cash",cash).putExtra("name",nick));
        else if(tag.equals(img4))
            setContent.setContent(new Intent(this, CashActivity.class).putExtra("cash",cash).putExtra("name",nick));
        else if(tag.equals(img5))
            setContent.setContent(new Intent(this, CustomerActivity.class));

        tabHost.addTab(setContent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
