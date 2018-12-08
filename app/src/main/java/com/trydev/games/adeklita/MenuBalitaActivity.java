package com.trydev.games.adeklita;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MenuBalitaActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton start, instruction, exit;
    MediaPlayer mediaPlayer;

    public static int seek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubalita);

        start = (ImageButton) findViewById(R.id.start);
        instruction = (ImageButton) findViewById(R.id.instruction);
        exit = (ImageButton) findViewById(R.id.exit);

        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.setLooping(true);
        start();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO
                )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MenuBalitaActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},200);
        }

        start.setOnClickListener(this);
        instruction.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.start:
                intent = new Intent(MenuBalitaActivity.this, CategoryActivity.class);
                mediaPlayer.pause();
                seek = mediaPlayer.getCurrentPosition();
                startActivity(intent);
                break;
            case R.id.instruction:
                intent = new Intent(MenuBalitaActivity.this, InstructionActivity.class);
                mediaPlayer.pause();
                seek = mediaPlayer.getCurrentPosition();
                startActivity(intent);
                break;
            case R.id.exit:
                intent = new Intent(MenuBalitaActivity.this, MainActivity.class);
                mediaPlayer.stop();
                startActivity(intent);
                break;
        }
    }



    @Override
    protected void onDestroy() {
        seek = 0;
        mediaPlayer.release();
        super.onDestroy();
//        stopService(it);
    }

    @Override
    protected void onStop() {
        mediaPlayer.pause();
        seek = mediaPlayer.getCurrentPosition();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        start();
    }

    private void start(){
        if (seek>0){
            mediaPlayer.seekTo(seek);
        }
        mediaPlayer.start();
    }
}
