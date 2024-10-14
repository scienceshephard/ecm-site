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
    @Size(min = 4, message = "Name must not be less than 4 characters")
    private String Username;
    
    @NotEmpty(message="Email is required")
    @Email(message="Email is not valid")
    private String Email;
    
    @NotEmpty(message="Password is required")
    @Size(min=6, message="Password must be at least 6 characters")
    private String Password;

    @NotEmpty(message="Confirm Password is required")
    private String ConfirmPassword;

    public void setConfirmPassword(String confirmPassword){
        this.ConfirmPassword=confirmPassword;
    }

    public boolean isPasswordMatching(){
        return this.Password != null && this.Password.equals(this.ConfirmPassword);
    }

}
