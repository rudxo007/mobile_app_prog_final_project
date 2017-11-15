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

public class M1 extends FragmentActivity {                 //수도권

    String nick;
    GpsInfo gps = new GpsInfo(M1.this);                     //현재 위도 경도 받는 클래스 생성
    final double latitude = gps.getLatitude();
    final double longitude = gps.getLongitude();

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1);

        Intent i = getIntent();
        nick=i.getStringExtra("nick");
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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.559986, 126.974365), 9));
    }

    public void addMarker()        //마커 추가(처음에 몇개 저장, 원하는곳 찍었을때 마커 추가가능)
    {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);



     //   googleMap.addMarker(new MarkerOptions().position(new LatLng(37.559986, 126.974365)).title("수도권이죠 그런거죠"));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.563181, 126.941737)).title("신촌").snippet("신촌을 못가 한번을 못가~").icon(bitmapDescriptor));
     /*   final MarkerOptions shopMarker[] =
                {
                   // new MarkerOptions().position(new LatLng(37.496299, 127.062446)).title("강남").snippet("굿굿굿"),
                   // new MarkerOptions().position(new LatLng(37.555166, 126.970562)).title("서울역").snippet("그렇군 도착이군"),
                    new MarkerOptions().position(new LatLng(37.563181, 126.941737)).title("신촌").snippet("신촌을 못가 한번을 못가~").icon(bitmapDescriptor)



                };*/

    /*    for (int i = 0;i < 3; ++i){
            shopMarker[i].icon(BitmapDescriptorFactory.fromResource(R.drawable.meal2));
            googleMap.addMarker(shopMarker[i]);
        }*/
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {          //인포윈도 화면 클릭시
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ko.wikipedia.org/wiki/%EC%8B%A0%EC%B4%8C"));
                startActivity(intent);

                Intent intent2 = new Intent(M1.this, board.class);
                intent2.putExtra("nick",nick);
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

                                                       title.setText("신촌");
                                                       snap.setText("신촌을 못가 한번을 못가~");


                                               return v;
                                           }
                                       }

            );


            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()

                                            {
                                                @Override
                                                public void onMapClick(LatLng latLng) {
                /*MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
                markerOptions.position(latLng);
                markerOptions.title("");

                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                googleMap.addMarker(markerOptions);*/
                                                    googleMap.addMarker(new MarkerOptions().position(latLng).title("내가선택"));

                                                }

                                            }

            );
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        }
    }
