package ua.opnu.dailyexpenses.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Назва витрати не повинна бути пустою")
    private String name;

    @NotNull(message = "Сума витрати не повинна бути нульовою")
    @DecimalMin(value = "0.01", message = "Сума витрати повинна бути більше ніж 0.01")
    @DecimalMax(value = "999999.99", message = "Сума витрати повинна бути менш ніж  999999,99")
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal amount;

    private String category;

    private String description;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
