package com.trydev.games.adeklita;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.trydev.games.adeklita.Quest.Quest;

import java.util.ArrayList;

import static com.trydev.games.adeklita.MenuBalitaActivity.seek;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton fruit, animal, alphabet, color;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        fruit = (ImageButton) findViewById(R.id.fruit);
        animal = (ImageButton) findViewById(R.id.animal);
        alphabet = (ImageButton) findViewById(R.id.alphabet);
        color = (ImageButton) findViewById(R.id.color);

        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.setLooping(true);
        start();

        fruit.setOnClickListener(this);
        animal.setOnClickListener(this);
        alphabet.setOnClickListener(this);
        color.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fruit:
                createFruitQuest();
                break;
            case R.id.animal:
                createAnimalQuest();
                break;
            case R.id.alphabet:
                crateAlphabetQuest();
                break;
            case R.id.color:
                crateColorQuest();
                break;
        }
        Intent intent;
        intent = new Intent(getApplicationContext(), GameActivity.class);
        mediaPlayer.pause();
        seek = mediaPlayer.getCurrentPosition();
        CategoryActivity.this.finish();
        startActivity(intent);
    }

    private void crateColorQuest() {
        ArrayList<Quest> colorQuest = new ArrayList<>();
        colorQuest.add(new Quest(R.drawable.error, "merah","warna"));
        colorQuest.add(new Quest(R.drawable.home, "putih","warna"));
        colorQuest.add(new Quest(R.drawable.love,"merah muda","warna"));
        colorQuest.add(new Quest(R.drawable.music, "oranye","warna"));
        colorQuest.add(new Quest(R.drawable.star,"kuning","warna"));
        colorQuest.add(new Quest(R.drawable.success,"hijau","warna"));
        colorQuest.add(new Quest(R.drawable.user,"ungu","warna"));
        colorQuest.add(new Quest(R.drawable.water_drop,"biru","warna"));
        GameActivity.myQuest = colorQuest;
    }

    private void crateAlphabetQuest() {
        ArrayList<Quest> alphabetQuest = new ArrayList<>();
        alphabetQuest.add(new Quest(R.drawable.h, "h","huruf"));
        alphabetQuest.add(new Quest(R.drawable.a,"a","huruf"));
        alphabetQuest.add(new Quest(R.drawable.p, "p","huruf"));
        alphabetQuest.add(new Quest(R.drawable.y,"y","huruf"));
        alphabetQuest.add(new Quest(R.drawable.d, "d","huruf"));
        alphabetQuest.add(new Quest(R.drawable.i,"i","huruf"));
        alphabetQuest.add(new Quest(R.drawable.n, "n","huruf"));
        alphabetQuest.add(new Quest(R.drawable.o,"o","huruf"));
        GameActivity.myQuest = alphabetQuest;
    }

    private void createAnimalQuest() {
        ArrayList<Quest> animalQuest = new ArrayList<>();
        animalQuest.add(new Quest(R.drawable.bear, "beruang","hewan"));
        animalQuest.add(new Quest(R.drawable.cow, "sapi","hewan"));
        animalQuest.add(new Quest(R.drawable.crocodile, "buaya","hewan"));
        animalQuest.add(new Quest(R.drawable.deer, "rusa","hewan"));
        animalQuest.add(new Quest(R.drawable.donkey, "keledai","hewan"));
        animalQuest.add(new Quest(R.drawable.koala, "koala","hewan"));
        animalQuest.add(new Quest(R.drawable.panda, "panda","hewan"));
        GameActivity.myQuest = animalQuest;
    }

    private void createFruitQuest() {
        ArrayList<Quest> fruitQuest = new ArrayList<>();
        fruitQuest.add(new Quest(R.drawable.apple_correct, "apel","buah"));
        fruitQuest.add(new Quest(R.drawable.banana_correct, "pisang","buah"));
        fruitQuest.add(new Quest(R.drawable.cherry_correct, "ceri","buah"));
        fruitQuest.add(new Quest(R.drawable.eggplant_correct, "eggplant","buah"));
        fruitQuest.add(new Quest(R.drawable.grape_correct, "anggur","buah"));
        fruitQuest.add(new Quest(R.drawable.orange_correct, "jeruk","buah"));
        fruitQuest.add(new Quest(R.drawable.pear_correct, "pir","buah"));
        fruitQuest.add(new Quest(R.drawable.strawberry_correct, "strawberry","buah"));
        fruitQuest.add(new Quest(R.drawable.tomato_correct, "tomat","buah"));
        GameActivity.myQuest = fruitQuest;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
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

    @Override
    public void onBackPressed() {
        mediaPlayer.pause();
        seek = mediaPlayer.getCurrentPosition();
        super.onBackPressed();
    }

    private void start(){
        if (seek>0){
            mediaPlayer.seekTo(seek);
        }
        mediaPlayer.start();
    }
}
