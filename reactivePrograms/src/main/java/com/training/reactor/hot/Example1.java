package com.training.reactor.hot;


import java.util.Arrays;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

/**
 * Example1
 * 
 * Hot vs Cold Observable
Observable mode depends on where your producer is created and activated.
COLD is when your observable creates/activates the producer (Producer created *inside* the observable)
HOT is when your observable listens to created/activated product (Producer created *outside* the observable)
 * 
 */
public class Example1 {

    public static void main(String[] args) {
        Flux<String> source = Flux.fromIterable(Arrays.asList("ram", "sam", "dam", "lam"))
            .doOnNext(System.out::println)
        //    .filter(s -> s.startsWith("l"))
            .map(String::toUpperCase);

    ConnectableFlux<String> connectable = source.publish();
    connectable.subscribe(d -> System.out.println("Subscriber 1: "+d));
    connectable.connect();
    connectable.subscribe(d -> System.out.println("Subscriber 2: "+d));
    }
}