package com.kitaplik.library_service.repository;

import com.kitaplik.library_service.mdel.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String> {
}