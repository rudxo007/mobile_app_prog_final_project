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
/*       double longitude = 128.987658;
            double latitude = 35.805981;
            int zoom = 9;
            String pos = String.format("geo:%f,%f?z=%d", latitude, longitude, zoom);
            Uri geo = Uri.parse(pos);
            Intent intent = new Intent(Intent.ACTION_VIEW, geo);
            startActivity(intent);*/

public class M5 extends FragmentActivity {
    double longitude = 128.987658;
    double latitude = 35.805981;
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m5);
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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.805981, 128.987658), 9));
    }

    private void addMarker() {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(35.0469059, 128.9640447)).title("다대포").icon(bitmapDescriptor));

       /* MarkerOptions shopMarker[] = {
                new MarkerOptions().position(new LatLng(34.8858839, 128.6067475)).title("거제").snippet("대한민국 두 번째로 큰 섬"),
                new MarkerOptions().position(new LatLng
                        (35.0469059, 128.9640447)).title("다대포").snippet("숨겨진 보물"),
                new MarkerOptions().position(new LatLng
                        (35.8888046, 128.6080875)).title("경북대").snippet("대구지역 학교")
        };

        for (int i = 0;i < 3; ++i){
            shopMarker[i].icon(BitmapDescriptorFactory.fromResource(R.drawable.meal2));
            googleMap.addMarker(shopMarker[i]);


        }*/
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {          //인포윈도 화면 클릭시
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ko.wikipedia.org/wiki/%EB%8B%A4%EB%8C%80%ED%8F%AC%ED%95%B4%EC%88%98%EC%9A%95%EC%9E%A5"));
                startActivity(intent);

                Intent intent2 = new Intent(M5.this, M5_board.class);
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

                                               title.setText("다대포");
                                               snap.setText("숨겨진 보물");
                                               img.setImageResource(R.drawable.cataract);
                                               return v;
                                           }
                                       }

        );
    }
}
