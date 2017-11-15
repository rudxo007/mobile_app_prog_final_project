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
/*int zoom = 9;
        String pos = String.format("geo:%f,%f?z=%d", latitude, longitude, zoom);
        Uri geo = Uri.parse(pos);
        Intent intent = new Intent(Intent.ACTION_VIEW, geo);
        startActivity(intent);*/

public class M2 extends FragmentActivity {      //강원도
    double longitude = 128.158061;
    double latitude = 37.844915;
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2);
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
                addMarker();
            }
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.844915, 128.158061), 9));
    }

    private void addMarker() {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.6457098, 128.6784184)).title("용평리조트").icon(bitmapDescriptor));

  /*      MarkerOptions shopMarker[] = {
                new MarkerOptions().position(new LatLng(37.6457098, 128.6784184)).title("용평리조트").snippet("겨울엔 스키지"),
                new MarkerOptions().position(new LatLng
                        (37.7867933, 128.7853902)).title("경포대").snippet("겨울바다 춥다..."),
                new MarkerOptions().position(new LatLng
                        (38.1196043, 128.4304528)).title("설악산").snippet("겨울경치가 장관이지")
        };

        for (int i = 0;i < 3; ++i){
            shopMarker[i].icon(BitmapDescriptorFactory.fromResource(R.drawable.meal2));
            googleMap.addMarker(shopMarker[i]);


        }*/
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {          //인포윈도 화면 클릭시
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ko.wikipedia.org/wiki/%EC%9A%A9%ED%8F%89%EB%A6%AC%EC%A1%B0%ED%8A%B8"));
                startActivity(intent);

                Intent intent2 = new Intent(M2.this, M2_board.class);
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

                                               title.setText("용평리조트");
                                               snap.setText("겨울엔 스키지");
                                               img.setImageResource(R.drawable.ski);


                                               return v;
                                           }
                                       }

        );
    }
}
