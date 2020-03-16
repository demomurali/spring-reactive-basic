package com.training.reactor.concurrency;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Example
 
Schedulers.immediate() – the current thread

Schedulers.single() – a single reusable thread. 
This will re-use the same single thread until the 
Scheduler is disposed of.
single-threaded ExecutorService-based worker


Schedulers.boundElastic() – creates new worker pool as needed and 
reuses the idle ones. I

that dynamically creates a bounded number of ExecutorService-based Workers, 
reusing them once the Workers have been shut down. 
The underlying daemon threads can be evicted if idle for more than SECONDS 60 seconds.

Schedulers.parallel() – you can create a specific number of threads 
for that Scheduler. It defaults to your CPU cores.
 
 
 */
public class Example {

    public static void main(String[] args) throws Exception{
        Flux emitter=Flux.interval(Duration.ofMillis(200), Schedulers.boundedElastic());
            emitter.subscribe(value->System.out.println("sub1 "+Thread.currentThread()+":"+value));
            emitter.subscribe(value->System.out.println("sub2"+Thread.currentThread()+":"+value));

  System.out.println(Thread.currentThread());    
  Thread.sleep(1000);
 
    }
}