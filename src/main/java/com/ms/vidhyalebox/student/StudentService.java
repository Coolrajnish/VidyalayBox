package com.ms.vidhyalebox.student;

import com.ms.shared.api.auth.studentDTO.StudentDTO;
import com.ms.shared.api.auth.studentDTO.StudentTransferDTO;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService extends IGenericService<GenericEntity, Long> {

    public boolean transferStudent(List<StudentTransferDTO> studentTransferDTO);
    public String addStudent(StudentDTO studentDTO, MultipartFile image);
}
