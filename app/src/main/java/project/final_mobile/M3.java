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
/*double longitude = 126.954099;
            double latitude = 36.562312;
            int zoom = 9;
            String pos = String.format("geo:%f,%f?z=%d", latitude, longitude, zoom);
            Uri geo = Uri.parse(pos);
            Intent intent = new Intent(Intent.ACTION_VIEW, geo);
            startActivity(intent);*/

public class M3 extends FragmentActivity {
    double longitude = 126.954099;
    double latitude = 36.562312;
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m3);
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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.562312, 126.954099), 9));
    }

    private void addMarker() {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(36.3693964, 127.3618309)).title("카이스트").icon(bitmapDescriptor));

  /*      MarkerOptions shopMarker[] = {
                new MarkerOptions().position(new LatLng(36.4589832, 127.1163975)).title("공주").snippet("역사가 살아있는 땅"),
                new MarkerOptions().position(new LatLng
                        (36.3425312, 127.1882808)).title("계룡산").snippet("계룡산이 짱이지"),
                new MarkerOptions().position(new LatLng
                        (36.3693964, 127.3618309)).title("카이스트").snippet("가고싶다...")
        };

        for (int i = 0;i < 3; ++i){
            shopMarker[i].icon(BitmapDescriptorFactory.fromResource(R.drawable.meal2));
            googleMap.addMarker(shopMarker[i]);


        }*/

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {          //인포윈도 화면 클릭시
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ko.wikipedia.org/wiki/%ED%95%9C%EA%B5%AD%EA%B3%BC%ED%95%99%EA%B8%B0%EC%88%A0%EC%9B%90"));
                startActivity(intent);

                Intent intent2 = new Intent(M3.this, M3_board.class);
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

                                               title.setText("카이스트");
                                               snap.setText("엄청난 학교");
                                               img.setImageResource(R.drawable.kaist);

                                               return v;
                                           }
                                       }

        );
    }


}
