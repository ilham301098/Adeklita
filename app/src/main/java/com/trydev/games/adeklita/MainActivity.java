package com.trydev.games.adeklita;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView Menu1, Menu2, MenuTentang;
    MediaPlayer mediaPlayer;

    public static int seek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.setLooping(true);
        start();

        Menu1 = (ImageView) findViewById(R.id.pgBalita);
        Menu2 = (ImageView) findViewById(R.id.pgOrtu);

        AssetManager assetManager = this.getAssets();
        InputStream is1= null;
        InputStream is2= null;

        try {
            is1 = assetManager.open("img/baby1.png");
            is2 = assetManager.open("img/ortu1.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap1 = BitmapFactory.decodeStream(is1);
        Bitmap bitmap2 = BitmapFactory.decodeStream(is2);
        Menu1.setImageBitmap(bitmap1);
        Menu2.setImageBitmap(bitmap2);
    }

    //cara kedua
    public void pageBalita(View view) {
        Intent hitungIntent = new Intent(MainActivity.this,MenuBalitaActivity.class);
        mediaPlayer.pause();
        seek = mediaPlayer.getCurrentPosition();
        startActivity(hitungIntent);

    }


    public void pageOrtu(View view){
        Intent i = new Intent(MainActivity.this, MenuActivity.class);
        mediaPlayer.pause();
        seek = mediaPlayer.getCurrentPosition();
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage(R.string.wanna_exit)
                .setCancelable(true)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.stop();
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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
