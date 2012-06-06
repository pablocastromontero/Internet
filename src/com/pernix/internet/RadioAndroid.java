package com.pernix.internet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

public class RadioAndroid extends Activity implements SeekBar.OnSeekBarChangeListener {
    /** Called when the activity is first created. */
    
    ProgressDialog dialog;
    Button button;
    MediaPlayer mp;
    Boolean first;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio);
        
        button=(Button)findViewById(R.id.cmdPlay);
        button.setText("Play");
        
        SeekBar mSeekBar;

        mSeekBar = (SeekBar)findViewById(R.id.sbVolume);
        mSeekBar.setOnSeekBarChangeListener(this);
       
       
        
        first=true;
        
button.setOnClickListener(new OnClickListener() {
            
            
            @Override
            public void onClick(View v) {
            
            if(button.getText().equals("Play")){
                
            if(first){
            
            dialog = new ProgressDialog(RadioAndroid.this);
            dialog.setMessage("Cargando...");
            dialog.show();
            
            first=false;
            playStreaming();
            
            
            }//if
            else{
                play();
            }
            
            }//if
            else{
                
            pauseStreaming();
                
            }//else
            
            }//onClick
        });


    }
    
    private void playStreaming() {
        
        String url="http://67.212.166.178:8092/";
	    mp = new MediaPlayer();
          try {
        
           mp.setDataSource(url);
           mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
           mp.prepareAsync();
           
           mp.setOnPreparedListener(new OnPreparedListener() {
            
            @Override
            public void onPrepared(MediaPlayer mp) {
            
                dialog.dismiss();
                mp.start();
                
                
            }
        });
           
           button.setText("Pause");
    
           
          } 
          catch (Exception e) {
              
         
           
          }
        
    }
    
    public void pauseStreaming(){
        
        mp.pause();
        button.setText("Play");
        
    }
    
    public void play(){
        
    mp.start();
    button.setText("Pause");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int volumen, boolean arg2) {
        
        mp.setVolume(volumen, volumen);
    }

    @Override
    public void onStartTrackingTouch(SeekBar arg0) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar arg0) {
    }
}