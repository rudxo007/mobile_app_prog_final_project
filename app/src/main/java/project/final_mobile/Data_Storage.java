package project.final_mobile;

import android.app.AlarmManager;
import android.app.LauncherActivity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

public class Data_Storage extends Activity implements View.OnClickListener {

    MyDBHelper mDBHelper;               //db
    SQLiteDatabase db;
    Drawable transf_data;


    int mId, mId2;
    EditText editTitle, editMemo;
    String board;

    private Bitmap selectedImage;       //이미지
    byte[] bArray;
    //image
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    Cursor cursor2;
    SimpleCursorAdapter adapter;                            //리스트뷰 어댑터


    View view1;
    EditText input;                 //password text

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;

    private Uri mImageCaptureUri;
    private ImageView mPhotoImageView1, mPhotoImageView2, mPhotoImageView3, mPhotoImageView4, mPhotoImageView5, mPhotoImageView6;


    Button  btnsave, btndel, btncancel;
    Bitmap photo2;
    Bitmap bm;

    ImageView blobImg;
    ImageView img1;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__storage);


        mPhotoImageView1 = (ImageView) findViewById(R.id.image1);
        mPhotoImageView2 = (ImageView) findViewById(R.id.image2);
        mPhotoImageView3 = (ImageView) findViewById(R.id.image3);
        mPhotoImageView4 = (ImageView) findViewById(R.id.image4);
        mPhotoImageView5 = (ImageView) findViewById(R.id.image5);
        mPhotoImageView6 = (ImageView) findViewById(R.id.image6);

        mPhotoImageView1.setOnClickListener(this);                  //사진 클릭시 이벤트
        mPhotoImageView2.setOnClickListener(this);
        mPhotoImageView3.setOnClickListener(this);
        mPhotoImageView4.setOnClickListener(this);
        mPhotoImageView5.setOnClickListener(this);
        mPhotoImageView6.setOnClickListener(this);


        //db생성부분


        editTitle = (EditText) findViewById(R.id.title);
        editMemo = (EditText) findViewById(R.id.memoedit);

        db = openOrCreateDatabase("Board.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS board " + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, memo TEXT, pic BLOB );");

        Intent intent = getIntent();
        mId = intent.getIntExtra("ParamID", -1);
        board = intent.getStringExtra("ParamDate");
        mDBHelper = new MyDBHelper(this, "Board.db", null, 1);          //Board.db 생성


            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM board WHERE _id='" + mId + "'", null);



        if (cursor.moveToNext()) {
                editTitle.setText(cursor.getString(1));
                editMemo.setText(cursor.getString(2));
            //mPhotoImageView1.setImageBitmap(photo2);

                //mPhotoImageView1(cursor.getString(3));
             //   editPass.setText(cursor.getString(4));


            }

  /*      Bitmap bitmap = BitmapFactory.decodeByteArray(bArray, 0, bArray.length);
        mPhotoImageView1.setImageBitmap(bitmap);*/



  /*      Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.f6);              //db 이미지 되는부분
        mPhotoImageView1.setImageBitmap(bm);
        try {
            FileOutputStream fos = openFileOutput("test.png", 0);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }catch (Exception e)
        {
            e.printStackTrace();
        }*/




      //  mPhotoImageView1.setImageBitmap(bm);




            mDBHelper.close();

        btnsave = (Button)findViewById(R.id.btnsave);
        btndel = (Button)findViewById(R.id.btndel);
        btncancel = (Button)findViewById(R.id.btncancel);



        Button btn1 = (Button) findViewById(R.id.btnsave);
        Button btn2 = (Button) findViewById(R.id.btndel);
        Button btn3 = (Button) findViewById(R.id.btncancel);

        if (mId == -1) {
            btndel.setVisibility(View.INVISIBLE);                     //저장된 내용이 없으면 삭제버튼 안보임
        }

        img1 = (ImageView)findViewById(R.id.img1);

    /*    blobImg= (ImageView)findViewById(R.id.image1);
        insertBlob("blob0000");
        showImg();*/
    }


    public void  insertBlob (String imageName) {
        SQLiteDatabase db=null;
       // WorldTourSqliteHelper mHelper = new WorldTourSqliteHelper(this);

        int count =0;
        try {
            db = mDBHelper.getWritableDatabase();
            String sql  = "UPDATE MASTER_IMAGE SET IMAGE=?";
            SQLiteStatement insertStmt      =   db.compileStatement(sql);
            insertStmt.clearBindings();
            insertStmt.bindBlob(1, getBlob (imageName));
            insertStmt.execute();
            mDBHelper.close();
            Log.d("dddd", "InsertComplete!!!!");
        }


     catch (Exception e) {
        Log.e("Thread", "Insert Error", e);

    } finally {
        mDBHelper.close();
    }
}


    private byte[] getBlob(String image){
        /*ByteArrayBuffer baf = new ByteArrayBuffer(500);
        OutputStream baf = new OutputStream(500);
        ByteArrayInputStream baf = new ByteArrayInputStream(500);*/
        ByteArrayOutputStream baf = new ByteArrayOutputStream(500);
        try {
            String FILE_PATH1 = "sdcard/";
            File file = new File(FILE_PATH1, image+".jpg");
            InputStream is = new FileInputStream(file);

            BufferedInputStream bis = new BufferedInputStream(is);
            int current = 0;
            while ((current = bis.read()) != -1) {
                //baf.append((byte) current);
                baf.write((byte) current);
            }
            return baf.toByteArray();
        } catch (Exception e) {
            Log.d("ImageManager", "Error: " + e.toString());
        }
        return baf.toByteArray();
    }

    private void showImg(){
        SQLiteDatabase db=null;
    //    WorldTourSqliteHelper mHelper = new WorldTourSqliteHelper(this);
        int count =0;

        try {
            db = mDBHelper.getWritableDatabase();
            String sql1 = "SELECT IMAGE FROM MASTER_IMAGE ";
            Cursor monthCursor = db.rawQuery(sql1, null);
            while (monthCursor.moveToNext()) {
                byte[] image = monthCursor.getBlob(0);
                Bitmap bm= BitmapFactory.decodeByteArray( image, 0, image.length);
                blobImg .setImageBitmap(bm);
            }
            monthCursor.close();

        }catch(Exception e){
        }
        finally{
            mDBHelper.close();
        }
    }


    /**
     * DB 영역
     */


 /*   public void BtnClick(View v){
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.btnsave:
                if (mId != -1) {
                    db.execSQL("UPDATE board SET title='" + editTitle.getText().toString()
                            + "',memo='" + editMemo.getText().toString()
                            + "', pic='" + editMemo.toString()
                            + "', pass='" + editPass.getText().toString()
                            + "' WHERE _id='" + mId
                            + "';");
                } else {
                    db.execSQL("INSERT INTO board VALUES(null, '"
                            + editTitle.getText().toString() + "', '"
                            + editMemo.getText().toString() + "', '"
                            + editMemo.toString() + "', '"
                            + editPass.getText().toString() + "');");
                }
                mDBHelper.close();
                setResult(RESULT_OK);
                break;

            case R.id.btndel:
                if (mId != -1) {
                    db.execSQL("DELETE FROM board WHERE _id='" + mId + "';");
                    mDBHelper.close();
                }
                setResult(RESULT_OK);
                break;

            case R.id.btncancel:
                setResult(RESULT_CANCELED);
                break;

        }
        finish();

    }
*/
    /**
     * DB 영역
     */

    public Bitmap getBitmap()
    {
        return photo2;
    }

  public void BtnClick2(View v){


      ContentValues values = new ContentValues();

     // SQLiteDatabase db = mDBHelper.getWritableDatabase();

      switch (v.getId())
      {

          case R.id.btnsave:
              if (mId != -1) {

                  db.execSQL("UPDATE board SET title='" + editTitle.getText().toString()
                          + "',memo='" + editMemo.getText().toString()
                          + "', pic='" + bArray
                          + "' WHERE _id='" + mId
                          + "';");

              }
              else
              {
                  values.put("title",editTitle.getText().toString());
                  values.put("memo",editMemo.getText().toString());
                  values.put("pic",bArray);
                  SQLiteDatabase db = mDBHelper.getWritableDatabase();
                  db.insert("board", null, values);
              }
              /*String sql1 = "SELECT pic FROM board;";
              Cursor monthCursor = db.rawQuery(sql1, null);
              while(monthCursor.moveToNext()) {
                  byte[] bb = monthCursor.getBlob(0);
                  Bitmap bm = BitmapFactory.decodeByteArray(bb, 0, bb.length);
                  mPhotoImageView1.setImageBitmap(bm);
              }*/



              setResult(RESULT_OK);
              mPhotoImageView1.setImageResource(R.drawable.sinchon1);


/*
              if(selectedImage == null)               //이미지 db
                  Log.e("upload", "selectedImage is null");
              selectedImage.compress(Bitmap.CompressFormat.PNG, 100, bos);
              bArray = bos.toByteArray();
              try {
                  bm = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageCaptureUri);
                  mPhotoImageView1.setImageBitmap(bm);
              }
              catch (Exception e)
              {
                  e.printStackTrace();
              }*/

                      break;


          case R.id.btndel:
              db.delete("board", "title=?", new String[] {editTitle.getText().toString()});
              if (mId != -1) {
                  db.execSQL("DELETE FROM board WHERE _id='" + mId + "';");
                  mDBHelper.close();
              }
              setResult(RESULT_OK);
              break;

          case R.id.btncancel:
              setResult(RESULT_CANCELED);
              break;



      }


    //       Bitmap bm = BitmapFactory.decodeResource(getResources(), transf_data);

  /*      mPhotoImageView1.setImageBitmap(bm);
        try {
            FileOutputStream fos = openFileOutput("test.png", 0);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }catch (Exception e)
        {
            e.printStackTrace();
        }*/

      finish();
 //     mPhotoImageView1.setImageBitmap(photo2);


  }





    /**
     * 이미지 DB 영역
     */



