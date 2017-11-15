package project.final_mobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {  //데이터베이스 클래스
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
// TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub

        db.execSQL("CREATE TABLE board(_id INTEGER PRIMARY KEY AUTOINCREMENT, "         //테이블에 제목, 작성자, 내용, 사진

                + "title TEXT, " + "memo TEXT , " + "pic BLOB );");    // title 제목, memo 내용, pic 사진

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXIST board;");
        onCreate(db);
    }
}