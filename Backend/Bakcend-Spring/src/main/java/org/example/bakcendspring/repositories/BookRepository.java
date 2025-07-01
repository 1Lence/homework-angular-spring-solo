package org.example.bakcendspring.repositories;

import org.example.bakcendspring.entity.BookEntity;
import org.example.bakcendspring.entity.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT b FROM BookEntity b WHERE " +
            "(:title IS NULL OR b.title = :title) AND " +
            "(:author IS NULL OR b.author = :author) AND " +
            "(:status IS NULL OR b.status = :status) AND " +
            "(:publishedDate IS NULL OR b.publishedDate = :publishedDate)")
    List<BookEntity> findByFilters(@Param("title") String title,
                                   @Param("author") String author,
                                   @Param("status")Status status,
                                   @Param("publishedDate") LocalDate publishedDate);
}