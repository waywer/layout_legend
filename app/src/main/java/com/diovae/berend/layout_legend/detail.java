package com.diovae.berend.layout_legend;

import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

import java.io.IOException;

public class detail extends AppCompatActivity implements SurfaceHolder.Callback {


    @SuppressWarnings("deprecation")
    Camera camera; // camera class variable
    SurfaceView camView; // drawing camera preview using this variable
    SurfaceHolder surfaceHolder; // variable to hold surface for surfaceView which means display
    boolean camCondition = false;  // conditional variable for camera preview checking and set to false

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);// get layout of detail screen(image)
        final SeekBar seeker = (SeekBar) findViewById(R.id.seekBar);
        // getWindow() to get window and set it's pixel format which is UNKNOWN
        getWindow().setFormat(PixelFormat.UNKNOWN);
        // refering the id of surfaceView
        camView = (SurfaceView) findViewById(R.id.camerapreview);
        final ImageView background_image = (ImageView) findViewById(R.id.imageView6);
        // getting access to the surface of surfaceView and return it to surfaceHolder
        surfaceHolder = camView.getHolder();
        // adding call back to this context means MainActivity
        surfaceHolder.addCallback(this);
        // to set surface type
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);

        // TODO keep the button switched if mobile is switched
        final Switch camera_switch =(Switch) findViewById(R.id.camera_button); // when switch is pushed: make background image (in)visible
        camera_switch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                camera_switch.setSelected(camera_switch.isPressed()); // if switch is activated: make background image invisible
                if(camera_switch.isChecked()) {
                    background_image.setVisibility(View.INVISIBLE);
                }
                else {                                               // if switch is activated: make background image visible
                    background_image.setVisibility(View.VISIBLE);
                }

            }
        });

        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { //if the seekbar value is changed, start process
                ImageView image = (ImageView) findViewById(R.id.detail_warImage);// image that will be change by the seekbar

                float waarde = seeker.getProgress(); // create variable that has the value of the seekbar
                image.setAlpha(waarde/255);// set alpha value of the image the same as the value of the seekbar
            }

            @Override// isn't used for the seekbar in this project but is needed
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override// isn't used for the seekbar in this project but is needed
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
        // stop the camera
        if(camCondition){
            camera.stopPreview(); // stop preview using stopPreview() method
            camCondition = false; // setting camera condition to false means stop
        }
        // condition to check whether your device have camera or not
        if (camera != null){
            try {
                Camera.Parameters parameters = camera.getParameters();
                camera.setParameters(parameters); // setting camera parameters
                camera.setPreviewDisplay(surfaceHolder); // setting preview of camera
                camera.startPreview();  // starting camera preview

                camCondition = true; // setting camera to true which means having camera
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        int rotation = getWindowManager().getDefaultDisplay().getRotation();//get ready to rotate
        camera = Camera.open();   // opening camera
        if(rotation == Surface.ROTATION_0){
            camera.setDisplayOrientation(90);   // setting camera preview orientation to landscape
        }
        else if(rotation == Surface.ROTATION_90) {
            camera.setDisplayOrientation(0);   // setting camera preview orientation to portrait
        }
        else if(rotation == Surface.ROTATION_270){
            camera.setDisplayOrientation(180);   // setting camera preview orientation to reverse landscape
        }
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        camera.stopPreview();  // stopping camera preview
        camera.release();       // releasing camera
        camera = null;          // setting camera to null when left
        camCondition = false;   // setting camera condition to false also when exit from application
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig); // restart activity for the camera orientation.
    }
}

