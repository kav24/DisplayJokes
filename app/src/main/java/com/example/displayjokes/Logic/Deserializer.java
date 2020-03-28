package com.example.displayjokes.Logic;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;

public class Deserializer {
    public static <T> T deserialize(Reader value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }
    public static <T> T deserialize(String value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }

    public static <T> String serialize (Object inputObject)
    {
        Gson gson = new Gson();
        return gson.toJson(inputObject);
    }

    public static <T> T serialize (String path, Class<T> returnType) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader infile = new FileReader(path);
        return  gson.fromJson(infile, returnType);
    }


}

