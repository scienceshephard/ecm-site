package demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Users {

    @NotEmpty(message="Name is required")
    private String Username;
    
    @NotEmpty(message="Email is required")
    @Email(message="Email is not valid")
    private String Email;
    
    @NotEmpty(message="Password is required")
    @Size(min=6, message="Password must be at least 6 characters")
    private String Password;

    @NotEmpty(message="Confirm Password is required")
    private String ConfirmPassword;

    public boolean isPasswordMatching(){
        return this.Password != null && this.Password.equals(this.ConfirmPassword);
    }

}
