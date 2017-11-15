package project.final_mobile;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
/*  double longitude = 126.954091;
            double latitude = 35.356427;
            int zoom = 9;
            String pos = String.format("geo:%f,%f?z=%d", latitude, longitude, zoom);
            Uri geo = Uri.parse(pos);
            Intent intent = new Intent(Intent.ACTION_VIEW, geo);
            startActivity(intent);*/

public class M4 extends FragmentActivity {
    double longitude = 126.954091;
    double latitude = 35.356427;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m4);
        init();
    }

    protected void onResume(){
        super.onResume();
        init();
    }

    void init(){
        if ( googleMap == null)
        {
            googleMap=((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment1)).getMap();
            if (googleMap != null){
                addMarker();}
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.356427, 126.954091), 9));
    }

    private void addMarker() {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(35.1768201, 126.7735883)).title("광주").icon(bitmapDescriptor));

        /*MarkerOptions shopMarker[] = {
                new MarkerOptions().position(new LatLng(35.8255645, 127.0964947)).title("전주시").snippet("호남지방의 중심지"),
                new MarkerOptions().position(new LatLng
                        (35.9659144, 126.6759447)).title("군산시").snippet("서해안 주요 항구 도시"),
                new MarkerOptions().position(new LatLng
                        (35.1768201, 126.7735883)).title("광주").snippet("호남권 거점 도시")
        };

        for (int i = 0;i < 3; ++i){
            shopMarker[i].icon(BitmapDescriptorFactory.fromResource(R.drawable.meal2));
            googleMap.addMarker(shopMarker[i]);


        }*/
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {          //인포윈도 화면 클릭시
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ko.wikipedia.org/wiki/%EA%B4%91%EC%A3%BC%EA%B4%91%EC%97%AD%EC%8B%9C"));
                startActivity(intent);

                Intent intent2 = new Intent(M4.this, M4_board.class);
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

                                               title.setText("광주");
                                               snap.setText("호남권 거점 도시");
                                               img.setImageResource(R.drawable.gwangju);


                                               return v;
                                           }
                                       }

        );
    }
}
