package com.example.graphql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BookStore {
    public static final List<Book> books = new ArrayList<>();
    public static final AtomicInteger atomicInteger = new AtomicInteger(10);

    static {
        books.add(Book.builder().id(atomicInteger.getAndIncrement()).name("ABC").pageCount(234).build());
        books.add(Book.builder().id(atomicInteger.getAndIncrement()).name("SDF").pageCount(236).build());
        books.add(Book.builder().id(atomicInteger.getAndIncrement()).name("GRG").pageCount(237).build());
        books.add(Book.builder().id(atomicInteger.getAndIncrement()).name("BVS").pageCount(238).build());
        books.add(Book.builder().id(atomicInteger.getAndIncrement()).name("WD").pageCount(2349).build());
        books.add(Book.builder().id(atomicInteger.getAndIncrement()).name("BDCD").pageCount(23).build());
        books.add(Book.builder().id(atomicInteger.getAndIncrement()).name("EGHBX").pageCount(34).build());
        books.add(Book.builder().id(atomicInteger.getAndIncrement()).name("SGGW").pageCount(234).build());
    }

    public static Book add(){
        Random random = new Random();
        Book book = Book.builder()
                .id(atomicInteger.get())
                .name("NEW BOOK" + atomicInteger.getAndIncrement())
                .pageCount(random.nextInt(500)).build();

        books.add(book);
        return book;
    }

    public static List<Book> getBooks(int startId){
        return books.stream()
                .filter(book -> book.getId()>=startId).toList();
    }

    public static Optional<Book> getBook(int bookId){
        return books.stream()
                .filter(book -> book.getId()==bookId).findFirst();
    }
}
