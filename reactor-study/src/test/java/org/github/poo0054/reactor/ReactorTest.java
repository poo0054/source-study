package org.github.poo0054.reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;


/**
 * @author zhangzhi
 */

public class ReactorTest {

    @Test
    void fluxTest() {


    }

    @Test
    void fluxErrorTest() {
        Flux<Integer> flux1 = Flux.range(1, 3);
        flux1.subscribe(System.out::println);
        System.out.println("-------------------------------------");

        Flux<Integer> ints = Flux.range(1, 6)
                .handle((i, sink) -> {
                    if (i <= 3) {
                        sink.next(i);
                        return;
                    }
                    sink.error(new RuntimeException("Got to 4"));
                });
        System.out.println("-------------------------------------");
        ints.subscribe(System.out::println,
                error -> System.err.println("Error: " + error));

        System.out.println("-------------------------------------");

        ints.subscribe(System.out::println,
                error -> System.err.println("Error ：" + error),
                () -> System.out.println("end"));

        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    public void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    public void hookOnNext(Integer integer) {
                        System.out.println("Cancelling after having received " + integer);
                        cancel();
                    }
                });

        System.out.println("-------------------------------------");
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });

        Flux<String> flux2 = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                });
    }

}
