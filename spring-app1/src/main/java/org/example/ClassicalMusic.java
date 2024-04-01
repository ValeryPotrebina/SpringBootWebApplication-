package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Random;

//@Component
//@Scope("prototype")
public class ClassicalMusic implements Music{
    private ArrayList<String> classicalMusicSongs = new ArrayList<>();
    public ClassicalMusic() {
        classicalMusicSongs.add("Hungarian Phapsody");
        classicalMusicSongs.add("Beethoven");
        classicalMusicSongs.add("Chaikovski");
    }
    public static ClassicalMusic getClassicalMusic() {
        System.out.println("creating object *ClassicalMusic*");
        return new ClassicalMusic();
    }
    @PostConstruct
    public void init() {
        System.out.println("Initialization *ClassicalMusic*");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destruction *ClassicalMusic*");
    }
    @Override
    public String getSong() {
        return classicalMusicSongs.get(new Random().nextInt(classicalMusicSongs.size()));
    }



}
