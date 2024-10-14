package demo.model;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import org.springframework.stereotype.Component;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Component
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;

    @NotEmpty(message = "Name is required")
    @Size(min = 4, message = "Name must not be less than 4 characters")
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String Email;

    @NotEmpty(message = "Password is Required")
    private String Password;

}
