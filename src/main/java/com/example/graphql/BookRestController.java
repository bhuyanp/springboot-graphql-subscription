package com.example.graphql;

import jdk.jfr.ContentType;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> getAllBooks() {
        return Flux.fromIterable(BookStore.books).delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/add")
    public Book addBook() {
        return BookStore.add();
    }

}
