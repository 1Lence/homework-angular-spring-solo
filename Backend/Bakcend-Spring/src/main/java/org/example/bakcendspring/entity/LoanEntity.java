package org.example.bakcendspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bakcendspring.entity.domain.LoanId;

import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan")
@IdClass(LoanId.class)
public class LoanEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @Builder.Default
    private BookEntity book = new BookEntity();
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Builder.Default
    private UserEntity user = new UserEntity();
    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;
}