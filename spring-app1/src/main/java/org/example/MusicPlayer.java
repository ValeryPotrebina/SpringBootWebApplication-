package org.example;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//@Component
public class MusicPlayer {
    @Value("${musicPlayer.name}")
    @Getter private String name;
    @Value("${musicPlayer.volume}")
    @Getter private int volume;
    private List<Music> music;
//    IoC
//    @Autowired
    public MusicPlayer(List<Music> music) {
        this.music = music;
    }
//    public MusicPlayer() {
//
//    }
    public String playMusic(){
        return music.get(new Random().nextInt(music.size())).getSong();
    }

    @PostConstruct
    private void init() {
        System.out.println("Initialization *MusicPlayer*");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Destruction *MusicPlayer*");
    }
}
