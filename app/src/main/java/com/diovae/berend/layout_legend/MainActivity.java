package com.diovae.berend.layout_legend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String image_id = new String(getIntent().getStringExtra("image_id"));
        ImageView dynamic_image = (ImageView) findViewById(R.id.WarImage);
        int resID = getResources().getIdentifier(image_id, "drawable", getApplicationContext().getPackageName());
        dynamic_image.setImageResource(resID);

        InputStream instream = getResources().openRawResource(R.raw.points_in_map);
        InputStreamReader inputreader = new InputStreamReader(instream);
        BufferedReader reader = new BufferedReader(inputreader);
        String line = "";

        try {
            while ((line = reader.readLine()) != null) // Read until end of file
            {
                String id = new String(line.split(";")[3]);
                String datum = new String(line.split(";")[5]);
                String location = new String(line.split(";")[6]);
                String description = new String(line.split(";")[7]);
                if (id == image_id) {
                    TextView text_datum = (TextView) findViewById(R.id.textView5);
                    TextView text_location = (TextView) findViewById(R.id.textView7);
                    TextView text_description = (TextView) findViewById(R.id.textView8);
                    text_datum.setText(datum);
                    text_location.setText(location);
                    text_description.setText(description);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void detail(View view){
        Intent intent = new Intent(this, detail.class);
        startActivity(intent);
    }
}
