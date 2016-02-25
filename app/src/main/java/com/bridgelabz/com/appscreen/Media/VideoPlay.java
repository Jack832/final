package com.bridgelabz.com.appscreen.Media;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bridgelabz.com.appscreen.R;

/**
 * Created by bridgelabz3 on 1/2/16.
 */
public class VideoPlay extends AppCompatActivity
{
    TextView text;
    private Toolbar toolbar;
    String VideoURL="http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_play);

        toolbar=(Toolbar)findViewById(R.id.app_bar1);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        text=(TextView)findViewById(R.id.text);
        videoView=(VideoView)findViewById(R.id.videoView);

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(VideoPlay.this);
            mediacontroller.setAnchorView(videoView);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            videoView.setMediaController(mediacontroller);
            videoView.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ImageButton btn=(ImageButton)findViewById(R.id.allMediaBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllMedia.class));
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
