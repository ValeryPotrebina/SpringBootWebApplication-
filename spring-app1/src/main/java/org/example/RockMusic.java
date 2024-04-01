package org.example;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Random;

//@Component
public class RockMusic implements Music{
    ArrayList<String> rockMusicSongs = new ArrayList<>();

    public RockMusic() {
        rockMusicSongs.add("Wind cries Mary");
        rockMusicSongs.add("rock2");
        rockMusicSongs.add("rock3");
    }

    @Override
    public String getSong() {
        return rockMusicSongs.get(new Random().nextInt(rockMusicSongs.size()));
    }

    @PostConstruct
    public void init() {
        System.out.println("Initialization *RockMusic*");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destruction *RockMusic*");
    }
}
