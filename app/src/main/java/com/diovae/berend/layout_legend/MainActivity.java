package com.diovae.berend.layout_legend;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String image_id = getIntent().getStringExtra("image_id");
        ImageView dynamic_image = (ImageView) findViewById(R.id.WarImage);
        int resID = getResources().getIdentifier(image_id, "drawable", getApplicationContext().getPackageName());
        dynamic_image.setImageResource(resID);

        InputStream instream = getResources().openRawResource(R.raw.points_in_map);
        InputStreamReader inputreader = new InputStreamReader(instream);
        BufferedReader reader = new BufferedReader(inputreader);
        String line;
        Connection con = connectionClass();
        if(con == null){
            String z = "Check internet connection";
        }else {

            String query = "Select * from dbo.stories where id =" + image_id ;
            Statement stmt = null;
            try {
                stmt = con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ResultSet selectStories = stmt.executeQuery(query);
                ResultSet iets = selectStories;
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
                while ((line = reader.readLine()) != null) // Read until end of file
                {
                    if (Objects.equals(image_id, line.split(";")[0])) {
                        String datum = line.split(";")[5];
                        String location = line.split(";")[6];
                        String description = line.split(";")[7];
                        TextView text_datum = (TextView) findViewById(R.id.Date);
                        TextView text_location = (TextView) findViewById(R.id.Location);
                        TextView text_description = (TextView) findViewById(R.id.Story);

                        text_datum.setText(datum);
                        text_location.setText(location);
                        text_description.setText(description);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        String detail_image_id = getIntent().getStringExtra("image_id");
        intent.putExtra("detail_image_id",detail_image_id);
        startActivity(intent);
    }

    public Connection connectionClass(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionUrl;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //dataConnectionString
            connectionUrl = "jdbc:jtds:sqlserver://diovae.database.windows.net:1433;DatabaseName=Layout_Legend;user=diovae@diovae;password=Berend123%;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(connectionUrl);
        }catch (SQLException e){
            Log.e("Error here 1 : ", e.getMessage());
        }catch (ClassNotFoundException e){
            Log.e("Error here 2 : ", e.getMessage());
        }catch (Exception e){
            Log.e("Error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
