package org.github.poo0054.reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @author zhangzhi
 */

public class ReactorTest {

    private static void print() {
        print("-------------------------------------");
    }

    private static void print(String x) {
        System.out.print("\n" + x + "\n");
    }

    /*
    subscribe();

subscribe(Consumer<? super T> consumer);

subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer);

subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer,
          Runnable completeConsumer);

subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer,
          Runnable completeConsumer,
          Consumer<? super Subscription> subscriptionConsumer);
     */
    @Test
    void subscribeTest() {
        Flux<Integer> flux1 = Flux.range(1, 3);
        flux1.subscribe(System.out::println);
        print();
        Flux<Integer> ints = Flux.range(1, 4)
                .handle((i, sink) -> {
                    if (i <= 3) {
                        sink.next(i);
                        return;
                    }
                    sink.error(new RuntimeException("Got to 4"));
                });

        print();
        ints.subscribe(System.out::println,
                error -> System.err.println("Error: " + error));


        print();
        ints.subscribe(System.out::println,
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));


        print("------------SampleSubscriber -------------------------");
    }

    @Test
    void subscriberTest() {
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(System.out::println,
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                s -> ss.request(10));
        ints.subscribe(ss);
    }

    /**
     * 最简单的创建 Flux 的方式就是使用 generate 方法。
     * <p>
     * 这是一种 同步地， 逐个地 产生值的方法，意味着 sink 是一个 SynchronousSink 而且其 next() 方法在每次回调的时候最多只能被调用一次。你也可以调用 error(Throwable) 或者 complete()，不过是可选的。
     * <p>
     * 最有用的一种方式就是同时能够记录一个状态值（state），从而在使用 sink 发出下一个元素的时候能够 基于这个状态值去产生元素。
     * 此时生成器（generator）方法就是一个 BiFunction<S, SynchronousSink<T>, S>， 其中 <S> 是状态对象的类型。
     * 你需要提供一个 Supplier<S> 来初始化状态值，而生成器需要 在每一“回合”生成元素后返回新的状态值（供下一回合使用）。
     */
    @Test
    void generateTest() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                });

        Flux<String> flux1 = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });

        flux.subscribe(System.out::println);
        print();
        flux1.subscribe(System.out::println);
    }

    @Test
    void createTest() {
        Flux<String> bridge = Flux.create(sink -> {
           /* myEventProcessor.register(
                    new MyEventListener<String>() {

                        public void onDataChunk(List<String> chunk) {
                            for (String s : chunk) {
                                sink.next(s);
                            }
                        }

                        public void processComplete() {
                            sink.complete();
                        }
                    });*/
        }, );
    }


    interface MyEventListener<T> {

        void onDataChunk(List<T> chunk);

        void processComplete();
    }

    /*
扩展的时候通常至少要覆盖 hookOnSubscribe(Subscription subscription) 和 hookOnNext(T value) 这两个方法。
 */
    static class SampleSubscriber<T> extends BaseSubscriber<T> {

        public void hookOnSubscribe(Subscription subscription) {
            print("Subscribed");
            request(1);
        }

        public void hookOnNext(T value) {
            System.out.println(value);
            request(1);
        }
    }
}



