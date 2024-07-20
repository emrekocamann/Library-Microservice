package com.kitaplik.book_service;

import com.kitaplik.book_service.model.Book;
import com.kitaplik.book_service.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class BookServiceApplication implements CommandLineRunner {

	private final BookRepository bookRepository;
	public BookServiceApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book1 = new Book("Clean Code",2000, "Robert C. Martin","A Publisher", "9141");
		Book book2 = new Book("Effective Java",1998, "Joshua Bloch","B Publisher", "9242");
		Book book3 = new Book("Head First Java",2008, "Kathy Sierra and Bert Bates","C Publisher", "9343");

		List<Book> books = bookRepository.saveAll(Arrays.asList(book1,book2,book3));

		books.forEach(System.out::println);

	}
}
