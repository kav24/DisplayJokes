package com.example.displayjokes.model;

public class Joke {
    String id;
    String joke;
    Integer status;

    public Joke(String id, String joke, Integer status) {
        this.id = id;
        this.joke = joke;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id='" + id + '\'' +
                ", joke='" + joke + '\'' +
                ", status=" + status +
                '}';
    }
}
