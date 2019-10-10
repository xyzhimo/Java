package com.xyz.rxjava;

import java.util.UUID;

public class RxJavaHelloWorld {

    public static void main(String[] args) {
//        Flowable.fromArray("hello", "world").subscribe(s -> System.out.println("输出" + s));
//
//
//        Observable<Event> observable = Observable.create(emitter -> {
//            Callback<Event> listener = new Callback<Event>() {
//                @Override
//                public void onEvent(Event e) {
//                    emitter.onNext(e);
//                    if (e.equals("end")) {
//                        emitter.onComplete();
//                    }
//                }
//
//                @Override
//                public void onException(Throwable t) {
//                    emitter.onError(t);
//                }
//            };
//
//            // click
//            new Button().onClick(listener);
//
//        });
//
//        observable.subscribe(System.out::println);

        UUID uuid = UUID.randomUUID();
        System.err.println(uuid.toString());
    }

}


