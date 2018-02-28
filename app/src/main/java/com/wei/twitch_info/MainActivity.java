package com.wei.twitch_info;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.twitch.tv/kraken/channels/";

    private TwitchService service;

    private ImageView img;
    private EditText editText_ch;
    private TextView tv_playing, tv_displayName, tv_followers, tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_ch = (EditText) findViewById(R.id.editText_ch);
        tv_playing = (TextView) findViewById(R.id.textView_playing);
        tv_displayName = (TextView) findViewById(R.id.textView_displayName);
        tv_followers = (TextView) findViewById(R.id.textView_followers);
        tv_title = (TextView) findViewById(R.id.textView_title);
        tv_title.setMovementMethod(new ScrollingMovementMethod());
        img = (ImageView) findViewById(R.id.img);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TwitchService.class);



    }

    public void onBtnGo(View view){

        String ch = editText_ch.getText().toString();

        if (ch == null){
            Toast.makeText(this, "Please enter the channel id!", Toast.LENGTH_LONG).show();

        }else {

            Call<TwitchInfo> repos = service.getInfo(ch);

            repos.enqueue(new Callback<TwitchInfo>() {
                @Override
                public void onResponse(Call<TwitchInfo> call, Response<TwitchInfo> response) {
                    TwitchInfo twitchInfo = response.body();
                    if(twitchInfo!=null) {
                        //建立一個AsyncTask執行緒進行圖片讀取動作，並帶入圖片連結網址路徑
                        new AsyncTask<String, Void, Bitmap>(){
                            @Override
                            protected Bitmap doInBackground(String... params) {
                                String url = params[0];
                                return getBitmapFromURL(url);
                            }
                            @Override
                            protected void onPostExecute(Bitmap result)
                            {
                                img. setImageBitmap (result);
                                super.onPostExecute(result);
                            }
                        }.execute(twitchInfo.getLogo());
                        tv_displayName.setText(twitchInfo.getDisplayName());
                        tv_title.setText(twitchInfo.getStatus());
                        tv_playing.setText(twitchInfo.getGame());
                        tv_followers.setText(String.valueOf(twitchInfo.getFollowers()));
                    }else{
                        Toast.makeText(MainActivity.this, "Something wrong! Maybe null channel id.", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<TwitchInfo> call, Throwable t) {

                }
            });
        }
    }

    //讀取網路圖片，型態為Bitmap
    private static Bitmap getBitmapFromURL(String imageUrl)
    {
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}


