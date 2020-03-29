package com.example.displayjokes.Logic;

import com.google.gson.Gson;

import java.io.*;

public class Deserializer {
    public static <T> T deserialize(Reader value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }
}

