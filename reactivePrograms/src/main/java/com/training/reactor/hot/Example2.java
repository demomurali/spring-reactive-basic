package com.training.reactor.hot;

import java.time.Duration;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;

/**
 * Example2
 */
public class Example2 {

    private static Stream<String> getMovie(){
        System.out.println("Got the movie streaming request");
        return Stream.of(
                "scene 1",
                "scene 2",
                "scene 3",
                "scene 4",
                "scene 5"
        );
    }
    
    public static void main(String[] args) throws Exception{
        //our NetFlux streamer
        //each scene will play for 2 seconds
        Flux<String> netFlux = Flux.fromStream(() -> getMovie())
                        .delayElements(Duration.ofSeconds(1)).share();

        // you start watching the movie
        netFlux.subscribe(scene -> System.out.println("sub1 is watching " + scene));
        //I join after sometime
        Thread.sleep(2000);
        System.out.println("sub2 started the stream");
        netFlux.subscribe(scene -> System.out.println("sub2 is watching " + scene));
   
        Thread.sleep(5000);

    }
}