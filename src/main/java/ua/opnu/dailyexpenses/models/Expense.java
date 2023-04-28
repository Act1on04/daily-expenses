package ua.opnu.dailyexpenses.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @NotEmpty(message = "Name cannot be empty")
    @NotNull
    private String name;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount cannot be less than 0.01")
    @DecimalMax(value = "999999.99", message = "Amount cannot be greater than 999999,99")
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

//    public Expense(String name,
//                   BigDecimal amount,
//                   String category,
//                   String description,
//                   LocalDate date) {
//
//        this.name = name;
//        this.amount = amount;
//        this.category = category;
//        this.description = description;
//        this.date = date;
//    }


}
