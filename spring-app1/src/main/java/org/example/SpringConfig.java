package org.example;

import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
//@ComponentScan("org.example")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {

    @Bean ClassicalMusic classicalMusic(){
        return new ClassicalMusic();
    }
    @Bean RockMusic rockMusic(){
        return new RockMusic();
    }
    @Bean JazzMusic jazzMusic(){
        return new JazzMusic();
    }
    @Bean List<Music> music(){
        return Arrays.asList(classicalMusic(), rockMusic(), jazzMusic());
    }
    @Bean MusicPlayer musicPlayer(){
        return new MusicPlayer(music());
    }
    @Bean Computer computer(){
        return new Computer(musicPlayer());
    }
}
