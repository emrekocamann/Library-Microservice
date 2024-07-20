package com.kitaplik.library_service.service;

import com.kitaplik.library_service.client.BookServiceClient;
import com.kitaplik.library_service.dto.AddBookRequest;
import com.kitaplik.library_service.dto.LibraryDto;
import com.kitaplik.library_service.exception.LibraryNotFoundException;
import com.kitaplik.library_service.mdel.Library;
import com.kitaplik.library_service.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    public LibraryService(LibraryRepository libraryRepository, BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }


    public LibraryDto getAllBooksInLibraryById(String id){
        Library library = libraryRepository.findById(id)
                .orElseThrow(()->new LibraryNotFoundException("Library counld not found by id: "+id));

        LibraryDto libraryDto = new LibraryDto(library.getId(),
                library.getUserBook()
                        .stream()
                        .map(bookServiceClient::getBookById)
                        .map(ResponseEntity::getBody)
                        .collect(Collectors.toList()));

        return libraryDto;
    }

    public LibraryDto createLibrary(){
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDto(newLibrary.getId());
    }

    public void addBookToLibrary(AddBookRequest request){
        String bookId = bookServiceClient.getBookByIsbn(request.getIsbn()).getBody().getBookId();

        Library library= libraryRepository.findById(request.getId())
                .orElseThrow(()->new LibraryNotFoundException("Library counld not found by id: "+request.getId()));
        library.getUserBook().add(bookId);
        libraryRepository.save(library);

    }


}
