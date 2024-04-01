package org.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Random;


public class JazzMusic implements Music {
    private ArrayList<String> jazzMusicSongs = new ArrayList<>();

    public JazzMusic() {
        jazzMusicSongs.add("jazz1");
        jazzMusicSongs.add("jazz2");
        jazzMusicSongs.add("jazz3");
    }
    @PostConstruct
    public void init() {
        System.out.println("Initialization *JazzMusic*");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destruction *JazzMusic*");
    }

    @Override
    public String getSong() {
        return jazzMusicSongs.get(new Random().nextInt(jazzMusicSongs.size()));
    }


}
