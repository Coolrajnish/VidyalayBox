package com.ms.vidhyalebox.classsubject;

import com.ms.shared.api.auth.classsubjectDTO.ClassSubjectDTO;
import com.ms.shared.api.generic.APiResponse;
import com.ms.shared.api.generic.GenericResponse;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import org.springframework.http.ResponseEntity;

public interface ClassSubjectService extends IGenericService<GenericEntity, Long> {

    public ResponseEntity<APiResponse<Object>> MapClassSubject(ClassSubjectDTO classSubjectDTO);
}
