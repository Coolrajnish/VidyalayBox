package com.ms.vidhyalebox.sharedapi;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

//import com.ms.shared.api.validator.DateOfBirth;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SignupRequestDTO extends GenericDTO {
    /**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "First Name is mandatory")
    private String firstName;
	
	@NotEmpty(message = "Last Name is mandatory")
    private String lastName;
    
    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, message = "Password should have minimum 8 character")
	private String password;
    
    @NotNull(message = "BirthDate is mandatory")
    @Past(message = "The date of birth must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
   // @DateOfBirth
    private Date dateOfBirth;
    
    @NotEmpty(message = "Gender is mandatory")
    private String gender;

    @NotEmpty(message = "Address is mandatory")
    private String address;

    @NotEmpty(message = "Role is mandatory")
    private String role;

    @NotEmpty(message = "schoolName is mandatory")
    private String schoolName;

    @NotEmpty(message = "orgId is mandatory")
    private String orgId;
}
