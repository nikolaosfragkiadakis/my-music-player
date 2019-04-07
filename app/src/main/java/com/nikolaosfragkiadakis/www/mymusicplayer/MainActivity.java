package com.nikolaosfragkiadakis.www.mymusicplayer;

/** MIT License
  *
  * Copyright (c) 2018 Nikolaos Fragkiadakis
  *
  * Permission is hereby granted, free of charge, to any person obtaining a copy
  * of this software and associated documentation files (the "Software"), to deal
  * in the Software without restriction, including without limitation the rights
  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  * copies of the Software, and to permit persons to whom the Software is
  * furnished to do so, subject to the following conditions:
  *
  * The above copyright notice and this permission notice shall be included in all
  * copies or substantial portions of the Software.
  *
  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  * SOFTWARE. */

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private Button pauseButton;
    private Button stopButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the Play Button in the activity_main.xml file by its ID.
        playButton = (Button) findViewById(R.id.play_button);
        // Find the Pause Button in the activity_main.xml file by its ID.
        pauseButton = (Button) findViewById(R.id.pause_button);
        // Find the Stop Button in the activity_main.xml file by its ID.
        stopButton = (Button)findViewById(R.id.stop_button);

        // Initialize the Media Player instance.
        mediaPlayer = MediaPlayer.create(this, R.raw.flymetothemoon);

        // Disable the Pause Button since the song isn't playing yet.
        pauseButton.setEnabled(false);

        // Set the setOnClickListener method for the Play Button.
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Figure out if the song is playing or not.
                if (!mediaPlayer.isPlaying()){
                    Toast.makeText(getApplicationContext(),"Play!",Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                    playButton.setEnabled(false);
                    pauseButton.setEnabled(true);

                    // Set the OnCompletionListener.
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(getApplicationContext(),"I'm done!", Toast.LENGTH_SHORT).show();
                            // Initialize again the Media Player.
                            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.flymetothemoon);
                            mediaPlayer.reset();
                            playButton.setEnabled(true);
                            pauseButton.setEnabled(false);

                        }
                    });
                }
            }
        });

        // Set the setOnClickListener method for the Pause Button.
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Figure out if the song is playing or not.
                if (mediaPlayer.isPlaying()){
                    Toast.makeText(getApplicationContext(),"Paused!",Toast.LENGTH_SHORT).show();
                    mediaPlayer.pause();
                    playButton.setEnabled(true);
                    pauseButton.setEnabled(false);
                }
            }
        });

        // Set the setOnClickListener method for the Stop Button.
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Figure out if the song is playing or not.
                if (mediaPlayer.isPlaying()){
                    Toast.makeText(getApplicationContext(),"Stopped!",Toast.LENGTH_SHORT).show();
                    mediaPlayer.reset();
                    playButton.setEnabled(true);
                    pauseButton.setEnabled(false);
                    // Initialize again the Media Player.
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.flymetothemoon);
                }
            }
        });
    }
}