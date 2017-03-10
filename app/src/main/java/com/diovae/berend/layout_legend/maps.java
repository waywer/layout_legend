package com.diovae.berend.layout_legend;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

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
                    View v = getLayoutInflater().inflate(R.layout.infow_window, null);

                    TextView tvHaarlem = (TextView) v.findViewById(R.id.tvHaarlem);
                    TextView tvSnippet = (TextView) v.findViewById(R.id.tvNoord_Holland);

                    LatLng ll = marker.getPosition();
                    tvHaarlem.setText(marker.getTitle());
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
                    Intent intent = new Intent(maps.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

        //Add polygons
        //Polygon Haarlem
        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(52.3972493025405, 4.67563038906216), new LatLng(52.3936879470701, 4.67319886991241),
                        new LatLng(52.3909053938453, 4.67452066841425), new LatLng(52.389737567664, 4.67771626131506),
                        new LatLng(52.3902866618205, 4.6811903617531), new LatLng(52.3882160847386, 4.68259586820105),
                        new LatLng(52.3874437027485, 4.68692113646379), new LatLng(52.3834728263859, 4.68600263540365),
                        new LatLng(52.3818068716301, 4.68397248207025), new LatLng(52.3798722059021, 4.68036400206407),
                        new LatLng(52.3774768293716, 4.67768063925706), new LatLng(52.3771906612989, 4.6774490786943),
                        new LatLng(52.3758260857329, 4.67467575195987), new LatLng(52.3740486490056, 4.67258253901697),
                        new LatLng(52.3715396617523, 4.67136608935146), new LatLng(52.3677415412861, 4.67224552442739),
                        new LatLng(52.3566972045618, 4.67629936445528), new LatLng(52.355644934862, 4.67653984362748),
                        new LatLng(52.3542871350804, 4.67644584787989), new LatLng(52.3532013412246, 4.67587556543209),
                        new LatLng(52.351402080676, 4.67401034150446), new LatLng(52.3508621702949, 4.67308805482703),
                        new LatLng(52.3406544061508, 4.64955165153368), new LatLng(52.3398454399224, 4.64634249088136),
                        new LatLng(52.338906077209, 4.63964801704714), new LatLng(52.3396668843352, 4.63924322376375),
                        new LatLng(52.3411229641406, 4.63963215360086), new LatLng(52.3421936047562, 4.64041863212025),
                        new LatLng(52.3430211060531, 4.64059504456707), new LatLng(52.3439041406835, 4.64099903759188),
                        new LatLng(52.3455629628869, 4.64032479968842), new LatLng(52.3476406746653, 4.64049505732217),
                        new LatLng(52.3494703659177, 4.64031105906557), new LatLng(52.3511572689273, 4.63953560689747),
                        new LatLng(52.3533590267873, 4.63638822563814), new LatLng(52.355320158789, 4.63514258578392),
                        new LatLng(52.3565836693078, 4.63511792623773), new LatLng(52.358325812502, 4.63678102974035),
                        new LatLng(52.3602175817055, 4.63737668798515), new LatLng(52.3614841764692, 4.63662633766716),
                        new LatLng(52.3608040483072, 4.63320349990147), new LatLng(52.360724286513, 4.62990293721263),
                        new LatLng(52.3614689731779, 4.62720638204766), new LatLng(52.3621293701589, 4.62556051429856),
                        new LatLng(52.3614633356901, 4.61844034363014), new LatLng(52.3638996209719, 4.60573251848173),
                        new LatLng(52.3670447141737, 4.60732388901046), new LatLng(52.3679721902126, 4.60430537296307),
                        new LatLng(52.3713552561224, 4.60458647959545), new LatLng(52.3723772578802, 4.60151194137007),
                        new LatLng(52.376801864733, 4.60106681926423), new LatLng(52.3774544500618, 4.59951558762392),
                        new LatLng(52.3826096342367, 4.60050391528942), new LatLng(52.3854185374468, 4.6043487342938),
                        new LatLng(52.3854649055612, 4.60842140530722), new LatLng(52.389821133028, 4.61081951241128),
                        new LatLng(52.3880945409135, 4.62084926438528), new LatLng(52.3915535878494, 4.62359684299284),
                        new LatLng(52.3955435752927, 4.62362483372662), new LatLng(52.4116658409752, 4.63164775299107),
                        new LatLng(52.4109841290204, 4.63627942245133), new LatLng(52.4285696618644, 4.65076861048112),
                        new LatLng(52.4259429154355, 4.66183167866398), new LatLng(52.4255616652021, 4.66515925106454),
                        new LatLng(52.422454640913, 4.67209303228572), new LatLng(52.418722487065, 4.67468791902461),
                        new LatLng(52.4191576497094, 4.68124186986617), new LatLng(52.4138486995183, 4.6824761689089),
                        new LatLng(52.4096379129416, 4.67555006521692), new LatLng(52.4091422748309, 4.6723888555115),
                        new LatLng(52.4064693165167, 4.67437943631928), new LatLng(52.4041627986238, 4.66881620991149),
                        new LatLng(52.4013994015931, 4.66866457676291), new LatLng(52.3994774535637, 4.67868568558831),
                        new LatLng(52.3985147343854, 4.67857027761336), new LatLng(52.3972493025405, 4.67563038906216))
                .strokeColor(Color.GRAY)
                .strokeWidth(2)
                .fillColor(0x15FF0000);
        Polygon polygon = mMap.addPolygon(rectOptions);


        //Add Marker
        MarkerOptions options = new MarkerOptions()
                .title("Paviljoen Welgelegen")
                .snippet("Manifestatie")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapmarker))
                .position(new LatLng(52.3716064, 4.6285076));
        mMap.addMarker(options);

        //Set min and max zoom
        mMap.setMinZoomPreference(12.0f);
        mMap.setMaxZoomPreference(20.0f);


        //Set boundaries
        LatLng center = new LatLng(52.3816064,4.6485076);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center));
        LatLngBounds Haarlem = new LatLngBounds(
                new LatLng(52.338906077209, 4.60050391528942), new LatLng(52.4259429154355, 4.68397248207025));
        mMap.setLatLngBoundsForCameraTarget(Haarlem);
    }
}



