/**
 * 이미지 DB 영역
 */




    /**
     * 카메라에서 이미지 가져오기
     */

    private void doTakePhotoAction()
    {
    /*
     * 참고 해볼곳
     * http://2009.hfoss.org/Tutorial:Camera_and_Gallery_Demo
     * http://stackoverflow.com/questions/1050297/how-to-get-the-url-of-the-captured-image
     * http://www.damonkohler.com/2009/02/android-recipes.html
     * http://www.firstclown.us/tag/android/
     */

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 임시로 사용할 파일의 경로를 생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        // 특정기기에서 사진을 저장못하는 문제가 있어 다음을 주석처리 합니다.
        //intent.putExtra("return-data", true);
        startActivityForResult(intent, PICK_FROM_CAMERA);


        //Drawable d = getResources().getDrawable(R.draw)



    }

    /**
     * 앨범에서 이미지 가져오기
     */
    private void doTakeAlbumAction()
    {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if(resultCode != RESULT_OK)
        {
            return;
        }

        switch(requestCode)
        {
            case CROP_FROM_CAMERA:
            {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.
                final Bundle extras = data.getExtras();

                if(extras != null)
                {
                    Bitmap photo = extras.getParcelable("data");
              /*      bm=photo;
                  //  photo2=photo;
                    Drawable transData = new BitmapDrawable(photo);
                    transf_data = transData;
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                        photo2=bm;
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }*/

                    if(view1==mPhotoImageView1) {
                        mPhotoImageView1.setImageBitmap(photo);
                    }
                    if(view1==mPhotoImageView2)
                        mPhotoImageView2.setImageBitmap(photo);
                    if(view1==mPhotoImageView3)
                        mPhotoImageView3.setImageBitmap(photo);
                    if(view1==mPhotoImageView4)
                        mPhotoImageView4.setImageBitmap(photo);
                    if(view1==mPhotoImageView5)
                        mPhotoImageView5.setImageBitmap(photo);
                    if(view1==mPhotoImageView6)
                        mPhotoImageView6.setImageBitmap(photo);
                }

                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                if(f.exists())
                {
                    f.delete();
                }

                break;
            }

            case PICK_FROM_ALBUM:
            {
                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.

                mImageCaptureUri = data.getData();
                if(resultCode==Activity.RESULT_OK)
                {
                    try {
                        //Uri에서 이미지 이름을 얻어온다.
                        //String name_Str = getImageNameToUri(data.getData());

                        //이미지 데이터를 비트맵으로 받아온다.
                        Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                        photo2=image_bitmap;
                        ImageView image = (ImageView)findViewById(R.id.image1);

                        //배치해놓은 ImageView에 set
                        image.setImageBitmap(image_bitmap);


                        //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();


                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            case PICK_FROM_CAMERA:
            {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("outputX", 90);
                intent.putExtra("outputY", 90);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }
        }




    }



    @Override
    public void onClick(View v) {


        view1 = v;
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakePhotoAction();
            }
        };

        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };


        new AlertDialog.Builder(this)
                .setTitle("업로드할 이미지 선택")
                .setPositiveButton("사진촬영", cameraListener)
                .setNeutralButton("앨범선택", albumListener)
                .setNegativeButton("취소", cancelListener)
                .show();


        }

}




