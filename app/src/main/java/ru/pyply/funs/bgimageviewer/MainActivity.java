package ru.pyply.funs.bgimageviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    final String STRING_URL = "https://fotki.ykt.ru/albums/userpics/24407/42_stars_mobile.jpg";
    ImageView viewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewer = findViewById(R.id.ImageView);
        new LoadImage().execute();
    }

    private class LoadImage extends AsyncTask<Void, Integer, Bitmap> {
        protected Bitmap doInBackground(Void... args) {
            try {
                Thread.sleep(2000); // Каждые 2 сек обновляем картинку

                final URL url = new URL(STRING_URL);
                InputStream inputStream = (InputStream) url.getContent();
                // Если всё хорошо то возвращаем в onPostExecute объект типа Bitmap
                return BitmapFactory.decodeStream(inputStream);
            } catch (InterruptedException | IOException e) {
                // Иначе печатаем ошибку и возвращаем null
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Bitmap bit) {
            super.onPostExecute(bit);

            if (bit == null) return; // Если ничего не передано, то не ставим картинку

            viewer.setImageBitmap(bit);
        }
    }
}