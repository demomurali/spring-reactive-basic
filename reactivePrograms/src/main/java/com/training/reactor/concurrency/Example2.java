package com.training.reactor.concurrency;

import java.util.Arrays;

import org.omg.CORBA.ExceptionList;

import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Example2
 */
public class Example2 {

    public static void main(String[] args) throws Exception{
        String[] arr={"ramesh","suresh","dhinesh"};
        Flux<String> namesFlux=Flux.fromIterable(Arrays.asList(arr));
        Flux<String> namesFlux1= namesFlux
                //.publishOn(Schedulers.boundedElastic())
                //.parallel()
                .map(value->{
                    try{
                        System.out.println("first map "+Thread.currentThread());
                    }catch(Exception e){}
                    return value;
                })
               // .runOn(Schedulers.parallel())
               .publishOn(Schedulers.boundedElastic())
               .map(name->{
                        try{Thread.sleep(100);}catch(Exception e){}
                        System.out.println("mapper"+Thread.currentThread()+ " "+name.toUpperCase());
                        return name.toUpperCase() ;
                    }).subscribeOn(Schedulers.newParallel("parallel-scheduler", 2));
            namesFlux1.subscribe(value->System.out.println("Inside sub1: "+value+" "+Thread.currentThread()));
            namesFlux1.subscribe(value->System.out.println("Inside sub2: "+value+" "+Thread.currentThread()));        
                Thread.sleep(2000);
    }
}