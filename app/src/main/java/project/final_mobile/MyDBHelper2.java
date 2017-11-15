package project.final_mobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper2 extends SQLiteOpenHelper {  //데이터베이스 클래스
    public MyDBHelper2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
// TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub

        db.execSQL("CREATE TABLE write_board(_id INTEGER PRIMARY KEY AUTOINCREMENT, "         //테이블에 작성자, 댓글 내용

                + "name TEXT, "  + "content TEXT );" );    // name 작성자, content 댓글

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXIST write_board;");
        onCreate(db);
    }
}