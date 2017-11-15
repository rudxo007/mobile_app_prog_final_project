package project.final_mobile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class M2_board extends Activity implements AdapterView.OnItemClickListener,

        View.OnClickListener {//오늘의 일정 목록 띄우기

    MyDBHelper mDBHelper;
    MyDBHelper2 mDBHelper2;
    String board;
    Cursor cursor;
    SimpleCursorAdapter adapter;
    ListView list;
    /** Called when the activity is first created. */
    ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2_board);

        Intent intent = getIntent();
        /*board = intent.getStringExtra("Param1");
        TextView text = (TextView) findViewById(R.id.texttoday);
        text.setText(board);*/

        mDBHelper = new MyDBHelper(this, "Board.db", null, 1);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        //     cursor = db.rawQuery("SELECT * FROM board WHERE title = '" + board + "'", null);
        cursor = db.rawQuery("SELECT * FROM board;", null);
        adapter = new SimpleCursorAdapter(this, R.layout.layout, cursor, new String[] { "title", "memo", "pic"},
                new int[] {R.id.textView1, R.id.textView2, R.id.img1 });

        list = (ListView) findViewById(R.id.list1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        mDBHelper.close();
        Button btn = (Button) findViewById(R.id.btnadd);
        btn.setOnClickListener(this);





    }
    @Override

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
// TODO Auto-generated method stub

      /*  if(memoedit.getText().toString().equals("0")) {*/
        Intent intent = new Intent(this, Data_Board.class);         //Data_Board
        cursor.moveToPosition(position);
        intent.putExtra("ParamID", cursor.getInt(0));
        startActivityForResult(intent, 0);
        /*}
        else
        {
            Intent intent = new Intent(this, Data_Storage.class);         //Data_Storage
            cursor.moveToPosition(position);
            intent.putExtra("ParamID", cursor.getInt(0));
            startActivityForResult(intent, 0);
        }*/

    }
    @Override
    public void onClick(View v) {
        img = (ImageView)findViewById(R.id.img1);
        img.setImageResource(R.drawable.sinchon1);
// TODO Auto-generated method stub
        Intent intent = new Intent(this, Data_Storage.class);
        intent.putExtra("ParamDate", board);

        startActivityForResult(intent, 1);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        img = (ImageView)findViewById(R.id.img1);
        img.setImageResource(R.drawable.sinchon1);
// TODO Auto-generated method stub
// super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
            case 1:
                if (resultCode == RESULT_OK) {
// adapter.notifyDataSetChanged();
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    //            cursor = db.rawQuery("SELECT * FROM board WHERE title = '" + board + "'", null);
                    cursor = db.rawQuery("SELECT * FROM board;", null);
                    adapter.changeCursor(cursor);
                    mDBHelper.close();
                }
                break;
        }




    }



}