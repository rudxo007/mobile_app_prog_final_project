package project.final_mobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.preference.DialogPreference;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class M6 extends FragmentActivity {                 //수도권



    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m6);
        init();
    }


    void init(){            //초기화
        if ( googleMap == null)
        {
            googleMap=((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment1)).getMap();
            if (googleMap != null){
                addMarker();
                //  mark();
            }

        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.8888003, 128.6080928), 9));
    }

    public void addMarker()        //마커 추가(처음에 몇개 저장, 원하는곳 찍었을때 마커 추가가능)
    {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(35.8888003, 128.6080928)).title("경북대").snippet("대구중심학교").icon(bitmapDescriptor));

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {          //인포윈도 화면 클릭시
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ko.wikipedia.org/wiki/%EA%B2%BD%EB%B6%81%EB%8C%80%ED%95%99%EA%B5%90"));
                startActivity(intent);

                Intent intent2 = new Intent(M6.this, M6_board.class);
                startActivity(intent2);

            }

        });

        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                                           // Use default InfoWindow frame
                                           @Override
                                           public View getInfoWindow(Marker arg0) {
                                               return null;
                                           }

                                           // Defines the contents of the InfoWindow
                                           @Override

                                           public View getInfoContents(Marker arg0) {
                                               View v = getLayoutInflater().inflate(R.layout.windowlayout, null);
                                               TextView title = (TextView) v.findViewById(R.id.title1);
                                               TextView snap = (TextView) v.findViewById(R.id.snap);
                                               ImageView img = (ImageView)v.findViewById(R.id.imageView);

                                               title.setText("경북대");
                                               snap.setText("대구중심학교");
                                               img.setImageResource(R.drawable.knu);

                                               return v;
                                           }
                                       }

        );




    }
}
