package com.example.skadush.lesonone;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by skadush on 23/05/17.
 */

public class OpenglAcitivty extends AppCompatActivity {


    GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView = new GLSurfaceView(this);

        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportES2 = configurationInfo.reqGlEsVersion >= 0x20000;

        int index = getIntent().getIntExtra("index",0);
        Toast.makeText(this, " " + index, Toast.LENGTH_SHORT).show();


        if (supportES2) {
            glSurfaceView.setEGLContextClientVersion(2);
            if(index == 1){
                glSurfaceView.setRenderer(new Lesson2Renderer(this));
            }else if(index == 2){
                glSurfaceView.setRenderer(new Lesson3Renderer(this));
            }else{
                glSurfaceView.setRenderer(new LessonOneRenderer(this));
            }
            //Toast.makeText(this, "wooohooo", Toast.LENGTH_SHORT).show();
        } else {
            return;
        }

        setContentView(glSurfaceView);



    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }


    // https://www.mkyong.com/java/how-to-convert-inputstream-to-string-in-java/
    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
