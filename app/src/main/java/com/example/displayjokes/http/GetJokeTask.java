package com.example.displayjokes.http;

import android.os.AsyncTask;

import androidx.loader.content.CursorLoader;

import com.example.displayjokes.Logic.Deserializer;
import com.example.displayjokes.model.Joke;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PrivateKey;

public class GetJokeTask extends AsyncTask<String, Integer, Joke> {
    private Joke joke;
    @Override
    protected Joke doInBackground(String... strings) {
        try {
            URL url = new URL("https://icanhazdadjoke.com/");
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream responseBody = connection.getInputStream();
                Reader myReader = new InputStreamReader(responseBody);
                joke = Deserializer.deserialize(myReader, Joke.class);
                //responseAll = Deserializer.deserialize(myReader, PersonAllResponse.class);

            }
            else {
                InputStream responseBody = connection.getInputStream();
                Reader myReader = new InputStreamReader(responseBody);
                //responseAll = Deserializer.deserialize(myReader, PersonAllResponse.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return joke;
    }

    public Joke getJoke() {
        return joke;
    }
}
