package com.training.reactor.concurrency;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Example3
 * 
 * 
 * publishOn applies in the same way as any other operator, 
 * in the middle of the subscriber chain.
 *  It takes signals from upstream and 
 * replays them downstream while executing the 
 * callback on a worker from the associated Scheduler. 
 * Consequently, it affects where the subsequent operators execute
 * 
 * 
 * subscribeOn applies to the subscription process, 
 * when that backward chain is constructed. 
 * As a consequence, no matter where you place the subscribeOn 
 * in the chain, 
 * it always affects the context of the source emission
 * 
 * 
 * 
 */
public class Example3 {

    public static void main(String[] args) {

        Scheduler schedulerA = Schedulers.newParallel("scheduler-a", 4);
        Scheduler schedulerB = Schedulers.newParallel("scheduler-b", 4);
      
        Flux.range(1, 2)
            .map(i -> {
                System.out.println(String.format("First map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                return i;
            }).publishOn(Schedulers.newParallel("scheduler-b", 4))
            .map(i -> {
                System.out.println(String.format("Second map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                return i;
            }).subscribe(value->System.out.println("Inside sub: "+value+" "+Thread.currentThread()));
        }
}