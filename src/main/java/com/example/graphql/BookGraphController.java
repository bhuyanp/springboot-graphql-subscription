package com.example.graphql;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Controller
public class BookGraphController {


    @QueryMapping(name = "getAllBooks")
    public Flux<Book> getAllBooks() {
        return Flux.fromIterable(BookStore.books).delayElements(Duration.ofSeconds(1));
    }

    @SubscriptionMapping(name = "subscribeToAllBooks")
    public Flux<Book> subscribeToAllBooks() {
        Random random = new Random(300);
        AtomicInteger startId = new AtomicInteger(10);
        return Flux.interval(Duration.ofSeconds(2))
                .mapNotNull(num-> {
                    Optional<Book> optionalBook = BookStore.getBook(startId.get());
                    if(optionalBook.isPresent()){
                        startId.incrementAndGet();
                        return optionalBook.get();
                    }
                    return null;
                });
    }

    @SubscriptionMapping(name = "getNumbers")
    public Flux<Integer> subscribeToNumbers() {
        //return Flux.just(1,2,3).delayElements(Duration.ofSeconds(1));
        Random random = new Random(300);
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
        Flux<Integer> events =
                Flux.fromStream(Stream.generate(()->random.nextInt(300)));
        return Flux.zip(events, interval, (key, value) -> key);
    }
}
