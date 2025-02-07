package com.ms.vidhyalebox.classsubject;

import org.springframework.http.ResponseEntity;

import com.ms.vidhyalebox.sharedapi.classsubjectDTO.ClassSubjectDTO;
import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;

public interface ClassSubjectService extends IGenericService<GenericEntity, Long> {

    public ResponseEntity<APiResponse<Object>> MapClassSubject(ClassSubjectDTO classSubjectDTO);
}
