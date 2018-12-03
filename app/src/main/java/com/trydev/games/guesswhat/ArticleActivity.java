package com.trydev.games.guesswhat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ArticleActivity extends AppCompatActivity {
    ListView listView;

    String[] daftarTitle;
    int[] fruitImages = {R.drawable.alita,R.drawable.alita,R.drawable.alita,R.drawable.alita,R.drawable.alita};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        listView = findViewById(R.id.idListView);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        daftarTitle = databaseAccess.getTitleBalita();

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Activitylistdata.class);
                intent.putExtra("name",daftarTitle[i]);
                intent.putExtra("image",fruitImages[i]);
                startActivity(intent);

            }
        });

    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return daftarTitle.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.fruits);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(daftarTitle[i]);
            image.setImageResource(fruitImages[i]);
            return view1;



        }
    }

}
