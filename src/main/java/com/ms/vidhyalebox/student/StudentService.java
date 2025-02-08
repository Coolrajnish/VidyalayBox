package com.ms.vidhyalebox.student;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.sharedapi.studentDTO.StudentDTO;
import com.ms.vidhyalebox.sharedapi.studentDTO.StudentTransferDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface StudentService extends IGenericService<GenericEntity, Long> {

    public boolean transferStudent(List<StudentTransferDTO> studentTransferDTO);
    public String addStudent(StudentDTO studentDTO, MultipartFile image);
    public Page<StudentEntity> search(String orgId, String searchText, int page, int size, String sortBy, String sortOrder);
}
