package com.alexperezbuildthatapp.ideatreejackson;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


public class TrackInfo extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_info);
        //Track TItle and Artist Name
        TextView trackTitle = findViewById(R.id.textView_SongTitle);
        TextView artistName = findViewById(R.id.textView_ArtistName);

        String titleData= getIntent().getStringExtra("trackTitle");
        String artistData = getIntent().getStringExtra("artistName");

        trackTitle.setText(titleData);
        artistName.setText(artistData);

        //Handling Time conversion
        TextView totalTime = findViewById(R.id.textView_EndTime);
        String durationData = getIntent().getStringExtra("duration");

        int seconds = (int) (Integer.valueOf(durationData) / 1000) % 60 ;
        int minutes = (int) ((Integer.valueOf(durationData) / (1000*60)) % 60);
        totalTime.setText(minutes + ":" + seconds);

        //Handling Music URL Preview Handler
        final String previewURL = getIntent().getStringExtra("previewURL");

        final ImageView playButton = findViewById(R.id.imageView_Play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 0){
                    flag = 1;
                    playButton.setImageResource(android.R.drawable.ic_media_pause);
                    //Base Case
                    if(mediaPlayer!=null) {
                        try {
                            mediaPlayer.release();
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }

                    mediaPlayer = new MediaPlayer();

                    try {
                        mediaPlayer.setDataSource(previewURL);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.start();
                    if(!mediaPlayer.isPlaying()){
                        playButton.setImageResource(android.R.drawable.ic_media_play);
                    }
                }
                else{
                    flag = 0;
                    playButton.setImageResource(android.R.drawable.ic_media_play);
                    if(mediaPlayer!=null) {
                        try {
                            mediaPlayer.release();
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

    }
}
