package project.final_mobile;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class Data_Board extends Activity   {

    MyDBHelper mDBHelper;               //db
    MyDBHelper2 mDBHelper2;
    Cursor cursor2;

    int mId, mId2;
    EditText editTitle, editMemo, editPic, editPass;
    String board;


    private ImageView mPhotoImageView1, mPhotoImageView2, mPhotoImageView3, mPhotoImageView4, mPhotoImageView5, mPhotoImageView6;
    private Button btnsave, btndel;             //댓글 저장, 삭제
    private EditText comment;                                     //댓글 적는 edittext
    private ListView write_board;
    SimpleCursorAdapter adapter;                            //리스트뷰 어댑터

    private final int imgWidth = 320;
    private final int imgHeight = 372;
    String nick;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__board);

        mPhotoImageView1 = (ImageView) findViewById(R.id.image1);
        mPhotoImageView2 = (ImageView) findViewById(R.id.image2);
        mPhotoImageView3 = (ImageView) findViewById(R.id.image3);
        mPhotoImageView4 = (ImageView) findViewById(R.id.image4);
        mPhotoImageView5 = (ImageView) findViewById(R.id.image5);
        mPhotoImageView6 = (ImageView) findViewById(R.id.image6);


        //db생성부분
        Intent i = getIntent();
        nick=i.getStringExtra("nick");

        editTitle = (EditText) findViewById(R.id.title);
        editMemo = (EditText) findViewById(R.id.memoedit);
        editPic = (EditText) findViewById(R.id.memoedit);


        Intent intent = getIntent();
        Intent intent2 = getIntent();



        mId = intent.getIntExtra("ParamID", -1);
        board = intent.getStringExtra("ParamDate");
        mDBHelper = new MyDBHelper(this, "Board.db", null, 1);          //Board.db 생성


        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM board WHERE _id='" + mId + "'", null);


        if (cursor.moveToNext()) {
            editTitle.setText(cursor.getString(1));
            editMemo.setText(cursor.getString(2));
        }

        mDBHelper.close();





        btnsave = (Button)findViewById(R.id.btnsave);               //댓글 추가, 삭제
        btndel = (Button)findViewById(R.id.btndel);

        comment = (EditText)findViewById(R.id.comment);             //댓글 적는텍스트
        write_board = (ListView)findViewById(R.id.write_board);



        mId2 = intent2.getIntExtra("ParamID", -1);


        mDBHelper2 = new MyDBHelper2(this, "Write.db", null, 1);
        SQLiteDatabase db2 = mDBHelper2.getWritableDatabase();

        cursor2 = db2.rawQuery("SELECT * FROM write;", null);
        adapter = new SimpleCursorAdapter(this, R.layout.layout2, cursor2, new String[] { "name", "content"},
                new int[] {R.id.txtname, R.id.txtcomment});

        if (cursor2.moveToNext()) {
            comment.setText(cursor2.getString(1));
            comment.setText(cursor2.getString(2));

        }
        write_board.setAdapter(adapter);

        mDBHelper2.close();



        mPhotoImageView1.setFocusable(false);
        mPhotoImageView2.setFocusable(false);
        mPhotoImageView3.setFocusable(false);
        mPhotoImageView4.setFocusable(false);
        mPhotoImageView5.setFocusable(false);
        mPhotoImageView6.setFocusable(false);
        editTitle.setFocusable(false);
        editMemo.setFocusable(false);



    }



    public void BtnClick2(View v)
    {

        SQLiteDatabase db2 = openOrCreateDatabase("Write.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db2.execSQL("CREATE TABLE IF NOT EXISTS write " + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, content TEXT );");
        ContentValues values = new ContentValues();
        values.put("name",comment.getText().toString());
        values.put("content", nick);


        switch (v.getId())
        {

            case R.id.btnsave:

                db2.insert("write", null, values);
                break;

            case R.id.btndel:
                db2.delete("write", "name=?", new String[] {comment.getText().toString()});
                break;

        }

        Intent intent2 = getIntent();
        mId2 = intent2.getIntExtra("ParamID", -1);


        mDBHelper2 = new MyDBHelper2(this, "Write.db", null, 1);
        db2 = mDBHelper2.getWritableDatabase();

        cursor2 = db2.rawQuery("SELECT * FROM write;", null);
        adapter = new SimpleCursorAdapter(this, R.layout.layout2, cursor2, new String[] { "name", "content"},
                new int[] {R.id.txtname, R.id.txtcomment});

        if (cursor2.moveToNext()) {
            comment.setText(cursor2.getString(1));
            comment.setText(cursor2.getString(2));
        }
        write_board.setAdapter(adapter);

    }
}

