package com.ms.shared.api.auth.studentDTO;

import com.ms.shared.api.auth.ParentSignupRequestDTO;
import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StudentDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Class Section is mandatory")
    private String classSection;

    @NotEmpty(message = "Session Year is mandatory")
    private String sessionYear;

    @NotEmpty(message = "Admission Date is mandatory")
    private String admissionDate;

    @NotEmpty(message = "Status is mandatory")
    private boolean isActive;

    @NotEmpty(message = "First Name is mandatory")
    private String firstName;

    @NotEmpty(message = "Last Name is mandatory")
    private String lastName;

    @NotEmpty(message = "Date of Birth is mandatory")
    private String dob;

    @NotEmpty(message = "Gender is mandatory")
    private String gender;

    private String studentMobile;

    @NotEmpty(message = "Current address is mandatory")
    private String currentAddr;

    @NotEmpty(message = "Permanent address is mandatory")
    private String permanentAddr;

    private String bloodgroup;

    private String emergencyContact;

    private String studentEmail;

    private String studentPWD;

    private ParentSignupRequestDTO parentSignupRequestDTO;

    private String identity;

}
