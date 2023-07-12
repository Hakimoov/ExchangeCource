package org.example.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.models.Valyuta;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class ValyutaServiceImpl implements ValyutaService{
    @Override
    public List<Valyuta> getValyuta() {
        try {
            URL url = new URL("https://cbu.uz/uz/arkhiv-kursov-valyut/json/");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String row;
            StringBuilder stringBuilder = new StringBuilder();
            while ((row = bufferedReader.readLine()) != null){
                stringBuilder.append(row);
            }

            Type type = new  TypeToken<List<Valyuta>>(){}.getType();
            Gson gson = new Gson();

            List<Valyuta> valyuta = gson.fromJson(stringBuilder.toString(), type);
            return valyuta;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
