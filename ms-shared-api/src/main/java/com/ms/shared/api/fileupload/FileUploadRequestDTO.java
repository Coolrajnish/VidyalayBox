package com.ms.shared.api.fileupload;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FileUploadRequestDTO extends GenericDTO {

    @NotNull(message = "Please upload file")
    private MultipartFile file;

    @NotEmpty(message = "Document Type is mandatory")
    private String documentType;
}
