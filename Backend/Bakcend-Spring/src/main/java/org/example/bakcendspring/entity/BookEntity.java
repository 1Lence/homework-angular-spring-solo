package org.example.bakcendspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bakcendspring.entity.domain.Status;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String isbn;
    @Column(name = "published_date",nullable = false)
    private LocalDate publishedDate;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "status_state")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Status status = Status.BORROWED;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanEntity> loans = new ArrayList<>();
}