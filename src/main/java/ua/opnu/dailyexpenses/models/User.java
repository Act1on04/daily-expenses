package ua.opnu.dailyexpenses.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Ім'я не повинно бути пустим")
    private String name;

    @Email(message="{register.email.invalid}")
    @NotBlank(message="{register.email.invalid}")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Введіть пароль")
    @NotBlank(message = "Введіть коректний пароль")
    private String password;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    private Timestamp createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private Timestamp updateDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

}
