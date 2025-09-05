
package pe.edu.vallegrande.quality.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Email format is invalid")
    private String email;
    
    @Min(value = 0, message = "Age must be positive")
    private Integer age;
}
