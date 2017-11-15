package project.final_mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity {

    String nick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent i = getIntent();

        nick=i.getStringExtra("name");

    }


    public void map(View v)
    {
        Button btn1 = (Button)findViewById(R.id.m1);            //수도권
        Button btn2 = (Button)findViewById(R.id.m2);            //강원도
        Button btn3 = (Button)findViewById(R.id.m3);            //충청도
        Button btn4 = (Button)findViewById(R.id.m4);            //전라도
        Button btn5 = (Button)findViewById(R.id.m5);            //경상도

        if(v==btn1)     //수도권
        {
            Intent intent = new Intent(MapActivity.this, M1.class);

            intent.putExtra("nick",nick);
            startActivity(intent);
        }
        if(v==btn2)     //강원도
        {
            Intent intent = new Intent(MapActivity.this, M2.class);
            startActivity(intent);
        }
        if(v==btn3)     //충청도
        {
            Intent intent = new Intent(MapActivity.this, M3.class);
            startActivity(intent);
        }
        if(v==btn4)     //전라도
        {
            Intent intent = new Intent(MapActivity.this, M4.class);
            startActivity(intent);
        }
        if(v==btn5)     //경상도
        {
            Intent intent = new Intent(MapActivity.this, M5.class);
            startActivity(intent);
        }
    }
}
