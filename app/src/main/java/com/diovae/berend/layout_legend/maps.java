package com.diovae.berend.layout_legend;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class maps extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    protected GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onMapReady(GoogleMap googlemap) {
        mMap = googlemap;

            if (mMap != null) {
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.info_window, null);

                    TextView infoWindowTitle =(TextView) v.findViewById(R.id.InfoWindowTitle);
                    TextView tvSnippet = (TextView) v.findViewById(R.id.InfoWindowDescription);

                    String image_id = marker.getTitle();
                    int resID = getResources().getIdentifier(image_id, "drawable", getApplicationContext().getPackageName());
                    ImageView img = (ImageView) v.findViewById(R.id.InfoWindowImage);
                    img.setImageResource(resID);

                    infoWindowTitle.setText(image_id.replace("_"," "));
                    tvSnippet.setText(marker.getSnippet());

                    return v;
                   }


                @Override
                public View getInfoContents(Marker marker) {
                    return null;
                }

            });

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    String image_id = marker.getTitle();
                    Intent intent = new Intent(maps.this, MainActivity.class);
                    intent.putExtra("image_id",image_id);
                    startActivity(intent);
                }
            });
        }

        //Set min and max zoom
        mMap.setMinZoomPreference(12.0f);
        mMap.setMaxZoomPreference(20.0f);


        //Set boundaries
        LatLng center = new LatLng(52.3816064, 4.6485076);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center));
        LatLngBounds Haarlem = new LatLngBounds(
                new LatLng(52.338906077209, 4.60050391528942), new LatLng(52.4259429154355, 4.68397248207025));
        mMap.setLatLngBoundsForCameraTarget(Haarlem);

        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            mMap.setMyLocationEnabled(true);
        }


        InputStream inStream = getResources().openRawResource(R.raw.points_in_map);
        InputStreamReader inputReader = new InputStreamReader(inStream);
        BufferedReader reader = new BufferedReader(inputReader);

        String line;

        try {
            while ((line = reader.readLine()) != null) // Read until end of file
            {
                double lat = Double.parseDouble(line.split(";")[1]);
                double lon = Double.parseDouble(line.split(";")[2]);
                String title_marker = line.split(";")[3];
                String description_marker = line.split(";")[4];
                LatLng pos = new LatLng(lat, lon);

                //coordinates too map
                mMap.addMarker(new MarkerOptions()
                        .title(title_marker)
                        .snippet(description_marker)
                        .position(pos));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}



































